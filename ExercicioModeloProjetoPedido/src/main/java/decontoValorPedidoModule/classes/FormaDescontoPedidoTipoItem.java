package decontoValorPedidoModule.classes;

import classes.CupomDescontoPedido;
import classes.Pedido;
import decontoValorPedidoModule.interfaces.IFormaDescontoValorPedido;

import java.util.HashMap;
import java.util.Map;

public class FormaDescontoPedidoTipoItem implements IFormaDescontoValorPedido {

    private Map<String, Double> descontos = new HashMap<>();

    public FormaDescontoPedidoTipoItem(){
        descontos.put("Alimentacao", 0.05);
        descontos.put("Educacao", 0.2);
        descontos.put("Lazer", 0.15);
    }

    @Override
    public void calcularDesconto(Pedido pedido) {
        double porcentagemDesconto = 0;
        if(seAplica(pedido)) {
            for(String tipo : pedido.getTipoItens()) {
                if(descontos.containsKey(tipo)){
                    porcentagemDesconto += descontos.get(tipo);
                }
            }
        }

        if(porcentagemDesconto > 0){
            pedido.aplicarDescontoValorPedido(
                    new CupomDescontoPedido(
                            "Desconto no pedido por Tipo de Item",
                            porcentagemDesconto * pedido.getValorPedido()
                    )
            );
        }
    }

    @Override
    public boolean seAplica(Pedido pedido) {
        validaPedido(pedido);
        boolean aplicavel = false;
        if(pedido.getCuponsDescontoPedido().isEmpty() && pedido.getValorPedido() > 0){
            aplicavel = true;
        }
        return aplicavel;
    }

    private void validaPedido(Pedido pedido){
        if (pedido == null){
            throw new RuntimeException("Falha ao calcular desconto para tipo de item. Informe um pedido valido!\n");
        }
        if (pedido.getItens().isEmpty()){
            throw new RuntimeException(("Falha ao calcular desconto para tipo de item. Nao existe nenhum item no pedido!\n"));
        }
    }
}
