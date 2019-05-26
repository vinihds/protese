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
                + " ORDER BY produtoValor.valor");
        query.setParameter("produto", produto);

        resultset = query.getResultList();

        return resultset;
    }

    public List<ProdutoValor> retornaTodos() {
        List<ProdutoValor> resultset = new ArrayList();

        Query query = createQuery("SELECT produtoValor FROM ProdutoValor AS produtoValor "
                + " INNER JOIN produtoValor.idproduto AS produto "
                + " WHERE produtoValor.excluido = false "
                + " AND produto.excluido = false "
                + " ORDER BY produtoValor.valor, produto.nome");

        resultset = query.getResultList();

        return resultset;
    }

    public List<ProdutoValor> retornaTodosPorNome(String nome) {
        List<ProdutoValor> resultset = new ArrayList();

        Query query = createQuery("SELECT produtoValor FROM ProdutoValor AS produtoValor "
                + " INNER JOIN produtoValor.idproduto AS produto "
                + " WHERE produtoValor.excluido = false "
                + " AND produto.excluido = false "
                + " AND produto.nome LIKE :produto "
                + " ORDER BY produtoValor.valor, produto.nome");
        query.setParameter("nome", "%" + nome + "%");

        resultset = query.getResultList();

        return resultset;
    }

    public List<ProdutoValor> retornaTodosPorCodigoProprio(String codigoProprio) {
        List<ProdutoValor> resultset = new ArrayList();

        Query query = createQuery("SELECT produtoValor FROM ProdutoValor AS produtoValor "
                + " INNER JOIN produtoValor.idproduto AS produto "
                + " WHERE produtoValor.excluido = false "
                + " AND produto.excluido = false "
                + " AND produto.codigo LIKE :codigoProprio "
                + " ORDER BY produtoValor.valor, produto.nome");
        query.setParameter("codigoProprio", "%" + codigoProprio + "%");

        resultset = query.getResultList();

        return resultset;
    }

    public List<ProdutoValor> retornaTodosPorGrupo(String codigoNomeGrupo) {
        List<ProdutoValor> resultset = new ArrayList();

        Query query = createQuery("SELECT produtoValor FROM ProdutoValor AS produtoValor "
                + " INNER JOIN produtoValor.idproduto AS produto "
                + " INNER JOIN produtoValor.idgrupo AS grupo "
                + " WHERE produto.excluido = false "
                + " AND produtoValor.excluido = false "
                + " AND grupo.excluido = false "
                + " AND (idgrupo.codigo LIKE :codigoProprio "
                + "     OR idgrupo.nome LIKE :codigoProprio) "
                + " ORDER BY produtoValor.valor, produto.nome");
        query.setParameter("codigoProprio", "%" + codigoNomeGrupo + "%");

        resultset = query.getResultList();

        return resultset;
    }
}
