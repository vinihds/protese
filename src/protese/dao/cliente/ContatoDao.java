package protese.dao.cliente;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Query;
import protese.jpa.interfaces.Dao;
import protese.model.cliente.Contato;

/**
 *
 * @author vinihds
 */
public class ContatoDao extends Dao<Contato> {

    private static ContatoDao unique = null;

    private ContatoDao() {
    }

    public static ContatoDao getInstance() {
        if (unique == null) {
            unique = new ContatoDao();
        }
        return unique;
    }

    public Contato salvar(Contato contato) {
        try {
            contato = super.gravar(contato);
        } catch (Exception e) {
        }
        return contato;
    }

    public Contato deletar(Contato contato) {
        try {
            contato.setExcluido(true);
            contato = salvar(contato);
        } catch (Exception e) {
        }
        return contato;
    }

    public List<Contato> retornaTodos() {
        List<Contato> resultset = new ArrayList();

        Query query = createQuery("SELECT contato FROM Contato AS contato "
                + " WHERE contato.excluido = false");

        resultset = query.getResultList();

        return resultset;
    }
}
