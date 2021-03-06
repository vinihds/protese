package protese.view.produto;

import java.awt.Font;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import protese.dao.produto.ProdutoValorDao;
import protese.model.produto.ProdutoValor;
import protese.util.utilidade.Utilidade;

/**
 *
 * @author vinihds
 */
public class FrmPesquisarProdutoValor extends javax.swing.JDialog {

    private ProdutoValorDao produtoValorDao = ProdutoValorDao.getInstance();
    private Utilidade utilidade = Utilidade.getInstance();
    private DefaultTableModel modelo = new DefaultTableModel();

    private ProdutoValor produtoValor = new ProdutoValor();

    public FrmPesquisarProdutoValor(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();

        tblProdutoValor.getTableHeader().setFont(utilidade.FONTE);
        preencherTabela(produtoValorDao.retornaTodos());
    }

    private void preencherTabela(List<ProdutoValor> produtoValorList) {
        modelo = (DefaultTableModel) tblProdutoValor.getModel();
        modelo.setRowCount(0);

        for (ProdutoValor produtoValor : produtoValorList) {
            modelo.addRow(new Object[]{produtoValor.getId(),
                produtoValor.getIdgrupo().getCodigo(),
                produtoValor.getIdproduto().getCodigo(),
                produtoValor.getIdproduto().getNome(),
                "R$ " + utilidade.decimalFormat(produtoValor.getValor())});
        }
    }

    public ProdutoValor getProdutoValor() {
        return this.produtoValor;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        txtPesquisa = new javax.swing.JTextField();
        btnPesquisar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblProdutoValor = new javax.swing.JTable();
        btnFechar = new javax.swing.JButton();
        btnSelecionar = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Pesquisa produto");

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(null);

        txtPesquisa.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txtPesquisa.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPesquisaKeyReleased(evt);
            }
        });
        jPanel1.add(txtPesquisa);
        txtPesquisa.setBounds(10, 30, 640, 40);

        btnPesquisar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/protese/util/icons/icons8-pesquisar-25.png"))); // NOI18N
        btnPesquisar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPesquisarActionPerformed(evt);
            }
        });
        jPanel1.add(btnPesquisar);
        btnPesquisar.setBounds(660, 10, 60, 60);

        tblProdutoValor.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        tblProdutoValor.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Cod Grupo", "Cod Produto", "Nome", "Valor"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblProdutoValor.setRowHeight(35);
        tblProdutoValor.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(tblProdutoValor);
        if (tblProdutoValor.getColumnModel().getColumnCount() > 0) {
            tblProdutoValor.getColumnModel().getColumn(0).setMinWidth(0);
            tblProdutoValor.getColumnModel().getColumn(0).setPreferredWidth(0);
            tblProdutoValor.getColumnModel().getColumn(0).setMaxWidth(0);
            tblProdutoValor.getColumnModel().getColumn(1).setPreferredWidth(120);
            tblProdutoValor.getColumnModel().getColumn(2).setPreferredWidth(120);
            tblProdutoValor.getColumnModel().getColumn(3).setPreferredWidth(300);
            tblProdutoValor.getColumnModel().getColumn(4).setPreferredWidth(160);
        }

        jPanel1.add(jScrollPane1);
        jScrollPane1.setBounds(10, 80, 710, 260);

        btnFechar.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnFechar.setText("Fechar");
        btnFechar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFecharActionPerformed(evt);
            }
        });
        jPanel1.add(btnFechar);
        btnFechar.setBounds(580, 350, 140, 40);

        btnSelecionar.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnSelecionar.setText("Selecionar");
        btnSelecionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSelecionarActionPerformed(evt);
            }
        });
        jPanel1.add(btnSelecionar);
        btnSelecionar.setBounds(10, 350, 140, 40);

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel2.setText("Pesquisar por nome, código próprio ou grupo");
        jPanel1.add(jLabel2);
        jLabel2.setBounds(10, 10, 580, 20);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 729, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnPesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPesquisarActionPerformed
        try {
            preencherTabela(produtoValorDao.retornaTodosPorNomeOuCodigoProprioOuGrupo(txtPesquisa.getText()));
        } catch (Exception e) {
            preencherTabela(produtoValorDao.retornaTodos());

            e.printStackTrace();
        }
    }//GEN-LAST:event_btnPesquisarActionPerformed

    private void btnFecharActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFecharActionPerformed
        produtoValor = new ProdutoValor();

        this.dispose();
    }//GEN-LAST:event_btnFecharActionPerformed

    private void btnSelecionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSelecionarActionPerformed
        int[] rows = tblProdutoValor.getSelectedRows();

        if (rows.length > 0) {
            try {
                produtoValor = produtoValorDao.consultarId(ProdutoValor.class, Long.parseLong(modelo.getValueAt(rows[0], 0).toString()));

                this.dispose();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }//GEN-LAST:event_btnSelecionarActionPerformed

    private void txtPesquisaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPesquisaKeyReleased
        btnPesquisarActionPerformed(null);
    }//GEN-LAST:event_txtPesquisaKeyReleased

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
            java.util.logging.Logger.getLogger(FrmPesquisarProdutoValor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmPesquisarProdutoValor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmPesquisarProdutoValor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmPesquisarProdutoValor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                FrmPesquisarProdutoValor dialog = new FrmPesquisarProdutoValor(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton btnFechar;
    private javax.swing.JButton btnPesquisar;
    private javax.swing.JButton btnSelecionar;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblProdutoValor;
    private javax.swing.JTextField txtPesquisa;
    // End of variables declaration//GEN-END:variables
}
