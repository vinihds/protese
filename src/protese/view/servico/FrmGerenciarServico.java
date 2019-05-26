package protese.view.servico;

import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import protese.dao.servico.ServicoDao;
import protese.model.servico.Servico;
import protese.util.utilidade.Utilidade;

/**
 *
 * @author vinihds
 */
public class FrmGerenciarServico extends javax.swing.JFrame {

    private ServicoDao servicoDao = ServicoDao.getInstance();

    private Utilidade utilidade = Utilidade.getInstance();
    private DefaultTableModel modelo = new DefaultTableModel();

    public FrmGerenciarServico() {
        initComponents();

        preencherTabela(servicoDao.retornaTodos());
    }

    private void preencherTabela(List<Servico> servicoList) {
        modelo = (DefaultTableModel) tblServicos.getModel();
        modelo.setRowCount(0);

        for (Servico servico : servicoList) {
            modelo.addRow(new Object[]{
                servico.getId(),
                servico.getTitulo(),
                servico.getIdcliente().getNome(),
                utilidade.sdfTimeStamp(servico.getDataCriacao()),
                servico.getDataFinalizacao() != null ? utilidade.sdfTimeStamp(servico.getDataFinalizacao()) : "",
                "R$ " + utilidade.decimalFormat(servico.getValorTotalServico())
            });
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblServicos = new javax.swing.JTable();
        btnNovo = new javax.swing.JButton();
        btnAlterar = new javax.swing.JButton();
        btnExcluir = new javax.swing.JButton();
        btnFechar = new javax.swing.JButton();
        comboPesquisa = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        txtPesquisa = new javax.swing.JTextField();
        btnPesquisar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Gerenciar serviços");

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(null);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setText("Pesquisar por");
        jPanel1.add(jLabel1);
        jLabel1.setBounds(10, 10, 140, 20);

        tblServicos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Titulo", "Cliente", "Data criação", "Data finalização", "Valor total"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblServicos.setRowHeight(30);
        tblServicos.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(tblServicos);
        if (tblServicos.getColumnModel().getColumnCount() > 0) {
            tblServicos.getColumnModel().getColumn(0).setMinWidth(0);
            tblServicos.getColumnModel().getColumn(0).setPreferredWidth(0);
            tblServicos.getColumnModel().getColumn(0).setMaxWidth(0);
        }

        jPanel1.add(jScrollPane1);
        jScrollPane1.setBounds(10, 80, 940, 500);

        btnNovo.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnNovo.setText("Novo");
        btnNovo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNovoActionPerformed(evt);
            }
        });
        jPanel1.add(btnNovo);
        btnNovo.setBounds(10, 590, 140, 40);

        btnAlterar.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnAlterar.setText("Alterar");
        btnAlterar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAlterarActionPerformed(evt);
            }
        });
        jPanel1.add(btnAlterar);
        btnAlterar.setBounds(160, 590, 140, 40);

        btnExcluir.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnExcluir.setText("Excluir");
        btnExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcluirActionPerformed(evt);
            }
        });
        jPanel1.add(btnExcluir);
        btnExcluir.setBounds(310, 590, 140, 40);

        btnFechar.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnFechar.setText("Fechar");
        btnFechar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFecharActionPerformed(evt);
            }
        });
        jPanel1.add(btnFechar);
        btnFechar.setBounds(810, 590, 140, 40);

        comboPesquisa.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        comboPesquisa.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "<Todos>", "Cliente", "Titulo/Descrição" }));
        jPanel1.add(comboPesquisa);
        comboPesquisa.setBounds(10, 30, 210, 40);

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setText("Pesquisa");
        jPanel1.add(jLabel2);
        jLabel2.setBounds(230, 10, 220, 20);

        txtPesquisa.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jPanel1.add(txtPesquisa);
        txtPesquisa.setBounds(230, 30, 650, 40);

        btnPesquisar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/protese/util/icons/icons8-pesquisar-25.png"))); // NOI18N
        btnPesquisar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPesquisarActionPerformed(evt);
            }
        });
        jPanel1.add(btnPesquisar);
        btnPesquisar.setBounds(890, 10, 60, 60);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 962, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 638, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAlterarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAlterarActionPerformed
        int[] rows = tblServicos.getSelectedRows();

        if (rows.length > 0) {
            try {
                Servico servico = servicoDao.consultarId(Servico.class, Long.parseLong(tblServicos.getValueAt(rows[0], 0).toString()));

                FrmServico frm = new FrmServico(servico);
                frm.setLocationRelativeTo(null);
                frm.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }//GEN-LAST:event_btnAlterarActionPerformed

    private void btnExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluirActionPerformed
        int[] rows = tblServicos.getSelectedRows();

        if (rows.length > 0) {
            if (JOptionPane.showConfirmDialog(
                    this,
                    "Deseja realmente excluir estes serviços?",
                    "Serviço",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {

                Servico servico;

                try {
                    for (int i = 0; i < rows.length; i++) {
                        servico = servicoDao.consultarId(Servico.class, Long.parseLong(tblServicos.getValueAt(rows[i], 0).toString()));
                        servicoDao.deletar(servico);
                    }

                    JOptionPane.showMessageDialog(this, "Serviços excluidos com sucesso!", "Serviço", JOptionPane.INFORMATION_MESSAGE);

                    preencherTabela(servicoDao.retornaTodos());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "Selecione os serviços para continuar!", "Serviço", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_btnExcluirActionPerformed

    private void btnNovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNovoActionPerformed
        FrmServico frm = new FrmServico(new Servico());
        frm.setLocationRelativeTo(null);
        frm.setVisible(true);
    }//GEN-LAST:event_btnNovoActionPerformed

    private void btnFecharActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFecharActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnFecharActionPerformed

    private void btnPesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPesquisarActionPerformed
        switch (comboPesquisa.getSelectedIndex()) {
            case 0:
                preencherTabela(servicoDao.retornaTodos());
            default:
                preencherTabela(servicoDao.retornaTodos());
        }
    }//GEN-LAST:event_btnPesquisarActionPerformed

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
            java.util.logging.Logger.getLogger(FrmGerenciarServico.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmGerenciarServico.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmGerenciarServico.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmGerenciarServico.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmGerenciarServico().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAlterar;
    private javax.swing.JButton btnExcluir;
    private javax.swing.JButton btnFechar;
    private javax.swing.JButton btnNovo;
    private javax.swing.JButton btnPesquisar;
    private javax.swing.JComboBox<String> comboPesquisa;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblServicos;
    private javax.swing.JTextField txtPesquisa;
    // End of variables declaration//GEN-END:variables
}
