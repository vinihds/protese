package protese.model.produto;

import protese.model.produto.ProdutoValor;
import java.io.Serializable;
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
import protese.jpa.interfaces.Entidade;

/**
 *
 * @author vinihds
 */
@Entity
@Table(name = "grupo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Grupo.findAll", query = "SELECT g FROM Grupo g")})
public class Grupo implements Serializable, Entidade {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idgrupo")
    private Long idgrupo;
    @Column(name = "codigo")
    private String codigo;
    @Column(name = "nome")
    private String nome;
    @Basic(optional = false)
    @Column(name = "excluido")
    private boolean excluido;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idgrupo", fetch = FetchType.LAZY)
    private List<ProdutoValor> produtoValorList;

    public Grupo() {
    }

    public Grupo(Long idgrupo) {
        this.idgrupo = idgrupo;
    }

    public Grupo(Long idgrupo, boolean excluido) {
        this.idgrupo = idgrupo;
        this.excluido = excluido;
    }

    @Override
    public Long getId() {
        return idgrupo;
    }

    public Long getIdgrupo() {
        return idgrupo;
    }

    public void setIdgrupo(Long idgrupo) {
        this.idgrupo = idgrupo;
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

    public boolean getExcluido() {
        return excluido;
    }

    public void setExcluido(boolean excluido) {
        this.excluido = excluido;
    }

    @XmlTransient
    public List<ProdutoValor> getProdutoValorList() {
        return produtoValorList;
    }

    public void setProdutoValorList(List<ProdutoValor> produtoValorList) {
        this.produtoValorList = produtoValorList;
    }

}
