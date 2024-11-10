package formaDescontoModule.classes;

import classes.CupomDescontoEntrega;
import classes.Pedido;
import interfaces.IFormaDescontoTaxaEntrega;

public class FormaDescontoValorPedido implements IFormaDescontoTaxaEntrega {

    private double limiteValorPedido;
    private static final double VALOR_DESCONTO = 5.0;

    public FormaDescontoValorPedido(double limiteValorPedido){
        this.limiteValorPedido = limiteValorPedido;
    }

    @Override
    public CupomDescontoEntrega calcularDesconto(Pedido pedido) {
        double valorDesconto = 0;
        if(pedido.getValorPedido() > limiteValorPedido){
            valorDesconto = VALOR_DESCONTO;
        }

        String nomeMetodoDesconto = "Desconto por Valor do Pedido";
        if(pedido.getDescontoConcedido() + valorDesconto > 10.0){
            valorDesconto = 10.0 - pedido.getDescontoConcedido();
            nomeMetodoDesconto = "Desconto parcial por Valor do Pedido";
        }

        return new CupomDescontoEntrega(nomeMetodoDesconto, valorDesconto);
    }

    @Override
    public boolean seAplica(Pedido pedido) {
        validaPedido(pedido);
        boolean aplicavel = false;
        if(pedido.getDescontoConcedido() < 10){
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
