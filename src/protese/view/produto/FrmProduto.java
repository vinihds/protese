package protese.view.produto;

import java.awt.Font;
import java.text.DecimalFormat;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import protese.dao.produto.ProdutoDao;
import protese.dao.produto.ProdutoValorDao;
import protese.model.produto.Produto;
import protese.model.produto.ProdutoValor;

/**
 *
 * @author Vinicius Silveira
 */
public class FrmProduto extends javax.swing.JDialog {

    private ProdutoDao produtoDao = ProdutoDao.getInstance();
    private ProdutoValorDao produtoValorDao = ProdutoValorDao.getInstance();

    private DefaultTableModel modelo = new DefaultTableModel();
    private DecimalFormat decimalFormat = new DecimalFormat("###,###,##0.00");

    private Produto produto = new Produto();

    private FrmProduto(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }

    public FrmProduto(java.awt.Frame parent, boolean modal, Produto produto) {
        super(parent, modal);
        initComponents();

        this.produto = produto;
        
        tblValor.getTableHeader().setFont(new Font("Tahoma", Font.BOLD, 18));

        if (produto.getId() == null) {
            liberaBotoes(false);
        }

        preencheProduto();
        preencheValor();
    }

    private void preencheProduto() {
        txtCodigoProprio.setText(produto.getCodigo());
        txtNome.setText(produto.getNome());
        txtDescricao.setText(produto.getDescricao());
    }

    private void preencheValor() {
        modelo = (DefaultTableModel) tblValor.getModel();
        modelo.setRowCount(0);

        if (produto.getId() != null) {
            for (ProdutoValor produtoValor : produtoValorDao.retornaTodosPorProduto(produto)) {
                modelo.addRow(new Object[]{produtoValor.getId(),
                    produtoValor.getIdgrupo().getNome(),
                    produtoValor.getIdgrupo().getCodigo() + produto.getCodigo(),
                    "R$ " + decimalFormat.format(produtoValor.getValor())});
            }
        }
    }

    private void liberaBotoes(boolean libera) {
        btnNovoValor.setEnabled(libera);
        btnAlterarValor.setEnabled(libera);
        btnExcluirValor.setEnabled(libera);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtCodigoProprio = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtNome = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtDescricao = new javax.swing.JTextArea();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblValor = new javax.swing.JTable();
        btnExcluirValor = new javax.swing.JButton();
        btnNovoValor = new javax.swing.JButton();
        btnFechar = new javax.swing.JButton();
        btnSalvar = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        btnAlterarValor = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Produto");

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(null);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setText("Código próprio");
        jPanel1.add(jLabel1);
        jLabel1.setBounds(10, 10, 160, 20);

        txtCodigoProprio.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jPanel1.add(txtCodigoProprio);
        txtCodigoProprio.setBounds(10, 30, 160, 40);

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel2.setText("Valores");
        jPanel1.add(jLabel2);
        jLabel2.setBounds(10, 270, 160, 20);

        txtNome.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jPanel1.add(txtNome);
        txtNome.setBounds(200, 30, 510, 40);

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel3.setText("Nome");
        jPanel1.add(jLabel3);
        jLabel3.setBounds(200, 10, 160, 20);

        txtDescricao.setColumns(20);
        txtDescricao.setFont(new java.awt.Font("Monospaced", 0, 18)); // NOI18N
        txtDescricao.setRows(5);
        jScrollPane1.setViewportView(txtDescricao);

        jPanel1.add(jScrollPane1);
        jScrollPane1.setBounds(10, 110, 700, 130);

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel4.setText("Descrição");
        jPanel1.add(jLabel4);
        jLabel4.setBounds(10, 90, 160, 20);

        tblValor.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        tblValor.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Grupo", "Código próprio", "Valor"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblValor.setRowHeight(35);
        tblValor.getTableHeader().setReorderingAllowed(false);
        jScrollPane2.setViewportView(tblValor);
        if (tblValor.getColumnModel().getColumnCount() > 0) {
            tblValor.getColumnModel().getColumn(0).setMinWidth(0);
            tblValor.getColumnModel().getColumn(0).setPreferredWidth(0);
            tblValor.getColumnModel().getColumn(0).setMaxWidth(0);
        }

        jPanel1.add(jScrollPane2);
        jScrollPane2.setBounds(10, 290, 650, 180);

        btnExcluirValor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/protese/util/icons/icons8-excluir-25.png"))); // NOI18N
        btnExcluirValor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcluirValorActionPerformed(evt);
            }
        });
        jPanel1.add(btnExcluirValor);
        btnExcluirValor.setBounds(660, 390, 50, 50);

        btnNovoValor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/protese/util/icons/icons8-soma-25.png"))); // NOI18N
        btnNovoValor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNovoValorActionPerformed(evt);
            }
        });
        jPanel1.add(btnNovoValor);
        btnNovoValor.setBounds(660, 290, 50, 50);

        btnFechar.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnFechar.setText("Fechar");
        btnFechar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFecharActionPerformed(evt);
            }
        });
        jPanel1.add(btnFechar);
        btnFechar.setBounds(570, 480, 140, 40);

        btnSalvar.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnSalvar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/protese/util/icons/icons8-selecionado-25.png"))); // NOI18N
        btnSalvar.setText("Salvar");
        btnSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalvarActionPerformed(evt);
            }
        });
        jPanel1.add(btnSalvar);
        btnSalvar.setBounds(10, 480, 140, 40);
        jPanel1.add(jSeparator1);
        jSeparator1.setBounds(10, 260, 700, 10);

        btnAlterarValor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/protese/util/icons/icons8-editar-25.png"))); // NOI18N
        btnAlterarValor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAlterarValorActionPerformed(evt);
            }
        });
        jPanel1.add(btnAlterarValor);
        btnAlterarValor.setBounds(660, 340, 50, 50);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 724, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 535, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnExcluirValorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluirValorActionPerformed
        int[] rows = tblValor.getSelectedRows();

        if (rows.length > 0) {
            if (JOptionPane.showConfirmDialog(
                    this,
                    "Deseja realmente excluir estes Valores?",
                    "Valores",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
                try {
                    ProdutoValor produtoValor;

                    for (int i = 0; i < rows.length; i++) {
                        produtoValor = produtoValorDao.consultarId(ProdutoValor.class, Long.parseLong(modelo.getValueAt(rows[i], 0).toString()));

                        produtoValorDao.deletar(produtoValor);
                    }

                    JOptionPane.showMessageDialog(this, "Valores excluídos com sucesso!", "Valor", JOptionPane.INFORMATION_MESSAGE);
                    preencheValor();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "Selecione o valor para continuar!", "Valor", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_btnExcluirValorActionPerformed

    private void btnNovoValorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNovoValorActionPerformed
        ProdutoValor produtoValor = new ProdutoValor();
        produtoValor.setIdproduto(produto);

        FrmProdutoValor frm = new FrmProdutoValor(null, true, produtoValor);
        frm.setLocationRelativeTo(null);
        frm.setVisible(true);

        preencheValor();
    }//GEN-LAST:event_btnNovoValorActionPerformed

    private void btnFecharActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFecharActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnFecharActionPerformed

    private void btnSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarActionPerformed
        produto.setCodigo(txtCodigoProprio.getText());
        produto.setNome(txtNome.getText());
        produto.setDescricao(txtDescricao.getText());
        produto = produtoDao.salvar(produto);

        if (produto.getId() != null) {
            JOptionPane.showMessageDialog(this, "Produto salvo com sucesso!", "Produto", JOptionPane.INFORMATION_MESSAGE);

            liberaBotoes(true);
            preencheValor();
        }
    }//GEN-LAST:event_btnSalvarActionPerformed

    private void btnAlterarValorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAlterarValorActionPerformed
        int[] rows = tblValor.getSelectedRows();

        if (rows.length > 0) {
            try {
                ProdutoValor produtoValor = produtoValorDao.consultarId(ProdutoValor.class, Long.parseLong(modelo.getValueAt(rows[0], 0).toString()));

                FrmProdutoValor frm = new FrmProdutoValor(null, true, produtoValor);
                frm.setLocationRelativeTo(null);
                frm.setVisible(true);

                preencheValor();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            JOptionPane.showMessageDialog(this, "Selecione o valor para continuar!", "Valor", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_btnAlterarValorActionPerformed

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
            java.util.logging.Logger.getLogger(FrmProduto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmProduto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmProduto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmProduto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                FrmProduto dialog = new FrmProduto(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton btnAlterarValor;
    private javax.swing.JButton btnExcluirValor;
    private javax.swing.JButton btnFechar;
    private javax.swing.JButton btnNovoValor;
    private javax.swing.JButton btnSalvar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTable tblValor;
    private javax.swing.JTextField txtCodigoProprio;
    private javax.swing.JTextArea txtDescricao;
    private javax.swing.JTextField txtNome;
    // End of variables declaration//GEN-END:variables
}
