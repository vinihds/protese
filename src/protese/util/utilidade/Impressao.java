package protese.util.utilidade;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRResultSetDataSource;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author Vinicius
 */
public class Impressao {

    private static Impressao impressao;
    private static Connection con = null;

    private Impressao() {
    }

    public static Impressao getInstance() {
        if (impressao == null) {
            impressao = new Impressao();
        }

        return impressao;
    }

    public static Connection getConnection() {
        try {
            con = DriverManager.getConnection("jdbc:postgresql://localhost/protese", "postgres", "toor");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return con;
    }

    public static void desconectar(Connection c) {
        try {
            c.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*public static void executarUpdateDelete(String sql) {
        try {
            Connection conn = getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.executeUpdate();
            ps.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void executarInsert(String sql, List<Object> l) {
        try {
            Connection conn = getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);

            for (int i = 0; i < l.size(); i++) {
                ps.setString(i + 1, l.get(i).toString());
            }

            ps.executeUpdate();
            ps.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static List<Object[]> executarSelect(String sql, List<String> l) {
        List<Object[]> result = new ArrayList();

        try {
            Connection conn = getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            //System.out.println("--> "+l.size());
            while (rs.next()) {
                Object[] row = new Object[l.size()];
                for (int i = 0; i < l.size(); i++) {
                    Object cell = rs.getObject(l.get(i));
                    if (cell == null || cell.toString().equals("")) {
                        row[i] = "";
                    } else {
                        row[i] = cell;
                    }
                }
                result.add(row);
            }
            rs.close();
            ps.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }*/
    public static ResultSet executarSelectRS(String sql) {
        List<Object[]> result = new ArrayList();
        ResultSet rs = null;
        try {
            Connection conn = getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return rs;
    }

    /*public void imprimir(HashMap parametros, String nomeRelatorio) throws IOException {
        String pathRelatorio = "./relatorios/";

        try {
            Class driverClass = Class.forName("org.postgresql.Driver");
            Connection jdbcConnection = DriverManager.getConnection("jdbc:postgresql://localhost/protese", "postgres", "toor");

            OutputStream saida = new FileOutputStream(nomeRelatorio + ".pdf");

            // compila jrxml em um arquivo .jasper
            String jasper = JasperCompileManager.compileReportToFile(pathRelatorio + nomeRelatorio + ".jrxml");

            // preenche relatorio
            JasperPrint print = JasperFillManager.fillReport(jasper, parametros, jdbcConnection);

            // exporta para pdf
            JRExporter exporter = new JRPdfExporter();
            exporter.setParameter(JRExporterParameter.JASPER_PRINT, print);
            exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, saida);
            exporter.exportReport();
        } catch (JRException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Não foi possivel abrir o relatório");
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Não foi possivel abrir o relatório");
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Não foi possivel abrir o relatório");
        }
    }*/
 /*public void imprimir(HashMap parametros, String nomeRelatorio) throws IOException {
        String pathRelatorio = "C:/protese/relatorios/";

        try {
            File jasper = new File(pathRelatorio + nomeRelatorio + ".jasper");
            OutputStream saida = new FileOutputStream(nomeRelatorio + ".pdf");

            Class driverClass = Class.forName("org.postgresql.Driver");
            Connection jdbcConnection = DriverManager.getConnection("jdbc:postgresql://localhost/protese", "postgres", "toor");

            JasperPrint jPrint  = JasperFillManager.fillReport(pathRelatorio + nomeRelatorio + ".jasper", parametros, jdbcConnection);

            // Criando objeto que gerencia a  exportação
            JRPdfExporter exporter = new JRPdfExporter();

            // Setando as configurações de entrada e saída no objeto que gerencia a exportação
            exporter.setExporterInput(new SimpleExporterInput(jPrint));
            exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(nomeRelatorio + ".pdf"));

            // Gravando o PDF.
            exporter.exportReport();

        } catch (JRException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Não foi possivel abrir o relatório");
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Não foi possivel abrir o relatório");
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Não foi possivel abrir o relatório");
        }
    }*/
 /*public void geraPDF(String arquivo, Map parametros, ResultSet rs) throws JRException {

        apagaArquivo(arquivo + ".pdf");//verifica se o arquivo a ser gerado já existe, caso sim apaga antes de gerar o novo

        try {
            JRResultSetDataSource jrRS = new JRResultSetDataSource(rs);
            JasperReport jr = (JasperReport) JRLoader.loadObject(arquivo);
            JasperPrint rel = JasperFillManager.fillReport(jr, parametros, jrRS);
            arquivo = arquivo.replaceAll(".jasper", "");
            JasperExportManager.exportReportToPdfFile(rel, arquivo + ".pdf");
            java.awt.Desktop.getDesktop().open(new File(arquivo + ".pdf"));
            //java.awt.Desktop.getDesktop().print(new File(arquivo + ".pdf"));

            //Runtime.getRuntime().exec("cmd /c start " + "C:/" + arquivo + ".pdf");
        } catch (JRException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    public void geraPDFSemAbrir(String arquivo, Map parametros, Connection con, ResultSet rs) throws JRException {

        apagaArquivo(arquivo + ".pdf");//verifica se o arquivo a ser gerado já existe, caso sim apaga antes de gerar o novo

        try {
            JRResultSetDataSource jrRS = new JRResultSetDataSource(rs);
            JasperReport jr = (JasperReport) JRLoader.loadObject(arquivo);
            JasperPrint rel = JasperFillManager.fillReport(jr, parametros, jrRS);
            arquivo = arquivo.replaceAll(".jasper", "");
            JasperExportManager.exportReportToPdfFile(rel, arquivo + ".pdf");
            //java.awt.Desktop.getDesktop().open(new File(arquivo + ".pdf"));
            //java.awt.Desktop.getDesktop().print(new File(arquivo + ".pdf"));

            //Runtime.getRuntime().exec("cmd /c start " + "C:/" + arquivo + ".pdf");
        } catch (JRException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    public void imprimeArquivoPDF(String arquivo, Map parametros, ResultSet rs) {
        apagaArquivo(arquivo + ".pdf");//verifica se o arquivo a ser gerado já existe, caso sim apaga antes de gerar o novo

        try {
            JRResultSetDataSource jrRS = new JRResultSetDataSource(rs);
            JasperReport jr = (JasperReport) JRLoader.loadObject(arquivo);
            JasperPrint rel = JasperFillManager.fillReport(jr, parametros, jrRS);
            arquivo = arquivo.replaceAll(".jasper", "");
            JasperExportManager.exportReportToPdfFile(rel, arquivo + ".pdf");
            java.awt.Desktop.getDesktop().print(new File(arquivo + ".pdf"));

        } catch (JRException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }*/
    public void apagaArquivo(String arquivo) {
        File fl = new File(arquivo);
        if (fl.exists()) {
            fl.delete();
        }
    }

    public void teste(String nomeArquivo, HashMap parametros) {
        try {
            ResultSet rs = executarSelectRS("SELECT 1");
            JasperPrint print;
            String arquivo = "/protese/relatorios/" + nomeArquivo + ".jasper";
            JRResultSetDataSource jrRS;
            JasperViewer viewer;
            jrRS = new JRResultSetDataSource(rs);
            print = JasperFillManager.fillReport(arquivo, parametros, jrRS);
            viewer = new JasperViewer(print, false);
            viewer.setExtendedState(JasperViewer.MAXIMIZED_BOTH);
            viewer.show();
        } catch (JRException e) {
            e.printStackTrace();
        }
    }
}
