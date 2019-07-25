package protese.dao.produto;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Query;
import protese.jpa.interfaces.Dao;
import protese.model.produto.Produto;

/**
 *
 * @author vinihds
 */
public class ProdutoDao extends Dao<Produto> {

    private static ProdutoDao unique = null;

    private ProdutoDao() {
    }

    public static ProdutoDao getInstance() {
        if (unique == null) {
            unique = new ProdutoDao();
        }
        return unique;
    }

    public Produto salvar(Produto produto) {
        try {
            produto = super.gravar(produto);
        } catch (Exception e) {
        }
        return produto;
    }

    public Produto deletar(Produto produto) {
        try {
            produto.setExcluido(true);
            produto = salvar(produto);
        } catch (Exception e) {
        }
        return produto;
    }

    public List<Produto> retornaTodos() {
        List<Produto> resultset = new ArrayList();

        Query query = createQuery("SELECT produto FROM Produto AS produto "
                + " WHERE produto.excluido = false "
                + " ORDER BY produto.nome");

        resultset = query.getResultList();

        return resultset;
    }

    public List<Produto> retornaTodosPorNome(String nome) {
        List<Produto> resultset = new ArrayList();

        Query query = createQuery("SELECT produto FROM Produto AS produto "
                + " WHERE produto.excluido = false "
                + " AND LOWER(produto.nome) LIKE LOWER(:nome) "
                + " ORDER BY produto.nome");
        query.setParameter("nome", "%" + nome + "%");

        resultset = query.getResultList();

        return resultset;
    }

    public List<Produto> retornaTodosPorCodigoProprio(String codigoProprio) {
        List<Produto> resultset = new ArrayList();

        Query query = createQuery("SELECT produto FROM Produto AS produto "
                + " WHERE produto.excluido = false "
                + " AND LOWER(produto.codigo) LIKE LOWER(:codigoProprio) "
                + " ORDER BY produto.nome");
        query.setParameter("codigoProprio", "%" + codigoProprio + "%");

        resultset = query.getResultList();

        return resultset;
    }

    public List<Produto> retornaTodosPorGrupo(String codigoNomeGrupo) {
        List<Produto> resultset = new ArrayList();

        Query query = createQuery("SELECT produto FROM Produto AS produto "
                + " INNER JOIN ProdutoValor AS produtoValor ON produto.idproduto = produtoValor.idproduto "
                + " INNER JOIN produtoValor.idgrupo AS grupo "
                + " WHERE produto.excluido = false "
                + " AND produtoValor.excluido = false "
                + " AND grupo.excluido = false "
                + " AND (LOWER(grupo.codigo) LIKE LOWER(:codigoProprio) "
                + "     OR LOWER(grupo.nome) LIKE LOWER(:codigoProprio)) "
                + " ORDER BY produto.nome");
        query.setParameter("codigoProprio", "%" + codigoNomeGrupo + "%");

        resultset = query.getResultList();

        return resultset;
    }

    public List<Produto> retornaTodosPorNomeOuCodigoProprioOuGrupo(String pesquisa) {
        List<Produto> resultset = new ArrayList();

        Query query = createQuery("SELECT produto FROM Produto AS produto "
                + " WHERE produto.excluido = false "
                + " AND (LOWER(produto.nome) LIKE LOWER(:pesquisa) "
                + "     OR LOWER(produto.codigo) LIKE LOWER(:pesquisa) "
                + "     OR (SELECT COUNT(produtoValor) FROM ProdutoValor AS produtoValor "
                + "         INNER JOIN produtoValor.idproduto AS produtoTemp "
                + "         INNER JOIN produtoValor.idgrupo AS grupo "
                + "         WHERE produtoTemp.excluido = false "
                + "         AND produtoValor.excluido = false "
                + "         AND grupo.excluido = false "
                + "         AND (LOWER(grupo.codigo) LIKE LOWER(:pesquisa) "
                + "             OR LOWER(grupo.nome) LIKE LOWER(:pesquisa)) "
                + "     ) > 0) "
                + " ORDER BY produto.nome");
        query.setParameter("pesquisa", "%" + pesquisa + "%");

        resultset = query.getResultList();

        return resultset;
    }
}
