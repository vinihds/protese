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
import protese.model.cliente.ClienteCreditoEntrada;

/**
 *
 * @author Vinicius Silveira
 */
@Entity
@Table(name = "servico_credito")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ServicoCredito.findAll", query = "SELECT s FROM ServicoCredito s")})
public class ServicoCredito implements Serializable, Entidade {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idservico_credito")
    private Long idservicoCredito;
    @Basic(optional = false)
    @Column(name = "excluido")
    private boolean excluido;
    @JoinColumn(name = "idcliente_credito_entrada", referencedColumnName = "idcliente_credito_entrada")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private ClienteCreditoEntrada idclienteCreditoEntrada;
    @JoinColumn(name = "idservico", referencedColumnName = "idservico")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Servico idservico;

    public ServicoCredito() {
    }

    public ServicoCredito(Long idservicoCredito) {
        this.idservicoCredito = idservicoCredito;
    }

    public ServicoCredito(Long idservicoCredito, boolean excluido) {
        this.idservicoCredito = idservicoCredito;
        this.excluido = excluido;
    }

    @Override
    public Long getId() {
        return idservicoCredito;
    }

    public Long getIdservicoCredito() {
        return idservicoCredito;
    }

    public void setIdservicoCredito(Long idservicoCredito) {
        this.idservicoCredito = idservicoCredito;
    }

    public boolean getExcluido() {
        return excluido;
    }

    public void setExcluido(boolean excluido) {
        this.excluido = excluido;
    }

    public ClienteCreditoEntrada getIdclienteCreditoEntrada() {
        return idclienteCreditoEntrada;
    }

    public void setIdclienteCreditoEntrada(ClienteCreditoEntrada idclienteCreditoEntrada) {
        this.idclienteCreditoEntrada = idclienteCreditoEntrada;
    }

    public Servico getIdservico() {
        return idservico;
    }

    public void setIdservico(Servico idservico) {
        this.idservico = idservico;
    }

}
