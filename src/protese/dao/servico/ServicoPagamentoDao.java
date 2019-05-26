package protese.dao.servico;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Query;
import protese.dao.cliente.ClienteCreditoEntradaDao;
import protese.dao.cliente.ClienteCreditoSaidaDao;
import protese.dao.pagamento.FormaPagamentoDao;
import protese.dao.pagamento.PagamentoDao;
import protese.jpa.interfaces.Dao;
import protese.model.pagamento.FormaPagamento;
import protese.model.pagamento.Pagamento;
import protese.model.servico.Servico;
import protese.model.servico.ServicoPagamento;

/**
 *
 * @author vinihds
 */
public class ServicoPagamentoDao extends Dao<ServicoPagamento> {

    private static ServicoPagamentoDao unique = null;

    private PagamentoDao pagamentoDao = PagamentoDao.getInstance();
    private ClienteCreditoEntradaDao creditoEntradaDao = ClienteCreditoEntradaDao.getInstance();
    private ClienteCreditoSaidaDao creditoSaidaDao = ClienteCreditoSaidaDao.getInstance();
    private FormaPagamentoDao formaPagamentoDao = FormaPagamentoDao.getInstance();

    private ServicoPagamentoDao() {
    }

    public static ServicoPagamentoDao getInstance() {
        if (unique == null) {
            unique = new ServicoPagamentoDao();
        }
        return unique;
    }

    public ServicoPagamento salvar(ServicoPagamento servicoPagamento) {
        try {
            servicoPagamento = super.gravar(servicoPagamento);
        } catch (Exception e) {
        }
        return servicoPagamento;
    }

    public ServicoPagamento deletar(ServicoPagamento servicoPagamento) {
        PagamentoDao pagamentoDao = PagamentoDao.getInstance();

        try {
            pagamentoDao.deletar(servicoPagamento.getIdpagamento());

            servicoPagamento.setExcluido(true);
            servicoPagamento = salvar(servicoPagamento);
        } catch (Exception e) {
        }
        return servicoPagamento;
    }

    public boolean salvarNovoServicoPagamento(Servico servico, FormaPagamento formaPagamento, LocalDateTime dataPagamento, double valorPagamento, double restantePagar) {
        FormaPagamento formaPagamentoSaidaCredito = formaPagamentoDao.retornaFormaPagamentoCreditoSaida();

        Pagamento pagamento = new Pagamento();
        pagamento.setIdformaPagamento(formaPagamento);
        pagamento.setDataLancamento(LocalDateTime.now());
        pagamento.setDataPagamento(dataPagamento);
        pagamento.setDataVencimento(LocalDateTime.now());
        pagamento.setValor(valorPagamento);
        pagamento = pagamentoDao.salvar(pagamento);

        if (pagamento.getId() > 0) {
            ServicoPagamento servicoPagamento = salvarServicoPagamento(servico, pagamento);

            if (servicoPagamento.getId() > 0) {

                //Se utilizou credito, deve dar baixa para o cliente
                if (formaPagamento.getId() == formaPagamentoSaidaCredito.getId()) {
                    creditoSaidaDao.salvarClienteCreditoSaida(servico.getIdcliente(), servicoPagamento);
                }

                return true;
            }
        }

        return false;
    }

    public ServicoPagamento salvarServicoPagamento(Servico servico, Pagamento pagamento) {
        ServicoPagamento servicoPagamento = new ServicoPagamento();

        servicoPagamento.setIdservico(servico);
        servicoPagamento.setIdpagamento(pagamento);

        return salvar(servicoPagamento);
    }

    public List<ServicoPagamento> retornaTodosPorServico(Servico servico) {
        List<ServicoPagamento> resultset = new ArrayList();

        Query query = createQuery("SELECT servicoPagamento FROM ServicoPagamento AS servicoPagamento "
                + " INNER JOIN servicoPagamento.idservico AS servico "
                + " INNER JOIN servicoPagamento.idpagamento AS pagamento "
                + " WHERE servico.excluido = false "
                + " AND servicoPagamento.excluido = false "
                + " AND pagamento.excluido = false "
                + " ORDER BY pagamento.dataLancamento");

        resultset = query.getResultList();

        return resultset;
    }
}
