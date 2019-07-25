package protese.dao.produto;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Query;
import protese.jpa.interfaces.Dao;
import protese.model.produto.Grupo;

/**
 *
 * @author vinihds
 */
public class GrupoDao extends Dao<Grupo> {

    private static GrupoDao unique = null;

    private GrupoDao() {
    }

    public static GrupoDao getInstance() {
        if (unique == null) {
            unique = new GrupoDao();
        }
        return unique;
    }

    public Grupo salvar(Grupo grupo) {
        try {
            grupo = super.gravar(grupo);
        } catch (Exception e) {
        }
        return grupo;
    }

    public Grupo deletar(Grupo grupo) {
        try {
            grupo.setExcluido(true);
            grupo = salvar(grupo);
        } catch (Exception e) {
        }
        return grupo;
    }

    public List<Grupo> retornaTodos() {
        List<Grupo> resultset = new ArrayList();

        Query query = createQuery("SELECT grupo FROM Grupo AS grupo "
                + " WHERE grupo.excluido = false "
                + " ORDER BY grupo.codigo");

        resultset = query.getResultList();

        return resultset;
    }

    public List<Grupo> retornaTodosPorNomeOuCodigoProprio(String pesquisa) {
        List<Grupo> resultset = new ArrayList();

        Query query = createQuery("SELECT grupo FROM Grupo AS grupo "
                + " WHERE grupo.excluido = false "
                + " AND (LOWER(grupo.nome) LIKE LOWER(:pesquisa) "
                + "     OR LOWER(grupo.codigo) LIKE LOWER(:pesquisa)) "
                + " ORDER BY grupo.codigo");
        query.setParameter("pesquisa", "%" + pesquisa + "%");

        resultset = query.getResultList();

        return resultset;
    }

}
