package protese.view.cliente;

import java.awt.Font;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import protese.dao.cliente.ClienteContatoDao;
import protese.dao.cliente.ClienteDao;
import protese.dao.cliente.ClienteEnderecoDao;
import protese.dao.cliente.ContatoDao;
import protese.dao.cliente.CreditoDao;
import protese.dao.cliente.EnderecoDao;
import protese.model.cliente.Cliente;
import protese.model.cliente.ClienteContato;
import protese.model.cliente.ClienteEndereco;
import protese.model.cliente.Contato;
import protese.model.cliente.Credito;
import protese.model.cliente.Endereco;
import protese.util.utilidade.Utilidade;

/**
 *
 * @author Vinicius Silveira
 */
public class FrmCliente extends javax.swing.JDialog {

    private ClienteDao clienteDao = ClienteDao.getInstance();
    private ContatoDao contatoDao = ContatoDao.getInstance();
    private ClienteContatoDao clienteContatoDao = ClienteContatoDao.getInstance();
    private EnderecoDao enderecoDao = EnderecoDao.getInstance();
    private ClienteEnderecoDao clienteEnderecoDao = ClienteEnderecoDao.getInstance();
    private CreditoDao creditoDao = CreditoDao.getInstance();

    private Cliente cliente = new Cliente();
    private ClienteEndereco clienteEndereco = new ClienteEndereco();

    private Utilidade utilidade = Utilidade.getInstance();
    private DefaultTableModel modelo = new DefaultTableModel();

    private FrmCliente(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }

    public FrmCliente(java.awt.Frame parent, boolean modal, Cliente cliente) {
        super(parent, modal);
        initComponents();

        this.cliente = cliente;
        comboDataDeCredito.setDate(utilidade.asDate(LocalDate.now().minusDays(1)));
        comboDataAteCredito.setDate(utilidade.asDate(LocalDate.now()));
        
        tblContatos.getTableHeader().setFont(new Font("Tahoma", Font.BOLD, 18));
        tblCredito.getTableHeader().setFont(new Font("Tahoma", Font.BOLD, 18));

        for (ClienteEndereco clienteEndereco : this.cliente.getClienteEnderecoList()) {
            this.clienteEndereco = clienteEndereco;
            break;
        }

        liberaAbas();
        preencheCliente();
        preencheEndereco();
        preencheContatos();

        if (this.cliente.getId() != null && this.cliente.getId() > 0) {
            preencheCredito(creditoDao.retornaTodosPorCliente(this.cliente));
        }
    }

    private void liberaAbas() {
        if (cliente.getId() != null && cliente.getId() > 0) {
            tabbedPaneCliente.setEnabledAt(1, true);
            tabbedPaneCliente.setEnabledAt(2, true);
            tabbedPaneCliente.setEnabledAt(3, true);
        } else {
            tabbedPaneCliente.setEnabledAt(1, false);
            tabbedPaneCliente.setEnabledAt(2, false);
            tabbedPaneCliente.setEnabledAt(3, false);
        }
    }

    private void preencheCliente() {
        txtNome.setText(cliente.getNome());
        txtRg.setText(cliente.getRg());
        txtDocumento.setText(cliente.getDocumento());
        txtEmail.setText(cliente.getEmail());
    }

    private void preencheEndereco() {
        if (clienteEndereco.getId() != null) {
            txtLogradouro.setText(clienteEndereco.getIdendereco().getLogradouro());
            txtNumeroEndereco.setText(clienteEndereco.getIdendereco().getNumero());
            txtBairro.setText(clienteEndereco.getIdendereco().getBairro());
            txtComplemento.setText(clienteEndereco.getIdendereco().getComplemento());
            txtCidade.setText(clienteEndereco.getIdendereco().getCidade());
            comboEstado.setSelectedItem(clienteEndereco.getIdendereco().getEstado());
            txtCep.setText(clienteEndereco.getIdendereco().getCep());
        }
    }

    private void preencheContatos() {
        txtDdi.setText("");
        txtDdd.setText("");
        txtNumeroContato.setText("");

        modelo = (DefaultTableModel) tblContatos.getModel();
        modelo.setRowCount(0);

        for (ClienteContato clienteContato : cliente.getClienteContatoList()) {
            modelo.addRow(new Object[]{
                clienteContato.getId(),
                clienteContato.getIdcontato().getDdi(),
                clienteContato.getIdcontato().getDdd(),
                clienteContato.getIdcontato().getNumero()
            });
        }
    }

    private void preencheCredito(List<Credito> creditoList) {
        modelo = (DefaultTableModel) tblCredito.getModel();
        modelo.setRowCount(0);

        for (Credito credito : creditoList) {
            modelo.addRow(new Object[]{
                credito.getId(),
                credito.getDescricao(),
                utilidade.sdfTimeStamp(credito.getData()),
                credito.getTipo(),
                "R$ " + utilidade.decimalFormat(credito.getValor())
            });
        }

        atualizaCredito();
    }

    private void atualizaCredito() {
        if (cliente.getId() != null && cliente.getId() > 0) {
            lblValorCreditoCliente.setText("R$ " + utilidade.decimalFormat(cliente.getSaldoAtual()));
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        btnFechar = new javax.swing.JButton();
        tabbedPaneCliente = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtRg = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtNome = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtDocumento = new javax.swing.JTextField();
        txtEmail = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        btnSalvarCliente = new javax.swing.JButton();
        jLabel22 = new javax.swing.JLabel();
        txtCodigoProprio = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        txtLogradouro = new javax.swing.JTextField();
        txtBairro = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtNumeroEndereco = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtCidade = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        comboEstado = new javax.swing.JComboBox<>();
        jLabel9 = new javax.swing.JLabel();
        btnSalvarEndereco = new javax.swing.JButton();
        jLabel15 = new javax.swing.JLabel();
        txtComplemento = new javax.swing.JTextField();
        txtCep = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblContatos = new javax.swing.JTable();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel10 = new javax.swing.JLabel();
        btnExcluirContato = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        txtDdi = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        txtDdd = new javax.swing.JTextField();
        txtNumeroContato = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        btnSalvarContato = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblCredito = new javax.swing.JTable();
        jLabel17 = new javax.swing.JLabel();
        comboFiltroCredito = new javax.swing.JComboBox<>();
        jLabel18 = new javax.swing.JLabel();
        comboDataAteCredito = new com.toedter.calendar.JDateChooser();
        comboDataDeCredito = new com.toedter.calendar.JDateChooser();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        btnPesquisarCredito = new javax.swing.JButton();
        lblValorCreditoCliente = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Cliente");

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
        btnFechar.setBounds(680, 533, 140, 40);

        tabbedPaneCliente.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(null);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setText("RG");
        jPanel2.add(jLabel1);
        jLabel1.setBounds(10, 170, 130, 20);

        txtRg.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jPanel2.add(txtRg);
        txtRg.setBounds(10, 190, 130, 40);

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel2.setText("Nome completo");
        jPanel2.add(jLabel2);
        jLabel2.setBounds(10, 90, 170, 20);

        txtNome.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jPanel2.add(txtNome);
        txtNome.setBounds(10, 110, 800, 40);

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel3.setText("CPF/CNPJ");
        jPanel2.add(jLabel3);
        jLabel3.setBounds(170, 170, 170, 20);

        txtDocumento.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jPanel2.add(txtDocumento);
        txtDocumento.setBounds(170, 190, 200, 40);

        txtEmail.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jPanel2.add(txtEmail);
        txtEmail.setBounds(10, 270, 800, 40);

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel4.setText("Email");
        jPanel2.add(jLabel4);
        jLabel4.setBounds(10, 250, 130, 20);

        btnSalvarCliente.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnSalvarCliente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/protese/util/icons/icons8-selecionado-25.png"))); // NOI18N
        btnSalvarCliente.setText("Salvar");
        btnSalvarCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalvarClienteActionPerformed(evt);
            }
        });
        jPanel2.add(btnSalvarCliente);
        btnSalvarCliente.setBounds(10, 490, 140, 40);

        jLabel22.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel22.setText("Código próprio");
        jPanel2.add(jLabel22);
        jLabel22.setBounds(10, 10, 190, 20);

        txtCodigoProprio.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jPanel2.add(txtCodigoProprio);
        txtCodigoProprio.setBounds(10, 30, 190, 40);

        tabbedPaneCliente.addTab("Dados cadastrais", jPanel2);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setLayout(null);

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel5.setText("Logradouro");
        jPanel3.add(jLabel5);
        jLabel5.setBounds(10, 10, 720, 20);

        txtLogradouro.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jPanel3.add(txtLogradouro);
        txtLogradouro.setBounds(10, 30, 800, 40);

        txtBairro.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jPanel3.add(txtBairro);
        txtBairro.setBounds(190, 110, 270, 40);

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel6.setText("Bairro");
        jPanel3.add(jLabel6);
        jLabel6.setBounds(190, 90, 130, 20);

        txtNumeroEndereco.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jPanel3.add(txtNumeroEndereco);
        txtNumeroEndereco.setBounds(10, 110, 150, 40);

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel7.setText("Número");
        jPanel3.add(jLabel7);
        jLabel7.setBounds(10, 90, 130, 20);

        txtCidade.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jPanel3.add(txtCidade);
        txtCidade.setBounds(10, 190, 450, 40);

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel8.setText("Estado");
        jPanel3.add(jLabel8);
        jLabel8.setBounds(490, 170, 130, 20);

        comboEstado.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        comboEstado.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "AC", "AL", "AP", "AM", "BA", "CE", "DF", "ES", "GO", "MA", "MT", "MS", "MG", "PA", "PB", "PR", "PE", "PI", "RJ", "RN", "RS", "RO", "RR", "SC", "SP", "SE", "TO" }));
        jPanel3.add(comboEstado);
        comboEstado.setBounds(490, 190, 140, 40);

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel9.setText("Cidade");
        jPanel3.add(jLabel9);
        jLabel9.setBounds(10, 170, 130, 20);

        btnSalvarEndereco.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnSalvarEndereco.setIcon(new javax.swing.ImageIcon(getClass().getResource("/protese/util/icons/icons8-selecionado-25.png"))); // NOI18N
        btnSalvarEndereco.setText("Salvar");
        btnSalvarEndereco.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalvarEnderecoActionPerformed(evt);
            }
        });
        jPanel3.add(btnSalvarEndereco);
        btnSalvarEndereco.setBounds(10, 490, 140, 40);

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel15.setText("Complemento");
        jPanel3.add(jLabel15);
        jLabel15.setBounds(490, 90, 290, 20);

        txtComplemento.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jPanel3.add(txtComplemento);
        txtComplemento.setBounds(490, 110, 320, 40);

        txtCep.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jPanel3.add(txtCep);
        txtCep.setBounds(660, 190, 150, 40);

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel16.setText("CEP");
        jPanel3.add(jLabel16);
        jLabel16.setBounds(660, 170, 130, 20);

        tabbedPaneCliente.addTab("Endereço", jPanel3);

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setLayout(null);

        tblContatos.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        tblContatos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "DDI", "DDD", "Número"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblContatos.setRowHeight(35);
        tblContatos.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(tblContatos);
        if (tblContatos.getColumnModel().getColumnCount() > 0) {
            tblContatos.getColumnModel().getColumn(0).setMinWidth(0);
            tblContatos.getColumnModel().getColumn(0).setPreferredWidth(0);
            tblContatos.getColumnModel().getColumn(0).setMaxWidth(0);
        }

        jPanel4.add(jScrollPane1);
        jScrollPane1.setBounds(10, 200, 800, 270);
        jPanel4.add(jSeparator1);
        jSeparator1.setBounds(10, 170, 800, 10);

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel10.setText("Cadastrar novo contato");
        jPanel4.add(jLabel10);
        jLabel10.setBounds(10, 10, 260, 20);

        btnExcluirContato.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnExcluirContato.setIcon(new javax.swing.ImageIcon(getClass().getResource("/protese/util/icons/icons8-excluir-25.png"))); // NOI18N
        btnExcluirContato.setText("Excluir");
        btnExcluirContato.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcluirContatoActionPerformed(evt);
            }
        });
        jPanel4.add(btnExcluirContato);
        btnExcluirContato.setBounds(10, 490, 140, 40);

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel11.setText("Listagem dos contatos");
        jPanel4.add(jLabel11);
        jLabel11.setBounds(10, 180, 240, 20);

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel12.setText("DDI");
        jPanel4.add(jLabel12);
        jLabel12.setBounds(10, 40, 170, 20);

        txtDdi.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jPanel4.add(txtDdi);
        txtDdi.setBounds(10, 60, 170, 40);

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel13.setText("DDD");
        jPanel4.add(jLabel13);
        jLabel13.setBounds(200, 40, 170, 20);

        txtDdd.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jPanel4.add(txtDdd);
        txtDdd.setBounds(200, 60, 170, 40);

        txtNumeroContato.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jPanel4.add(txtNumeroContato);
        txtNumeroContato.setBounds(390, 60, 420, 40);

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel14.setText("Número");
        jPanel4.add(jLabel14);
        jLabel14.setBounds(390, 40, 340, 20);

        btnSalvarContato.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnSalvarContato.setIcon(new javax.swing.ImageIcon(getClass().getResource("/protese/util/icons/icons8-selecionado-25.png"))); // NOI18N
        btnSalvarContato.setText("Salvar");
        btnSalvarContato.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalvarContatoActionPerformed(evt);
            }
        });
        jPanel4.add(btnSalvarContato);
        btnSalvarContato.setBounds(10, 110, 120, 40);

        tabbedPaneCliente.addTab("Contatos", jPanel4);

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setLayout(null);

        tblCredito.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        tblCredito.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Serviço", "Data", "Tipo", "Valor"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblCredito.setRowHeight(35);
        tblCredito.getTableHeader().setReorderingAllowed(false);
        jScrollPane2.setViewportView(tblCredito);
        if (tblCredito.getColumnModel().getColumnCount() > 0) {
            tblCredito.getColumnModel().getColumn(0).setMinWidth(0);
            tblCredito.getColumnModel().getColumn(0).setPreferredWidth(0);
            tblCredito.getColumnModel().getColumn(0).setMaxWidth(0);
        }

        jPanel5.add(jScrollPane2);
        jScrollPane2.setBounds(10, 110, 800, 360);

        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel17.setText("Até");
        jPanel5.add(jLabel17);
        jLabel17.setBounds(490, 40, 210, 20);

        comboFiltroCredito.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        comboFiltroCredito.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "<Todos>", "Somente entrada", "Somente saida" }));
        jPanel5.add(comboFiltroCredito);
        comboFiltroCredito.setBounds(10, 60, 210, 40);

        jLabel18.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel18.setText("Listagem das movimentações de entrada e saida de crédito");
        jPanel5.add(jLabel18);
        jLabel18.setBounds(10, 10, 560, 20);

        comboDataAteCredito.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jPanel5.add(comboDataAteCredito);
        comboDataAteCredito.setBounds(490, 60, 250, 40);

        comboDataDeCredito.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jPanel5.add(comboDataDeCredito);
        comboDataDeCredito.setBounds(230, 60, 250, 40);

        jLabel19.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel19.setText("Saldo atual");
        jPanel5.add(jLabel19);
        jLabel19.setBounds(270, 500, 100, 20);

        jLabel20.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel20.setText("De");
        jPanel5.add(jLabel20);
        jLabel20.setBounds(230, 40, 210, 20);

        btnPesquisarCredito.setIcon(new javax.swing.ImageIcon(getClass().getResource("/protese/util/icons/icons8-pesquisar-25.png"))); // NOI18N
        btnPesquisarCredito.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPesquisarCreditoActionPerformed(evt);
            }
        });
        jPanel5.add(btnPesquisarCredito);
        btnPesquisarCredito.setBounds(750, 40, 60, 60);

        lblValorCreditoCliente.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lblValorCreditoCliente.setForeground(new java.awt.Color(0, 153, 51));
        lblValorCreditoCliente.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblValorCreditoCliente.setText("R$ 0,00");
        jPanel5.add(lblValorCreditoCliente);
        lblValorCreditoCliente.setBounds(370, 500, 100, 20);

        jLabel21.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel21.setText("Pesquisar por");
        jPanel5.add(jLabel21);
        jLabel21.setBounds(10, 40, 210, 20);

        tabbedPaneCliente.addTab("Crédito", jPanel5);

        jPanel1.add(tabbedPaneCliente);
        tabbedPaneCliente.setBounds(10, 10, 830, 570);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 846, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 587, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnFecharActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFecharActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnFecharActionPerformed

    private void btnSalvarClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarClienteActionPerformed
        cliente.setNome(txtNome.getText());
        cliente.setRg(txtRg.getText());
        cliente.setDocumento(txtDocumento.getText());
        cliente.setEmail(txtEmail.getText());

        cliente = clienteDao.salvar(cliente);

        if (cliente.getId() != null && cliente.getId() > 0) {
            JOptionPane.showMessageDialog(this, "Cliente salvo com sucesso!", "Cliente", JOptionPane.INFORMATION_MESSAGE);

            liberaAbas();
        }
    }//GEN-LAST:event_btnSalvarClienteActionPerformed

    private void btnSalvarEnderecoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarEnderecoActionPerformed
        Endereco endereco = new Endereco();

        if (clienteEndereco.getId() != null && clienteEndereco.getId() > 0) {
            endereco = clienteEndereco.getIdendereco();
        }

        endereco.setLogradouro(txtLogradouro.getText());
        endereco.setNumero(txtNumeroEndereco.getText());
        endereco.setBairro(txtBairro.getText());
        endereco.setComplemento(txtComplemento.getText());
        endereco.setCidade(txtCidade.getText());
        endereco.setEstado(comboEstado.getSelectedItem().toString());
        endereco.setCep(txtCep.getText());
        endereco = enderecoDao.salvar(endereco);

        if (clienteEndereco.getId() == null) {
            clienteEndereco.setIdcliente(cliente);
            clienteEndereco.setIdendereco(endereco);
            clienteEndereco = clienteEnderecoDao.salvar(clienteEndereco);
        }

        if (clienteEndereco.getId() != null && clienteEndereco.getId() > 0) {
            JOptionPane.showMessageDialog(this, "Endereço salvo com sucesso!", "Cliente", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_btnSalvarEnderecoActionPerformed

    private void btnExcluirContatoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluirContatoActionPerformed
        ClienteContato clienteContato;
        int[] rows = tblContatos.getSelectedRows();

        if (rows.length > 0) {
            if (JOptionPane.showConfirmDialog(
                    this,
                    "Deseja realmente excluir estes contatos?",
                    "Contatos",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {

                try {
                    for (int i = 0; i < rows.length; i++) {
                        clienteContato = clienteContatoDao.consultarId(ClienteContato.class, Long.parseLong(tblContatos.getValueAt(rows[i], 0).toString()));

                        clienteContatoDao.deletar(clienteContato);
                    }

                    JOptionPane.showMessageDialog(this, "Contatos excluidos com sucesso!", "Cliente", JOptionPane.INFORMATION_MESSAGE);
                    preencheContatos();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }//GEN-LAST:event_btnExcluirContatoActionPerformed

    private void btnSalvarContatoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarContatoActionPerformed
        Contato contato = new Contato();

        if (!txtNumeroContato.getText().trim().isEmpty()) {
            contato.setDdi(txtDdi.getText());
            contato.setDdd(txtDdd.getText());
            contato.setNumero(txtNumeroContato.getText());
            contato = contatoDao.salvar(contato);

            ClienteContato clienteContato = new ClienteContato();
            clienteContato.setIdcliente(cliente);
            clienteContato.setIdcontato(contato);
            clienteContato = clienteContatoDao.salvar(clienteContato);

            if (clienteContato.getId() != null && clienteContato.getId() > 0) {
                JOptionPane.showMessageDialog(this, "Contato salvo com sucesso!", "Cliente", JOptionPane.INFORMATION_MESSAGE);

                preencheContatos();
            }
        } else {
            JOptionPane.showMessageDialog(this, "Informe o número do contato para continuar!", "Contato", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_btnSalvarContatoActionPerformed

    private void btnPesquisarCreditoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPesquisarCreditoActionPerformed
        switch (comboFiltroCredito.getSelectedIndex()) {
            case 0:
                preencheCredito(creditoDao.retornaTodosPorCliente(
                        cliente,
                        0,
                        utilidade.asLocalDate(comboDataDeCredito.getDate()).atTime(LocalTime.of(0, 0, 0)),
                        utilidade.asLocalDate(comboDataAteCredito.getDate()).atTime(LocalTime.of(23, 59, 59))));
                break;
            case 1:
                //Entrada
                preencheCredito(creditoDao.retornaTodosPorCliente(
                        cliente,
                        1,
                        utilidade.asLocalDate(comboDataDeCredito.getDate()).atTime(LocalTime.of(0, 0, 0)),
                        utilidade.asLocalDate(comboDataAteCredito.getDate()).atTime(LocalTime.of(23, 59, 59))));
                break;
            case 2:
                preencheCredito(creditoDao.retornaTodosPorCliente(
                        cliente,
                        2,
                        utilidade.asLocalDate(comboDataDeCredito.getDate()).atTime(LocalTime.of(0, 0, 0)),
                        utilidade.asLocalDate(comboDataAteCredito.getDate()).atTime(LocalTime.of(23, 59, 59))));
                //Saida
                break;
            default:
                preencheCredito(creditoDao.retornaTodosPorCliente(cliente));
                break;
        }
    }//GEN-LAST:event_btnPesquisarCreditoActionPerformed

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
            java.util.logging.Logger.getLogger(FrmCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                FrmCliente dialog = new FrmCliente(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton btnExcluirContato;
    private javax.swing.JButton btnFechar;
    private javax.swing.JButton btnPesquisarCredito;
    private javax.swing.JButton btnSalvarCliente;
    private javax.swing.JButton btnSalvarContato;
    private javax.swing.JButton btnSalvarEndereco;
    private com.toedter.calendar.JDateChooser comboDataAteCredito;
    private com.toedter.calendar.JDateChooser comboDataDeCredito;
    private javax.swing.JComboBox<String> comboEstado;
    private javax.swing.JComboBox<String> comboFiltroCredito;
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
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel lblValorCreditoCliente;
    private javax.swing.JTabbedPane tabbedPaneCliente;
    private javax.swing.JTable tblContatos;
    private javax.swing.JTable tblCredito;
    private javax.swing.JTextField txtBairro;
    private javax.swing.JTextField txtCep;
    private javax.swing.JTextField txtCidade;
    private javax.swing.JTextField txtCodigoProprio;
    private javax.swing.JTextField txtComplemento;
    private javax.swing.JTextField txtDdd;
    private javax.swing.JTextField txtDdi;
    private javax.swing.JTextField txtDocumento;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtLogradouro;
    private javax.swing.JTextField txtNome;
    private javax.swing.JTextField txtNumeroContato;
    private javax.swing.JTextField txtNumeroEndereco;
    private javax.swing.JTextField txtRg;
    // End of variables declaration//GEN-END:variables
}
