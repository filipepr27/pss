package decontoValorPedidoModule.classes;

import classes.CupomDescontoPedido;
import classes.Pedido;
import decontoValorPedidoModule.interfaces.IFormaDescontoValorPedido;

import java.util.HashMap;
import java.util.Map;

public class FormaDescontoPedidoPorCodigoDeCupom implements IFormaDescontoValorPedido {

    private Map<String, Double> descontos = new HashMap<>();

    public FormaDescontoPedidoPorCodigoDeCupom(){
        descontos.put("DESC10", 0.1);
        descontos.put("DESC20", 0.2);
        descontos.put("DESC30", 0.3);
    }

    @Override
    public void calcularDesconto(Pedido pedido) {
        if(seAplica(pedido) && descontos.containsKey(pedido.getCodigoDeCupom())){
            pedido.aplicarDescontoValorPedido(
                    new CupomDescontoPedido(
                            "Desconto por Codigo de Cupom",
                            descontos.get(pedido.getCodigoDeCupom()) * pedido.getValorPedido()
                    )
            );
        }
    }

    @Override
    public boolean seAplica(Pedido pedido) {
        validaPedido(pedido);
        boolean aplicavel = false;
        if(pedido.getValorPedido() > 0 && !pedido.getCodigoDeCupom().isEmpty()){
            aplicavel = true;
        }
        return aplicavel;
    }

    private void validaPedido(Pedido pedido){
        if (pedido == null) {
            throw new RuntimeException("Falha ao calcular desconto por Codigo de Cupom. Informe um pedido valido!\n");
        }
    }
}
