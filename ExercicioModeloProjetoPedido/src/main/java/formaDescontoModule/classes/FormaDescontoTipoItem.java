package formaDescontoModule.classes;

import classes.CupomDescontoEntrega;
import classes.Item;
import classes.Pedido;
import interfaces.IFormaDescontoTaxaEntrega;

import java.util.HashMap;
import java.util.Map;

public class FormaDescontoTipoItem implements IFormaDescontoTaxaEntrega {
    private Map<String, Double> descontosPorItem;

    public FormaDescontoTipoItem(){
        // posso passar o validaPedido no construtor?
        this.descontosPorItem = new HashMap<>();
    }

    @Override
    public void calcularDesconto(Pedido pedido){
        validaPedido(pedido);
        if(seAplica(pedido)){
            double valorDesconto = 0;
            String nomeMetodoDesconto = "Desconto por Tipo de Item";
            for(Item item : pedido.getItens()){
                switch (item.getTipo()){
                    case "Alimentacao":
                        this.descontosPorItem.put("Desconto por item do tipo Alimentacao", 5.0);
                        valorDesconto += 5.0 * item.getQuantidade();
                        break;
                    case "Educacao":
                        this.descontosPorItem.put("Desconto por item do tipo Educacao", 2.0);
                        valorDesconto += 2.0 * item.getQuantidade();
                        break;
                    case "Lazer":
                        this.descontosPorItem.put("Desconto por item do tipo Lazer", 1.5);
                        valorDesconto += 1.5 * item.getQuantidade();
                        break;
                    default:
                        break;
                }
            }

            if(pedido.getDescontoConcedido() + valorDesconto > 10.0){
                valorDesconto = 10.0 - pedido.getDescontoConcedido();
                nomeMetodoDesconto = "Desconto parcial por Tipo de Item";
            }

            if(valorDesconto > 0){
                pedido.aplicarDesconto(new CupomDescontoEntrega(nomeMetodoDesconto, valorDesconto));
            }
        }
    }

    @Override
    public boolean seAplica(Pedido pedido){
        // esse metodo serve pra verificar se o item do pedido se enquadra nos descontos ou se ele ainda é elegivel por conta dos descontos já aplicados?
        validaPedido(pedido);
        boolean aplicavel = false;
        if(!pedido.getItens().isEmpty()){
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
