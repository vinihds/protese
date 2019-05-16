package protese.dao.cliente;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Query;
import protese.jpa.interfaces.Dao;
import protese.model.cliente.Cliente;
import protese.model.cliente.ClienteEndereco;

/**
 *
 * @author vinihds
 */
public class ClienteEnderecoDao extends Dao<ClienteEndereco> {

    private static ClienteEnderecoDao unique = null;

    private ClienteEnderecoDao() {
    }

    public static ClienteEnderecoDao getInstance() {
        if (unique == null) {
            unique = new ClienteEnderecoDao();
        }
        return unique;
    }

    public ClienteEndereco salvar(ClienteEndereco clienteEndereco) {
        try {
            clienteEndereco = super.gravar(clienteEndereco);
        } catch (Exception e) {
        }
        return clienteEndereco;
    }

    public ClienteEndereco deletar(ClienteEndereco clienteEndereco) {
        try {
            clienteEndereco.setExcluido(true);
            clienteEndereco = salvar(clienteEndereco);
        } catch (Exception e) {
        }
        return clienteEndereco;
    }

    public List<ClienteEndereco> retornaTodosPorCliente(Cliente cliente) {
        List<ClienteEndereco> resultset = new ArrayList();

        Query query = createQuery("SELECT clienteEndereco FROM ClienteEndereco AS clienteEndereco "
                + " INNER JOIN clienteEndereco.idendereco AS endereco "
                + " INNER JOIN clienteEndereco.idcliente AS cliente "
                + " WHERE clienteEndereco.excluido = false "
                + " AND endereco.excluido = false "
                + " AND clienteEndereco.idcliente = :cliente");
        query.setParameter("cliente", cliente);

        resultset = query.getResultList();

        return resultset;
    }
}
