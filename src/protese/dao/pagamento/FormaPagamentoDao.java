package protese.dao.pagamento;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Query;
import protese.jpa.interfaces.Dao;
import protese.model.pagamento.FormaPagamento;

/**
 *
 * @author vinihds
 */
public class FormaPagamentoDao extends Dao<FormaPagamento> {

    private static FormaPagamentoDao unique = null;

    private FormaPagamentoDao() {
    }

    public static FormaPagamentoDao getInstance() {
        if (unique == null) {
            unique = new FormaPagamentoDao();
        }
        return unique;
    }

    public FormaPagamento salvar(FormaPagamento formaPagamento) {
        try {
            formaPagamento = super.gravar(formaPagamento);
        } catch (Exception e) {
        }
        return formaPagamento;
    }

    public FormaPagamento deletar(FormaPagamento formaPagamento) {
        try {
            formaPagamento.setExcluido(true);
            formaPagamento = salvar(formaPagamento);
        } catch (Exception e) {
        }
        return formaPagamento;
    }

    public List<FormaPagamento> retornaTodos() {
        List<FormaPagamento> resultset = new ArrayList();

        Query query = createQuery("SELECT formaPagamento FROM FormaPagamento AS formaPagamento "
                + " WHERE formaPagamento.excluido = false "
                + " AND formaPagamento.visivel = true "
                + " ORDER BY formaPagamento.idformaPagamento");

        resultset = query.getResultList();

        return resultset;
    }

    public FormaPagamento retornaFormaPagamentoCreditoEntrada() {
        FormaPagamento formaPagamento = new FormaPagamento();

        Query query = createQuery("SELECT formaPagamento FROM FormaPagamento AS formaPagamento "
                + " WHERE formaPagamento.excluido = false "
                + " AND formaPagamento.tipo = 'creditoEntrada'");

        formaPagamento = (FormaPagamento) query.getSingleResult();

        return formaPagamento;
    }

    public FormaPagamento retornaFormaPagamentoCreditoSaida() {
        FormaPagamento formaPagamento = new FormaPagamento();

        Query query = createQuery("SELECT formaPagamento FROM FormaPagamento AS formaPagamento "
                + " WHERE formaPagamento.excluido = false "
                + " AND formaPagamento.tipo = 'creditoSaida'");

        formaPagamento = (FormaPagamento) query.getSingleResult();
        
        return formaPagamento;
    }
}
