package protese.view.consulta;

import java.awt.Font;
import javax.swing.table.DefaultTableModel;
import protese.dao.cliente.ClienteDao;
import protese.dao.cliente.CreditoDao;
import protese.model.cliente.Cliente;
import protese.model.cliente.Credito;
import protese.util.utilidade.Utilidade;
import protese.view.cliente.FrmPesquisarCliente;

/**
 *
 * @author Vinicius Silveira
 */
public class FrmMovimentacaoCreditoCliente extends javax.swing.JFrame {

    private ClienteDao clienteDao = ClienteDao.getInstance();
    private CreditoDao creditoDao = CreditoDao.getInstance();
    private Utilidade utilidade = Utilidade.getInstance();
    private DefaultTableModel modelo = new DefaultTableModel();

    private Cliente cliente = new Cliente();

    public FrmMovimentacaoCreditoCliente() {
        initComponents();

        tblCliente.getTableHeader().setFont(new Font("Tahoma", Font.BOLD, 18));
        txtNomeCliente.setEditable(false);
    }

    private void preencheTabela() {
        modelo = (DefaultTableModel) tblCliente.getModel();
        modelo.setRowCount(0);

        if (cliente != null && cliente.getId() != null && cliente.getId() > 0) {
            double saldoAtual = cliente.getSaldoAtual();
            lblValorCredito.setText("R$ " + utilidade.decimalFormat(saldoAtual));
            txtNomeCliente.setText(cliente.getNome());

            for (Credito credito : creditoDao.retornaTodosPorCliente(cliente)) {
                modelo.addRow(new Object[]{
                    credito.getId(),
                    credito.getDescricao(),
                    utilidade.sdfTimeStamp(credito.getData()),
                    credito.getTipo().toUpperCase(),
                    "R$ " + utilidade.decimalFormat(credito.getValor())
                });
            }
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblCliente = new javax.swing.JTable();
        btnFechar = new javax.swing.JButton();
        painelCredito = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        lblValorCredito = new javax.swing.JLabel();
        txtNomeCliente = new javax.swing.JTextField();
        btnPesquisar = new javax.swing.JButton();
        btnAdicionarCredito = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Movimentações de créditos");

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(null);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setText("Filtre pelo cliente que deseja");
        jPanel1.add(jLabel1);
        jLabel1.setBounds(10, 10, 590, 20);

        tblCliente.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        tblCliente.setModel(new javax.swing.table.DefaultTableModel(
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
        tblCliente.setRowHeight(35);
        tblCliente.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(tblCliente);
        if (tblCliente.getColumnModel().getColumnCount() > 0) {
            tblCliente.getColumnModel().getColumn(0).setMinWidth(0);
            tblCliente.getColumnModel().getColumn(0).setPreferredWidth(0);
            tblCliente.getColumnModel().getColumn(0).setMaxWidth(0);
            tblCliente.getColumnModel().getColumn(1).setPreferredWidth(450);
            tblCliente.getColumnModel().getColumn(2).setPreferredWidth(150);
            tblCliente.getColumnModel().getColumn(3).setPreferredWidth(250);
            tblCliente.getColumnModel().getColumn(4).setPreferredWidth(150);
        }

        jPanel1.add(jScrollPane1);
        jScrollPane1.setBounds(10, 110, 1040, 500);

        btnFechar.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnFechar.setText("Fechar");
        btnFechar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFecharActionPerformed(evt);
            }
        });
        jPanel1.add(btnFechar);
        btnFechar.setBounds(910, 620, 140, 50);

        painelCredito.setBackground(new java.awt.Color(0, 153, 51));
        painelCredito.setLayout(null);

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Crédito");
        painelCredito.add(jLabel2);
        jLabel2.setBounds(10, 10, 330, 22);

        lblValorCredito.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        lblValorCredito.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblValorCredito.setText("R$ 0,00");
        painelCredito.add(lblValorCredito);
        lblValorCredito.setBounds(11, 40, 330, 40);

        jPanel1.add(painelCredito);
        painelCredito.setBounds(700, 10, 350, 90);

        txtNomeCliente.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jPanel1.add(txtNomeCliente);
        txtNomeCliente.setBounds(10, 30, 590, 50);

        btnPesquisar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/protese/util/icons/icons8-pesquisar-25.png"))); // NOI18N
        btnPesquisar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPesquisarActionPerformed(evt);
            }
        });
        jPanel1.add(btnPesquisar);
        btnPesquisar.setBounds(600, 10, 70, 70);

        btnAdicionarCredito.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnAdicionarCredito.setForeground(new java.awt.Color(0, 153, 51));
        btnAdicionarCredito.setText("Adicionar crédito!");
        btnAdicionarCredito.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdicionarCreditoActionPerformed(evt);
            }
        });
        jPanel1.add(btnAdicionarCredito);
        btnAdicionarCredito.setBounds(10, 620, 240, 50);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 1062, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 683, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnFecharActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFecharActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnFecharActionPerformed

    private void btnPesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPesquisarActionPerformed
        FrmPesquisarCliente frm = new FrmPesquisarCliente(this, true);
        frm.setLocationRelativeTo(null);
        frm.setVisible(true);

        cliente = frm.getCliente();

        preencheTabela();
    }//GEN-LAST:event_btnPesquisarActionPerformed

    private void btnAdicionarCreditoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdicionarCreditoActionPerformed
        if (cliente != null && cliente.getId() != null && cliente.getId() > 0) {
            FrmAdicionarCredito frm = new FrmAdicionarCredito(this, true, cliente);
            frm.setLocationRelativeTo(null);
            frm.setVisible(true);

            preencheTabela();
        }
    }//GEN-LAST:event_btnAdicionarCreditoActionPerformed

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
            java.util.logging.Logger.getLogger(FrmMovimentacaoCreditoCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmMovimentacaoCreditoCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmMovimentacaoCreditoCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmMovimentacaoCreditoCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmMovimentacaoCreditoCliente().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdicionarCredito;
    private javax.swing.JButton btnFechar;
    private javax.swing.JButton btnPesquisar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblValorCredito;
    private javax.swing.JPanel painelCredito;
    private javax.swing.JTable tblCliente;
    private javax.swing.JTextField txtNomeCliente;
    // End of variables declaration//GEN-END:variables
}
