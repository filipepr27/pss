package formaDescontoModule.classes;

import classes.CupomDescontoEntrega;
import classes.Pedido;
import interfaces.IFormaDescontoTaxaEntrega;

import java.util.Map;

public class FormaDescontoPorTipoCliente implements IFormaDescontoTaxaEntrega {

    private Map<String, Double> descontosPorTipoCliente; // pq se um cliente só pertence a um tipo?
    private String tipoCliente;

    public FormaDescontoPorTipoCliente(){

    }

    @Override
    public void calcularDesconto(Pedido pedido) {
        validaPedido(pedido);
        double valorDesconto = 0;
        if (seAplica(pedido)){
        String nomeMetodoDesconto = "Desconto por Tipo de Cliente";
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

            if(pedido.getDescontoConcedido() + valorDesconto > 10.0){
                valorDesconto = 10.0 - pedido.getDescontoConcedido();
                nomeMetodoDesconto = "Desconto parcial por Tipo de Cliente";
            }

            if(valorDesconto > 0){
                pedido.aplicarDesconto(new CupomDescontoEntrega(nomeMetodoDesconto + " " + this.tipoCliente, valorDesconto));
            }
        }
    }

    @Override
    public boolean seAplica(Pedido pedido) {
        validaPedido(pedido);
        boolean aplicavel = false;
        if(pedido.getCliente().getTipo() != null){
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
