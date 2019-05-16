package protese.model.produto;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import protese.jpa.interfaces.Entidade;

/**
 *
 * @author vinihds
 */
@Entity
@Table(name = "produto_valor")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ProdutoValor.findAll", query = "SELECT p FROM ProdutoValor p")})
public class ProdutoValor implements Serializable, Entidade {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idproduto_valor")
    private Long idprodutoValor;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "valor")
    private Double valor;
    @Basic(optional = false)
    @Column(name = "excluido")
    private boolean excluido;
    @JoinColumn(name = "idgrupo", referencedColumnName = "idgrupo")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Grupo idgrupo;
    @JoinColumn(name = "idproduto", referencedColumnName = "idproduto")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Produto idproduto;

    public ProdutoValor() {
    }

    public ProdutoValor(Long idprodutoValor) {
        this.idprodutoValor = idprodutoValor;
    }

    public ProdutoValor(Long idprodutoValor, boolean excluido) {
        this.idprodutoValor = idprodutoValor;
        this.excluido = excluido;
    }

    @Override
    public Long getId() {
        return idprodutoValor;
    }

    public Long getIdprodutoValor() {
        return idprodutoValor;
    }

    public void setIdprodutoValor(Long idprodutoValor) {
        this.idprodutoValor = idprodutoValor;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public boolean getExcluido() {
        return excluido;
    }

    public void setExcluido(boolean excluido) {
        this.excluido = excluido;
    }

    public Grupo getIdgrupo() {
        return idgrupo;
    }

    public void setIdgrupo(Grupo idgrupo) {
        this.idgrupo = idgrupo;
    }

    public Produto getIdproduto() {
        return idproduto;
    }

    public void setIdproduto(Produto idproduto) {
        this.idproduto = idproduto;
    }

}
