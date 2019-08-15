package protese.dao.cliente;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Query;
import protese.jpa.interfaces.Dao;
import protese.model.cliente.Cliente;
import protese.model.cliente.ClienteDebito;

/**
 *
 * @author Vinicius Silveira
 */
public class ClienteDebitoDao extends Dao<ClienteDebito> {

    private static ClienteDebitoDao unique = null;

    private ClienteDebitoDao() {
    }

    public static ClienteDebitoDao getInstance() {
        if (unique == null) {
            unique = new ClienteDebitoDao();
        }
        return unique;
    }

    public ClienteDebito salvar(ClienteDebito clienteDebito) {
        try {
            clienteDebito = super.gravar(clienteDebito);
        } catch (Exception e) {
        }
        return clienteDebito;
    }

    public ClienteDebito deletar(ClienteDebito clienteDebito) {
        try {
            clienteDebito.setExcluido(true);
            clienteDebito = salvar(clienteDebito);
        } catch (Exception e) {
        }
        return clienteDebito;
    }

    public List<ClienteDebito> retornaTodosPorCliente(Cliente cliente) {
        List<ClienteDebito> resultset = new ArrayList();

        Query query = createQuery("SELECT debito FROM ClienteDebito AS debito "
                + " INNER JOIN debito.idcliente AS cliente "
                + " WHERE debito.excluido = false "
                + " AND debito.idcliente = :cliente "
                + " ORDER BY debito.data DESC");
        query.setParameter("cliente", cliente);

        resultset = query.getResultList();

        return resultset;
    }

    public List<ClienteDebito> retornaTodosNaoUtilizadosPorCliente(Cliente cliente) {
        List<ClienteDebito> resultset = new ArrayList();

        Query query = createQuery("SELECT debito FROM ClienteDebito AS debito "
                + " INNER JOIN debito.idcliente AS cliente "
                + " WHERE debito.excluido = false "
                + " AND debito.idcliente = :cliente "
                + " AND (SELECT COUNT(servicoDebito.id) FROM ServicoDebito AS servicoDebito "
                + "     INNER JOIN servicoDebito.idclienteDebito AS debitoTemp "
                + "     WHERE debitoTemp.idclienteDebito = debito.idclienteDebito "
                + "     AND servicoDebito.excluido = false) = 0 "
                + " ORDER BY debito.data DESC");
        query.setParameter("cliente", cliente);

        resultset = query.getResultList();

        return resultset;
    }

    public ClienteDebito geraDebitoCliente(Cliente cliente, double valor, String descricao, LocalDateTime data) {
        ClienteDebito clienteDebito = new ClienteDebito();

        clienteDebito.setIdcliente(cliente);
        clienteDebito.setDescricao(descricao);
        clienteDebito.setValorDebito(valor);
        clienteDebito.setData(data);

        return salvar(clienteDebito);
    }

}
