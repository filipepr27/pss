package descontoTaxaEntregaModule.classes;

import classes.CupomDescontoEntrega;
import classes.Pedido;
import descontoTaxaEntregaModule.interfaces.IFormaDescontoTaxaEntrega;

import java.util.HashMap;
import java.util.Map;


public class FormaDescontoTaxaEntregaPorTipoCliente implements IFormaDescontoTaxaEntrega {

    private Map<String, Double> descontos = new HashMap<>(); // pq se um cliente só pertence a um tipo?
    private String tipoCliente;

    public FormaDescontoTaxaEntregaPorTipoCliente(){
        descontos.put("Ouro", 0.30);
        descontos.put("Prata", 0.20);
        descontos.put("Bronze", 0.10);
    }

    @Override
    public void calcularDesconto(Pedido pedido) {
        validaPedido(pedido);
        if (seAplica(pedido) && descontos.containsKey(tipoCliente)){
            pedido.aplicarDescontoEntrega(
                    new CupomDescontoEntrega(
                            "Desconto por Tipo de Cliente " + this.tipoCliente,
                            descontos.get(tipoCliente) * pedido.getTaxaEntrega()
                    )
            );
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
