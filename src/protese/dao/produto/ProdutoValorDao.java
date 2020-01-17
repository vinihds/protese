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
                + " AND LOWER(produto.nome) LIKE LOWER(:produto) "
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
                + " AND LOWER(produto.codigo) LIKE LOWER(:codigoProprio) "
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
                + " AND (LOWER(grupo.codigo) LIKE LOWER(:codigoProprio) "
                + "     OR LOWER(grupo.nome) LIKE LOWER(:codigoProprio)) "
                + " ORDER BY produtoValor.valor, produto.nome");
        query.setParameter("codigoProprio", "%" + codigoNomeGrupo + "%");

        resultset = query.getResultList();

        return resultset;
    }

    public List<ProdutoValor> retornaTodosPorNomeOuCodigoProprioOuGrupo(String pesquisa) {
        List<ProdutoValor> resultset = new ArrayList();

        Query query = createQuery("SELECT produtoValor FROM ProdutoValor AS produtoValor "
                + " INNER JOIN produtoValor.idproduto AS produto "
                + " INNER JOIN produtoValor.idgrupo AS grupo "
                + " WHERE produtoValor.excluido = false "
                + " AND produto.excluido = false "
                + " AND (LOWER(produto.nome) LIKE LOWER(:pesquisa) "
                + "     OR LOWER(produto.codigo) LIKE LOWER(:pesquisa) "
                + "     OR LOWER(grupo.codigo) LIKE LOWER(:pesquisa) "
                + "     OR LOWER(grupo.nome) LIKE LOWER(:pesquisa) "
                + "     OR LOWER(CONCAT(grupo.codigo, produto.codigo)) LIKE LOWER(:pesquisa)) "
                + " ORDER BY produtoValor.valor, produto.nome");
        query.setParameter("pesquisa", "%" + pesquisa + "%");

        resultset = query.getResultList();

        return resultset;
    }
}
