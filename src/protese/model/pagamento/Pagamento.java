package protese.model.pagamento;

import protese.model.servico.ServicoPagamento;
import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import protese.jpa.interfaces.Entidade;

/**
 *
 * @author vinihds
 */
@Entity
@Table(name = "pagamento")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Pagamento.findAll", query = "SELECT p FROM Pagamento p")})
public class Pagamento implements Serializable, Entidade {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idpagamento")
    private Long idpagamento;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "valor")
    private Double valor;
    @Column(name = "data_lancamento")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataLancamento;
    @Column(name = "data_vencimento")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataVencimento;
    @Column(name = "data_pagamento")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataPagamento;
    @Basic(optional = false)
    @Column(name = "excluido")
    private boolean excluido;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idpagamento", fetch = FetchType.LAZY)
    private List<ServicoPagamento> servicoPagamentoList;
    @JoinColumn(name = "idforma_pagamento", referencedColumnName = "idforma_pagamento")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private FormaPagamento idformaPagamento;

    public Pagamento() {
    }

    public Pagamento(Long idpagamento) {
        this.idpagamento = idpagamento;
    }

    public Pagamento(Long idpagamento, boolean excluido) {
        this.idpagamento = idpagamento;
        this.excluido = excluido;
    }

    @Override
    public Long getId() {
        return idpagamento;
    }

    public Long getIdpagamento() {
        return idpagamento;
    }

    public void setIdpagamento(Long idpagamento) {
        this.idpagamento = idpagamento;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public Date getDataLancamento() {
        return dataLancamento;
    }

    public void setDataLancamento(Date dataLancamento) {
        this.dataLancamento = dataLancamento;
    }

    public Date getDataVencimento() {
        return dataVencimento;
    }

    public void setDataVencimento(Date dataVencimento) {
        this.dataVencimento = dataVencimento;
    }

    public Date getDataPagamento() {
        return dataPagamento;
    }

    public void setDataPagamento(Date dataPagamento) {
        this.dataPagamento = dataPagamento;
    }

    public boolean getExcluido() {
        return excluido;
    }

    public void setExcluido(boolean excluido) {
        this.excluido = excluido;
    }

    @XmlTransient
    public List<ServicoPagamento> getServicoPagamentoList() {
        return servicoPagamentoList;
    }

    public void setServicoPagamentoList(List<ServicoPagamento> servicoPagamentoList) {
        this.servicoPagamentoList = servicoPagamentoList;
    }

    public FormaPagamento getIdformaPagamento() {
        return idformaPagamento;
    }

    public void setIdformaPagamento(FormaPagamento idformaPagamento) {
        this.idformaPagamento = idformaPagamento;
    }

}
