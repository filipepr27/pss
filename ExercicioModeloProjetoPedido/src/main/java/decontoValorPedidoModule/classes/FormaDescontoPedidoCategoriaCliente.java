package decontoValorPedidoModule.classes;

import classes.CupomDescontoPedido;
import classes.Pedido;
import decontoValorPedidoModule.interfaces.IFormaDescontoValorPedido;

import java.util.HashMap;
import java.util.Map;

public class FormaDescontoPedidoCategoriaCliente implements IFormaDescontoValorPedido {

    private String categoriaCliente;
    private Map<String, Double> descontos = new HashMap<>();

    public FormaDescontoPedidoCategoriaCliente(){
        // poderia pegar de um aqruivo e armazenar no hash
        descontos.put("Ouro", 0.30);
        descontos.put("Prata", 0.20);
        descontos.put("Bronze", 0.10);
    }

    @Override
    public void calcularDesconto(Pedido pedido) {
        if(seAplica(pedido) && descontos.containsKey(categoriaCliente)){
            pedido.aplicarDescontoValorPedido(
                    new CupomDescontoPedido(
                            "Desconto no pedido por Categoria de Cliente " + categoriaCliente,
                            descontos.get(categoriaCliente) * pedido.getValorPedido()
                    )
            );
        }
    }

    @Override
    public boolean seAplica(Pedido pedido) {
        validaPedido(pedido);
        boolean aplicavel = false;
        if(pedido.getValorPedido() > 0){
            aplicavel = true;
        }
        return aplicavel;
    }

    public Map<String, Double> getDescontosCategoria(){
        return descontos;
    }

    private void validaPedido(Pedido pedido){
        if (pedido == null) {
            throw new RuntimeException("Falha ao calcular desconto por Codigo de cupom. Informe um pedido valido!\n");
        }

        categoriaCliente = pedido.getCliente().getTipo();
    }
}
