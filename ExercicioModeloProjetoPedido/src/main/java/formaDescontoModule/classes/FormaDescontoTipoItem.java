package formaDescontoModule.classes;

import classes.CupomDescontoEntrega;
import classes.Item;
import classes.Pedido;
import interfaces.IFormaDescontoTaxaEntrega;

import java.util.Map;

public class FormaDescontoTipoItem implements IFormaDescontoTaxaEntrega {
    private Map<String, Double> descontosPorItem;

    public FormaDescontoTipoItem(){
        // posso passar o validaPedido no construtor?
    }

    @Override
    public CupomDescontoEntrega calcularDesconto(Pedido pedido){
        validaPedido(pedido);
        double valorDesconto = 0;
        for(Item item : pedido.getItens()){
            switch (item.getTipo()){
                case "Alimentacao":
                    this.descontosPorItem.put("Desconto por item do tipo Alimentacao", 5.0);
                    valorDesconto += 5.0;
                    break;
                case "Educacao":
                    this.descontosPorItem.put("Desconto por item do tipo Educacao", 2.0);
                    valorDesconto += 2.0;
                    break;
                case "Lazer":
                    this.descontosPorItem.put("Desconto por item do tipo Lazer", 1.5);
                    valorDesconto += 1.5;
                    break;
                default:
                    break;
            }
        }

        return new CupomDescontoEntrega("Desconto por Tipo de Item", valorDesconto);
    }

    @Override
    public boolean seAplica(Pedido pedido){
        // esse metodo serve pra verificar se o item do pedido se enquadra nos descontos ou se ele ainda é elegivel por conta dos descontos já aplicados?
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
        if (pedido == null){
            throw new RuntimeException("Falha ao calcular desconto para tipo de item. Informe um pedido valido!\n");
        }
        if (pedido.getItens().isEmpty()){
            throw new RuntimeException(("Falha ao calcular desconto para tipo de item. Nao existe nenhum item no pedido!\n"));
        }
    }
}
