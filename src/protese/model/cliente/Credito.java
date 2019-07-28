package protese.model.cliente;

import java.time.LocalDateTime;
import protese.jpa.interfaces.Entidade;

/**
 *
 * @author vinihds
 */
public class Credito implements Entidade {

    private Long idcredito;
    private String descricao;
    private String tipo;
    private LocalDateTime data;
    private double valor;

    @Override
    public Long getId() {
        return idcredito;
    }

    public Long getIdcredito() {
        return idcredito;
    }

    public void setIdcredito(Long idcredito) {
        this.idcredito = idcredito;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public LocalDateTime getData() {
        return data;
    }

    public void setData(LocalDateTime data) {
        this.data = data;
    }

}
