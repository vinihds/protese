package protese.model.cliente;

import protese.model.servico.ServicoDebito;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import protese.jpa.interfaces.Entidade;

/**
 *
 * @author Vinicius Silveira
 */
@Entity
@Table(name = "cliente_debito")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ClienteDebito.findAll", query = "SELECT c FROM ClienteDebito c")})
public class ClienteDebito implements Serializable, Entidade {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idcliente_debito")
    private Long idclienteDebito;
    @Column(name = "descricao")
    private String descricao;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "valor_debito")
    private Double valorDebito;
    @Column(name = "data")
    private LocalDateTime data;
    @Basic(optional = false)
    @Column(name = "excluido")
    private boolean excluido;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idclienteDebito", fetch = FetchType.LAZY)
    private List<ServicoDebito> servicoDebitoList;
    @JoinColumn(name = "idcliente", referencedColumnName = "idcliente")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Cliente idcliente;

    public ClienteDebito() {
    }

    public ClienteDebito(Long idclienteDebito) {
        this.idclienteDebito = idclienteDebito;
    }

    public ClienteDebito(Long idclienteDebito, boolean excluido) {
        this.idclienteDebito = idclienteDebito;
        this.excluido = excluido;
    }

    @Override
    public Long getId() {
        return idclienteDebito;
    }

    public Long getIdclienteDebito() {
        return idclienteDebito;
    }

    public void setIdclienteDebito(Long idclienteDebito) {
        this.idclienteDebito = idclienteDebito;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Double getValorDebito() {
        return valorDebito;
    }

    public void setValorDebito(Double valorDebito) {
        this.valorDebito = valorDebito;
    }

    public LocalDateTime getData() {
        return data;
    }

    public void setData(LocalDateTime data) {
        this.data = data;
    }

    public boolean getExcluido() {
        return excluido;
    }

    public void setExcluido(boolean excluido) {
        this.excluido = excluido;
    }

    @XmlTransient
    public List<ServicoDebito> getServicoDebitoList() {
        return servicoDebitoList;
    }

    public void setServicoDebitoList(List<ServicoDebito> servicoDebitoList) {
        this.servicoDebitoList = servicoDebitoList;
    }

    public Cliente getIdcliente() {
        return idcliente;
    }

    public void setIdcliente(Cliente idcliente) {
        this.idcliente = idcliente;
    }

}
