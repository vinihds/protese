package protese.dao.cliente;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Query;
import protese.jpa.interfaces.Dao;
import protese.model.cliente.Endereco;

/**
 *
 * @author vinihds
 */
public class EnderecoDao extends Dao<Endereco> {

    private static EnderecoDao unique = null;

    private EnderecoDao() {
    }

    public static EnderecoDao getInstance() {
        if (unique == null) {
            unique = new EnderecoDao();
        }
        return unique;
    }

    public Endereco salvar(Endereco endereco) {
        try {
            endereco = super.gravar(endereco);
        } catch (Exception e) {
        }
        return endereco;
    }

    public Endereco deletar(Endereco endereco) {
        try {
            endereco.setExcluido(true);
            endereco = salvar(endereco);
        } catch (Exception e) {
        }
        return endereco;
    }

    public List<Endereco> retornaTodos() {
        List<Endereco> resultset = new ArrayList();

        Query query = createQuery("SELECT endereco FROM Endereco AS endereco "
                + " WHERE endereco.excluido = false");

        resultset = query.getResultList();

        return resultset;
    }
}
