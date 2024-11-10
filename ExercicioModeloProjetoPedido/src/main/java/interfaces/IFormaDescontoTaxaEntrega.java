package interfaces;

import classes.CupomDescontoEntrega;
import classes.Pedido;

public interface IFormaDescontoTaxaEntrega {
    public CupomDescontoEntrega calcularDesconto(Pedido pedido);
    public boolean seAplica(Pedido pedido);
}
