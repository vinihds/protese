package protese.dao.servico;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Query;
import protese.jpa.interfaces.Dao;
import protese.model.cliente.Cliente;
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
    
    public List<ServicoDetalhe> retornaTodosPorClientePorPeriodo(Cliente cliente, LocalDateTime dataDe, LocalDateTime dataAte) {
        List<ServicoDetalhe> resultset = new ArrayList();
        
        Query query = createQuery("SELECT detalhe FROM ServicoDetalhe AS detalhe "
                + " INNER JOIN detalhe.idprodutoValor AS produtoValor "
                + " INNER JOIN produtoValor.idproduto AS produto "
                + " INNER JOIN produtoValor.idgrupo AS grupo "
                + " INNER JOIN detalhe.idservico AS servico "
                + " INNER JOIN servico.idcliente AS cliente "
                + " WHERE servico.idcliente = :cliente "
                + " AND detalhe.dataLancamento BETWEEN :dataDe AND :dataAte "
                + " AND detalhe.excluido = false "
                + " AND servico.excluido = false "
                + " ORDER BY servico.dataReferente DESC, detalhe.dataLancamento DESC");
        query.setParameter("cliente", cliente);
        query.setParameter("dataDe", dataDe);
        query.setParameter("dataAte", dataAte);
        
        resultset = query.getResultList();
        
        return resultset;
    }
}
