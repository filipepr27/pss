package decontoValorPedidoModule.interfaces;

import classes.Pedido;

public interface IFormaDescontoValorPedido {
    public void calcularDesconto(Pedido pedido);
    public boolean seAplica(Pedido pedido);

}
