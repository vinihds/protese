package protese.dao.servico;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Query;
import protese.dao.cliente.ClienteCreditoEntradaDao;
import protese.jpa.interfaces.Dao;
import protese.model.cliente.Cliente;
import protese.model.servico.Servico;

/**
 *
 * @author vinihds
 */
public class ServicoDao extends Dao<Servico> {

    private static ServicoDao unique = null;

    private ClienteCreditoEntradaDao creditoEntradaDao = ClienteCreditoEntradaDao.getInstance();

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

    public List<Servico> retornaTodosPorCliente(Cliente cliente) {
        List<Servico> resultset = new ArrayList();

        Query query = createQuery("SELECT servico FROM Servico AS servico "
                + " INNER JOIN servico.idcliente AS cliente "
                + " WHERE servico.excluido = false "
                + " AND servico.idcliente = :cliente "
                + " ORDER BY servico.dataCriacao DESC");
        query.setParameter("cliente", cliente);

        resultset = query.getResultList();

        return resultset;
    }
    
    public List<Servico> retornaTodosPorClienteNome(String pesquisa) {
        List<Servico> resultset = new ArrayList();

        Query query = createQuery("SELECT servico FROM Servico AS servico "
                + " INNER JOIN servico.idcliente AS cliente "
                + " WHERE servico.excluido = false "
                + " AND (cliente.nome LIKE :pesquisa "
                + "     OR cliente.documento LIKE :pesquisa) "
                + " ORDER BY servico.dataCriacao DESC");
        query.setParameter("pesquisa", "%" + pesquisa + "%");

        resultset = query.getResultList();

        return resultset;
    }
    
    public List<Servico> retornaTodosPorTitulo(String pesquisa) {
        List<Servico> resultset = new ArrayList();

        Query query = createQuery("SELECT servico FROM Servico AS servico "
                + " WHERE servico.excluido = false "
                + " AND (servico.titulo LIKE :pesquisa "
                + "     OR servico.descricao LIKE :pesquisa) "
                + " ORDER BY servico.dataCriacao DESC");
        query.setParameter("pesquisa", "%" + pesquisa + "%");

        resultset = query.getResultList();

        return resultset;
    }

    public Servico finalizarServico(Servico servico) {
        double valorCredito = servico.getValorTotalServico() - servico.getValorTotalPago();

        if (valorCredito < 0) {
            //Tornando o crédito positivo
            valorCredito = valorCredito * -1;
        }

        if (valorCredito > 0) {
            //Deve adicionar crédito
            creditoEntradaDao.salvarClienteCreditoEntrada(servico.getIdcliente(), servico, valorCredito);
        }

        servico.setDataFinalizacao(LocalDateTime.now());
        servico = salvar(servico);

        return servico;
    }

    public List<Servico> retornaTodosFinalizados(String pesquisa) {
        List<Servico> resultset = new ArrayList();

        Query query = createQuery("SELECT servico FROM Servico AS servico "
                + " WHERE servico.excluido = false "
                + " AND (servico.titulo LIKE :pesquisa "
                + "     OR servico.descricao LIKE :pesquisa) "
                + " AND servico.dataFinalizacao IS NOT NULL "
                + " ORDER BY servico.dataCriacao DESC");
        query.setParameter("pesquisa", "%" + pesquisa + "%");

        resultset = query.getResultList();

        return resultset;
    }

    public List<Servico> retornaTodosNaoFinalizados(String pesquisa) {
        List<Servico> resultset = new ArrayList();

        Query query = createQuery("SELECT servico FROM Servico AS servico "
                + " WHERE servico.excluido = false "
                + " AND (servico.titulo LIKE :pesquisa "
                + "     OR servico.descricao LIKE :pesquisa) "
                + " AND servico.dataFinalizacao IS NULL "
                + " ORDER BY servico.dataCriacao DESC");
        query.setParameter("pesquisa", "%" + pesquisa + "%");

        resultset = query.getResultList();

        return resultset;
    }
}
