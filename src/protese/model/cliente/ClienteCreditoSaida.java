package protese.model.cliente;

import protese.model.servico.ServicoPagamento;
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

/**
 *
 * @author vinihds
 */
@Entity
@Table(name = "cliente_credito_saida")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ClienteCreditoSaida.findAll", query = "SELECT c FROM ClienteCreditoSaida c")})
public class ClienteCreditoSaida implements Serializable, Entidade {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idcliente_credito_saida")
    private Long idclienteCreditoSaida;
    @Basic(optional = false)
    @Column(name = "excluido")
    private boolean excluido;
    @JoinColumn(name = "idcliente", referencedColumnName = "idcliente")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Cliente idcliente;
    @JoinColumn(name = "idservico_pagamento", referencedColumnName = "idservico_pagamento")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private ServicoPagamento idservicoPagamento;

    public ClienteCreditoSaida() {
    }

    public ClienteCreditoSaida(Long idclienteCreditoSaida) {
        this.idclienteCreditoSaida = idclienteCreditoSaida;
    }

    public ClienteCreditoSaida(Long idclienteCreditoSaida, boolean excluido) {
        this.idclienteCreditoSaida = idclienteCreditoSaida;
        this.excluido = excluido;
    }

    @Override
    public Long getId() {
        return idclienteCreditoSaida;
    }

    public Long getIdclienteCreditoSaida() {
        return idclienteCreditoSaida;
    }

    public void setIdclienteCreditoSaida(Long idclienteCreditoSaida) {
        this.idclienteCreditoSaida = idclienteCreditoSaida;
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

    public ServicoPagamento getIdservicoPagamento() {
        return idservicoPagamento;
    }

    public void setIdservicoPagamento(ServicoPagamento idservicoPagamento) {
        this.idservicoPagamento = idservicoPagamento;
    }

}
