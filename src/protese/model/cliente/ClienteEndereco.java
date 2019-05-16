package protese.model.cliente;

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
@Table(name = "cliente_endereco")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ClienteEndereco.findAll", query = "SELECT c FROM ClienteEndereco c")})
public class ClienteEndereco implements Serializable, Entidade {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idcliente_endereco")
    private Long idclienteEndereco;
    @Basic(optional = false)
    @Column(name = "excluido")
    private boolean excluido;
    @JoinColumn(name = "idcliente", referencedColumnName = "idcliente")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Cliente idcliente;
    @JoinColumn(name = "idendereco", referencedColumnName = "idendereco")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Endereco idendereco;

    public ClienteEndereco() {
    }

    public ClienteEndereco(Long idclienteEndereco) {
        this.idclienteEndereco = idclienteEndereco;
    }

    public ClienteEndereco(Long idclienteEndereco, boolean excluido) {
        this.idclienteEndereco = idclienteEndereco;
        this.excluido = excluido;
    }

    @Override
    public Long getId() {
        return idclienteEndereco;
    }

    public Long getIdclienteEndereco() {
        return idclienteEndereco;
    }

    public void setIdclienteEndereco(Long idclienteEndereco) {
        this.idclienteEndereco = idclienteEndereco;
    }

    public boolean getExcluido() {
        return excluido;
    }

    public void setExcluido(boolean excluido) {
        this.excluido = excluido;
    }

    public Cliente getIdcliente() {
        return idcliente;
    }

    public void setIdcliente(Cliente idcliente) {
        this.idcliente = idcliente;
    }

    public Endereco getIdendereco() {
        return idendereco;
    }

    public void setIdendereco(Endereco idendereco) {
        this.idendereco = idendereco;
    }

}
