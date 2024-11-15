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
            double porcentagemDesconto = 0;
            String nomeMetodoDesconto = "Desconto por Tipo de Item";
            for(Item item : pedido.getItens()){
                double descontoItem;
                switch (item.getTipo()){
                    case "Alimentacao":
                        descontoItem = 0.05;
                        this.descontosPorItem.put("Desconto por item do tipo Alimentacao", descontoItem);
                        porcentagemDesconto += descontoItem;
                        break;
                    case "Educacao":
                        descontoItem = 0.20;
                        this.descontosPorItem.put("Desconto por item do tipo Educacao", descontoItem);
                        porcentagemDesconto += descontoItem;
                        break;
                    case "Lazer":
                        descontoItem = 0.15;
                        this.descontosPorItem.put("Desconto por item do tipo Lazer", descontoItem);
                        porcentagemDesconto += descontoItem;
                        break;
                    default:
                        break;
                }
            }

            if(porcentagemDesconto > 0){
                pedido.aplicarDesconto(new CupomDescontoEntrega(nomeMetodoDesconto, porcentagemDesconto * pedido.getTaxaEntrega()));
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
