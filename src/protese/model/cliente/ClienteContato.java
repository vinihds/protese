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
@Table(name = "cliente_contato")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ClienteContato.findAll", query = "SELECT c FROM ClienteContato c")})
public class ClienteContato implements Serializable, Entidade {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idcliente_contato")
    private Long idclienteContato;
    @Basic(optional = false)
    @Column(name = "excluido")
    private boolean excluido;
    @JoinColumn(name = "idcliente", referencedColumnName = "idcliente")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Cliente idcliente;
    @JoinColumn(name = "idcontato", referencedColumnName = "idcontato")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Contato idcontato;

    public ClienteContato() {
    }

    public ClienteContato(Long idclienteContato) {
        this.idclienteContato = idclienteContato;
    }

    public ClienteContato(Long idclienteContato, boolean excluido) {
        this.idclienteContato = idclienteContato;
        this.excluido = excluido;
    }

    @Override
    public Long getId() {
        return idclienteContato;
    }

    public Long getIdclienteContato() {
        return idclienteContato;
    }

    public void setIdclienteContato(Long idclienteContato) {
        this.idclienteContato = idclienteContato;
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

    public Contato getIdcontato() {
        return idcontato;
    }

    public void setIdcontato(Contato idcontato) {
        this.idcontato = idcontato;
    }

}
