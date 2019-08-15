package protese.dao.cliente;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Query;
import protese.dao.servico.ServicoCreditoDao;
import protese.jpa.interfaces.Dao;
import protese.model.cliente.Cliente;
import protese.model.cliente.ClienteCreditoEntrada;
import protese.model.servico.Servico;
import protese.util.utilidade.Utilidade;

/**
 *
 * @author vinihds
 */
public class ClienteCreditoEntradaDao extends Dao<ClienteCreditoEntrada> {

    private static ClienteCreditoEntradaDao unique = null;

    private Utilidade utilidade = Utilidade.getInstance();
    private ServicoCreditoDao servicoCreditoDao = ServicoCreditoDao.getInstance();

    private ClienteCreditoEntradaDao() {
    }

    public static ClienteCreditoEntradaDao getInstance() {
        if (unique == null) {
            unique = new ClienteCreditoEntradaDao();
        }
        return unique;
    }

    public ClienteCreditoEntrada salvar(ClienteCreditoEntrada creditoEntrada) {
        try {
            creditoEntrada = super.gravar(creditoEntrada);
        } catch (Exception e) {
        }
        return creditoEntrada;
    }

    public ClienteCreditoEntrada deletar(ClienteCreditoEntrada creditoEntrada) {
        try {
            creditoEntrada.setExcluido(true);
            creditoEntrada = salvar(creditoEntrada);
        } catch (Exception e) {
        }
        return creditoEntrada;
    }

    public List<ClienteCreditoEntrada> retornaTodosPorCliente(Cliente cliente) {
        List<ClienteCreditoEntrada> resultset = new ArrayList();

        Query query = createQuery("SELECT creditoEntrada FROM ClienteCreditoEntrada AS creditoEntrada "
                + " INNER JOIN creditoEntrada.idcliente AS cliente "
                + " WHERE creditoEntrada.excluido = false "
                + " AND creditoEntrada.idcliente = :cliente "
                + " ORDER BY creditoEntrada.data DESC");
        query.setParameter("cliente", cliente);

        resultset = query.getResultList();

        return resultset;
    }

    public ClienteCreditoEntrada salvarClienteCreditoEntrada(Cliente cliente, Servico servico, double valorCredito, LocalDateTime dataCredito) {
        ClienteCreditoEntrada creditoEntrada = salvarClienteCreditoEntrada(cliente,
                "Entrada de crédito - " + utilidade.mesAno(servico.getDataReferente()).toUpperCase(),
                valorCredito,
                dataCredito);

        servicoCreditoDao.salvarServicoCredito(creditoEntrada, servico);

        return creditoEntrada;
    }

    public ClienteCreditoEntrada salvarClienteCreditoEntrada(Cliente cliente, String descricao, double valorCredito, LocalDateTime dataCredito) {
        ClienteCreditoEntrada creditoEntrada = new ClienteCreditoEntrada();

        creditoEntrada.setIdcliente(cliente);
        creditoEntrada.setDescricao(descricao);
        creditoEntrada.setValorCredito(valorCredito);
        creditoEntrada.setData(dataCredito);

        return salvar(creditoEntrada);
    }

    public List<ClienteCreditoEntrada> retornaTodosPorCliente(Cliente cliente, LocalDateTime dataInicial, LocalDateTime dataFinal) {
        List<ClienteCreditoEntrada> resultset = new ArrayList();

        Query query = createQuery("SELECT creditoEntrada FROM ClienteCreditoEntrada AS creditoEntrada "
                + " INNER JOIN creditoEntrada.idcliente AS cliente "
                + " WHERE creditoEntrada.excluido = false "
                + " AND creditoEntrada.data BETWEEN :dataInicial AND :dataFinal "
                + " AND creditoEntrada.idcliente = :cliente "
                + " ORDER BY creditoEntrada.data DESC");
        query.setParameter("cliente", cliente);
        query.setParameter("dataInicial", dataInicial);
        query.setParameter("dataFinal", dataFinal);

        resultset = query.getResultList();

        return resultset;
    }
}
