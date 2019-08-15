package protese.model.pagamento;

import java.util.ArrayList;
import java.util.List;
import protese.model.cliente.Cliente;

/**
 *
 * @author Vinicius Silveira
 */
public class PagamentoPorAno {

    private Cliente cliente;
    private List<PagamentoPorMes> pagamentoPorMesList = new ArrayList();
    private int ano;

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<PagamentoPorMes> getPagamentoPorMesList() {
        return pagamentoPorMesList;
    }

    public void setPagamentoPorMesList(List<PagamentoPorMes> pagamentoPorMesList) {
        this.pagamentoPorMesList = pagamentoPorMesList;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

}
