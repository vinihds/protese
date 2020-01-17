package protese.view.consulta;

import java.awt.Font;
import java.time.LocalDateTime;
import javax.swing.table.DefaultTableModel;
import protese.dao.pagamento.PagamentoDao;
import protese.model.cliente.Cliente;
import protese.model.pagamento.PagamentoPorMes;
import protese.util.utilidade.Utilidade;
import protese.view.cliente.FrmPesquisarCliente;

/**
 *
 * @author Vinicius Silveira
 */
public class FrmPagamentosPorAno extends javax.swing.JFrame {

    private PagamentoDao pagamentoDao = PagamentoDao.getInstance();
    private Utilidade utilidade = Utilidade.getInstance();

    private Cliente cliente;

    private DefaultTableModel modelo = new DefaultTableModel();

    public FrmPagamentosPorAno() {
        initComponents();

        tblPagamentos.getTableHeader().setFont(utilidade.FONTE);
        txtNomeCliente.setEditable(false);

        preencheAnos();
    }

    private void preencheAnos() {
        LocalDateTime hoje = LocalDateTime.now();

        for (int i = 2018; i <= hoje.getYear() + 1; i++) {
            comboAno.addItem("" + i);
        }

        comboAno.setSelectedItem(String.valueOf(hoje.getYear()));
    }

    private void preencheTabela() {
        modelo = (DefaultTableModel) tblPagamentos.getModel();
        modelo.setRowCount(0);

        double valorTotalPago = 0;
        
        if (comboAno.getSelectedItem() != null) {
            int ano = Integer.parseInt(comboAno.getSelectedItem().toString());

            if (cliente != null && cliente.getId() != null && cliente.getId() > 0) {
                Object[] valores = new Object[12];
                int index = 0;

                for (PagamentoPorMes porMes : pagamentoDao.listagemPagamentoPorAno(cliente, ano).getPagamentoPorMesList()) {
                    valores[index] = utilidade.decimalFormat(porMes.getValor());
                    valorTotalPago += porMes.getValor();
                    index++;
                }

                modelo.addRow(valores);
            }
        }

        lblValorTotalPago.setText("R$ " + utilidade.decimalFormat(valorTotalPago));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblPagamentos = new javax.swing.JTable();
        btnFechar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        txtNomeCliente = new javax.swing.JTextField();
        btnPesquisarCliente = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        comboAno = new javax.swing.JComboBox<>();
        btnImprimir = new javax.swing.JButton();
        lblValorTotalPago = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Pagamentos por ano");

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(null);

        tblPagamentos.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        tblPagamentos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "JAN", "FEV", "MAR", "ABR", "MAI", "JUN", "JUL", "AGO", "SET", "OUT", "NOV", "DEZ"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblPagamentos.setRowHeight(40);
        tblPagamentos.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(tblPagamentos);

        jPanel1.add(jScrollPane1);
        jScrollPane1.setBounds(10, 100, 1040, 450);

        btnFechar.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnFechar.setText("Fechar");
        btnFechar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFecharActionPerformed(evt);
            }
        });
        jPanel1.add(btnFechar);
        btnFechar.setBounds(910, 620, 140, 50);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setText("Filtre pelo ano dos pagamentos");
        jPanel1.add(jLabel1);
        jLabel1.setBounds(750, 10, 310, 20);

        txtNomeCliente.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jPanel1.add(txtNomeCliente);
        txtNomeCliente.setBounds(10, 30, 640, 50);

        btnPesquisarCliente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/protese/util/icons/icons8-pesquisar-25.png"))); // NOI18N
        btnPesquisarCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPesquisarClienteActionPerformed(evt);
            }
        });
        jPanel1.add(btnPesquisarCliente);
        btnPesquisarCliente.setBounds(650, 10, 70, 70);

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel2.setText("Filtre pelo cliente que deseja");
        jPanel1.add(jLabel2);
        jLabel2.setBounds(10, 10, 500, 20);

        comboAno.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        comboAno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboAnoActionPerformed(evt);
            }
        });
        jPanel1.add(comboAno);
        comboAno.setBounds(750, 30, 300, 50);

        btnImprimir.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnImprimir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/protese/util/icons/icons8-print-50.png"))); // NOI18N
        btnImprimir.setText("Imprimir!");
        jPanel1.add(btnImprimir);
        btnImprimir.setBounds(10, 620, 200, 50);

        lblValorTotalPago.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        lblValorTotalPago.setForeground(new java.awt.Color(0, 153, 51));
        lblValorTotalPago.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblValorTotalPago.setText("R$ 0,00");
        jPanel1.add(lblValorTotalPago);
        lblValorTotalPago.setBounds(830, 550, 220, 40);

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel8.setText("Valor total:");
        jPanel1.add(jLabel8);
        jLabel8.setBounds(710, 550, 140, 40);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 1062, Short.MAX_VALUE)
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

    private void comboAnoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboAnoActionPerformed
        preencheTabela();
    }//GEN-LAST:event_comboAnoActionPerformed

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
            java.util.logging.Logger.getLogger(FrmPagamentosPorAno.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmPagamentosPorAno.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmPagamentosPorAno.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmPagamentosPorAno.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmPagamentosPorAno().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnFechar;
    private javax.swing.JButton btnImprimir;
    private javax.swing.JButton btnPesquisarCliente;
    private javax.swing.JComboBox<String> comboAno;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblValorTotalPago;
    private javax.swing.JTable tblPagamentos;
    private javax.swing.JTextField txtNomeCliente;
    // End of variables declaration//GEN-END:variables
}
