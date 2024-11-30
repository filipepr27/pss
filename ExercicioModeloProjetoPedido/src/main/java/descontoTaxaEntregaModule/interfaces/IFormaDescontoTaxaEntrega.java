package descontoTaxaEntregaModule.interfaces;

import classes.Pedido;

public interface IFormaDescontoTaxaEntrega {
    public void calcularDesconto(Pedido pedido);
    public boolean seAplica(Pedido pedido);
}
