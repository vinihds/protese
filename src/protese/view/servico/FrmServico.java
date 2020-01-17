package protese.view.servico;

import java.awt.Font;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import protese.dao.pagamento.FormaPagamentoDao;
import protese.dao.pagamento.PagamentoDao;
import protese.dao.servico.ServicoDao;
import protese.dao.servico.ServicoDebitoDao;
import protese.dao.servico.ServicoDetalheDao;
import protese.dao.servico.ServicoPagamentoDao;
import protese.model.cliente.Cliente;
import protese.model.pagamento.FormaPagamento;
import protese.model.produto.ProdutoValor;
import protese.model.servico.Servico;
import protese.model.servico.ServicoDebito;
import protese.model.servico.ServicoDetalhe;
import protese.model.servico.ServicoPagamento;
import protese.util.utilidade.Utilidade;
import protese.view.cliente.FrmPesquisarCliente;
import protese.view.produto.FrmPesquisarProdutoValor;

/**
 *
 * @author Vinicius Silveira
 */
public class FrmServico extends javax.swing.JDialog {

    private ServicoDao servicoDao = ServicoDao.getInstance();
    private ServicoDetalheDao servicoDetalheDao = ServicoDetalheDao.getInstance();
    private PagamentoDao pagamentoDao = PagamentoDao.getInstance();
    private ServicoPagamentoDao servicoPagamentoDao = ServicoPagamentoDao.getInstance();
    private FormaPagamentoDao formaPagamentoDao = FormaPagamentoDao.getInstance();
    private ServicoDebitoDao servicoDebitoDao = ServicoDebitoDao.getInstance();

    private Utilidade utilidade = Utilidade.getInstance();
    private DefaultTableModel modelo = new DefaultTableModel();

    private Servico servico = new Servico();
    private Cliente cliente = new Cliente();
    private ProdutoValor produtoValor = new ProdutoValor();
    private List<FormaPagamento> formaPagamentoList = new ArrayList();

    private FrmServico(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }

    public FrmServico(java.awt.Frame parent, boolean modal, Servico servico) {
        super(parent, modal);
        initComponents();

        this.servico = servico;
        comboDataLancamento.setDate(new Date());
        comboDataPagamento.setDate(new Date());
        btnFinalizarServico.setVisible(false);
        preencheFormaPagamentoList();
        preencheMes();
        preencheAnos();

        tblPagamentos.getTableHeader().setFont(utilidade.FONTE);
        tblServicoDetalhe.getTableHeader().setFont(utilidade.FONTE);
        tblServicoDebito.getTableHeader().setFont(utilidade.FONTE);

        if (this.servico.getId() != null && this.servico.getId() > 0) {
            cliente = servico.getIdcliente();

            preencheServico();
            liberaAbas(true);
        } else {
            liberaAbas(false);
        }

        //Cliente
        txtNomeCliente.setEditable(false);
        //Produto
        txtNomeProduto.setEditable(false);
        txtCodigoGrupo.setEditable(false);
        txtCodigoProduto.setEditable(false);
    }

    private void preencheMes() {
        LocalDateTime hoje = LocalDateTime.now();

        comboReferenteMes.setSelectedIndex(hoje.getMonthValue() - 1);
    }

    private void preencheAnos() {
        LocalDateTime hoje = LocalDateTime.now();

        for (int i = 2018; i <= hoje.getYear() + 1; i++) {
            comboReferenteAno.addItem("" + i);
        }

        comboReferenteAno.setSelectedItem(String.valueOf(hoje.getYear()));
    }

    private void preencheServico() {
        txtTitulo.setText(servico.getTitulo());

        LocalDateTime dataReferente = servico.getDataReferente();
        comboReferenteMes.setSelectedIndex(dataReferente.getMonthValue() - 1);
        comboReferenteMes.setEnabled(false);
        comboReferenteAno.setSelectedItem(String.valueOf(dataReferente.getYear()));
        comboReferenteAno.setEnabled(false);

        txtDescricao.setText(servico.getDescricao());

        preencheCliente();
        preencheValores();
        preencheTabelaServicoDetalhe();
        preencheTabelaServicoDebito();
        preencheTabelaServicoPagamento();
        verificaServicoFinalizado();
    }

    private void preencheCliente() {
        if (cliente != null && cliente.getId() != null && cliente.getId() > 0) {
            double clienteSaldo = cliente.getSaldoAtual();
            double clienteDebito = cliente.getDebitoPendente();

            txtNomeCliente.setText(cliente.getNome());

            lblValorCreditoCliente.setText("R$ " + utilidade.decimalFormat(clienteSaldo));
            lblValorDebitoCliente.setText("R$ " + utilidade.decimalFormat(clienteDebito));
        }
    }

    private void verificaServicoFinalizado() {
        //Verificando finalização do serviço
        if (servico.getDataFinalizacao() == null) {

            if (servico.getId() != null && servico.getId() > 0) {
                btnFinalizarServico.setVisible(true);
            }
        } else {
            //btnSalvarServico.setEnabled(false);
            btnFinalizarServico.setVisible(true);
            btnFinalizarServico.setEnabled(false);

            //Cliente
            btnPesquisarCliente.setEnabled(false);

            //Produto
            btnPesquisarProduto.setEnabled(false);
            btnLancarProduto.setEnabled(false);
            btnExcluirProduto.setEnabled(false);

            //Débito
            btnAgregarDebitos.setEnabled(false);
            btnExcluirDebito.setEnabled(false);
            btnLancarDebito.setEnabled(false);

            //Pagamento
            btnLancarPagamento.setEnabled(false);
            btnUtilizarCreditos.setEnabled(false);
            btnExcluirPagamento.setEnabled(false);

            //Crédito
            btnUtilizarCreditos.setEnabled(false);
        }
    }

    private void preencheProduto() {
        if (produtoValor.getId() != null && produtoValor.getId() > 0) {
            //Produto
            txtNomeProduto.setText(produtoValor.getIdproduto().getNome());
            txtCodigoGrupo.setText(produtoValor.getIdgrupo().getCodigo());
            txtCodigoProduto.setText(produtoValor.getIdproduto().getCodigo());
            comboDataLancamento.setDate(new Date());
            //Valor
            lblValorUnitario.setText("R$ " + utilidade.decimalFormat(produtoValor.getValor()));
            txtQuantidadeProduto.setText("1");
            lblValorTotal.setText("R$ 0,00");

            calculaValorNovoLancamento();
        } else {
            //Produto
            txtNomeProduto.setText("");
            txtCodigoGrupo.setText("");
            txtCodigoProduto.setText("");
            comboDataLancamento.setDate(new Date());
            //Valor
            lblValorUnitario.setText("R$ 0,00");
            txtQuantidadeProduto.setText("1");
            lblValorTotal.setText("R$ 0,00");
        }
    }

    private void calculaValorNovoLancamento() {
        double quantidade = 0;
        double valorTotal = 0;

        if (!txtQuantidadeProduto.getText().isEmpty()) {
            quantidade = Double.parseDouble(txtQuantidadeProduto.getText());
        }

        valorTotal = produtoValor.getValor() * quantidade;

        lblValorTotal.setText("R$ " + utilidade.decimalFormat(valorTotal));
    }

    private void liberaAbas(boolean libera) {
        tabbedPaneServico.setEnabledAt(1, libera);
        tabbedPaneServico.setEnabledAt(2, libera);
        tabbedPaneServico.setEnabledAt(3, libera);
    }

    private void preencheValores() {
        double valorTotalServico = servico.getValorTotalServico();
        double valorTotalProdutos = servico.getValorTotalProdutos();
        double valorTotalDebitos = servico.getValorTotalDebitos();
        double valorTotalPago = servico.getValorTotalPago();
        double valorRestante = servico.getRestantePagar();

        //Valor total serviço/produtos
        lblValorTotalServicoGeral.setText("R$ " + utilidade.decimalFormat(valorTotalServico));
        lblValorTotalProdutos.setText("R$ " + utilidade.decimalFormat(valorTotalProdutos));

        //Valor total pago
        lblValorTotalPagoGeral.setText("R$ " + utilidade.decimalFormat(valorTotalPago));
        lblValorTotalPagamentos.setText("R$ " + utilidade.decimalFormat(valorTotalPago));

        //Valor total débitos
        lblValorTotalDebitos.setText("R$ " + utilidade.decimalFormat(valorTotalDebitos));

        //Restante a pagar
        lblRestantePagarGeral.setText("R$ " + utilidade.decimalFormat(valorRestante));
        lblRestantePagar.setText("R$ " + utilidade.decimalFormat(valorRestante));
    }

    private void preencheTabelaServicoDetalhe() {
        modelo = (DefaultTableModel) tblServicoDetalhe.getModel();
        modelo.setRowCount(0);

        for (ServicoDetalhe servicoDetalhe : servico.getServicoDetalheList()) {
            modelo.addRow(new Object[]{
                servicoDetalhe.getId(),
                servicoDetalhe.getIdprodutoValor().getCodigoCompleto(),
                servicoDetalhe.getIdprodutoValor().getIdproduto().getNome(),
                utilidade.sdfTimeStamp(servicoDetalhe.getDataLancamento()),
                "R$ " + utilidade.decimalFormat(servicoDetalhe.getIdprodutoValor().getValor()),
                servicoDetalhe.getQuantidade(),
                "R$ " + utilidade.decimalFormat(servicoDetalhe.getValorTotal())
            });
        }
    }

    private void preencheTabelaServicoDebito() {
        modelo = (DefaultTableModel) tblServicoDebito.getModel();
        modelo.setRowCount(0);

        for (ServicoDebito servicoDebito : servico.getServicoDebitoList()) {
            modelo.addRow(new Object[]{
                servicoDebito.getId(),
                servicoDebito.getIdclienteDebito().getDescricao(),
                utilidade.sdfTimeStamp(servicoDebito.getIdclienteDebito().getData()),
                "R$ " + utilidade.decimalFormat(servicoDebito.getIdclienteDebito().getValorDebito())
            });
        }
    }

    private void preencheTabelaServicoPagamento() {
        modelo = (DefaultTableModel) tblPagamentos.getModel();
        modelo.setRowCount(0);

        for (ServicoPagamento servicoPagamento : servico.getServicoPagamentoList()) {
            modelo.addRow(new Object[]{
                servicoPagamento.getId(),
                servicoPagamento.getIdpagamento().getIdformaPagamento().getNome(),
                utilidade.sdfTimeStamp(servicoPagamento.getIdpagamento().getDataLancamento()),
                utilidade.sdfTimeStamp(servicoPagamento.getIdpagamento().getDataPagamento()),
                "R$ " + utilidade.decimalFormat(servicoPagamento.getIdpagamento().getValor())
            });
        }
    }

    private void preencheFormaPagamentoList() {
        formaPagamentoList = formaPagamentoDao.retornaTodos();

        for (FormaPagamento formaPagamento : formaPagamentoList) {
            comboFormaPagamento.addItem(formaPagamento.getNome());
        }
    }

    private void novoPagamento(FormaPagamento formaPagamento, double valorPagamento, LocalDateTime dataPagamento, boolean isMostrarMensagem) {
        double restantePagar = servico.getRestantePagar();
        double saldoCliente = cliente.getSaldoAtual();

        //Verificando crédito do cliente
        if (formaPagamento.getTipo().equals("creditoSaida")) {
            if (restantePagar == 0) {
                if (isMostrarMensagem) {
                    JOptionPane.showMessageDialog(this, "Você não pode utilizar créditos para pagar um serviço já totalmente pago!", "Pagamento do serviço", JOptionPane.WARNING_MESSAGE);
                }

                return;
            } else if (valorPagamento > restantePagar) {
                if (isMostrarMensagem) {
                    JOptionPane.showMessageDialog(this, "Ao utilizar crédito, o 'restante a pagar' delimita o valor máximo de crédito que pode ser utilizado "
                            + "\n"
                            + "\n Crédito utilizado: R$ " + utilidade.decimalFormat(valorPagamento) + " / Restante a pagar: R$ " + utilidade.decimalFormat(restantePagar) + ""
                            + "\n"
                            + "\n Efetue o pagamento utilizando crédito até o valor máximo de R$ " + utilidade.decimalFormat(restantePagar) + " para continuar!", "Pagamento do serviço", JOptionPane.WARNING_MESSAGE);
                }

                return;
            } else if (valorPagamento > saldoCliente) {
                if (isMostrarMensagem) {
                    JOptionPane.showMessageDialog(this, "Este cliente não possui crédito suficiente para realizar o pagamento!", "Pagamento do serviço", JOptionPane.WARNING_MESSAGE);
                }

                return;
            }
        }

        //Gerando pagamento do serviço
        if (servicoPagamentoDao.salvarNovoServicoPagamento(
                servico,
                formaPagamento,
                dataPagamento,
                valorPagamento,
                restantePagar)) {
            if (isMostrarMensagem) {
                JOptionPane.showMessageDialog(this, "Pagamento do serviço salvo com sucesso!", "Pagamento do serviço", JOptionPane.INFORMATION_MESSAGE);
            }

            txtValorPagamento.setText("");
            preencheServico();
        } else {
            if (isMostrarMensagem) {
                JOptionPane.showMessageDialog(this, "Ocorreu um erro na geraçao do pagamento!", "Pagamento do serviço", JOptionPane.WARNING_MESSAGE);
            }
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        btnFechar = new javax.swing.JButton();
        tabbedPaneServico = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        btnSalvarServico = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtDescricao = new javax.swing.JTextArea();
        jLabel1 = new javax.swing.JLabel();
        txtTitulo = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        btnPesquisarCliente = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        txtNomeCliente = new javax.swing.JTextField();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        lblRestantePagarGeral = new javax.swing.JLabel();
        lblValorTotalServicoGeral = new javax.swing.JLabel();
        lblValorTotalPagoGeral = new javax.swing.JLabel();
        btnFinalizarServico = new javax.swing.JButton();
        comboReferenteMes = new javax.swing.JComboBox<>();
        comboReferenteAno = new javax.swing.JComboBox<>();
        jLabel23 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblServicoDetalhe = new javax.swing.JTable();
        btnExcluirProduto = new javax.swing.JButton();
        btnLancarProduto = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel12 = new javax.swing.JLabel();
        txtCodigoGrupo = new javax.swing.JTextField();
        txtNomeProduto = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        btnPesquisarProduto = new javax.swing.JButton();
        jLabel16 = new javax.swing.JLabel();
        txtQuantidadeProduto = new javax.swing.JTextField();
        lblValorTotal = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        lblValorUnitario = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        txtCodigoProduto = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        comboDataLancamento = new com.toedter.calendar.JDateChooser();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        lblValorTotalProdutos = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblServicoDebito = new javax.swing.JTable();
        painelDebito = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        lblValorDebitoCliente = new javax.swing.JLabel();
        btnAgregarDebitos = new javax.swing.JButton();
        btnExcluirDebito = new javax.swing.JButton();
        jLabel26 = new javax.swing.JLabel();
        lblValorTotalDebitos = new javax.swing.JLabel();
        btnLancarDebito = new javax.swing.JButton();
        jLabel27 = new javax.swing.JLabel();
        jSeparator5 = new javax.swing.JSeparator();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblPagamentos = new javax.swing.JTable();
        jLabel10 = new javax.swing.JLabel();
        btnExcluirPagamento = new javax.swing.JButton();
        jSeparator4 = new javax.swing.JSeparator();
        jLabel11 = new javax.swing.JLabel();
        btnLancarPagamento = new javax.swing.JButton();
        comboFormaPagamento = new javax.swing.JComboBox<>();
        comboDataPagamento = new com.toedter.calendar.JDateChooser();
        jLabel13 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        txtValorPagamento = new javax.swing.JTextField();
        jLabel25 = new javax.swing.JLabel();
        lblValorTotalPagamentos = new javax.swing.JLabel();
        lblRestantePagar = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        painelCredito = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        lblValorCreditoCliente = new javax.swing.JLabel();
        btnUtilizarCreditos = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Serviço");

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(null);

        btnFechar.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnFechar.setText("Fechar");
        btnFechar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFecharActionPerformed(evt);
            }
        });
        jPanel1.add(btnFechar);
        btnFechar.setBounds(820, 590, 150, 50);

        tabbedPaneServico.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(null);

        btnSalvarServico.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnSalvarServico.setIcon(new javax.swing.ImageIcon(getClass().getResource("/protese/util/icons/icons8-selecionado-25.png"))); // NOI18N
        btnSalvarServico.setText("Salvar");
        btnSalvarServico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalvarServicoActionPerformed(evt);
            }
        });
        jPanel2.add(btnSalvarServico);
        btnSalvarServico.setBounds(10, 550, 150, 50);

        txtDescricao.setColumns(20);
        txtDescricao.setFont(new java.awt.Font("Monospaced", 0, 18)); // NOI18N
        txtDescricao.setRows(5);
        jScrollPane2.setViewportView(txtDescricao);

        jPanel2.add(jScrollPane2);
        jScrollPane2.setBounds(10, 320, 950, 220);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setText("Descrição do serviço");
        jPanel2.add(jLabel1);
        jLabel1.setBounds(10, 300, 220, 20);

        txtTitulo.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jPanel2.add(txtTitulo);
        txtTitulo.setBounds(10, 30, 950, 40);

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel2.setText("Título");
        jPanel2.add(jLabel2);
        jLabel2.setBounds(10, 10, 130, 20);

        btnPesquisarCliente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/protese/util/icons/icons8-pesquisar-25.png"))); // NOI18N
        btnPesquisarCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPesquisarClienteActionPerformed(evt);
            }
        });
        jPanel2.add(btnPesquisarCliente);
        btnPesquisarCliente.setBounds(400, 80, 70, 70);

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel3.setText("Serviço referente ao periodo de");
        jPanel2.add(jLabel3);
        jLabel3.setBounds(500, 90, 420, 20);

        txtNomeCliente.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jPanel2.add(txtNomeCliente);
        txtNomeCliente.setBounds(10, 110, 390, 40);
        jPanel2.add(jSeparator1);
        jSeparator1.setBounds(10, 270, 950, 20);

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel4.setText("Cliente");
        jPanel2.add(jLabel4);
        jLabel4.setBounds(10, 90, 140, 20);

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel6.setText("Valor restante a pagar");
        jPanel2.add(jLabel6);
        jLabel6.setBounds(650, 180, 310, 30);

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel7.setText("Valor total do serviço");
        jPanel2.add(jLabel7);
        jLabel7.setBounds(10, 180, 290, 30);

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel8.setText("Valor total pago");
        jPanel2.add(jLabel8);
        jLabel8.setBounds(390, 180, 220, 30);

        lblRestantePagarGeral.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        lblRestantePagarGeral.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblRestantePagarGeral.setText("R$ 0,00");
        jPanel2.add(lblRestantePagarGeral);
        lblRestantePagarGeral.setBounds(690, 210, 270, 40);

        lblValorTotalServicoGeral.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        lblValorTotalServicoGeral.setForeground(new java.awt.Color(255, 0, 0));
        lblValorTotalServicoGeral.setText("R$ 0,00");
        jPanel2.add(lblValorTotalServicoGeral);
        lblValorTotalServicoGeral.setBounds(10, 210, 270, 40);

        lblValorTotalPagoGeral.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        lblValorTotalPagoGeral.setForeground(new java.awt.Color(0, 153, 51));
        lblValorTotalPagoGeral.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblValorTotalPagoGeral.setText("R$ 0,00");
        jPanel2.add(lblValorTotalPagoGeral);
        lblValorTotalPagoGeral.setBounds(350, 210, 270, 40);

        btnFinalizarServico.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnFinalizarServico.setForeground(new java.awt.Color(0, 153, 51));
        btnFinalizarServico.setText("Finalizar serviço!");
        btnFinalizarServico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFinalizarServicoActionPerformed(evt);
            }
        });
        jPanel2.add(btnFinalizarServico);
        btnFinalizarServico.setBounds(370, 550, 220, 50);

        comboReferenteMes.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        comboReferenteMes.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "JAN", "FEV", "MAR", "ABR", "MAI", "JUN", "JUL", "AGO", "SET", "OUT", "NOV", "DEZ" }));
        jPanel2.add(comboReferenteMes);
        comboReferenteMes.setBounds(500, 110, 210, 40);

        comboReferenteAno.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jPanel2.add(comboReferenteAno);
        comboReferenteAno.setBounds(750, 110, 210, 40);

        jLabel23.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel23.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel23.setText("/");
        jPanel2.add(jLabel23);
        jLabel23.setBounds(710, 110, 40, 40);

        tabbedPaneServico.addTab("Dados do serviço", jPanel2);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setLayout(null);

        tblServicoDetalhe.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        tblServicoDetalhe.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Produto", "Nome", "Lançamento", "Valor unit", "Qtd", "Valor total"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblServicoDetalhe.setRowHeight(35);
        tblServicoDetalhe.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(tblServicoDetalhe);
        if (tblServicoDetalhe.getColumnModel().getColumnCount() > 0) {
            tblServicoDetalhe.getColumnModel().getColumn(0).setMinWidth(0);
            tblServicoDetalhe.getColumnModel().getColumn(0).setPreferredWidth(0);
            tblServicoDetalhe.getColumnModel().getColumn(0).setMaxWidth(0);
            tblServicoDetalhe.getColumnModel().getColumn(1).setPreferredWidth(150);
            tblServicoDetalhe.getColumnModel().getColumn(2).setPreferredWidth(200);
            tblServicoDetalhe.getColumnModel().getColumn(3).setPreferredWidth(200);
            tblServicoDetalhe.getColumnModel().getColumn(4).setPreferredWidth(100);
            tblServicoDetalhe.getColumnModel().getColumn(5).setPreferredWidth(50);
            tblServicoDetalhe.getColumnModel().getColumn(6).setPreferredWidth(100);
        }

        jPanel3.add(jScrollPane1);
        jScrollPane1.setBounds(10, 270, 950, 270);

        btnExcluirProduto.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnExcluirProduto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/protese/util/icons/icons8-excluir-25.png"))); // NOI18N
        btnExcluirProduto.setText("Excluir");
        btnExcluirProduto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcluirProdutoActionPerformed(evt);
            }
        });
        jPanel3.add(btnExcluirProduto);
        btnExcluirProduto.setBounds(10, 550, 150, 50);

        btnLancarProduto.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnLancarProduto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/protese/util/icons/icons8-selecionado-25.png"))); // NOI18N
        btnLancarProduto.setText("Lançar produto!");
        btnLancarProduto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLancarProdutoActionPerformed(evt);
            }
        });
        jPanel3.add(btnLancarProduto);
        btnLancarProduto.setBounds(10, 160, 250, 60);

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel9.setText("Listagem dos produtos deste serviço");
        jPanel3.add(jLabel9);
        jLabel9.setBounds(10, 250, 350, 20);
        jPanel3.add(jSeparator2);
        jSeparator2.setBounds(10, 230, 950, 20);

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel12.setText("Grupo");
        jPanel3.add(jLabel12);
        jLabel12.setBounds(10, 80, 90, 20);

        txtCodigoGrupo.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jPanel3.add(txtCodigoGrupo);
        txtCodigoGrupo.setBounds(10, 100, 130, 40);

        txtNomeProduto.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jPanel3.add(txtNomeProduto);
        txtNomeProduto.setBounds(10, 30, 530, 40);

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel14.setText("Nome do produto");
        jPanel3.add(jLabel14);
        jLabel14.setBounds(10, 10, 230, 20);

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel15.setText("Valor total");
        jPanel3.add(jLabel15);
        jLabel15.setBounds(650, 180, 150, 40);

        btnPesquisarProduto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/protese/util/icons/icons8-pesquisar-25.png"))); // NOI18N
        btnPesquisarProduto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPesquisarProdutoActionPerformed(evt);
            }
        });
        jPanel3.add(btnPesquisarProduto);
        btnPesquisarProduto.setBounds(540, 10, 60, 60);

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel16.setText("Quantidade");
        jPanel3.add(jLabel16);
        jLabel16.setBounds(670, 90, 150, 40);

        txtQuantidadeProduto.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txtQuantidadeProduto.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtQuantidadeProduto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtQuantidadeProdutoKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtQuantidadeProdutoKeyTyped(evt);
            }
        });
        jPanel3.add(txtQuantidadeProduto);
        txtQuantidadeProduto.setBounds(830, 90, 130, 40);

        lblValorTotal.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        lblValorTotal.setForeground(new java.awt.Color(255, 0, 0));
        lblValorTotal.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblValorTotal.setText("R$ 0,00");
        jPanel3.add(lblValorTotal);
        lblValorTotal.setBounds(830, 180, 130, 40);
        jPanel3.add(jSeparator3);
        jSeparator3.setBounds(670, 160, 290, 30);

        lblValorUnitario.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lblValorUnitario.setForeground(new java.awt.Color(255, 0, 0));
        lblValorUnitario.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblValorUnitario.setText("R$ 0,00");
        jPanel3.add(lblValorUnitario);
        lblValorUnitario.setBounds(830, 30, 130, 40);

        jLabel19.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel19.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel19.setText("Valor unitário");
        jPanel3.add(jLabel19);
        jLabel19.setBounds(670, 30, 150, 40);

        txtCodigoProduto.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jPanel3.add(txtCodigoProduto);
        txtCodigoProduto.setBounds(160, 100, 130, 40);

        jLabel20.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel20.setText("Data de lançamento");
        jPanel3.add(jLabel20);
        jLabel20.setBounds(310, 80, 220, 20);

        comboDataLancamento.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jPanel3.add(comboDataLancamento);
        comboDataLancamento.setBounds(310, 100, 290, 40);

        jLabel21.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel21.setText("Produto");
        jPanel3.add(jLabel21);
        jLabel21.setBounds(160, 80, 90, 20);

        jLabel22.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel22.setText("Valor total dos produtos");
        jPanel3.add(jLabel22);
        jLabel22.setBounds(260, 550, 300, 40);

        lblValorTotalProdutos.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        lblValorTotalProdutos.setForeground(new java.awt.Color(255, 0, 0));
        lblValorTotalProdutos.setText("R$ 0,00");
        jPanel3.add(lblValorTotalProdutos);
        lblValorTotalProdutos.setBounds(560, 550, 190, 40);

        tabbedPaneServico.addTab("Produtos vendidos", jPanel3);

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setLayout(null);

        tblServicoDebito.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        tblServicoDebito.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Descrição", "Data", "Valor"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblServicoDebito.setRowHeight(35);
        tblServicoDebito.getTableHeader().setReorderingAllowed(false);
        jScrollPane4.setViewportView(tblServicoDebito);
        if (tblServicoDebito.getColumnModel().getColumnCount() > 0) {
            tblServicoDebito.getColumnModel().getColumn(0).setMinWidth(0);
            tblServicoDebito.getColumnModel().getColumn(0).setPreferredWidth(0);
            tblServicoDebito.getColumnModel().getColumn(0).setMaxWidth(0);
            tblServicoDebito.getColumnModel().getColumn(1).setPreferredWidth(450);
            tblServicoDebito.getColumnModel().getColumn(2).setPreferredWidth(200);
            tblServicoDebito.getColumnModel().getColumn(3).setPreferredWidth(150);
        }

        jPanel5.add(jScrollPane4);
        jScrollPane4.setBounds(10, 270, 950, 270);

        painelDebito.setBackground(new java.awt.Color(255, 0, 0));
        painelDebito.setLayout(null);

        jLabel18.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel18.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel18.setText("Débito");
        painelDebito.add(jLabel18);
        jLabel18.setBounds(220, 10, 180, 22);

        lblValorDebitoCliente.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        lblValorDebitoCliente.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblValorDebitoCliente.setText("R$ 0,00");
        painelDebito.add(lblValorDebitoCliente);
        lblValorDebitoCliente.setBounds(220, 40, 180, 40);

        btnAgregarDebitos.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnAgregarDebitos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/protese/util/icons/icons8-selecionado-25.png"))); // NOI18N
        btnAgregarDebitos.setText("Agregar débitos!");
        btnAgregarDebitos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarDebitosActionPerformed(evt);
            }
        });
        painelDebito.add(btnAgregarDebitos);
        btnAgregarDebitos.setBounds(10, 10, 210, 70);

        jPanel5.add(painelDebito);
        painelDebito.setBounds(550, 10, 410, 90);

        btnExcluirDebito.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnExcluirDebito.setIcon(new javax.swing.ImageIcon(getClass().getResource("/protese/util/icons/icons8-excluir-25.png"))); // NOI18N
        btnExcluirDebito.setText("Excluir");
        btnExcluirDebito.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcluirDebitoActionPerformed(evt);
            }
        });
        jPanel5.add(btnExcluirDebito);
        btnExcluirDebito.setBounds(10, 550, 150, 50);

        jLabel26.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel26.setText("Valor total dos débitos");
        jPanel5.add(jLabel26);
        jLabel26.setBounds(260, 550, 280, 40);

        lblValorTotalDebitos.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        lblValorTotalDebitos.setForeground(new java.awt.Color(255, 0, 0));
        lblValorTotalDebitos.setText("R$ 0,00");
        jPanel5.add(lblValorTotalDebitos);
        lblValorTotalDebitos.setBounds(540, 550, 190, 40);

        btnLancarDebito.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnLancarDebito.setIcon(new javax.swing.ImageIcon(getClass().getResource("/protese/util/icons/icons8-selecionado-25.png"))); // NOI18N
        btnLancarDebito.setText("Lançar débito!");
        jPanel5.add(btnLancarDebito);
        btnLancarDebito.setBounds(10, 160, 250, 60);

        jLabel27.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel27.setText("Listagem dos débitos deste serviço");
        jPanel5.add(jLabel27);
        jLabel27.setBounds(10, 250, 410, 20);
        jPanel5.add(jSeparator5);
        jSeparator5.setBounds(10, 230, 950, 20);

        tabbedPaneServico.addTab("Débitos do cliente", jPanel5);

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setLayout(null);

        tblPagamentos.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        tblPagamentos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Forma de pagamento", "Data de lançamento", "Data do pagamento", "Valor"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblPagamentos.setRowHeight(35);
        tblPagamentos.getTableHeader().setReorderingAllowed(false);
        jScrollPane3.setViewportView(tblPagamentos);
        if (tblPagamentos.getColumnModel().getColumnCount() > 0) {
            tblPagamentos.getColumnModel().getColumn(0).setMinWidth(0);
            tblPagamentos.getColumnModel().getColumn(0).setPreferredWidth(0);
            tblPagamentos.getColumnModel().getColumn(0).setMaxWidth(0);
            tblPagamentos.getColumnModel().getColumn(1).setPreferredWidth(300);
            tblPagamentos.getColumnModel().getColumn(2).setPreferredWidth(200);
            tblPagamentos.getColumnModel().getColumn(3).setPreferredWidth(200);
            tblPagamentos.getColumnModel().getColumn(4).setPreferredWidth(200);
        }

        jPanel4.add(jScrollPane3);
        jScrollPane3.setBounds(10, 270, 950, 270);

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel10.setText("Listagem dos pagamentos deste serviço");
        jPanel4.add(jLabel10);
        jLabel10.setBounds(10, 250, 410, 20);

        btnExcluirPagamento.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnExcluirPagamento.setIcon(new javax.swing.ImageIcon(getClass().getResource("/protese/util/icons/icons8-excluir-25.png"))); // NOI18N
        btnExcluirPagamento.setText("Excluir");
        btnExcluirPagamento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcluirPagamentoActionPerformed(evt);
            }
        });
        jPanel4.add(btnExcluirPagamento);
        btnExcluirPagamento.setBounds(10, 550, 150, 50);
        jPanel4.add(jSeparator4);
        jSeparator4.setBounds(10, 230, 950, 20);

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel11.setText("Restante a pagar");
        jPanel4.add(jLabel11);
        jLabel11.setBounds(570, 180, 230, 40);

        btnLancarPagamento.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnLancarPagamento.setIcon(new javax.swing.ImageIcon(getClass().getResource("/protese/util/icons/icons8-selecionado-25.png"))); // NOI18N
        btnLancarPagamento.setText("Lançar pagamento!");
        btnLancarPagamento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLancarPagamentoActionPerformed(evt);
            }
        });
        jPanel4.add(btnLancarPagamento);
        btnLancarPagamento.setBounds(10, 160, 250, 60);

        comboFormaPagamento.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jPanel4.add(comboFormaPagamento);
        comboFormaPagamento.setBounds(10, 30, 250, 40);

        comboDataPagamento.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jPanel4.add(comboDataPagamento);
        comboDataPagamento.setBounds(280, 30, 250, 40);

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel13.setText("Forma de pagamento");
        jPanel4.add(jLabel13);
        jLabel13.setBounds(10, 10, 220, 20);

        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel17.setText("Data do pagamento");
        jPanel4.add(jLabel17);
        jLabel17.setBounds(280, 10, 220, 20);

        txtValorPagamento.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txtValorPagamento.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtValorPagamentoKeyReleased(evt);
            }
        });
        jPanel4.add(txtValorPagamento);
        txtValorPagamento.setBounds(10, 100, 190, 40);

        jLabel25.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel25.setText("Valor total pago");
        jPanel4.add(jLabel25);
        jLabel25.setBounds(310, 550, 200, 40);

        lblValorTotalPagamentos.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        lblValorTotalPagamentos.setForeground(new java.awt.Color(0, 153, 51));
        lblValorTotalPagamentos.setText("R$ 0,00");
        jPanel4.add(lblValorTotalPagamentos);
        lblValorTotalPagamentos.setBounds(520, 550, 190, 40);

        lblRestantePagar.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        lblRestantePagar.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblRestantePagar.setText("R$ 0,00");
        jPanel4.add(lblRestantePagar);
        lblRestantePagar.setBounds(780, 180, 180, 40);

        jLabel24.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel24.setText("Valor do pagamento");
        jPanel4.add(jLabel24);
        jLabel24.setBounds(10, 80, 190, 20);

        painelCredito.setBackground(new java.awt.Color(0, 153, 51));
        painelCredito.setLayout(null);

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Crédito");
        painelCredito.add(jLabel5);
        jLabel5.setBounds(220, 10, 180, 22);

        lblValorCreditoCliente.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        lblValorCreditoCliente.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblValorCreditoCliente.setText("R$ 0,00");
        painelCredito.add(lblValorCreditoCliente);
        lblValorCreditoCliente.setBounds(220, 40, 180, 40);

        btnUtilizarCreditos.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnUtilizarCreditos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/protese/util/icons/icons8-selecionado-25.png"))); // NOI18N
        btnUtilizarCreditos.setText("Utilizar \ncréditos!");
        btnUtilizarCreditos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUtilizarCreditosActionPerformed(evt);
            }
        });
        painelCredito.add(btnUtilizarCreditos);
        btnUtilizarCreditos.setBounds(10, 10, 210, 70);

        jPanel4.add(painelCredito);
        painelCredito.setBounds(550, 10, 410, 90);

        tabbedPaneServico.addTab("Pagamentos realizados", jPanel4);

        jPanel1.add(tabbedPaneServico);
        tabbedPaneServico.setBounds(10, 10, 970, 640);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 989, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 659, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnFecharActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFecharActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnFecharActionPerformed

    private void btnSalvarServicoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarServicoActionPerformed
        if (cliente.getId() == null) {
            JOptionPane.showMessageDialog(this, "Selecione o cliente para continuar!", "Cliente", JOptionPane.WARNING_MESSAGE);
        } else {
            servico.setIdcliente(cliente);

            if (txtTitulo.getText().isEmpty()) {
                servico.setTitulo("Serviço referente a " + comboReferenteMes.getSelectedItem().toString() + "/" + comboReferenteAno.getSelectedItem().toString());
            } else {
                servico.setTitulo(txtTitulo.getText());
            }

            servico.setDescricao(txtDescricao.getText());

            //Cadastra a data de criação somente no momento de criação do serviço
            //Ao alterar o serviço, essa data não será alterada
            boolean verificaAdicaoDebitos = false;
            if (servico.getId() == null || servico.getId() == 0) {
                verificaAdicaoDebitos = true;
                servico.setDataCriacao(LocalDateTime.now());
            }

            //Data referente
            int dia = 1;
            int mes = comboReferenteMes.getSelectedIndex() + 1;
            int ano = Integer.parseInt(comboReferenteAno.getSelectedItem().toString());
            servico.setDataReferente(LocalDateTime.of(ano, mes, dia, 12, 0, 0));

            servico = servicoDao.salvar(servico);

            if (servico.getId() != null && servico.getId() > 0) {
                txtTitulo.setText(servico.getTitulo());

                JOptionPane.showMessageDialog(this, "Serviço salvo com sucesso!", "Serviço", JOptionPane.INFORMATION_MESSAGE);

                if (verificaAdicaoDebitos) {
                    servicoDebitoDao.verificaAgregarDebitos(servico);
                }

                liberaAbas(true);
            }
        }
    }//GEN-LAST:event_btnSalvarServicoActionPerformed

    private void btnPesquisarClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPesquisarClienteActionPerformed
        FrmPesquisarCliente frm = new FrmPesquisarCliente(null, true);
        frm.setLocationRelativeTo(null);
        frm.setVisible(true);

        cliente = frm.getCliente();

        preencheCliente();
    }//GEN-LAST:event_btnPesquisarClienteActionPerformed

    private void btnFinalizarServicoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFinalizarServicoActionPerformed
        if (servico.getId() != null && servico.getId() > 0 && servico.getDataFinalizacao() == null) {

            if (JOptionPane.showConfirmDialog(
                    this,
                    "Deseja realmente finalizar este serviço?",
                    "Produtos do serviço",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {

                // Adicionando crédito como pagamento automaticamente
                double restantePagar = servico.getRestantePagar();
                double saldoCliente = cliente.getSaldoAtual();
                double valorPagamento = 0;

                if (saldoCliente >= restantePagar) {
                    valorPagamento = restantePagar;
                } else if (restantePagar >= saldoCliente) {
                    valorPagamento = saldoCliente;
                }
                // Realizando pagamento com crédito
                novoPagamento(formaPagamentoDao.retornaFormaPagamentoCreditoSaida(), valorPagamento, LocalDateTime.now(), false);

                // Finalizando serviço
                servico = servicoDao.finalizarServico(servico);

                if (servico.getDataFinalizacao() != null) {
                    JOptionPane.showMessageDialog(this, "Serviço finalizado com sucesso!", "Serviço", JOptionPane.INFORMATION_MESSAGE);

                    preencheServico();
                }
            }
        }
    }//GEN-LAST:event_btnFinalizarServicoActionPerformed

    private void btnExcluirProdutoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluirProdutoActionPerformed
        int[] rows = tblServicoDetalhe.getSelectedRows();

        if (rows.length > 0) {
            if (JOptionPane.showConfirmDialog(
                    this,
                    "Deseja realmente excluir estes produtos do serviço?",
                    "Produtos do serviço",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {

                ServicoDetalhe servicoDetalhe;

                try {
                    for (int i = 0; i < rows.length; i++) {
                        servicoDetalhe = servicoDetalheDao.consultarId(ServicoDetalhe.class, Long.parseLong(tblServicoDetalhe.getValueAt(rows[i], 0).toString()));
                        servicoDetalheDao.deletar(servicoDetalhe);
                    }

                    JOptionPane.showMessageDialog(this, "Produtos do serviço excluidos com sucesso!", "Produto do serviço", JOptionPane.INFORMATION_MESSAGE);

                    preencheServico();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "Selecione o produto do serviço para continuar!", "Produto do serviço", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_btnExcluirProdutoActionPerformed

    private void btnLancarProdutoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLancarProdutoActionPerformed
        if (produtoValor.getId() == null || produtoValor.getId() == 0) {
            JOptionPane.showMessageDialog(this, "Selecione o produto para continuar!", "Produto", JOptionPane.WARNING_MESSAGE);
        } else if (txtQuantidadeProduto.getText().isEmpty() || Double.parseDouble(txtQuantidadeProduto.getText()) == 0) {
            JOptionPane.showMessageDialog(this, "Informe uma quantidade válida para continuar!", "Produto", JOptionPane.WARNING_MESSAGE);
        } else {
            ServicoDetalhe servicoDetalhe = new ServicoDetalhe();

            servicoDetalhe.setIdservico(servico);
            servicoDetalhe.setIdprodutoValor(produtoValor);
            servicoDetalhe.setDataLancamento(utilidade.asLocalDateTime(comboDataLancamento.getDate()));
            servicoDetalhe.setQuantidade(Double.parseDouble(txtQuantidadeProduto.getText()));
            servicoDetalhe.setValorUnitario(produtoValor.getValor());
            servicoDetalhe.setValorTotal(produtoValor.getValor() * servicoDetalhe.getQuantidade());
            servicoDetalhe.setDescricao("");
            servicoDetalhe = servicoDetalheDao.salvar(servicoDetalhe);

            if (servicoDetalhe.getId() != null && servicoDetalhe.getId() > 0) {
                JOptionPane.showMessageDialog(this, "Produto do serviço salvo com sucesso!", "Produto do serviço", JOptionPane.INFORMATION_MESSAGE);

                produtoValor = new ProdutoValor();
                preencheServico();
                preencheProduto();
            }
        }
    }//GEN-LAST:event_btnLancarProdutoActionPerformed

    private void btnPesquisarProdutoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPesquisarProdutoActionPerformed
        FrmPesquisarProdutoValor frm = new FrmPesquisarProdutoValor(null, true);
        frm.setLocationRelativeTo(null);
        frm.setVisible(true);

        produtoValor = frm.getProdutoValor();

        preencheProduto();
    }//GEN-LAST:event_btnPesquisarProdutoActionPerformed

    private void txtQuantidadeProdutoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtQuantidadeProdutoKeyReleased
        calculaValorNovoLancamento();
    }//GEN-LAST:event_txtQuantidadeProdutoKeyReleased

    private void txtQuantidadeProdutoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtQuantidadeProdutoKeyTyped
        String caracteres = "1234567890.";

        if (!caracteres.contains(evt.getKeyChar() + "")) {
            evt.consume();
        }
    }//GEN-LAST:event_txtQuantidadeProdutoKeyTyped

    private void btnExcluirPagamentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluirPagamentoActionPerformed
        int[] rows = tblPagamentos.getSelectedRows();

        if (rows.length > 0) {
            if (JOptionPane.showConfirmDialog(
                    this,
                    "Deseja realmente excluir pagamentos do serviço?",
                    "Pagamento do serviço",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {

                ServicoPagamento servicoPagamento;

                try {
                    for (int i = 0; i < rows.length; i++) {
                        servicoPagamento = servicoPagamentoDao.consultarId(ServicoPagamento.class, Long.parseLong(tblPagamentos.getValueAt(rows[i], 0).toString()));
                        servicoPagamentoDao.deletar(servicoPagamento);
                    }

                    JOptionPane.showMessageDialog(this, "Pagamentos do serviço excluidos com sucesso!", "Pagamento do serviço", JOptionPane.INFORMATION_MESSAGE);

                    txtValorPagamento.setText("");
                    preencheServico();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }//GEN-LAST:event_btnExcluirPagamentoActionPerformed

    private void btnLancarPagamentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLancarPagamentoActionPerformed
        if (txtValorPagamento.getText().isEmpty() || Double.parseDouble(txtValorPagamento.getText()) == 0) {
            JOptionPane.showMessageDialog(this, "Informe um valor de pagamento válido para continuar!", "Pagamento do serviço", JOptionPane.WARNING_MESSAGE);
            return;
        }

        FormaPagamento formaPagamento = formaPagamentoList.get(comboFormaPagamento.getSelectedIndex());
        double valorPagamento = Double.parseDouble(txtValorPagamento.getText());

        novoPagamento(formaPagamento, valorPagamento, utilidade.asLocalDateTime(comboDataPagamento.getDate()), true);
    }//GEN-LAST:event_btnLancarPagamentoActionPerformed

    private void txtValorPagamentoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtValorPagamentoKeyReleased
        String caracteres = "1234567890.";

        if (!caracteres.contains(evt.getKeyChar() + "")) {
            evt.consume();
        }
    }//GEN-LAST:event_txtValorPagamentoKeyReleased

    private void btnUtilizarCreditosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUtilizarCreditosActionPerformed
        double restantePagar = servico.getRestantePagar();
        double saldoCliente = cliente.getSaldoAtual();
        double valorPagamento = 0;

        if (saldoCliente >= restantePagar) {
            valorPagamento = restantePagar;
        } else if (restantePagar >= saldoCliente) {
            valorPagamento = saldoCliente;
        }

        if (valorPagamento > 0) {
            if (JOptionPane.showConfirmDialog(
                    this,
                    "Deseja realmente utilizar os créditos do cliente?",
                    "Produtos do serviço",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
                novoPagamento(formaPagamentoDao.retornaFormaPagamentoCreditoSaida(), valorPagamento, utilidade.asLocalDateTime(comboDataPagamento.getDate()), true);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Informe um valor de crédito válido para continuar!", "Pagamento do serviço", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_btnUtilizarCreditosActionPerformed

    private void btnAgregarDebitosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarDebitosActionPerformed
        if (JOptionPane.showConfirmDialog(this,
                "Deseja realmente agregar os débitos pendentes do cliente ao serviço?",
                "Atenção!",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
            
            //Verificando e agregando débitos
            if (servicoDebitoDao.verificaAgregarDebitos(servico)) {
                JOptionPane.showMessageDialog(this, "Débitos pendentes agregados com sucesso!", "Atenção!", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Este cliente não possui débitos a serem agregados!", "Atenção!", JOptionPane.INFORMATION_MESSAGE);
            }

            preencheServico();
        }
    }//GEN-LAST:event_btnAgregarDebitosActionPerformed

    private void btnExcluirDebitoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluirDebitoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnExcluirDebitoActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FrmServico.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmServico.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmServico.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmServico.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                FrmServico dialog = new FrmServico(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregarDebitos;
    private javax.swing.JButton btnExcluirDebito;
    private javax.swing.JButton btnExcluirPagamento;
    private javax.swing.JButton btnExcluirProduto;
    private javax.swing.JButton btnFechar;
    private javax.swing.JButton btnFinalizarServico;
    private javax.swing.JButton btnLancarDebito;
    private javax.swing.JButton btnLancarPagamento;
    private javax.swing.JButton btnLancarProduto;
    private javax.swing.JButton btnPesquisarCliente;
    private javax.swing.JButton btnPesquisarProduto;
    private javax.swing.JButton btnSalvarServico;
    private javax.swing.JButton btnUtilizarCreditos;
    private com.toedter.calendar.JDateChooser comboDataLancamento;
    private com.toedter.calendar.JDateChooser comboDataPagamento;
    private javax.swing.JComboBox<String> comboFormaPagamento;
    private javax.swing.JComboBox<String> comboReferenteAno;
    private javax.swing.JComboBox<String> comboReferenteMes;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JLabel lblRestantePagar;
    private javax.swing.JLabel lblRestantePagarGeral;
    private javax.swing.JLabel lblValorCreditoCliente;
    private javax.swing.JLabel lblValorDebitoCliente;
    private javax.swing.JLabel lblValorTotal;
    private javax.swing.JLabel lblValorTotalDebitos;
    private javax.swing.JLabel lblValorTotalPagamentos;
    private javax.swing.JLabel lblValorTotalPagoGeral;
    private javax.swing.JLabel lblValorTotalProdutos;
    private javax.swing.JLabel lblValorTotalServicoGeral;
    private javax.swing.JLabel lblValorUnitario;
    private javax.swing.JPanel painelCredito;
    private javax.swing.JPanel painelDebito;
    private javax.swing.JTabbedPane tabbedPaneServico;
    private javax.swing.JTable tblPagamentos;
    private javax.swing.JTable tblServicoDebito;
    private javax.swing.JTable tblServicoDetalhe;
    private javax.swing.JTextField txtCodigoGrupo;
    private javax.swing.JTextField txtCodigoProduto;
    private javax.swing.JTextArea txtDescricao;
    private javax.swing.JTextField txtNomeCliente;
    private javax.swing.JTextField txtNomeProduto;
    private javax.swing.JTextField txtQuantidadeProduto;
    private javax.swing.JTextField txtTitulo;
    private javax.swing.JTextField txtValorPagamento;
    // End of variables declaration//GEN-END:variables
}
