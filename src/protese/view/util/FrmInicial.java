package protese.view.util;

import protese.view.cliente.FrmGerenciarCliente;
import javax.swing.JFrame;
import protese.model.servico.Servico;
import protese.view.produto.FrmGerenciarGrupo;
import protese.view.produto.FrmGerenciarProduto;
import protese.view.servico.FrmGerenciarServico;
import protese.view.servico.FrmServico;

/**
 *
 * @author vinihds
 */
public class FrmInicial extends javax.swing.JFrame {

    public FrmInicial() {
        initComponents();

        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        menuCliente = new javax.swing.JMenuItem();
        menuGrupo = new javax.swing.JMenuItem();
        menuProduto = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        menuNovoServico = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        menuGerenciarServico = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Dentista");

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(null);

        jMenuBar1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jMenu1.setText("Cadastro");
        jMenu1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N

        menuCliente.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        menuCliente.setText("Cliente");
        menuCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuClienteActionPerformed(evt);
            }
        });
        jMenu1.add(menuCliente);

        menuGrupo.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        menuGrupo.setText("Grupo");
        menuGrupo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuGrupoActionPerformed(evt);
            }
        });
        jMenu1.add(menuGrupo);

        menuProduto.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        menuProduto.setText("Produto");
        menuProduto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuProdutoActionPerformed(evt);
            }
        });
        jMenu1.add(menuProduto);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Serviço");
        jMenu2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N

        menuNovoServico.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        menuNovoServico.setText("Novo serviço");
        menuNovoServico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuNovoServicoActionPerformed(evt);
            }
        });
        jMenu2.add(menuNovoServico);
        jMenu2.add(jSeparator1);

        menuGerenciarServico.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        menuGerenciarServico.setText("Serviços");
        menuGerenciarServico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuGerenciarServicoActionPerformed(evt);
            }
        });
        jMenu2.add(menuGerenciarServico);

        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 719, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 495, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void menuClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuClienteActionPerformed
        FrmGerenciarCliente frm = new FrmGerenciarCliente();
        frm.setLocationRelativeTo(null);
        frm.setVisible(true);
    }//GEN-LAST:event_menuClienteActionPerformed

    private void menuGrupoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuGrupoActionPerformed
        FrmGerenciarGrupo frm = new FrmGerenciarGrupo();
        frm.setLocationRelativeTo(null);
        frm.setVisible(true);
    }//GEN-LAST:event_menuGrupoActionPerformed

    private void menuProdutoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuProdutoActionPerformed
        FrmGerenciarProduto frm = new FrmGerenciarProduto();
        frm.setLocationRelativeTo(null);
        frm.setVisible(true);
    }//GEN-LAST:event_menuProdutoActionPerformed

    private void menuNovoServicoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuNovoServicoActionPerformed
        FrmServico frm = new FrmServico(new Servico());
        frm.setLocationRelativeTo(null);
        frm.setVisible(true);
    }//GEN-LAST:event_menuNovoServicoActionPerformed

    private void menuGerenciarServicoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuGerenciarServicoActionPerformed
        FrmGerenciarServico frm = new FrmGerenciarServico();
        frm.setLocationRelativeTo(null);
        frm.setVisible(true);
    }//GEN-LAST:event_menuGerenciarServicoActionPerformed

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
            java.util.logging.Logger.getLogger(FrmInicial.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmInicial.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmInicial.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmInicial.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmInicial().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JMenuItem menuCliente;
    private javax.swing.JMenuItem menuGerenciarServico;
    private javax.swing.JMenuItem menuGrupo;
    private javax.swing.JMenuItem menuNovoServico;
    private javax.swing.JMenuItem menuProduto;
    // End of variables declaration//GEN-END:variables
}
