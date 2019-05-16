package protese.model.produto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import protese.dao.produto.ProdutoValorDao;
import protese.jpa.interfaces.Entidade;

/**
 *
 * @author vinihds
 */
@Entity
@Table(name = "produto")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Produto.findAll", query = "SELECT p FROM Produto p")})
public class Produto implements Serializable, Entidade {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idproduto")
    private Long idproduto;
    @Column(name = "codigo")
    private String codigo;
    @Column(name = "nome")
    private String nome;
    @Column(name = "descricao")
    private String descricao;
    @Basic(optional = false)
    @Column(name = "excluido")
    private boolean excluido;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idproduto", fetch = FetchType.LAZY)
    private List<ProdutoValor> produtoValorList;

    public Produto() {
    }

    public Produto(Long idproduto) {
        this.idproduto = idproduto;
    }

    public Produto(Long idproduto, boolean excluido) {
        this.idproduto = idproduto;
        this.excluido = excluido;
    }

    @Override
    public Long getId() {
        return idproduto;
    }

    public Long getIdproduto() {
        return idproduto;
    }

    public void setIdproduto(Long idproduto) {
        this.idproduto = idproduto;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public boolean getExcluido() {
        return excluido;
    }

    public void setExcluido(boolean excluido) {
        this.excluido = excluido;
    }

    @XmlTransient
    public List<ProdutoValor> getProdutoValorList() {
        ProdutoValorDao produtoValorDao = ProdutoValorDao.getInstance();

        produtoValorList = new ArrayList();

        if (idproduto != null && idproduto > 0) {
            produtoValorList = produtoValorDao.retornaTodosPorProduto(this);
        }

        return produtoValorList;
    }

    public void setProdutoValorList(List<ProdutoValor> produtoValorList) {
        this.produtoValorList = produtoValorList;
    }

}
