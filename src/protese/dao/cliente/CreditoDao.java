package protese.dao.cliente;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import protese.jpa.interfaces.Dao;
import protese.model.cliente.Cliente;
import protese.model.cliente.ClienteCreditoEntrada;
import protese.model.cliente.ClienteCreditoSaida;
import protese.model.cliente.Credito;

/**
 *
 * @author vinihds
 */
public class CreditoDao extends Dao<Credito> {

    private static CreditoDao unique = null;

    private ClienteCreditoEntradaDao creditoEntradaDao = ClienteCreditoEntradaDao.getInstance();
    private ClienteCreditoSaidaDao creditoSaidaDao = ClienteCreditoSaidaDao.getInstance();

    private CreditoDao() {
    }

    public static CreditoDao getInstance() {
        if (unique == null) {
            unique = new CreditoDao();
        }
        return unique;
    }

    private Credito preencheCredito(ClienteCreditoEntrada creditoEntrada) {
        Credito credito = new Credito();

        credito.setIdcredito(creditoEntrada.getId());
        credito.setServico(creditoEntrada.getIdservico());
        credito.setTipo("Entrada");
        credito.setData(creditoEntrada.getIdservico().getDataFinalizacao());
        credito.setValor(creditoEntrada.getValorCredito());

        return credito;
    }

    private Credito preencheCredito(ClienteCreditoSaida creditoSaida) {
        Credito credito = new Credito();

        credito.setIdcredito(creditoSaida.getId());
        credito.setServico(creditoSaida.getIdservicoPagamento().getIdservico());
        credito.setTipo("Saida");
        credito.setData(creditoSaida.getIdservicoPagamento().getIdpagamento().getDataPagamento());
        credito.setValor(creditoSaida.getIdservicoPagamento().getIdpagamento().getValor());

        return credito;
    }

    public List<Credito> retornaTodosPorCliente(Cliente cliente) {
        List<Credito> creditoList = new ArrayList();

        for (ClienteCreditoEntrada creditoEntrada : creditoEntradaDao.retornaTodosPorCliente(cliente)) {
            creditoList.add(preencheCredito(creditoEntrada));
        }

        for (ClienteCreditoSaida creditoSaida : creditoSaidaDao.retornaTodosPorCliente(cliente)) {
            creditoList.add(preencheCredito(creditoSaida));
        }

        creditoList.sort(Comparator.comparing(credito -> credito.getData()));

        return creditoList;
    }

    public List<Credito> retornaTodosPorCliente(Cliente cliente, int tipo, LocalDateTime dataInicial, LocalDateTime dataFinal) {
        //Tipo: 0 - Todos/ 1 - Somente entrada/ 2 - Somente saida
        List<Credito> creditoList = new ArrayList();

        //Entrada
        if (tipo == 0 || tipo == 1) {
            for (ClienteCreditoEntrada creditoEntrada : creditoEntradaDao.retornaTodosPorCliente(cliente, dataInicial, dataFinal)) {
                creditoList.add(preencheCredito(creditoEntrada));
            }
        }

        //Saida
        if (tipo == 0 || tipo == 2) {
            for (ClienteCreditoSaida creditoSaida : creditoSaidaDao.retornaTodosPorCliente(cliente, dataInicial, dataFinal)) {
                creditoList.add(preencheCredito(creditoSaida));
            }
        }

        //Ordenação
        creditoList.sort(Comparator.comparing(credito -> credito.getData()));

        return creditoList;
    }
}
