package protese.dao.servico;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Query;
import protese.dao.cliente.ClienteCreditoEntradaDao;
import protese.dao.cliente.ClienteDebitoDao;
import protese.jpa.interfaces.Dao;
import protese.model.cliente.Cliente;
import protese.model.servico.Servico;
import protese.util.utilidade.Utilidade;

/**
 *
 * @author vinihds
 */
public class ServicoDao extends Dao<Servico> {

    private static ServicoDao unique = null;

    private ClienteCreditoEntradaDao creditoEntradaDao = ClienteCreditoEntradaDao.getInstance();
    private ClienteDebitoDao clienteDebitoDao = ClienteDebitoDao.getInstance();
    private Utilidade utilidade = Utilidade.getInstance();

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
                + " ORDER BY servico.dataReferente DESC");

        resultset = query.getResultList();

        return resultset;
    }

    public List<Servico> retornaTodosPorCliente(Cliente cliente) {
        List<Servico> resultset = new ArrayList();

        Query query = createQuery("SELECT servico FROM Servico AS servico "
                + " INNER JOIN servico.idcliente AS cliente "
                + " WHERE servico.excluido = false "
                + " AND servico.idcliente = :cliente "
                + " ORDER BY servico.dataReferente DESC");
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
                + " ORDER BY servico.dataReferente DESC");
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
                + " ORDER BY servico.dataReferente DESC");
        query.setParameter("pesquisa", "%" + pesquisa + "%");

        resultset = query.getResultList();

        return resultset;
    }

    public Servico finalizarServico(Servico servico) {
        double totalServico = servico.getValorTotalServico();
        double totalPago = servico.getValorTotalPago();
        double valorCredito = 0;
        double valorDebito = 0;

        if (totalPago > totalServico) {
            valorCredito = totalPago - totalServico;
        } else if (totalServico > totalPago) {
            valorDebito = totalServico - totalPago;
        }

        if (valorCredito > 0) {
            //Deve adicionar crédito
            creditoEntradaDao.salvarClienteCreditoEntrada(servico.getIdcliente(), servico, valorCredito, LocalDateTime.now());
        }

        if (valorDebito > 0) {
            //Deve adicionar débito do cliente
            clienteDebitoDao.geraDebitoCliente(servico.getIdcliente(),
                    valorDebito,
                    "Débito pendente - " + utilidade.mesAno(servico.getDataReferente()).toUpperCase(),
                    LocalDateTime.now());
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
                + " ORDER BY servico.dataReferente DESC");
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
                + " ORDER BY servico.dataReferente DESC");
        query.setParameter("pesquisa", "%" + pesquisa + "%");

        resultset = query.getResultList();

        return resultset;
    }

    public List<Servico> retornaTodosPorPesquisa(String pesquisa, Cliente cliente, LocalDateTime dataInicial, LocalDateTime dataFinal, int filtro) {
        List<Servico> resultset = new ArrayList();

        String sql = "SELECT servico FROM Servico AS servico "
                + " INNER JOIN servico.idcliente AS cliente "
                + " WHERE servico.excluido = false "
                + " AND (LOWER(servico.titulo) LIKE LOWER(:pesquisa) "
                + "     OR LOWER(servico.descricao) LIKE LOWER(:pesquisa)) "
                + " AND servico.dataReferente BETWEEN :dataInicial AND :dataFinal ";

        if (cliente != null && cliente.getId() != null && cliente.getId() > 0) {
            sql += " AND servico.idcliente = :cliente ";
        }

        if (filtro == 1) {
            //Em aberto
            sql += " AND servico.dataFinalizacao IS NULL ";
        } else if (filtro == 2) {
            //Finalizados
            sql += " AND servico.dataFinalizacao IS NOT NULL ";
        }

        sql += " ORDER BY servico.dataReferente DESC";

        Query query = createQuery(sql);
        query.setParameter("pesquisa", "%" + pesquisa + "%");

        if (cliente != null && cliente.getId() != null && cliente.getId() > 0) {
            query.setParameter("cliente", cliente);
        }

        query.setParameter("dataInicial", dataInicial);
        query.setParameter("dataFinal", dataFinal);

        resultset = query.getResultList();

        return resultset;
    }
}
