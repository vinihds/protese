package protese.dao.servico;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Query;
import protese.dao.cliente.ClienteDebitoDao;
import protese.jpa.interfaces.Dao;
import protese.model.cliente.Cliente;
import protese.model.cliente.ClienteDebito;
import protese.model.servico.Servico;
import protese.model.servico.ServicoDebito;

/**
 *
 * @author Vinicius Silveira
 */
public class ServicoDebitoDao extends Dao<ServicoDebito> {

    private static ServicoDebitoDao unique = null;
    
    private ClienteDebitoDao clienteDebitoDao = ClienteDebitoDao.getInstance();

    private ServicoDebitoDao() {
    }

    public static ServicoDebitoDao getInstance() {
        if (unique == null) {
            unique = new ServicoDebitoDao();
        }
        return unique;
    }

    public ServicoDebito salvar(ServicoDebito servicoDebito) {
        try {
            servicoDebito = super.gravar(servicoDebito);
        } catch (Exception e) {
        }
        return servicoDebito;
    }

    public ServicoDebito deletar(ServicoDebito servicoDebito) {
        try {
            servicoDebito.setExcluido(true);
            servicoDebito = salvar(servicoDebito);
        } catch (Exception e) {
        }
        return servicoDebito;
    }
    
    public List<ServicoDebito> retornaTodosAtivosPorCliente(Cliente cliente) {
        List<ServicoDebito> resultset = new ArrayList();
        
        Query query = createQuery("SELECT servicoDebito FROM ServicoDebito AS servicoDebito "
                + " INNER JOIN servicoDebito.idclienteDebito AS clienteDebito "
                + " INNER JOIN servicoDebito.idservico AS servico "
                + " WHERE servicoDebito.excluido = false "
                + " AND servico.excluido = false "
                + " AND clienteDebito.excluido = false "
                + " AND servico.dataFinalizacao IS NULL "
                + " AND servico.idcliente = :cliente "
                + " ORDER BY clienteDebito.data DESC");
        query.setParameter("cliente", cliente);
        
        resultset = query.getResultList();
        
        return resultset;
    }

    public ServicoDebito salvarServicoDebito(ClienteDebito clienteDebito, Servico servico) {
        ServicoDebito servicoDebito = new ServicoDebito();

        servicoDebito.setIdservico(servico);
        servicoDebito.setIdclienteDebito(clienteDebito);

        return salvar(servicoDebito);
    }
    
    public void verificaAgregarDebitos(Servico servico) {
        for (ClienteDebito debito : clienteDebitoDao.retornaTodosNaoUtilizadosPorCliente(servico.getIdcliente())) {
            ServicoDebito servicoDebito = new ServicoDebito();
            
            servicoDebito.setIdservico(servico);
            servicoDebito.setIdclienteDebito(debito);
            
            salvar(servicoDebito);
        }
    }

    public List<ServicoDebito> retornaTodosPorServico(Servico servico) {
        List<ServicoDebito> resultset = new ArrayList();
        
        Query query = createQuery("SELECT servicoDebito FROM ServicoDebito AS servicoDebito "
                + " INNER JOIN servicoDebito.idclienteDebito AS clienteDebito "
                + " INNER JOIN servicoDebito.idservico AS servico "
                + " WHERE servicoDebito.excluido = false "
                + " AND servico.excluido = false "
                + " AND clienteDebito.excluido = false "
                + " AND servicoDebito.idservico = :servico "
                + " ORDER BY clienteDebito.data DESC");
        query.setParameter("servico", servico);
        
        resultset = query.getResultList();
        
        return resultset;
    }
}
