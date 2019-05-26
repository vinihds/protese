package protese.dao.pagamento;

import protese.jpa.interfaces.Dao;
import protese.model.pagamento.Pagamento;

/**
 *
 * @author vinihds
 */
public class PagamentoDao extends Dao<Pagamento> {

    private static PagamentoDao unique = null;

    private PagamentoDao() {
    }

    public static PagamentoDao getInstance() {
        if (unique == null) {
            unique = new PagamentoDao();
        }
        return unique;
    }

    public Pagamento salvar(Pagamento pagamento) {
        try {
            pagamento = super.gravar(pagamento);
        } catch (Exception e) {
        }
        return pagamento;
    }

    public Pagamento deletar(Pagamento pagamento) {
        try {
            pagamento.setExcluido(true);
            pagamento = salvar(pagamento);
        } catch (Exception e) {
        }
        return pagamento;
    }

}
