package protese.model.cliente;

import java.time.LocalDateTime;
import protese.jpa.interfaces.Entidade;
import protese.model.servico.Servico;

/**
 *
 * @author vinihds
 */
public class Credito implements Entidade {

    private Long idcredito;
    private Servico servico;
    private String tipo;
    private LocalDateTime data;

    public LocalDateTime getData() {
        return data;
    }

    public void setData(LocalDateTime data) {
        this.data = data;
    }
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

    public Servico getServico() {
        return servico;
    }

    public void setServico(Servico servico) {
        this.servico = servico;
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

}
