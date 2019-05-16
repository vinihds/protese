package protese.view.produto;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import protese.dao.produto.GrupoDao;
import protese.dao.produto.ProdutoValorDao;
import protese.model.produto.Grupo;
import protese.model.produto.ProdutoValor;

/**
 *
 * @author vinihds
 */
public class FrmProdutoValor extends javax.swing.JDialog {

    private ProdutoValorDao produtoValorDao = ProdutoValorDao.getInstance();
    private GrupoDao grupoDao = GrupoDao.getInstance();

    private ProdutoValor produtoValor = new ProdutoValor();
    private List<Grupo> grupoList = new ArrayList();

    private FrmProdutoValor(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }

    public FrmProdutoValor(java.awt.Frame parent, boolean modal, ProdutoValor produtoValor) {
        super(parent, modal);
        initComponents();

        this.produtoValor = produtoValor;

        preencheGrupo();
        preencheProdutoValor();
    }

    private void preencheProdutoValor() {
        lblNomeProduto.setText(produtoValor.getIdproduto().getNome());

        if (produtoValor.getId() != null && produtoValor.getId() > 0) {
            txtValor.setText(produtoValor.getValor().toString());

            for (int i = 0; i < grupoList.size(); i++) {
                if (grupoList.get(i).getId() == produtoValor.getIdgrupo().getId()) {
                    comboGrupo.setSelectedIndex(i);

                    break;
                }
            }
        }
    }

    private void preencheGrupo() {
        grupoList = grupoDao.retornaTodos();

        for (Grupo grupo : grupoList) {
            comboGrupo.addItem(grupo.getNome() + " (" + grupo.getCodigo() + ")");
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        lblNomeProduto = new javax.swing.JLabel();
        btnSalvar = new javax.swing.JButton();
        btnFechar = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        txtValor = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        comboGrupo = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Valor do produto");

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(null);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setText("Produto");
        jPanel1.add(jLabel1);
        jLabel1.setBounds(10, 10, 160, 20);

        lblNomeProduto.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblNomeProduto.setText("- - - - - - - - - - - - - - - - - - - - -");
        jPanel1.add(lblNomeProduto);
        lblNomeProduto.setBounds(10, 30, 380, 30);

        btnSalvar.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnSalvar.setText("Salvar");
        btnSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalvarActionPerformed(evt);
            }
        });
        jPanel1.add(btnSalvar);
        btnSalvar.setBounds(10, 160, 140, 40);

        btnFechar.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnFechar.setText("Fechar");
        btnFechar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFecharActionPerformed(evt);
            }
        });
        jPanel1.add(btnFechar);
        btnFechar.setBounds(250, 160, 140, 40);

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setText("Valor");
        jPanel1.add(jLabel3);
        jLabel3.setBounds(210, 70, 140, 20);

        txtValor.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtValor.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtValorKeyTyped(evt);
            }
        });
        jPanel1.add(txtValor);
        txtValor.setBounds(210, 90, 180, 40);

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel4.setText("Grupo");
        jPanel1.add(jLabel4);
        jLabel4.setBounds(10, 70, 170, 20);

        comboGrupo.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jPanel1.add(comboGrupo);
        comboGrupo.setBounds(10, 90, 180, 40);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 401, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 212, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnFecharActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFecharActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnFecharActionPerformed

    private void btnSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarActionPerformed
        if (comboGrupo.getItemCount() == 0) {
            JOptionPane.showMessageDialog(this, "Cadastre os grupos antes de continuar!", "Grupo", JOptionPane.WARNING_MESSAGE);
        } else if (txtValor.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Informe um valor válido para continuar!", "Valor", JOptionPane.WARNING_MESSAGE);
        } else {
            Grupo grupo = grupoList.get(comboGrupo.getSelectedIndex());

            produtoValor.setIdgrupo(grupo);
            produtoValor.setValor(Double.parseDouble(txtValor.getText()));
            produtoValorDao.salvar(produtoValor);

            JOptionPane.showMessageDialog(this, "Valor salvo com sucesso!", "Valor", JOptionPane.INFORMATION_MESSAGE);
            btnFecharActionPerformed(null);
        }
    }//GEN-LAST:event_btnSalvarActionPerformed

    private void txtValorKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtValorKeyTyped
        String caracteres = "1234567890.";

        if (!caracteres.contains(evt.getKeyChar() + "")) {
            evt.consume();
        }
    }//GEN-LAST:event_txtValorKeyTyped

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
            java.util.logging.Logger.getLogger(FrmProdutoValor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmProdutoValor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmProdutoValor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmProdutoValor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                FrmProdutoValor dialog = new FrmProdutoValor(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton btnSalvar;
    private javax.swing.JComboBox<String> comboGrupo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lblNomeProduto;
    private javax.swing.JTextField txtValor;
    // End of variables declaration//GEN-END:variables
}
