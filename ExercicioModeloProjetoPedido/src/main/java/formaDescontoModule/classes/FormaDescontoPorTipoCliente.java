package formaDescontoModule.classes;

import classes.CupomDescontoEntrega;
import classes.Pedido;
import interfaces.IFormaDescontoTaxaEntrega;

import java.util.Map;

public class FormaDescontoPorTipoCliente implements IFormaDescontoTaxaEntrega {

    private Map<String, Double> descontosPorTipoCLiente; // pq se um cliente só pertence a um tipo?
    private String tipoCliente;

    public FormaDescontoPorTipoCliente(){

    }

    @Override
    public CupomDescontoEntrega calcularDesconto(Pedido pedido) {
        validaPedido(pedido);
        double valorDesconto = 0;
        switch (this.tipoCliente){
            case "Ouro":
                valorDesconto = 3.0;
                break;
            case "Prata":
                valorDesconto = 2.0;
                break;
            case "Bronze":
                valorDesconto = 1.0;
                break;
            default:
                break;
        }

        return new CupomDescontoEntrega("Desconto por Tipo de Cliente", valorDesconto);
    }

    @Override
    public boolean seAplica(Pedido pedido) {
        validaPedido(pedido);
        boolean aplicavel = false;
        double totalDescontos = 0;
        for (CupomDescontoEntrega cupom : pedido.getCuponsDesconto()){
            totalDescontos += cupom.getValorDesconto();
        }
        if(totalDescontos <= 10){
            aplicavel = true;
        }
        return aplicavel;
    }

    private void validaPedido(Pedido pedido){
        if (pedido == null) {
            throw new RuntimeException("Falha ao calcular desconto por Tipo de Cliente. Informe um pedido valido!\n");
        }
        if (pedido.getCliente() == null){
            throw new RuntimeException("Falha ao calcular desconto por Tipo de Cliente. Informe um cliente valido para o pedido!\n");
        }
        this.tipoCliente = pedido.getCliente().getTipo();

    }
}
