package protese.dao.cliente;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Query;
import protese.jpa.interfaces.Dao;
import protese.model.cliente.Cliente;
import protese.model.cliente.ClienteCreditoEntrada;

/**
 *
 * @author vinihds
 */
public class ClienteCreditoEntradaDao extends Dao<ClienteCreditoEntrada> {

    private static ClienteCreditoEntradaDao unique = null;

    private ClienteCreditoEntradaDao() {
    }

    public static ClienteCreditoEntradaDao getInstance() {
        if (unique == null) {
            unique = new ClienteCreditoEntradaDao();
        }
        return unique;
    }

    public ClienteCreditoEntrada salvar(ClienteCreditoEntrada creditoEntrada) {
        try {
            creditoEntrada = super.gravar(creditoEntrada);
        } catch (Exception e) {
        }
        return creditoEntrada;
    }

    public ClienteCreditoEntrada deletar(ClienteCreditoEntrada creditoEntrada) {
        try {
            creditoEntrada.setExcluido(true);
            creditoEntrada = salvar(creditoEntrada);
        } catch (Exception e) {
        }
        return creditoEntrada;
    }

    public List<ClienteCreditoEntrada> retornaTodosPorCliente(Cliente cliente) {
        List<ClienteCreditoEntrada> resultset = new ArrayList();

        Query query = createQuery("SELECT creditoEntrada FROM ClienteCreditoEntrada AS creditoEntrada "
                + " INNER JOIN creditoEntrada.idcliente AS cliente "
                + " INNER JOIN creditoEntrada.idservicoPagamento AS servicoPagamento "
                + " INNER JOIN servicoPagamento.idpagamento AS pagamento "
                + " WHERE creditoEntrada.excluido = false "
                + " AND pagamento.excluido = false "
                + " AND creditoEntrada.idcliente = :cliente");
        query.setParameter("cliente", cliente);

        resultset = query.getResultList();

        return resultset;
    }
}
