package formaDescontoModule.classes;

import classes.CupomDescontoEntrega;
import classes.Pedido;
import interfaces.IFormaDescontoTaxaEntrega;

//import java.util.Map;

public class FormaDescontoPorTipoCliente implements IFormaDescontoTaxaEntrega {

//    private Map<String, Double> descontosPorTipoCliente; // pq se um cliente só pertence a um tipo?
    private String tipoCliente;

    public FormaDescontoPorTipoCliente(){

    }

    @Override
    public void calcularDesconto(Pedido pedido) {
        validaPedido(pedido);
        double porcentagemDesconto = 0;
        if (seAplica(pedido)){
        String nomeMetodoDesconto = "Desconto por Tipo de Cliente";
            switch (this.tipoCliente){
                case "Ouro":
                    porcentagemDesconto = 0.30;
                    break;
                case "Prata":
                    porcentagemDesconto = 0.20;
                    break;
                case "Bronze":
                    porcentagemDesconto = 0.10;
                    break;
                default:
                    break;
            }

            if(porcentagemDesconto > 0){
                pedido.aplicarDesconto(new CupomDescontoEntrega(nomeMetodoDesconto + " " + this.tipoCliente, porcentagemDesconto * pedido.getTaxaEntrega()));
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
