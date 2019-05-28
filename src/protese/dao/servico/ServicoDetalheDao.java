package protese.dao.servico;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Query;
import protese.jpa.interfaces.Dao;
import protese.model.servico.Servico;
import protese.model.servico.ServicoDetalhe;

/**
 *
 * @author vinihds
 */
public class ServicoDetalheDao extends Dao<ServicoDetalhe> {

    private static ServicoDetalheDao unique = null;

    private ServicoDetalheDao() {
    }

    public static ServicoDetalheDao getInstance() {
        if (unique == null) {
            unique = new ServicoDetalheDao();
        }
        return unique;
    }

    public ServicoDetalhe salvar(ServicoDetalhe servicoDetalhe) {
        try {
            servicoDetalhe = super.gravar(servicoDetalhe);
        } catch (Exception e) {
        }
        return servicoDetalhe;
    }

    public ServicoDetalhe deletar(ServicoDetalhe servicoDetalhe) {
        try {
            servicoDetalhe.setExcluido(true);
            servicoDetalhe = salvar(servicoDetalhe);
        } catch (Exception e) {
        }
        return servicoDetalhe;
    }

    public List<ServicoDetalhe> retornaTodosPorServico(Servico servico) {
        List<ServicoDetalhe> resultset = new ArrayList();

        Query query = createQuery("SELECT servicoDetalhe FROM ServicoDetalhe AS servicoDetalhe "
                + " INNER JOIN servicoDetalhe.idservico AS servico "
                + " WHERE servico.excluido = false "
                + " AND servicoDetalhe.excluido = false "
                + " AND servicoDetalhe.idservico = :servico "
                + " ORDER BY servicoDetalhe.dataLancamento");
        query.setParameter("servico", servico);

        resultset = query.getResultList();

        return resultset;
    }
}
