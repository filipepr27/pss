package formaDescontoModule;

import classes.Pedido;
import formaDescontoModule.classes.FormaDescontoPorTipoCliente;
import formaDescontoModule.classes.FormaDescontoTaxaPorBairro;
import formaDescontoModule.classes.FormaDescontoTipoItem;
import formaDescontoModule.classes.FormaDescontoValorPedido;
import interfaces.IFormaDescontoTaxaEntrega;

import java.util.ArrayList;
import java.util.List;

public class CalculadoraDeDescontoService {
    private List<IFormaDescontoTaxaEntrega> metodosDeDesconto;

    public CalculadoraDeDescontoService(){
        this.metodosDeDesconto = new ArrayList<>();
        this.metodosDeDesconto.add(new FormaDescontoTaxaPorBairro());
        this.metodosDeDesconto.add(new FormaDescontoTipoItem());
        this.metodosDeDesconto.add(new FormaDescontoPorTipoCliente());
        this.metodosDeDesconto.add(new FormaDescontoValorPedido(200.0));
    }

    public void calcularDesconto(Pedido pedido){
        for (IFormaDescontoTaxaEntrega metodoDesconto : metodosDeDesconto){
            pedido.aplicarDesconto(metodoDesconto.calcularDesconto(pedido));
        }
    }
}
