package descontoTaxaEntregaModule.classes;

import classes.CupomDescontoEntrega;
import classes.Item;
import classes.Pedido;
import descontoTaxaEntregaModule.interfaces.IFormaDescontoTaxaEntrega;

import java.util.HashMap;
import java.util.Map;

public class FormaDescontoTaxaEntregaTipoItem implements IFormaDescontoTaxaEntrega {
    private Map<String, Double> descontosPorItem;
    private Map<String, Double> descontos = new HashMap<>();

    public FormaDescontoTaxaEntregaTipoItem(){
        // posso passar o validaPedido no construtor?
        this.descontosPorItem = new HashMap<>();
        descontos.put("Alimentacao", 0.05);
        descontos.put("Educacao", 0.20);
        descontos.put("Lazer", 0.15);
    }

    @Override
    public void calcularDesconto(Pedido pedido){
        validaPedido(pedido);
        if(seAplica(pedido)){
            double porcentagemDesconto = 0;
            for(String tipo : pedido.getTipoItens()){
                if(descontos.containsKey(tipo)){
                    descontosPorItem.put("Desconto por item do tipo " + tipo, descontos.get(tipo));
                    porcentagemDesconto += descontos.get(tipo);
                }
            }

            if(porcentagemDesconto > 0){
                pedido.aplicarDescontoEntrega(new CupomDescontoEntrega("Desconto por Tipo de Item", porcentagemDesconto * pedido.getTaxaEntrega()));
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
