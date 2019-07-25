package protese.dao.cliente;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Query;
import protese.jpa.interfaces.Dao;
import protese.model.cliente.Cliente;

/**
 *
 * @author vinihds
 */
public class ClienteDao extends Dao<Cliente> {

    private static ClienteDao unique = null;

    private ClienteDao() {
    }

    public static ClienteDao getInstance() {
        if (unique == null) {
            unique = new ClienteDao();
        }
        return unique;
    }

    public Cliente salvar(Cliente cliente) {
        try {
            cliente = super.gravar(cliente);
        } catch (Exception e) {
        }
        return cliente;
    }

    public Cliente deletar(Cliente cliente) {
        try {
            cliente.setExcluido(true);
            cliente = salvar(cliente);
        } catch (Exception e) {
        }
        return cliente;
    }

    public List<Cliente> retornaTodos() {
        List<Cliente> resultset = new ArrayList();

        Query query = createQuery("SELECT cliente FROM Cliente AS cliente "
                + " WHERE cliente.excluido = false "
                + " ORDER BY cliente.nome");

        resultset = query.getResultList();

        return resultset;
    }

    public List<Cliente> retornaTodosPorNome(String pesquisa) {
        List<Cliente> resultset = new ArrayList();

        Query query = createQuery("SELECT cliente FROM Cliente AS cliente "
                + " WHERE cliente.excluido = false "
                + " AND cliente.nome LIKE LOWER(:pesquisa) "
                + " ORDER BY cliente.nome");
        query.setParameter("pesquisa", "%" + pesquisa + "%");

        resultset = query.getResultList();

        return resultset;
    }

    public List<Cliente> retornaTodosPorNomeOuCodigoProprio(String pesquisa) {
        List<Cliente> resultset = new ArrayList();

        Query query = createQuery("SELECT cliente FROM Cliente AS cliente "
                + " WHERE cliente.excluido = false "
                + " AND (LOWER(cliente.nome) LIKE LOWER(:pesquisa) "
                + "     OR LOWER(cliente.codigoProprio) LIKE LOWER(:pesquisa)) "
                + " ORDER BY cliente.nome");
        query.setParameter("pesquisa", "%" + pesquisa + "%");

        resultset = query.getResultList();

        return resultset;
    }
}
