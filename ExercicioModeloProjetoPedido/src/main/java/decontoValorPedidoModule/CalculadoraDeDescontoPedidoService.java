package decontoValorPedidoModule;

import classes.Pedido;
import decontoValorPedidoModule.classes.FormaDescontoPedidoCategoriaCliente;
import decontoValorPedidoModule.classes.FormaDescontoPedidoPorCodigoDeCupom;
import decontoValorPedidoModule.classes.FormaDescontoPedidoTipoItem;
import decontoValorPedidoModule.interfaces.IFormaDescontoValorPedido;
import descontoTaxaEntregaModule.interfaces.IFormaDescontoTaxaEntrega;

import java.util.ArrayList;
import java.util.List;

public class CalculadoraDeDescontoPedidoService {

    private List<IFormaDescontoValorPedido> metodosDeDesconto;

    public CalculadoraDeDescontoPedidoService(){
        this.metodosDeDesconto = new ArrayList<>();
        this.metodosDeDesconto.add(new FormaDescontoPedidoPorCodigoDeCupom());
        this.metodosDeDesconto.add(new FormaDescontoPedidoCategoriaCliente());
        this.metodosDeDesconto.add(new FormaDescontoPedidoTipoItem()); // como garantir a ordem para não quebrar regra de negocio?
    }

    public void calcularDesconto(Pedido pedido){
        for (IFormaDescontoValorPedido formaDesconto : metodosDeDesconto){
            formaDesconto.calcularDesconto(pedido);
        }
    }

    public void adicionar(IFormaDescontoValorPedido formaDesconto){
        this.metodosDeDesconto.add(formaDesconto);
    }
}
