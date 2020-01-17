package protese.view.consulta;

import java.time.LocalDate;
import java.time.LocalDateTime;
import javax.swing.table.DefaultTableModel;
import protese.dao.servico.ServicoDetalheDao;
import protese.model.cliente.Cliente;
import protese.model.servico.ServicoDetalhe;
import protese.util.utilidade.Utilidade;
import protese.view.cliente.FrmPesquisarCliente;

/**
 *
 * @author Vinicius
 */
public class FrmServicosPorCliente extends javax.swing.JFrame {

    private Utilidade utilidade = Utilidade.getInstance();

    private Cliente cliente;

    private DefaultTableModel modelo = new DefaultTableModel();
    private ServicoDetalheDao servicoDetalheDao = ServicoDetalheDao.getInstance();

    public FrmServicosPorCliente() {
        initComponents();

        tblServicos.getTableHeader().setFont(utilidade.FONTE);
        txtNomeCliente.setEditable(false);

        // Setando valores iniciais
        comboDe.setDate(utilidade.asDate(LocalDate.now().withDayOfMonth(1)));
        comboAte.setDate(utilidade.asDate(LocalDate.now().plusMonths(1).withDayOfMonth(1)));

        preencheTabela();
    }

    private void preencheTabela() {
        modelo = (DefaultTableModel) tblServicos.getModel();
        modelo.setRowCount(0);

        LocalDateTime dataDe = utilidade.asLocalDateTime(comboDe.getDate());
        LocalDateTime dataAte = utilidade.asLocalDateTime(comboAte.getDate());
        double valorTotalServicos = 0;

        if (cliente != null && cliente.getId() != null && cliente.getId() > 0) {
            for (ServicoDetalhe servicoDetalhe : servicoDetalheDao.retornaTodosPorClientePorPeriodo(cliente, dataDe, dataAte)) {
                modelo.addRow(new Object[]{servicoDetalhe.getIdprodutoValor().getIdgrupo().getCodigo(),
                    servicoDetalhe.getIdprodutoValor().getIdproduto().getCodigo(),
                    utilidade.mesAno(servicoDetalhe.getIdservico().getDataReferente()).toUpperCase(),
                    servicoDetalhe.getIdprodutoValor().getIdproduto().getNome(),
                    utilidade.sdfTimeStamp(servicoDetalhe.getDataLancamento()),
                    servicoDetalhe.getQuantidade(),
                    utilidade.decimalFormat(servicoDetalhe.getValorTotal())});

                valorTotalServicos += servicoDetalhe.getValorTotal();
            }
        }
        
        lblValorTotal.setText("R$ " + utilidade.decimalFormat(valorTotalServicos));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txtNomeCliente = new javax.swing.JTextField();
        btnPesquisarCliente = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblServicos = new javax.swing.JTable();
        comboAte = new com.toedter.calendar.JDateChooser();
        comboDe = new com.toedter.calendar.JDateChooser();
        lblValorTotal = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        btnFechar = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        btnPesquisarServicos = new javax.swing.JButton();
        btnImprimir = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Serviços por cliente");

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(null);

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel2.setText("Até");
        jPanel1.add(jLabel2);
        jLabel2.setBounds(710, 10, 140, 20);

        txtNomeCliente.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jPanel1.add(txtNomeCliente);
        txtNomeCliente.setBounds(10, 30, 380, 50);

        btnPesquisarCliente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/protese/util/icons/icons8-pesquisar-25.png"))); // NOI18N
        btnPesquisarCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPesquisarClienteActionPerformed(evt);
            }
        });
        jPanel1.add(btnPesquisarCliente);
        btnPesquisarCliente.setBounds(390, 10, 70, 70);

        tblServicos.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        tblServicos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Grupo", "Produto", "Serviço", "Nome", "Lançamento", "Qtd", "Valor total"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblServicos.setRowHeight(40);
        tblServicos.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(tblServicos);
        if (tblServicos.getColumnModel().getColumnCount() > 0) {
            tblServicos.getColumnModel().getColumn(0).setMinWidth(70);
            tblServicos.getColumnModel().getColumn(0).setPreferredWidth(70);
            tblServicos.getColumnModel().getColumn(1).setMinWidth(100);
            tblServicos.getColumnModel().getColumn(1).setPreferredWidth(100);
            tblServicos.getColumnModel().getColumn(2).setMinWidth(100);
            tblServicos.getColumnModel().getColumn(2).setPreferredWidth(100);
            tblServicos.getColumnModel().getColumn(3).setMinWidth(300);
            tblServicos.getColumnModel().getColumn(3).setPreferredWidth(300);
            tblServicos.getColumnModel().getColumn(4).setMinWidth(200);
            tblServicos.getColumnModel().getColumn(4).setPreferredWidth(200);
            tblServicos.getColumnModel().getColumn(5).setMinWidth(70);
            tblServicos.getColumnModel().getColumn(5).setPreferredWidth(70);
            tblServicos.getColumnModel().getColumn(6).setMinWidth(150);
            tblServicos.getColumnModel().getColumn(6).setPreferredWidth(150);
        }

        jPanel1.add(jScrollPane1);
        jScrollPane1.setBounds(10, 100, 1000, 450);

        comboAte.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jPanel1.add(comboAte);
        comboAte.setBounds(710, 30, 210, 50);

        comboDe.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jPanel1.add(comboDe);
        comboDe.setBounds(480, 30, 210, 50);

        lblValorTotal.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        lblValorTotal.setForeground(new java.awt.Color(0, 153, 51));
        lblValorTotal.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblValorTotal.setText("R$ 0,00");
        jPanel1.add(lblValorTotal);
        lblValorTotal.setBounds(790, 550, 220, 40);

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel8.setText("Valor total:");
        jPanel1.add(jLabel8);
        jLabel8.setBounds(670, 550, 140, 40);

        btnFechar.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnFechar.setText("Fechar");
        btnFechar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFecharActionPerformed(evt);
            }
        });
        jPanel1.add(btnFechar);
        btnFechar.setBounds(870, 620, 140, 50);

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel3.setText("Filtre pelo cliente que deseja");
        jPanel1.add(jLabel3);
        jLabel3.setBounds(10, 10, 370, 20);

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel4.setText("De");
        jPanel1.add(jLabel4);
        jLabel4.setBounds(480, 10, 140, 20);

        btnPesquisarServicos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/protese/util/icons/icons8-pesquisar-25.png"))); // NOI18N
        btnPesquisarServicos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPesquisarServicosActionPerformed(evt);
            }
        });
        jPanel1.add(btnPesquisarServicos);
        btnPesquisarServicos.setBounds(940, 10, 70, 70);

        btnImprimir.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnImprimir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/protese/util/icons/icons8-print-50.png"))); // NOI18N
        btnImprimir.setText("Imprimir!");
        btnImprimir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnImprimirActionPerformed(evt);
            }
        });
        jPanel1.add(btnImprimir);
        btnImprimir.setBounds(10, 620, 200, 50);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 1020, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 682, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnPesquisarClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPesquisarClienteActionPerformed
        FrmPesquisarCliente frm = new FrmPesquisarCliente(this, true);
        frm.setLocationRelativeTo(null);
        frm.setVisible(true);

        this.cliente = frm.getCliente();
        txtNomeCliente.setText(cliente.getNome());

        preencheTabela();
    }//GEN-LAST:event_btnPesquisarClienteActionPerformed

    private void btnFecharActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFecharActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnFecharActionPerformed

    private void btnPesquisarServicosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPesquisarServicosActionPerformed
        preencheTabela();
    }//GEN-LAST:event_btnPesquisarServicosActionPerformed

    private void btnImprimirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnImprimirActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnImprimirActionPerformed

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
            java.util.logging.Logger.getLogger(FrmServicosPorCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmServicosPorCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmServicosPorCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmServicosPorCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmServicosPorCliente().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnFechar;
    private javax.swing.JButton btnImprimir;
    private javax.swing.JButton btnPesquisarCliente;
    private javax.swing.JButton btnPesquisarServicos;
    private com.toedter.calendar.JDateChooser comboAte;
    private com.toedter.calendar.JDateChooser comboDe;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblValorTotal;
    private javax.swing.JTable tblServicos;
    private javax.swing.JTextField txtNomeCliente;
    // End of variables declaration//GEN-END:variables
}
