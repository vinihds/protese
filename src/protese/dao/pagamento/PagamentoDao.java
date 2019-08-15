package protese.dao.pagamento;

import java.time.LocalDateTime;
import java.time.LocalTime;
import javax.persistence.Query;
import protese.jpa.interfaces.Dao;
import protese.model.cliente.Cliente;
import protese.model.pagamento.Pagamento;
import protese.model.pagamento.PagamentoPorAno;
import protese.model.pagamento.PagamentoPorMes;

/**
 *
 * @author vinihds
 */
public class PagamentoDao extends Dao<Pagamento> {

    private static PagamentoDao unique = null;

    private PagamentoDao() {
    }

    public static PagamentoDao getInstance() {
        if (unique == null) {
            unique = new PagamentoDao();
        }
        return unique;
    }

    public Pagamento salvar(Pagamento pagamento) {
        try {
            pagamento = super.gravar(pagamento);
        } catch (Exception e) {
        }
        return pagamento;
    }

    public Pagamento deletar(Pagamento pagamento) {
        try {
            pagamento.setExcluido(true);
            pagamento = salvar(pagamento);
        } catch (Exception e) {
        }
        return pagamento;
    }
    
    private double retornaTotalPagoClientePorPeriodo(Cliente cliente, LocalDateTime dataInicial, LocalDateTime dataFinal) {
        double valorTotalPagamento = 0;
        
        Query query = createQuery("SELECT COALESCE(SUM(pagamento.valor), 0) FROM Pagamento AS pagamento, ServicoPagamento as servicoPagamento,"
                + " Servico AS servico "
                + " WHERE pagamento.excluido = false "
                + " AND pagamento.idpagamento = servicoPagamento.idpagamento "
                + " AND servicoPagamento.idservico = servico.idservico "
                + " AND servico.excluido = false "
                + " AND servicoPagamento.excluido = false "
                + " AND servico.idcliente = :cliente "
                + " AND pagamento.dataPagamento BETWEEN :dataInicial AND :dataFinal "
                + " AND pagamento.idformaPagamento != (SELECT formaPagamento.idformaPagamento FROM FormaPagamento AS formaPagamento "
                + "     WHERE tipo = 'creditoSaida')");
        query.setParameter("cliente", cliente);
        query.setParameter("dataInicial", dataInicial);
        query.setParameter("dataFinal", dataFinal);
        
        valorTotalPagamento += (Double) query.getSingleResult();
        
        query = createQuery("SELECT COALESCE(SUM(creditoEntrada.valorCredito), 0) FROM ClienteCreditoEntrada AS creditoEntrada "
                + " INNER JOIN creditoEntrada.idcliente AS cliente "
                + " WHERE creditoEntrada.excluido = false "
                + " AND creditoEntrada.idcliente = :cliente "
                + " AND creditoEntrada.data BETWEEN :dataInicial AND :dataFinal "
                + " AND (SELECT COUNT(servicoCredito.idservicoCredito) FROM ServicoCredito AS servicoCredito "
                + "     WHERE servicoCredito.idclienteCreditoEntrada = creditoEntrada.idclienteCreditoEntrada) = 0");
        query.setParameter("cliente", cliente);
        query.setParameter("dataInicial", dataInicial);
        query.setParameter("dataFinal", dataFinal);
        
        valorTotalPagamento += (Double) query.getSingleResult();
        
        return valorTotalPagamento;
    }
    
    public PagamentoPorAno listagemPagamentoPorAno(Cliente cliente, int ano) {
        PagamentoPorAno porAno = new PagamentoPorAno();
        
        LocalDateTime data = LocalDateTime.now().withYear(ano);
        LocalDateTime dataInicial;
        LocalDateTime dataFinal;
        LocalTime horaInicial = LocalTime.of(0, 0, 0, 0);
        LocalTime horaFinal = LocalTime.of(23, 59, 59, 999);
        
        for (int i = 1; i <= 12; i++) {
            dataInicial = data.withMonth(i).withDayOfMonth(1).toLocalDate().atTime(horaInicial);
            dataFinal = dataInicial.withDayOfMonth(dataInicial.toLocalDate().lengthOfMonth()).toLocalDate().atTime(horaFinal);
            
            PagamentoPorMes porMes = new PagamentoPorMes();
            porMes.setMes(i);
            porMes.setValor(retornaTotalPagoClientePorPeriodo(cliente, dataInicial, dataFinal));
            
            porAno.getPagamentoPorMesList().add(porMes);
        }
        
        return porAno;
    }

}
