package protese.view.servico;

import java.text.DecimalFormat;
import javax.swing.JOptionPane;
import protese.dao.servico.ServicoDao;
import protese.model.cliente.Cliente;
import protese.model.produto.ProdutoValor;
import protese.model.servico.Servico;
import protese.view.cliente.FrmPesquisarCliente;

/**
 *
 * @author vinihds
 */
public class FrmServico extends javax.swing.JFrame {

    private ServicoDao servicoDao = ServicoDao.getInstance();
    private DecimalFormat decimalFormat = new DecimalFormat("###,###,##0.00");

    private Servico servico = new Servico();
    private Cliente cliente = new Cliente();
    private ProdutoValor produtoValor = new ProdutoValor();

    private FrmServico() {

    }

    public FrmServico(Servico servico) {
        initComponents();

        this.servico = servico;

        if (this.servico.getId() != null && this.servico.getId() > 0) {
            cliente = servico.getIdcliente();
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

    private void preencheCliente() {
        txtNomeCliente.setText(cliente.getNome());
    }

    private void preencheProduto() {
        //Produto
        txtNomeProduto.setText(produtoValor.getIdproduto().getNome());
        txtCodigoGrupo.setText(produtoValor.getIdgrupo().getCodigo());
        txtCodigoProduto.setText(produtoValor.getIdproduto().getCodigo());
        //Valor
        lblValorUnitario.setText("R$ " + decimalFormat.format(produtoValor.getValor()));
        txtQuantidadeProduto.setText("");
        lblValorTotal.setText("R$ 0,00");

        calculaValorNovoLancamento();
    }

    private void calculaValorNovoLancamento() {
        double quantidade = 0;
        double valorTotal = 0;

        if (!txtQuantidadeProduto.getText().isEmpty()) {
            quantidade = Double.parseDouble(txtQuantidadeProduto.getText());
        }

        valorTotal = produtoValor.getValor() * quantidade;

        lblValorTotal.setText("R$ " + decimalFormat.format(valorTotal));
    }
    
    private void liberaAbas(boolean libera) {
        tabbedPaneServico.setEnabledAt(1, libera);
        tabbedPaneServico.setEnabledAt(2, libera);
    }
    
    private void calculaValorTotalServico() {
        
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel5 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
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
        comboDataCriacao = new com.toedter.calendar.JDateChooser();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
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
        jLabel23 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();

        jLabel5.setText("jLabel5");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Serviço");

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(null);

        tabbedPaneServico.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(null);

        btnSalvarServico.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnSalvarServico.setText("Salvar");
        btnSalvarServico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalvarServicoActionPerformed(evt);
            }
        });
        jPanel2.add(btnSalvarServico);
        btnSalvarServico.setBounds(10, 520, 140, 40);

        txtDescricao.setColumns(20);
        txtDescricao.setRows(5);
        jScrollPane2.setViewportView(txtDescricao);

        jPanel2.add(jScrollPane2);
        jScrollPane2.setBounds(10, 290, 860, 220);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setText("Descrição do serviço");
        jPanel2.add(jLabel1);
        jLabel1.setBounds(10, 270, 160, 20);

        txtTitulo.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jPanel2.add(txtTitulo);
        txtTitulo.setBounds(10, 30, 860, 40);

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setText("Título");
        jPanel2.add(jLabel2);
        jLabel2.setBounds(10, 10, 130, 20);

        btnPesquisarCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPesquisarClienteActionPerformed(evt);
            }
        });
        jPanel2.add(btnPesquisarCliente);
        btnPesquisarCliente.setBounds(370, 90, 60, 60);

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setText("Data de criação");
        jPanel2.add(jLabel3);
        jLabel3.setBounds(450, 90, 140, 20);

        txtNomeCliente.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jPanel2.add(txtNomeCliente);
        txtNomeCliente.setBounds(10, 110, 360, 40);
        jPanel2.add(comboDataCriacao);
        comboDataCriacao.setBounds(450, 110, 420, 40);
        jPanel2.add(jSeparator1);
        jSeparator1.setBounds(10, 260, 860, 10);

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel4.setText("Cliente");
        jPanel2.add(jLabel4);
        jLabel4.setBounds(10, 90, 140, 20);

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel6.setText("Valor restante a receber");
        jPanel2.add(jLabel6);
        jLabel6.setBounds(680, 190, 190, 20);

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel7.setText("Valor total do serviço");
        jPanel2.add(jLabel7);
        jLabel7.setBounds(10, 190, 190, 20);

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel8.setText("Valor total pago");
        jPanel2.add(jLabel8);
        jLabel8.setBounds(380, 190, 120, 20);

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 102, 0));
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel10.setText("R$ 0,00");
        jPanel2.add(jLabel10);
        jLabel10.setBounds(600, 210, 270, 40);

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel11.setText("R$ 0,00");
        jPanel2.add(jLabel11);
        jLabel11.setBounds(10, 210, 270, 40);

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(0, 204, 0));
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel13.setText("R$ 0,00");
        jPanel2.add(jLabel13);
        jLabel13.setBounds(300, 210, 270, 40);

        tabbedPaneServico.addTab("Dados do serviço", jPanel2);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setLayout(null);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jTable1.setRowHeight(25);
        jScrollPane1.setViewportView(jTable1);

        jPanel3.add(jScrollPane1);
        jScrollPane1.setBounds(10, 250, 860, 260);

        jButton4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton4.setText("Excluir");
        jPanel3.add(jButton4);
        jButton4.setBounds(10, 520, 140, 40);

        jButton5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton5.setText("Lançar!");
        jPanel3.add(jButton5);
        jButton5.setBounds(10, 170, 140, 40);

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel9.setText("Listagem dos produtos deste serviço");
        jPanel3.add(jLabel9);
        jLabel9.setBounds(10, 230, 280, 20);
        jPanel3.add(jSeparator2);
        jSeparator2.setBounds(10, 220, 860, 20);

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel12.setText("Grupo");
        jPanel3.add(jLabel12);
        jLabel12.setBounds(10, 80, 90, 20);

        txtCodigoGrupo.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jPanel3.add(txtCodigoGrupo);
        txtCodigoGrupo.setBounds(10, 100, 110, 40);

        txtNomeProduto.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jPanel3.add(txtNomeProduto);
        txtNomeProduto.setBounds(10, 30, 490, 40);

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel14.setText("Nome do produto");
        jPanel3.add(jLabel14);
        jLabel14.setBounds(10, 10, 140, 20);

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel15.setText("Valor total");
        jPanel3.add(jLabel15);
        jLabel15.setBounds(630, 130, 100, 20);

        btnPesquisarProduto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPesquisarProdutoActionPerformed(evt);
            }
        });
        jPanel3.add(btnPesquisarProduto);
        btnPesquisarProduto.setBounds(500, 10, 60, 60);

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel16.setText("Quantidade");
        jPanel3.add(jLabel16);
        jLabel16.setBounds(640, 70, 90, 20);

        txtQuantidadeProduto.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
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
        txtQuantidadeProduto.setBounds(740, 60, 130, 40);

        lblValorTotal.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblValorTotal.setForeground(new java.awt.Color(255, 102, 0));
        lblValorTotal.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblValorTotal.setText("R$ 0,00");
        jPanel3.add(lblValorTotal);
        lblValorTotal.setBounds(740, 120, 130, 40);
        jPanel3.add(jSeparator3);
        jSeparator3.setBounds(620, 110, 250, 20);

        lblValorUnitario.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblValorUnitario.setForeground(new java.awt.Color(255, 102, 0));
        lblValorUnitario.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblValorUnitario.setText("R$ 0,00");
        jPanel3.add(lblValorUnitario);
        lblValorUnitario.setBounds(740, 10, 130, 40);

        jLabel19.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel19.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel19.setText("Valor unitário");
        jPanel3.add(jLabel19);
        jLabel19.setBounds(630, 20, 100, 20);

        txtCodigoProduto.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jPanel3.add(txtCodigoProduto);
        txtCodigoProduto.setBounds(140, 100, 110, 40);

        jLabel20.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel20.setText("Data de lançamento");
        jPanel3.add(jLabel20);
        jLabel20.setBounds(270, 80, 170, 20);
        jPanel3.add(comboDataLancamento);
        comboDataLancamento.setBounds(270, 100, 290, 40);

        jLabel21.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel21.setText("Produto");
        jPanel3.add(jLabel21);
        jLabel21.setBounds(140, 80, 90, 20);

        jLabel22.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel22.setText("Valor total do serviço");
        jPanel3.add(jLabel22);
        jLabel22.setBounds(280, 530, 160, 20);

        jLabel23.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel23.setText("R$ 0,00");
        jPanel3.add(jLabel23);
        jLabel23.setBounds(440, 520, 190, 40);

        tabbedPaneServico.addTab("Produtos vendidos", jPanel3);

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setLayout(null);
        tabbedPaneServico.addTab("Pagamentos realizados", jPanel4);

        jPanel1.add(tabbedPaneServico);
        tabbedPaneServico.setBounds(10, 10, 890, 600);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 904, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 618, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnPesquisarClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPesquisarClienteActionPerformed
        FrmPesquisarCliente frm = new FrmPesquisarCliente(this, true);
        frm.setLocationRelativeTo(null);
        frm.setVisible(true);

        cliente = frm.getCliente();

        preencheCliente();
    }//GEN-LAST:event_btnPesquisarClienteActionPerformed

    private void btnPesquisarProdutoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPesquisarProdutoActionPerformed
        preencheProduto();
    }//GEN-LAST:event_btnPesquisarProdutoActionPerformed

    private void txtQuantidadeProdutoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtQuantidadeProdutoKeyTyped
        String caracteres = "1234567890.";

        if (!caracteres.contains(evt.getKeyChar() + "")) {
            evt.consume();
        }
    }//GEN-LAST:event_txtQuantidadeProdutoKeyTyped

    private void txtQuantidadeProdutoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtQuantidadeProdutoKeyReleased
        calculaValorNovoLancamento();
    }//GEN-LAST:event_txtQuantidadeProdutoKeyReleased

    private void btnSalvarServicoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarServicoActionPerformed
        if (cliente.getId() == null) {
            JOptionPane.showMessageDialog(this, "Selecione o cliente para continuar!", "Cliente", JOptionPane.WARNING_MESSAGE);
        } else {
            servico.setIdcliente(cliente);
            servico.setTitulo(txtTitulo.getText());
            servico.setDescricao(txtDescricao.getText());
            //servico.setDataCriacao();
            servico = servicoDao.salvar(servico);
            
            JOptionPane.showMessageDialog(this, "Serviço salvo com sucesso!", "Serviço", JOptionPane.INFORMATION_MESSAGE);
            liberaAbas(true);
        }
    }//GEN-LAST:event_btnSalvarServicoActionPerformed

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

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmServico().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnPesquisarCliente;
    private javax.swing.JButton btnPesquisarProduto;
    private javax.swing.JButton btnSalvarServico;
    private com.toedter.calendar.JDateChooser comboDataCriacao;
    private com.toedter.calendar.JDateChooser comboDataLancamento;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
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
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel lblValorTotal;
    private javax.swing.JLabel lblValorUnitario;
    private javax.swing.JTabbedPane tabbedPaneServico;
    private javax.swing.JTextField txtCodigoGrupo;
    private javax.swing.JTextField txtCodigoProduto;
    private javax.swing.JTextArea txtDescricao;
    private javax.swing.JTextField txtNomeCliente;
    private javax.swing.JTextField txtNomeProduto;
    private javax.swing.JTextField txtQuantidadeProduto;
    private javax.swing.JTextField txtTitulo;
    // End of variables declaration//GEN-END:variables
}
