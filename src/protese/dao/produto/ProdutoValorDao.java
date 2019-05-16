package protese.dao.produto;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Query;
import protese.jpa.interfaces.Dao;
import protese.model.produto.Produto;
import protese.model.produto.ProdutoValor;

/**
 *
 * @author vinihds
 */
public class ProdutoValorDao extends Dao<ProdutoValor> {

    private static ProdutoValorDao unique = null;

    private ProdutoValorDao() {
    }

    public static ProdutoValorDao getInstance() {
        if (unique == null) {
            unique = new ProdutoValorDao();
        }
        return unique;
    }

    public ProdutoValor salvar(ProdutoValor produtoValor) {
        try {
            produtoValor = super.gravar(produtoValor);
        } catch (Exception e) {
        }
        return produtoValor;
    }

    public ProdutoValor deletar(ProdutoValor produtoValor) {
        try {
            produtoValor.setExcluido(true);
            produtoValor = salvar(produtoValor);
        } catch (Exception e) {
        }
        return produtoValor;
    }

    public List<ProdutoValor> retornaTodosPorProduto(Produto produto) {
        List<ProdutoValor> resultset = new ArrayList();

        Query query = createQuery("SELECT produtoValor FROM ProdutoValor AS produtoValor "
                + " INNER JOIN produtoValor.idproduto AS produto "
                + " WHERE produtoValor.excluido = false "
                + " AND produto.excluido = false "
                + " AND produtoValor.idproduto = :produto "
                + " ORDER BY produtoValor.idprodutoValor");
        query.setParameter("produto", produto);

        resultset = query.getResultList();

        return resultset;
    }

}
