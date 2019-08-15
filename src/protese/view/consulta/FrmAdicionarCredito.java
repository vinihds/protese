package protese.view.consulta;

import java.time.LocalTime;
import java.util.Date;
import javax.swing.JOptionPane;
import protese.dao.cliente.ClienteCreditoEntradaDao;
import protese.model.cliente.Cliente;
import protese.util.utilidade.Utilidade;

/**
 *
 * @author Vinicius Silveira
 */
public class FrmAdicionarCredito extends javax.swing.JDialog {

    private Cliente cliente;
    private ClienteCreditoEntradaDao creditoEntradaDao = ClienteCreditoEntradaDao.getInstance();
    private Utilidade utilidade = Utilidade.getInstance();

    private FrmAdicionarCredito(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }

    public FrmAdicionarCredito(java.awt.Frame parent, boolean modal, Cliente cliente) {
        super(parent, modal);
        initComponents();

        this.cliente = cliente;
        lblNomeCliente.setText(cliente.getNome());

        comboDataCredito.setDate(new Date());
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        lblNomeCliente = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtValorCredito = new javax.swing.JTextField();
        comboDataCredito = new com.toedter.calendar.JDateChooser();
        jLabel4 = new javax.swing.JLabel();
        btnFechar = new javax.swing.JButton();
        btnAdicionar = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtDescricao = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Adicionar crédito");

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(null);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setText("Data do crédito");
        jPanel1.add(jLabel1);
        jLabel1.setBounds(270, 80, 210, 30);

        lblNomeCliente.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        lblNomeCliente.setText("- - - - - - - - - - - - - - - - - - - - - ");
        jPanel1.add(lblNomeCliente);
        lblNomeCliente.setBounds(10, 40, 500, 30);

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel3.setText("Cliente");
        jPanel1.add(jLabel3);
        jLabel3.setBounds(10, 10, 210, 30);

        txtValorCredito.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txtValorCredito.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtValorCreditoKeyTyped(evt);
            }
        });
        jPanel1.add(txtValorCredito);
        txtValorCredito.setBounds(10, 110, 240, 40);

        comboDataCredito.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jPanel1.add(comboDataCredito);
        comboDataCredito.setBounds(270, 110, 240, 40);

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel4.setText("Descrição");
        jPanel1.add(jLabel4);
        jLabel4.setBounds(10, 160, 240, 30);

        btnFechar.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnFechar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/protese/util/icons/icons8-excluir-25.png"))); // NOI18N
        btnFechar.setText("Cancelar");
        btnFechar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFecharActionPerformed(evt);
            }
        });
        jPanel1.add(btnFechar);
        btnFechar.setBounds(310, 360, 200, 60);

        btnAdicionar.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnAdicionar.setForeground(new java.awt.Color(0, 153, 51));
        btnAdicionar.setText("Adicionar!");
        btnAdicionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdicionarActionPerformed(evt);
            }
        });
        jPanel1.add(btnAdicionar);
        btnAdicionar.setBounds(10, 360, 200, 60);

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel5.setText("Valor do crédito");
        jPanel1.add(jLabel5);
        jLabel5.setBounds(10, 80, 240, 30);

        txtDescricao.setColumns(20);
        txtDescricao.setFont(new java.awt.Font("Monospaced", 0, 18)); // NOI18N
        txtDescricao.setRows(5);
        jScrollPane1.setViewportView(txtDescricao);

        jPanel1.add(jScrollPane1);
        jScrollPane1.setBounds(10, 190, 500, 160);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 518, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 430, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnFecharActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFecharActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnFecharActionPerformed

    private void txtValorCreditoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtValorCreditoKeyTyped
        String caracteres = "1234567890.";

        if (!caracteres.contains(evt.getKeyChar() + "")) {
            evt.consume();
        }
    }//GEN-LAST:event_txtValorCreditoKeyTyped

    private void btnAdicionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdicionarActionPerformed
        if (cliente != null && cliente.getId() != null && cliente.getId() > 0) {

            if (!txtValorCredito.getText().isEmpty()) {
                creditoEntradaDao.salvarClienteCreditoEntrada(cliente, txtDescricao.getText(),
                        Double.parseDouble(txtValorCredito.getText()), utilidade.asLocalDate(comboDataCredito.getDate()).atTime(LocalTime.now()));

                JOptionPane.showMessageDialog(null, "Crédito adicionado com sucesso!", "Atenção!", JOptionPane.INFORMATION_MESSAGE);

                btnFecharActionPerformed(null);
            } else {
                JOptionPane.showMessageDialog(null, "Informe um valor válido para continuar!", "Atenção!", JOptionPane.WARNING_MESSAGE);
            }
        }
    }//GEN-LAST:event_btnAdicionarActionPerformed

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
            java.util.logging.Logger.getLogger(FrmAdicionarCredito.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmAdicionarCredito.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmAdicionarCredito.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmAdicionarCredito.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                FrmAdicionarCredito dialog = new FrmAdicionarCredito(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton btnAdicionar;
    private javax.swing.JButton btnFechar;
    private com.toedter.calendar.JDateChooser comboDataCredito;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblNomeCliente;
    private javax.swing.JTextArea txtDescricao;
    private javax.swing.JTextField txtValorCredito;
    // End of variables declaration//GEN-END:variables
}
