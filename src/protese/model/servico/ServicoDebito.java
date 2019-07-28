package protese.model.servico;

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
import protese.model.cliente.ClienteDebito;

/**
 *
 * @author Vinicius Silveira
 */
@Entity
@Table(name = "servico_debito")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ServicoDebito.findAll", query = "SELECT s FROM ServicoDebito s")})
public class ServicoDebito implements Serializable, Entidade {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idservico_debito")
    private Long idservicoDebito;
    @Basic(optional = false)
    @Column(name = "excluido")
    private boolean excluido;
    @JoinColumn(name = "idcliente_debito", referencedColumnName = "idcliente_debito")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private ClienteDebito idclienteDebito;
    @JoinColumn(name = "idservico", referencedColumnName = "idservico")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Servico idservico;

    public ServicoDebito() {
    }

    public ServicoDebito(Long idservicoDebito) {
        this.idservicoDebito = idservicoDebito;
    }

    public ServicoDebito(Long idservicoDebito, boolean excluido) {
        this.idservicoDebito = idservicoDebito;
        this.excluido = excluido;
    }

    @Override
    public Long getId() {
        return idservicoDebito;
    }

    public Long getIdservicoDebito() {
        return idservicoDebito;
    }

    public void setIdservicoDebito(Long idservicoDebito) {
        this.idservicoDebito = idservicoDebito;
    }

    public boolean getExcluido() {
        return excluido;
    }

    public void setExcluido(boolean excluido) {
        this.excluido = excluido;
    }

    public ClienteDebito getIdclienteDebito() {
        return idclienteDebito;
    }

    public void setIdclienteDebito(ClienteDebito idclienteDebito) {
        this.idclienteDebito = idclienteDebito;
    }

    public Servico getIdservico() {
        return idservico;
    }

    public void setIdservico(Servico idservico) {
        this.idservico = idservico;
    }

}
