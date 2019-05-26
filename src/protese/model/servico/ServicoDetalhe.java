package protese.model.servico;

import java.io.Serializable;
import java.time.LocalDateTime;
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
import protese.model.produto.ProdutoValor;

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
    @JoinColumn(name = "idproduto_valor", referencedColumnName = "idproduto_valor")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private ProdutoValor idprodutoValor;
    @JoinColumn(name = "idservico", referencedColumnName = "idservico")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Servico idservico;
    @Column(name = "quantidade")
    private Double quantidade;
    @Column(name = "valor_unitario")
    private Double valorUnitario;
    @Column(name = "valor_total")
    private Double valorTotal;
    @Column(name = "data_lancamento")
    private LocalDateTime dataLancamento;
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

    public ServicoDetalhe(Long idservicoDetalhe, boolean excluido) {
        this.idservicoDetalhe = idservicoDetalhe;
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

    public LocalDateTime getDataLancamento() {
        return dataLancamento;
    }

    public void setDataLancamento(LocalDateTime dataLancamento) {
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

    public ProdutoValor getIdprodutoValor() {
        return idprodutoValor;
    }

    public void setIdprodutoValor(ProdutoValor idprodutoValor) {
        this.idprodutoValor = idprodutoValor;
    }

    public Servico getIdservico() {
        return idservico;
    }

    public void setIdservico(Servico idservico) {
        this.idservico = idservico;
    }

}
