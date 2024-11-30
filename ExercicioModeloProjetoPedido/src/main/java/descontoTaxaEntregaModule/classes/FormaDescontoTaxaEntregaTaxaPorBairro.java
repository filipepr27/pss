package descontoTaxaEntregaModule.classes;

import classes.CupomDescontoEntrega;
import classes.Pedido;
import descontoTaxaEntregaModule.interfaces.IFormaDescontoTaxaEntrega;

import java.util.HashMap;
import java.util.Map;

public class FormaDescontoTaxaEntregaTaxaPorBairro implements IFormaDescontoTaxaEntrega {

    private String bairroCliente;
    private Map<String, Double> descontos = new HashMap<>();

    public FormaDescontoTaxaEntregaTaxaPorBairro(){
        descontos.put("Centro", 0.20);
        descontos.put("Bela Vista", 0.30);
        descontos.put("Cidade Maravilhosa", 0.15);
    }

    @Override
    public void calcularDesconto(Pedido pedido) {
        validaPedido(pedido);
        if(seAplica(pedido) && descontos.containsKey(bairroCliente)){
            pedido.aplicarDescontoEntrega(
                    new CupomDescontoEntrega(
                            "Desconto por Bairro " + this.bairroCliente,
                            descontos.get(bairroCliente) * pedido.getTaxaEntrega())
            );
        }
    }

    @Override
    public boolean seAplica(Pedido pedido) {
        validaPedido(pedido);
        boolean aplicavel = false;
        if(pedido.getCliente().getBairro() != null){
            aplicavel = true;
        }
        return aplicavel;
    }

    private void validaPedido(Pedido pedido){
        if (pedido == null) {
            throw new RuntimeException("Falha ao calcular desconto por bairro. Informe um pedido valido!\n");
        }
        if (pedido.getCliente() == null){
            throw new RuntimeException("Falha ao calcular desconto por bairro. Informe um cliente valido para o pedido!\n");
        }
        this.bairroCliente = pedido.getCliente().getBairro();
    }
}
