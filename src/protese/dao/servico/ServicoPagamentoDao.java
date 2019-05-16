package protese.dao.servico;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Query;
import protese.jpa.interfaces.Dao;
import protese.model.servico.Servico;
import protese.model.servico.ServicoPagamento;

/**
 *
 * @author vinihds
 */
public class ServicoPagamentoDao extends Dao<ServicoPagamento> {

    private static ServicoPagamentoDao unique = null;

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
        try {
            servicoPagamento.setExcluido(true);
            servicoPagamento = salvar(servicoPagamento);
        } catch (Exception e) {
        }
        return servicoPagamento;
    }

    public List<Servico> retornaTodosPorServico(Servico servico) {
        List<Servico> resultset = new ArrayList();

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
