package protese.model.cliente;

import protese.model.servico.Servico;
import java.io.Serializable;
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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import protese.dao.cliente.ClienteContatoDao;
import protese.dao.cliente.ClienteCreditoEntradaDao;
import protese.dao.cliente.ClienteCreditoSaidaDao;
import protese.dao.cliente.ClienteEnderecoDao;
import protese.dao.servico.ServicoDao;
import protese.jpa.interfaces.Entidade;

/**
 *
 * @author vinihds
 */
@Entity
@Table(name = "cliente")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Cliente.findAll", query = "SELECT c FROM Cliente c")})
public class Cliente implements Serializable, Entidade {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idcliente")
    private Long idcliente;
    @Column(name = "nome")
    private String nome;
    @Column(name = "rg")
    private String rg;
    @Column(name = "documento")
    private String documento;
    @Column(name = "email")
    private String email;
    @Basic(optional = false)
    @Column(name = "excluido")
    private boolean excluido;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idcliente", fetch = FetchType.LAZY)
    private List<ClienteCreditoEntrada> clienteCreditoEntradaList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idcliente", fetch = FetchType.LAZY)
    private List<ClienteEndereco> clienteEnderecoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idcliente", fetch = FetchType.LAZY)
    private List<ClienteContato> clienteContatoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idcliente", fetch = FetchType.LAZY)
    private List<ClienteCreditoSaida> clienteCreditoSaidaList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idcliente", fetch = FetchType.LAZY)
    private List<Servico> servicoList;

    public Cliente() {
    }

    public Cliente(Long idcliente) {
        this.idcliente = idcliente;
    }

    @Override
    public Long getId() {
        return idcliente;
    }

    public Long getIdcliente() {
        return idcliente;
    }

    public void setIdcliente(Long idcliente) {
        this.idcliente = idcliente;
    }

    @Override
    public String toString() {
        return "protese.model.Cliente[ idcliente=" + idcliente + " ]";
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean getExcluido() {
        return excluido;
    }

    public void setExcluido(boolean excluido) {
        this.excluido = excluido;
    }

    @XmlTransient
    public List<ClienteCreditoEntrada> getClienteCreditoEntradaList() {
        ClienteCreditoEntradaDao creditoEntradaDao = ClienteCreditoEntradaDao.getInstance();

        clienteCreditoEntradaList = new ArrayList();

        if (idcliente != null && idcliente > 0) {
            clienteCreditoEntradaList = creditoEntradaDao.retornaTodosPorCliente(this);
        }

        return clienteCreditoEntradaList;
    }

    public void setClienteCreditoEntradaList(List<ClienteCreditoEntrada> clienteCreditoEntradaList) {
        this.clienteCreditoEntradaList = clienteCreditoEntradaList;
    }

    @XmlTransient
    public List<ClienteEndereco> getClienteEnderecoList() {
        ClienteEnderecoDao clienteEnderecoDao = ClienteEnderecoDao.getInstance();

        clienteEnderecoList = new ArrayList();

        if (idcliente != null && idcliente > 0) {
            clienteEnderecoList = clienteEnderecoDao.retornaTodosPorCliente(this);
        }

        return clienteEnderecoList;
    }

    public void setClienteEnderecoList(List<ClienteEndereco> clienteEnderecoList) {
        this.clienteEnderecoList = clienteEnderecoList;
    }

    @XmlTransient
    public List<ClienteContato> getClienteContatoList() {
        ClienteContatoDao clienteContatoDao = ClienteContatoDao.getInstance();

        clienteContatoList = new ArrayList();

        if (idcliente != null && idcliente > 0) {
            clienteContatoList = clienteContatoDao.retornaTodosPorCliente(this);
        }

        return clienteContatoList;
    }

    public void setClienteContatoList(List<ClienteContato> clienteContatoList) {
        this.clienteContatoList = clienteContatoList;
    }

    @XmlTransient
    public List<ClienteCreditoSaida> getClienteCreditoSaidaList() {
        ClienteCreditoSaidaDao creditoSaidaDao = ClienteCreditoSaidaDao.getInstance();

        clienteCreditoSaidaList = new ArrayList();

        if (idcliente != null && idcliente > 0) {
            clienteCreditoSaidaList = creditoSaidaDao.retornaTodosPorCliente(this);
        }

        return clienteCreditoSaidaList;
    }

    public void setClienteCreditoSaidaList(List<ClienteCreditoSaida> clienteCreditoSaidaList) {
        this.clienteCreditoSaidaList = clienteCreditoSaidaList;
    }

    @XmlTransient
    public List<Servico> getServicoList() {
        ServicoDao servicoDao = ServicoDao.getInstance();

        servicoList = new ArrayList();

        if (idcliente != null && idcliente > 0) {
            servicoList = servicoDao.retornaTodosPorCliente(this);
        }

        return servicoList;
    }

    public void setServicoList(List<Servico> servicoList) {
        this.servicoList = servicoList;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getSaldoAtual() {
        double totalEntrada = 0;
        double totalSaida = 0;

        for (ClienteCreditoEntrada entrada : getClienteCreditoEntradaList()) {
            totalEntrada += entrada.getValorCredito();
        }

        for (ClienteCreditoSaida saida : getClienteCreditoSaidaList()) {
            totalSaida += saida.getIdservicoPagamento().getIdpagamento().getValor();
        }

        return totalEntrada - totalSaida;
    }

}
