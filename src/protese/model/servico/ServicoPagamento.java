package protese.model.servico;

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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import protese.jpa.interfaces.Entidade;
import protese.model.cliente.ClienteCreditoEntrada;
import protese.model.cliente.ClienteCreditoSaida;
import protese.model.pagamento.Pagamento;

/**
 *
 * @author vinihds
 */
@Entity
@Table(name = "servico_pagamento")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ServicoPagamento.findAll", query = "SELECT s FROM ServicoPagamento s")})
public class ServicoPagamento implements Serializable, Entidade {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idservico_pagamento")
    private Long idservicoPagamento;
    @Basic(optional = false)
    @Column(name = "excluido")
    private boolean excluido;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idservicoPagamento", fetch = FetchType.LAZY)
    private List<ClienteCreditoEntrada> clienteCreditoEntradaList;
    @JoinColumn(name = "idpagamento", referencedColumnName = "idpagamento")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Pagamento idpagamento;
    @JoinColumn(name = "idservico", referencedColumnName = "idservico")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Servico idservico;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idservicoPagamento", fetch = FetchType.LAZY)
    private List<ClienteCreditoSaida> clienteCreditoSaidaList;

    public ServicoPagamento() {
    }

    public ServicoPagamento(Long idservicoPagamento) {
        this.idservicoPagamento = idservicoPagamento;
    }

    public ServicoPagamento(Long idservicoPagamento, boolean excluido) {
        this.idservicoPagamento = idservicoPagamento;
        this.excluido = excluido;
    }

    @Override
    public Long getId() {
        return idservicoPagamento;
    }

    public Long getIdservicoPagamento() {
        return idservicoPagamento;
    }

    public void setIdservicoPagamento(Long idservicoPagamento) {
        this.idservicoPagamento = idservicoPagamento;
    }

    public boolean getExcluido() {
        return excluido;
    }

    public void setExcluido(boolean excluido) {
        this.excluido = excluido;
    }

    @XmlTransient
    public List<ClienteCreditoEntrada> getClienteCreditoEntradaList() {
        return clienteCreditoEntradaList;
    }

    public void setClienteCreditoEntradaList(List<ClienteCreditoEntrada> clienteCreditoEntradaList) {
        this.clienteCreditoEntradaList = clienteCreditoEntradaList;
    }

    public Pagamento getIdpagamento() {
        return idpagamento;
    }

    public void setIdpagamento(Pagamento idpagamento) {
        this.idpagamento = idpagamento;
    }

    public Servico getIdservico() {
        return idservico;
    }

    public void setIdservico(Servico idservico) {
        this.idservico = idservico;
    }

    @XmlTransient
    public List<ClienteCreditoSaida> getClienteCreditoSaidaList() {
        return clienteCreditoSaidaList;
    }

    public void setClienteCreditoSaidaList(List<ClienteCreditoSaida> clienteCreditoSaidaList) {
        this.clienteCreditoSaidaList = clienteCreditoSaidaList;
    }

}
