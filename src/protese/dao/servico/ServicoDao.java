package protese.dao.servico;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Query;
import protese.jpa.interfaces.Dao;
import protese.model.servico.Servico;

/**
 *
 * @author vinihds
 */
public class ServicoDao extends Dao<Servico> {

    private static ServicoDao unique = null;

    private ServicoDao() {
    }

    public static ServicoDao getInstance() {
        if (unique == null) {
            unique = new ServicoDao();
        }
        return unique;
    }

    public Servico salvar(Servico servico) {
        try {
            servico = super.gravar(servico);
        } catch (Exception e) {
        }
        return servico;
    }

    public Servico deletar(Servico servico) {
        try {
            servico.setExcluido(true);
            servico = salvar(servico);
        } catch (Exception e) {
        }
        return servico;
    }

    public List<Servico> retornaTodos() {
        List<Servico> resultset = new ArrayList();

        Query query = createQuery("SELECT servico FROM Servico AS servico "
                + " WHERE servico.excluido = false "
                + " ORDER BY servico.dataCriacao DESC");

        resultset = query.getResultList();

        return resultset;
    }
}
