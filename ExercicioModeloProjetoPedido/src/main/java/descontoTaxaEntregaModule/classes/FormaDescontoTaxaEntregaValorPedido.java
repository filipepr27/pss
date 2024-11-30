package descontoTaxaEntregaModule.classes;

import classes.CupomDescontoEntrega;
import classes.Pedido;
import descontoTaxaEntregaModule.interfaces.IFormaDescontoTaxaEntrega;

public class FormaDescontoTaxaEntregaValorPedido implements IFormaDescontoTaxaEntrega {

    private double limiteValorPedido;
    private final double PORCENTAGEM_DESCONTO;

    public FormaDescontoTaxaEntregaValorPedido(double limiteValorPedido, double porcentagemDesconto){
        this.limiteValorPedido = limiteValorPedido;
        this.PORCENTAGEM_DESCONTO = porcentagemDesconto;
    }

    @Override
    public void calcularDesconto(Pedido pedido) {
        validaPedido(pedido);
        if(seAplica(pedido) && (pedido.getValorPedido() > limiteValorPedido)){
            pedido.aplicarDescontoEntrega(new CupomDescontoEntrega("Desconto por Valor do Pedido", PORCENTAGEM_DESCONTO));
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

    private void validaPedido(Pedido pedido){
        if (pedido == null) {
            throw new RuntimeException("Falha ao calcular desconto por Valor do Pedido. Informe um pedido valido!\n");
        }
    }
}
