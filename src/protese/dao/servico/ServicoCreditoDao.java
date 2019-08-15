package protese.dao.servico;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Query;
import protese.jpa.interfaces.Dao;
import protese.model.cliente.ClienteCreditoEntrada;
import protese.model.servico.Servico;
import protese.model.servico.ServicoCredito;

/**
 *
 * @author Vinicius Silveira
 */
public class ServicoCreditoDao extends Dao<ServicoCredito> {

    private static ServicoCreditoDao unique = null;

    private ServicoCreditoDao() {
    }

    public static ServicoCreditoDao getInstance() {
        if (unique == null) {
            unique = new ServicoCreditoDao();
        }
        return unique;
    }

    public ServicoCredito salvar(ServicoCredito servicoCredito) {
        try {
            servicoCredito = super.gravar(servicoCredito);
        } catch (Exception e) {
        }
        return servicoCredito;
    }

    public ServicoCredito deletar(ServicoCredito servicoCredito) {
        try {
            servicoCredito.setExcluido(true);
            servicoCredito = salvar(servicoCredito);
        } catch (Exception e) {
        }
        return servicoCredito;
    }

    public ServicoCredito salvarServicoCredito(ClienteCreditoEntrada creditoEntrada, Servico servico) {
        ServicoCredito servicoCredito = new ServicoCredito();

        servicoCredito.setIdservico(servico);
        servicoCredito.setIdclienteCreditoEntrada(creditoEntrada);

        return salvar(servicoCredito);
    }

    public List<ServicoCredito> retornaTodosPorServico(Servico servico) {
        List<ServicoCredito> resultset = new ArrayList();
        
        Query query = createQuery("SELECT servicoCredito FROM ServicoCredito AS servicoCredito "
                + " INNER JOIN servicoCredito.idclienteCreditoEntrada AS creditoEntrada "
                + " INNER JOIN servicoCredito.idservico AS servico "
                + " WHERE creditoEntrada.excluido = false "
                + " AND servicoCredito.excluido = false "
                + " AND servicoCredito.idservico = :servico "
                + " ORDER BY creditoEntrada.data DESC");
        query.setParameter("servico", servico);
        
        return resultset;
    }
}
