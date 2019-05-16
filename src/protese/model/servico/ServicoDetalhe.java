package protese.model.servico;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import protese.jpa.interfaces.Entidade;

/**
 *
 * @author vinihds
 */
@Entity
@Table(name = "servico_detalhe")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ServicoDetalhe.findAll", query = "SELECT s FROM ServicoDetalhe s")})
public class ServicoDetalhe implements Serializable, Entidade {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idservico_detalhe")
    private Long idservicoDetalhe;
    @Basic(optional = false)
    @Column(name = "idservico")
    private short idservico;
    @Basic(optional = false)
    @Column(name = "idproduto_valor")
    private short idprodutoValor;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "quantidade")
    private Double quantidade;
    @Column(name = "valor_unitario")
    private Double valorUnitario;
    @Column(name = "valor_total")
    private Double valorTotal;
    @Column(name = "data_lancamento")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataLancamento;
    @Column(name = "descricao")
    private String descricao;
    @Basic(optional = false)
    @Column(name = "excluido")
    private boolean excluido;

    public ServicoDetalhe() {
    }

    public ServicoDetalhe(Long idservicoDetalhe) {
        this.idservicoDetalhe = idservicoDetalhe;
    }

    public ServicoDetalhe(Long idservicoDetalhe, short idservico, short idprodutoValor, boolean excluido) {
        this.idservicoDetalhe = idservicoDetalhe;
        this.idservico = idservico;
        this.idprodutoValor = idprodutoValor;
        this.excluido = excluido;
    }

    @Override
    public Long getId() {
        return idservicoDetalhe;
    }

    public Long getIdservicoDetalhe() {
        return idservicoDetalhe;
    }

    public void setIdservicoDetalhe(Long idservicoDetalhe) {
        this.idservicoDetalhe = idservicoDetalhe;
    }

    public short getIdservico() {
        return idservico;
    }

    public void setIdservico(short idservico) {
        this.idservico = idservico;
    }

    public short getIdprodutoValor() {
        return idprodutoValor;
    }

    public void setIdprodutoValor(short idprodutoValor) {
        this.idprodutoValor = idprodutoValor;
    }

    public Double getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Double quantidade) {
        this.quantidade = quantidade;
    }

    public Double getValorUnitario() {
        return valorUnitario;
    }

    public void setValorUnitario(Double valorUnitario) {
        this.valorUnitario = valorUnitario;
    }

    public Double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(Double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public Date getDataLancamento() {
        return dataLancamento;
    }

    public void setDataLancamento(Date dataLancamento) {
        this.dataLancamento = dataLancamento;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public boolean getExcluido() {
        return excluido;
    }

    public void setExcluido(boolean excluido) {
        this.excluido = excluido;
    }

}
