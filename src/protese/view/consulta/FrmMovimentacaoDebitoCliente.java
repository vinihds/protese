package protese.view.consulta;

import java.awt.Font;
import javax.swing.table.DefaultTableModel;
import protese.dao.cliente.ClienteDebitoDao;
import protese.dao.servico.ServicoDebitoDao;
import protese.model.cliente.Cliente;
import protese.model.cliente.ClienteDebito;
import protese.model.servico.ServicoDebito;
import protese.util.utilidade.Utilidade;
import protese.view.cliente.FrmPesquisarCliente;

/**
 *
 * @author Vinicius Silveira
 */
public class FrmMovimentacaoDebitoCliente extends javax.swing.JFrame {

    private Cliente cliente;

    private ClienteDebitoDao clienteDebitoDao = ClienteDebitoDao.getInstance();
    private ServicoDebitoDao servicoDebitoDao = ServicoDebitoDao.getInstance();
    private Utilidade utilidade = Utilidade.getInstance();
    private DefaultTableModel modelo = new DefaultTableModel();

    public FrmMovimentacaoDebitoCliente() {
        initComponents();

        tblDebito.getTableHeader().setFont(utilidade.FONTE);
    }

    private void preencheTabela() {
        modelo = (DefaultTableModel) tblDebito.getModel();
        modelo.setRowCount(0);

        if (cliente != null && cliente.getId() != null && cliente.getId() > 0) {
            txtNomeCliente.setText(cliente.getNome());
            lblValorDebito.setText("R$ " + utilidade.decimalFormat(cliente.getDebitoPendente()));

            for (ClienteDebito debito : clienteDebitoDao.retornaTodosNaoUtilizadosPorCliente(cliente)) {
                modelo.addRow(new Object[]{
                    debito.getId(),
                    debito.getDescricao(),
                    utilidade.mesAno(debito.getData()).toUpperCase(),
                    "- - NÃO AGREGADO - -",
                    "R$ " + utilidade.decimalFormat(debito.getValorDebito())
                });
            }

            modelo.addRow(new Object[5]);

            for (ServicoDebito servicoDebito : servicoDebitoDao.retornaTodosAtivosPorCliente(cliente)) {
                modelo.addRow(new Object[]{
                    servicoDebito.getId(),
                    servicoDebito.getIdclienteDebito().getDescricao(),
                    utilidade.mesAno(servicoDebito.getIdclienteDebito().getData()).toUpperCase(),
                    "AGREGADO - " + utilidade.mesAno(servicoDebito.getIdservico().getDataReferente()).toUpperCase(),
                    "R$ " + utilidade.decimalFormat(servicoDebito.getIdclienteDebito().getValorDebito())
                });
            }
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtNomeCliente = new javax.swing.JTextField();
        btnPesquisar = new javax.swing.JButton();
        painelCredito = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        lblValorDebito = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblDebito = new javax.swing.JTable();
        btnFechar = new javax.swing.JButton();
        btnAdicionarDebito = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Débitos do cliente");

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(null);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setText("Filtre pelo cliente que deseja");
        jPanel1.add(jLabel1);
        jLabel1.setBounds(10, 10, 560, 20);

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

        painelCredito.setBackground(new java.awt.Color(255, 0, 0));
        painelCredito.setLayout(null);

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Débito");
        painelCredito.add(jLabel2);
        jLabel2.setBounds(10, 10, 330, 22);

        lblValorDebito.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        lblValorDebito.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblValorDebito.setText("R$ 0,00");
        painelCredito.add(lblValorDebito);
        lblValorDebito.setBounds(11, 40, 330, 40);

        jPanel1.add(painelCredito);
        painelCredito.setBounds(700, 10, 350, 90);

        tblDebito.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        tblDebito.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "id", "Descrição", "Data", "Status", "Valor"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblDebito.setRowHeight(35);
        tblDebito.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(tblDebito);
        if (tblDebito.getColumnModel().getColumnCount() > 0) {
            tblDebito.getColumnModel().getColumn(0).setMinWidth(0);
            tblDebito.getColumnModel().getColumn(0).setPreferredWidth(0);
            tblDebito.getColumnModel().getColumn(0).setMaxWidth(0);
            tblDebito.getColumnModel().getColumn(1).setPreferredWidth(450);
            tblDebito.getColumnModel().getColumn(2).setPreferredWidth(150);
            tblDebito.getColumnModel().getColumn(3).setPreferredWidth(250);
            tblDebito.getColumnModel().getColumn(4).setPreferredWidth(150);
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

        btnAdicionarDebito.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnAdicionarDebito.setForeground(new java.awt.Color(255, 0, 0));
        btnAdicionarDebito.setText("Adicionar débito!");
        btnAdicionarDebito.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdicionarDebitoActionPerformed(evt);
            }
        });
        jPanel1.add(btnAdicionarDebito);
        btnAdicionarDebito.setBounds(10, 620, 240, 50);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 1058, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 680, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnPesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPesquisarActionPerformed
        FrmPesquisarCliente frm = new FrmPesquisarCliente(this, true);
        frm.setLocationRelativeTo(null);
        frm.setVisible(true);

        cliente = frm.getCliente();

        preencheTabela();
    }//GEN-LAST:event_btnPesquisarActionPerformed

    private void btnFecharActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFecharActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnFecharActionPerformed

    private void btnAdicionarDebitoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdicionarDebitoActionPerformed
        if (cliente != null && cliente.getId() != null && cliente.getId() > 0) {
            FrmAdicionarDebito frm = new FrmAdicionarDebito(this, true, cliente);
            frm.setLocationRelativeTo(null);
            frm.setVisible(true);

            preencheTabela();
        }
    }//GEN-LAST:event_btnAdicionarDebitoActionPerformed

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
            java.util.logging.Logger.getLogger(FrmMovimentacaoDebitoCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmMovimentacaoDebitoCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmMovimentacaoDebitoCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmMovimentacaoDebitoCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmMovimentacaoDebitoCliente().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdicionarDebito;
    private javax.swing.JButton btnFechar;
    private javax.swing.JButton btnPesquisar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblValorDebito;
    private javax.swing.JPanel painelCredito;
    private javax.swing.JTable tblDebito;
    private javax.swing.JTextField txtNomeCliente;
    // End of variables declaration//GEN-END:variables
}
