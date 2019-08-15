package protese.view.util;

import protese.view.cliente.FrmGerenciarCliente;
import javax.swing.JFrame;
import protese.model.servico.Servico;
import protese.view.consulta.FrmMovimentacaoCreditoCliente;
import protese.view.consulta.FrmMovimentacaoDebitoCliente;
import protese.view.consulta.FrmPagamentosPorAno;
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
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        menuCliente = new javax.swing.JMenuItem();
        menuGrupo = new javax.swing.JMenuItem();
        menuProduto = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        menuNovoServico = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        menuGerenciarServico = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        menuMovimentacaoCredito = new javax.swing.JMenuItem();
        jSeparator2 = new javax.swing.JPopupMenu.Separator();
        menuMovimentacaoDebito = new javax.swing.JMenuItem();
        jSeparator3 = new javax.swing.JPopupMenu.Separator();
        menuPagamentosPorAno = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Dentista");

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 288, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 218, Short.MAX_VALUE)
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 288, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 218, Short.MAX_VALUE)
        );

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 288, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 218, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(54, 54, 54)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(54, 54, 54)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(69, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(270, Short.MAX_VALUE))
        );

        jMenuBar1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        jMenu1.setText("Cadastro");
        jMenu1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N

        menuCliente.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        menuCliente.setText("Cliente");
        menuCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuClienteActionPerformed(evt);
            }
        });
        jMenu1.add(menuCliente);

        menuGrupo.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        menuGrupo.setText("Grupo");
        menuGrupo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuGrupoActionPerformed(evt);
            }
        });
        jMenu1.add(menuGrupo);

        menuProduto.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        menuProduto.setText("Produto");
        menuProduto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuProdutoActionPerformed(evt);
            }
        });
        jMenu1.add(menuProduto);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Serviço");
        jMenu2.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N

        menuNovoServico.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        menuNovoServico.setText("Novo serviço");
        menuNovoServico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuNovoServicoActionPerformed(evt);
            }
        });
        jMenu2.add(menuNovoServico);
        jMenu2.add(jSeparator1);

        menuGerenciarServico.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        menuGerenciarServico.setText("Serviços");
        menuGerenciarServico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuGerenciarServicoActionPerformed(evt);
            }
        });
        jMenu2.add(menuGerenciarServico);

        jMenuBar1.add(jMenu2);

        jMenu3.setText("Consultas");
        jMenu3.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N

        menuMovimentacaoCredito.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        menuMovimentacaoCredito.setText("Movimentações de créditos");
        menuMovimentacaoCredito.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuMovimentacaoCreditoActionPerformed(evt);
            }
        });
        jMenu3.add(menuMovimentacaoCredito);
        jMenu3.add(jSeparator2);

        menuMovimentacaoDebito.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        menuMovimentacaoDebito.setText("Movimentações de débitos");
        menuMovimentacaoDebito.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuMovimentacaoDebitoActionPerformed(evt);
            }
        });
        jMenu3.add(menuMovimentacaoDebito);
        jMenu3.add(jSeparator3);

        menuPagamentosPorAno.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        menuPagamentosPorAno.setText("Pagamentos dos clientes por ano");
        menuPagamentosPorAno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuPagamentosPorAnoActionPerformed(evt);
            }
        });
        jMenu3.add(menuPagamentosPorAno);

        jMenuBar1.add(jMenu3);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
        FrmServico frm = new FrmServico(this, true, new Servico());
        frm.setLocationRelativeTo(null);
        frm.setVisible(true);
    }//GEN-LAST:event_menuNovoServicoActionPerformed

    private void menuGerenciarServicoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuGerenciarServicoActionPerformed
        FrmGerenciarServico frm = new FrmGerenciarServico();
        frm.setLocationRelativeTo(null);
        frm.setVisible(true);
    }//GEN-LAST:event_menuGerenciarServicoActionPerformed

    private void menuMovimentacaoCreditoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuMovimentacaoCreditoActionPerformed
        FrmMovimentacaoCreditoCliente frm = new FrmMovimentacaoCreditoCliente();
        frm.setLocationRelativeTo(null);
        frm.setVisible(true);
    }//GEN-LAST:event_menuMovimentacaoCreditoActionPerformed

    private void menuMovimentacaoDebitoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuMovimentacaoDebitoActionPerformed
        FrmMovimentacaoDebitoCliente frm = new FrmMovimentacaoDebitoCliente();
        frm.setLocationRelativeTo(null);
        frm.setVisible(true);
    }//GEN-LAST:event_menuMovimentacaoDebitoActionPerformed

    private void menuPagamentosPorAnoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuPagamentosPorAnoActionPerformed
        FrmPagamentosPorAno frm = new FrmPagamentosPorAno();
        frm.setLocationRelativeTo(null);
        frm.setVisible(true);
    }//GEN-LAST:event_menuPagamentosPorAnoActionPerformed

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
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JPopupMenu.Separator jSeparator2;
    private javax.swing.JPopupMenu.Separator jSeparator3;
    private javax.swing.JMenuItem menuCliente;
    private javax.swing.JMenuItem menuGerenciarServico;
    private javax.swing.JMenuItem menuGrupo;
    private javax.swing.JMenuItem menuMovimentacaoCredito;
    private javax.swing.JMenuItem menuMovimentacaoDebito;
    private javax.swing.JMenuItem menuNovoServico;
    private javax.swing.JMenuItem menuPagamentosPorAno;
    private javax.swing.JMenuItem menuProduto;
    // End of variables declaration//GEN-END:variables
}
