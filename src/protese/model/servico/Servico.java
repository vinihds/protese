package protese.model.servico;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
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
import protese.dao.servico.ServicoCreditoDao;
import protese.dao.servico.ServicoDebitoDao;
import protese.dao.servico.ServicoDetalheDao;
import protese.dao.servico.ServicoPagamentoDao;
import protese.jpa.interfaces.Entidade;
import protese.model.cliente.Cliente;

/**
 *
 * @author vinihds
 */
@Entity
@Table(name = "servico")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Servico.findAll", query = "SELECT s FROM Servico s")})
public class Servico implements Serializable, Entidade {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idservico")
    private Long idservico;
    @Column(name = "titulo")
    private String titulo;
    @Column(name = "descricao")
    private String descricao;
    @Column(name = "data_criacao")
    private LocalDateTime dataCriacao;
    @Column(name = "data_finalizacao")
    private LocalDateTime dataFinalizacao;
    @Column(name = "data_referente")
    private LocalDateTime dataReferente;
    @Basic(optional = false)
    @Column(name = "excluido")
    private boolean excluido;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idservico", fetch = FetchType.LAZY)
    private List<ServicoPagamento> servicoPagamentoList;
    @JoinColumn(name = "idcliente", referencedColumnName = "idcliente")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Cliente idcliente;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idservico", fetch = FetchType.LAZY)
    private List<ServicoDetalhe> servicoDetalheList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idservico", fetch = FetchType.LAZY)
    private List<ServicoCredito> servicoCreditoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idservico", fetch = FetchType.LAZY)
    private List<ServicoDebito> servicoDebitoList;

    public Servico() {
    }

    public Servico(Long idservico) {
        this.idservico = idservico;
    }

    public Servico(Long idservico, boolean excluido) {
        this.idservico = idservico;
        this.excluido = excluido;
    }

    @Override
    public Long getId() {
        return idservico;
    }

    public Long getIdservico() {
        return idservico;
    }

    public void setIdservico(Long idservico) {
        this.idservico = idservico;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDateTime dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public LocalDateTime getDataFinalizacao() {
        return dataFinalizacao;
    }

    public void setDataFinalizacao(LocalDateTime dataFinalizacao) {
        this.dataFinalizacao = dataFinalizacao;
    }

    public boolean getExcluido() {
        return excluido;
    }

    public void setExcluido(boolean excluido) {
        this.excluido = excluido;
    }

    @XmlTransient
    public List<ServicoPagamento> getServicoPagamentoList() {
        ServicoPagamentoDao servicoPagamentoDao = ServicoPagamentoDao.getInstance();

        servicoPagamentoList = new ArrayList();

        if (this.idservico != null && this.idservico > 0) {
            servicoPagamentoList = servicoPagamentoDao.retornaTodosPorServico(this);
        }

        return servicoPagamentoList;
    }

    public void setServicoPagamentoList(List<ServicoPagamento> servicoPagamentoList) {
        this.servicoPagamentoList = servicoPagamentoList;
    }

    public Cliente getIdcliente() {
        return idcliente;
    }

    public void setIdcliente(Cliente idcliente) {
        this.idcliente = idcliente;
    }

    @XmlTransient
    public List<ServicoDetalhe> getServicoDetalheList() {
        ServicoDetalheDao servicoDetalheDao = ServicoDetalheDao.getInstance();

        servicoDetalheList = new ArrayList();

        if (this.idservico != null && this.idservico > 0) {
            servicoDetalheList = servicoDetalheDao.retornaTodosPorServico(this);
        }

        return servicoDetalheList;
    }

    public void setServicoDetalheList(List<ServicoDetalhe> servicoDetalheList) {
        this.servicoDetalheList = servicoDetalheList;
    }
    
    public double getValorTotalProdutos() {
        double valorTotal = 0;

        for (ServicoDetalhe detalhe : getServicoDetalheList()) {
            valorTotal += detalhe.getValorTotal();
        }
        
        return valorTotal;
    }
    
    public double getValorTotalDebitos() {
        double valorTotal = 0;

        for (ServicoDebito debito : getServicoDebitoList()) {
            valorTotal += debito.getIdclienteDebito().getValorDebito();
        }
        
        return valorTotal;
    }

    public double getValorTotalServico() {
        double valorTotal = 0;

        valorTotal += getValorTotalProdutos();
        
        valorTotal += getValorTotalDebitos();

        return valorTotal;
    }

    public double getValorTotalPago() {
        double valorTotal = 0;

        for (ServicoPagamento pagamento : getServicoPagamentoList()) {
            valorTotal += pagamento.getIdpagamento().getValor();
        }

        return valorTotal;
    }

    public double getRestantePagar() {
        double restantePagar = getValorTotalServico() - getValorTotalPago();

        return restantePagar < 0 ? 0 : restantePagar;
    }

    public LocalDateTime getDataReferente() {
        return dataReferente;
    }

    public void setDataReferente(LocalDateTime dataReferente) {
        this.dataReferente = dataReferente;
    }

    @XmlTransient
    public List<ServicoCredito> getServicoCreditoList() {
        ServicoCreditoDao servicoCreditoDao = ServicoCreditoDao.getInstance();
        
        servicoCreditoList = new ArrayList();
        
        if (this.idservico != null && this.idservico > 0) {
            servicoCreditoList = servicoCreditoDao.retornaTodosPorServico(this);
        }
        
        return servicoCreditoList;
    }

    public void setServicoCreditoList(List<ServicoCredito> servicoCreditoList) {
        this.servicoCreditoList = servicoCreditoList;
    }

    @XmlTransient
    public List<ServicoDebito> getServicoDebitoList() {
        ServicoDebitoDao servicoDebitoDao = ServicoDebitoDao.getInstance();
        
        servicoDebitoList = new ArrayList();
        
        if (this.idservico != null && this.idservico > 0) {
            servicoDebitoList = servicoDebitoDao.retornaTodosPorServico(this);
        }
        
        return servicoDebitoList;
    }

    public void setServicoDebitoList(List<ServicoDebito> servicoDebitoList) {
        this.servicoDebitoList = servicoDebitoList;
    }

}
