package protese.model.cliente;

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
import protese.model.servico.ServicoCredito;

/**
 *
 * @author vinihds
 */
@Entity
@Table(name = "cliente_credito_entrada")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ClienteCreditoEntrada.findAll", query = "SELECT c FROM ClienteCreditoEntrada c")})
public class ClienteCreditoEntrada implements Serializable, Entidade {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idcliente_credito_entrada")
    private Long idclienteCreditoEntrada;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "valor_credito")
    private Double valorCredito;
    @Column(name = "descricao")
    private String descricao;
    @Column(name = "data")
    private LocalDateTime data;
    @Basic(optional = false)
    @Column(name = "excluido")
    private boolean excluido;
    @JoinColumn(name = "idcliente", referencedColumnName = "idcliente")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Cliente idcliente;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idclienteCreditoEntrada", fetch = FetchType.LAZY)
    private List<ServicoCredito> servicoCreditoList;

    public ClienteCreditoEntrada() {
    }

    public ClienteCreditoEntrada(Long idclienteCreditoEntrada) {
        this.idclienteCreditoEntrada = idclienteCreditoEntrada;
    }

    public ClienteCreditoEntrada(Long idclienteCreditoEntrada, boolean excluido) {
        this.idclienteCreditoEntrada = idclienteCreditoEntrada;
        this.excluido = excluido;
    }

    @Override
    public Long getId() {
        return idclienteCreditoEntrada;
    }

    public Long getIdclienteCreditoEntrada() {
        return idclienteCreditoEntrada;
    }

    public void setIdclienteCreditoEntrada(Long idclienteCreditoEntrada) {
        this.idclienteCreditoEntrada = idclienteCreditoEntrada;
    }

    public Double getValorCredito() {
        return valorCredito;
    }

    public void setValorCredito(Double valorCredito) {
        this.valorCredito = valorCredito;
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

    @XmlTransient
    public List<ServicoCredito> getServicoCreditoList() {
        return servicoCreditoList;
    }

    public void setServicoCreditoList(List<ServicoCredito> servicoCreditoList) {
        this.servicoCreditoList = servicoCreditoList;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public LocalDateTime getData() {
        return data;
    }

    public void setData(LocalDateTime data) {
        this.data = data;
    }

}
