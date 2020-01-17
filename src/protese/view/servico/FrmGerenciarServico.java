package protese.view.servico;

import java.awt.Font;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import protese.dao.cliente.ClienteDao;
import protese.dao.servico.ServicoDao;
import protese.model.cliente.Cliente;
import protese.model.servico.Servico;
import protese.util.utilidade.Impressao;
import protese.util.utilidade.Utilidade;
import protese.view.cliente.FrmPesquisarCliente;

/**
 *
 * @author vinihds
 */
public class FrmGerenciarServico extends javax.swing.JFrame {

    private ServicoDao servicoDao = ServicoDao.getInstance();
    private ClienteDao clienteDao = ClienteDao.getInstance();

    private Impressao impressao = Impressao.getInstance();
    private Utilidade utilidade = Utilidade.getInstance();
    private DefaultTableModel modelo = new DefaultTableModel();
    private List<Cliente> clienteList = new ArrayList();

    private Cliente cliente;
    private HashMap parametros = new HashMap();

    public FrmGerenciarServico() {
        initComponents();

        clienteList = clienteDao.retornaTodos();

        txtNomeCliente.setEditable(false);
        preencheMeses();
        preencheAnos();

        tblServicos.getTableHeader().setFont(utilidade.FONTE);

        btnPesquisarActionPerformed(null);
    }

    private void preencheMeses() {
        LocalDateTime hoje = LocalDateTime.now();

        comboReferenteMesInicial.setSelectedIndex(hoje.getMonthValue() - 1);

        if (hoje.getMonthValue() == 12) {
            comboReferenteMesFinal.setSelectedIndex(0);
        } else {
            comboReferenteMesFinal.setSelectedIndex(hoje.getMonthValue());
        }
    }

    private void preencheAnos() {
        LocalDateTime hoje = LocalDateTime.now();

        for (int i = 2018; i <= hoje.getYear() + 1; i++) {
            comboReferenteAnoInicial.addItem("" + i);
            comboReferenteAnoFinal.addItem("" + i);
        }

        comboReferenteAnoInicial.setSelectedItem(String.valueOf(hoje.getYear()));

        if (hoje.getMonthValue() == 12) {
            comboReferenteAnoFinal.setSelectedItem(String.valueOf(hoje.getYear() + 1));
        } else {
            comboReferenteAnoFinal.setSelectedItem(String.valueOf(hoje.getYear()));
        }
    }

    private void preencherTabela(List<Servico> servicoList) {
        modelo = (DefaultTableModel) tblServicos.getModel();
        modelo.setRowCount(0);

        for (Servico servico : servicoList) {
            modelo.addRow(new Object[]{
                servico.getId(),
                servico.getTitulo(),
                servico.getIdcliente().getNome(),
                utilidade.mesAno(servico.getDataReferente()).toUpperCase(),
                servico.getDataFinalizacao() != null ? "Finalizado!" : "- - - - - - - - -",
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
        comboFiltro = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        txtPesquisa = new javax.swing.JTextField();
        btnPesquisar = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        comboReferenteAnoInicial = new javax.swing.JComboBox<>();
        comboReferenteAnoFinal = new javax.swing.JComboBox<>();
        comboReferenteMesFinal = new javax.swing.JComboBox<>();
        comboReferenteMesInicial = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtNomeCliente = new javax.swing.JTextField();
        btnPesquisarCliente = new javax.swing.JButton();
        btnImprimir = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Gerenciar serviços");

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(null);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setText("Data inicial");
        jPanel1.add(jLabel1);
        jLabel1.setBounds(320, 90, 140, 20);

        tblServicos.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        tblServicos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nota", "Titulo", "Cliente", "Data do serviço", "Finalização", "Valor total"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblServicos.setRowHeight(35);
        tblServicos.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(tblServicos);
        if (tblServicos.getColumnModel().getColumnCount() > 0) {
            tblServicos.getColumnModel().getColumn(0).setPreferredWidth(50);
            tblServicos.getColumnModel().getColumn(1).setPreferredWidth(200);
            tblServicos.getColumnModel().getColumn(2).setPreferredWidth(250);
            tblServicos.getColumnModel().getColumn(3).setPreferredWidth(100);
            tblServicos.getColumnModel().getColumn(4).setPreferredWidth(100);
            tblServicos.getColumnModel().getColumn(5).setPreferredWidth(80);
        }

        jPanel1.add(jScrollPane1);
        jScrollPane1.setBounds(10, 170, 1000, 480);

        btnNovo.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnNovo.setText("Novo");
        btnNovo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNovoActionPerformed(evt);
            }
        });
        jPanel1.add(btnNovo);
        btnNovo.setBounds(10, 660, 140, 40);

        btnAlterar.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnAlterar.setText("Dados");
        btnAlterar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAlterarActionPerformed(evt);
            }
        });
        jPanel1.add(btnAlterar);
        btnAlterar.setBounds(160, 660, 140, 40);

        btnExcluir.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnExcluir.setText("Excluir");
        btnExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcluirActionPerformed(evt);
            }
        });
        jPanel1.add(btnExcluir);
        btnExcluir.setBounds(310, 660, 140, 40);

        btnFechar.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnFechar.setText("Fechar");
        btnFechar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFecharActionPerformed(evt);
            }
        });
        jPanel1.add(btnFechar);
        btnFechar.setBounds(870, 660, 140, 40);

        comboFiltro.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        comboFiltro.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "<Todos>", "Em aberto", "Finalizados" }));
        jPanel1.add(comboFiltro);
        comboFiltro.setBounds(10, 110, 280, 40);

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel2.setText("Selecione o cliente");
        jPanel1.add(jLabel2);
        jLabel2.setBounds(10, 20, 390, 20);

        txtPesquisa.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txtPesquisa.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPesquisaKeyReleased(evt);
            }
        });
        jPanel1.add(txtPesquisa);
        txtPesquisa.setBounds(470, 40, 430, 40);

        btnPesquisar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/protese/util/icons/icons8-pesquisar-25.png"))); // NOI18N
        btnPesquisar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPesquisarActionPerformed(evt);
            }
        });
        jPanel1.add(btnPesquisar);
        btnPesquisar.setBounds(920, 20, 90, 130);

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel3.setText("Filtrar por");
        jPanel1.add(jLabel3);
        jLabel3.setBounds(10, 90, 140, 20);

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel4.setText("Data final");
        jPanel1.add(jLabel4);
        jLabel4.setBounds(620, 90, 140, 20);

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel5.setText("Pesquisar por título ou descrição");
        jPanel1.add(jLabel5);
        jLabel5.setBounds(470, 20, 390, 20);

        comboReferenteAnoInicial.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jPanel1.add(comboReferenteAnoInicial);
        comboReferenteAnoInicial.setBounds(470, 110, 130, 40);

        comboReferenteAnoFinal.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jPanel1.add(comboReferenteAnoFinal);
        comboReferenteAnoFinal.setBounds(770, 110, 130, 40);

        comboReferenteMesFinal.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        comboReferenteMesFinal.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "JAN", "FEV", "MAR", "ABR", "MAI", "JUN", "JUL", "AGO", "SET", "OUT", "NOV", "DEZ" }));
        jPanel1.add(comboReferenteMesFinal);
        comboReferenteMesFinal.setBounds(620, 110, 130, 40);

        comboReferenteMesInicial.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        comboReferenteMesInicial.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "JAN", "FEV", "MAR", "ABR", "MAI", "JUN", "JUL", "AGO", "SET", "OUT", "NOV", "DEZ" }));
        jPanel1.add(comboReferenteMesInicial);
        comboReferenteMesInicial.setBounds(320, 110, 130, 40);

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("/");
        jPanel1.add(jLabel6);
        jLabel6.setBounds(440, 110, 40, 40);

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("/");
        jPanel1.add(jLabel7);
        jLabel7.setBounds(740, 110, 40, 40);

        txtNomeCliente.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jPanel1.add(txtNomeCliente);
        txtNomeCliente.setBounds(10, 40, 380, 40);

        btnPesquisarCliente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/protese/util/icons/icons8-pesquisar-25.png"))); // NOI18N
        btnPesquisarCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPesquisarClienteActionPerformed(evt);
            }
        });
        jPanel1.add(btnPesquisarCliente);
        btnPesquisarCliente.setBounds(390, 10, 70, 70);

        btnImprimir.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnImprimir.setText("Imprimir!");
        btnImprimir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnImprimirActionPerformed(evt);
            }
        });
        jPanel1.add(btnImprimir);
        btnImprimir.setBounds(490, 660, 280, 40);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 1020, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 709, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAlterarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAlterarActionPerformed
        int[] rows = tblServicos.getSelectedRows();

        if (rows.length > 0) {
            try {
                Servico servico = servicoDao.consultarId(Servico.class, Long.parseLong(tblServicos.getValueAt(rows[0], 0).toString()));

                FrmServico frm = new FrmServico(this, true, servico);
                frm.setLocationRelativeTo(null);
                frm.setVisible(true);

                btnPesquisarActionPerformed(null);
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
        FrmServico frm = new FrmServico(this, true, new Servico());
        frm.setLocationRelativeTo(null);
        frm.setVisible(true);

        btnPesquisarActionPerformed(null);
    }//GEN-LAST:event_btnNovoActionPerformed

    private void btnFecharActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFecharActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnFecharActionPerformed

    private void btnPesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPesquisarActionPerformed
        int indexFiltro = comboFiltro.getSelectedIndex();

        int diaInicial = 1;
        int mesInicial = comboReferenteMesInicial.getSelectedIndex() + 1;
        int anoInicial = Integer.parseInt(comboReferenteAnoInicial.getSelectedItem().toString());
        LocalDateTime dataInicial = LocalDateTime.of(anoInicial, mesInicial, diaInicial, 0, 0, 0);

        int diaFinal = 1;
        int mesFinal = comboReferenteMesFinal.getSelectedIndex() + 1;
        int anoFinal = Integer.parseInt(comboReferenteAnoFinal.getSelectedItem().toString());
        LocalDateTime dataFinal = LocalDateTime.of(anoFinal, mesFinal, diaFinal, 23, 59, 59);
        dataFinal = dataFinal.withDayOfMonth(dataFinal.getMonth().maxLength());

        try {
            preencherTabela(servicoDao.retornaTodosPorPesquisa(txtPesquisa.getText(), cliente, dataInicial, dataFinal, indexFiltro));
        } catch (Exception e) {
            preencherTabela(servicoDao.retornaTodos());

            e.printStackTrace();
        }
    }//GEN-LAST:event_btnPesquisarActionPerformed

    private void txtPesquisaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPesquisaKeyReleased
        btnPesquisarActionPerformed(null);
    }//GEN-LAST:event_txtPesquisaKeyReleased

    private void btnPesquisarClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPesquisarClienteActionPerformed
        FrmPesquisarCliente frm = new FrmPesquisarCliente(this, true);
        frm.setLocationRelativeTo(null);
        frm.setVisible(true);

        this.cliente = frm.getCliente();
        txtNomeCliente.setText(cliente.getNome());
    }//GEN-LAST:event_btnPesquisarClienteActionPerformed

    private void btnImprimirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnImprimirActionPerformed
        int row = tblServicos.getSelectedRow();

        if (row > -1) {
            parametros = new HashMap();
            
            parametros.put("idservico", tblServicos.getValueAt(row, 0).toString());
            impressao.teste("impressoServico", parametros);
        } else {
            JOptionPane.showMessageDialog(this, "Selecione os serviços para continuar!", "Serviço", JOptionPane.WARNING_MESSAGE);
        }
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
    private javax.swing.JButton btnImprimir;
    private javax.swing.JButton btnNovo;
    private javax.swing.JButton btnPesquisar;
    private javax.swing.JButton btnPesquisarCliente;
    private javax.swing.JComboBox<String> comboFiltro;
    private javax.swing.JComboBox<String> comboReferenteAnoFinal;
    private javax.swing.JComboBox<String> comboReferenteAnoInicial;
    private javax.swing.JComboBox<String> comboReferenteMesFinal;
    private javax.swing.JComboBox<String> comboReferenteMesInicial;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblServicos;
    private javax.swing.JTextField txtNomeCliente;
    private javax.swing.JTextField txtPesquisa;
    // End of variables declaration//GEN-END:variables
}
