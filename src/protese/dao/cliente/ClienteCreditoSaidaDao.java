package protese.dao.cliente;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Query;
import protese.jpa.interfaces.Dao;
import protese.model.cliente.Cliente;
import protese.model.cliente.ClienteCreditoSaida;

/**
 *
 * @author vinihds
 */
public class ClienteCreditoSaidaDao extends Dao<ClienteCreditoSaida> {

    private static ClienteCreditoSaidaDao unique = null;

    private ClienteCreditoSaidaDao() {
    }

    public static ClienteCreditoSaidaDao getInstance() {
        if (unique == null) {
            unique = new ClienteCreditoSaidaDao();
        }
        return unique;
    }

    public ClienteCreditoSaida salvar(ClienteCreditoSaida creditoSaida) {
        try {
            creditoSaida = super.gravar(creditoSaida);
        } catch (Exception e) {
        }
        return creditoSaida;
    }

    public ClienteCreditoSaida deletar(ClienteCreditoSaida creditoSaida) {
        try {
            creditoSaida.setExcluido(true);
            creditoSaida = salvar(creditoSaida);
        } catch (Exception e) {
        }
        return creditoSaida;
    }

    public List<ClienteCreditoSaida> retornaTodosPorCliente(Cliente cliente) {
        List<ClienteCreditoSaida> resultset = new ArrayList();

        Query query = createQuery("SELECT creditoSaida FROM ClienteCreditoSaida AS creditoSaida "
                + " INNER JOIN creditoSaida.idcliente AS cliente "
                + " INNER JOIN creditoSaida.idservicoPagamento AS servicoPagamento "
                + " INNER JOIN servicoPagamento.idpagamento AS pagamento "
                + " WHERE creditoSaida.excluido = false "
                + " AND pagamento.excluido = false "
                + " AND creditoSaida.idcliente = :cliente");
        query.setParameter("cliente", cliente);

        resultset = query.getResultList();

        return resultset;
    }
}
