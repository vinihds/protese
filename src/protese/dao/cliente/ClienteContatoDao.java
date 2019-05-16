package protese.dao.cliente;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Query;
import protese.jpa.interfaces.Dao;
import protese.model.cliente.Cliente;
import protese.model.cliente.ClienteContato;

/**
 *
 * @author vinihds
 */
public class ClienteContatoDao extends Dao<ClienteContato> {

    private static ClienteContatoDao unique = null;

    private ClienteContatoDao() {
    }

    public static ClienteContatoDao getInstance() {
        if (unique == null) {
            unique = new ClienteContatoDao();
        }
        return unique;
    }

    public ClienteContato salvar(ClienteContato clienteContato) {
        try {
            clienteContato = super.gravar(clienteContato);
        } catch (Exception e) {
        }
        return clienteContato;
    }

    public ClienteContato deletar(ClienteContato clienteContato) {
        try {
            clienteContato.setExcluido(true);
            clienteContato = salvar(clienteContato);
        } catch (Exception e) {
        }
        return clienteContato;
    }

    public List<ClienteContato> retornaTodosPorCliente(Cliente cliente) {
        List<ClienteContato> resultset = new ArrayList();

        Query query = createQuery("SELECT clienteContato FROM ClienteContato AS clienteContato "
                + " INNER JOIN clienteContato.idcontato AS contato "
                + " INNER JOIN clienteContato.idcliente AS cliente "
                + " WHERE clienteContato.excluido = false "
                + " AND contato.excluido = false "
                + " AND clienteContato.idcliente = :cliente "
                + " ORDER BY clienteContato.idclienteContato");
        query.setParameter("cliente", cliente);

        resultset = query.getResultList();

        return resultset;
    }
}
