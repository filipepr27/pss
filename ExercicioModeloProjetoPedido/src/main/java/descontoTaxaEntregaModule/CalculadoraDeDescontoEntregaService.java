package descontoTaxaEntregaModule;

import classes.Pedido;
import descontoTaxaEntregaModule.classes.FormaDescontoTaxaEntregaPorTipoCliente;
import descontoTaxaEntregaModule.classes.FormaDescontoTaxaEntregaTaxaPorBairro;
import descontoTaxaEntregaModule.classes.FormaDescontoTaxaEntregaTipoItem;
import descontoTaxaEntregaModule.classes.FormaDescontoTaxaEntregaValorPedido;
import descontoTaxaEntregaModule.interfaces.IFormaDescontoTaxaEntrega;

import java.util.ArrayList;
import java.util.List;

public class CalculadoraDeDescontoEntregaService {
    private List<IFormaDescontoTaxaEntrega> metodosDeDesconto;

    public CalculadoraDeDescontoEntregaService(){
        this.metodosDeDesconto = new ArrayList<>();
        this.metodosDeDesconto.add(new FormaDescontoTaxaEntregaTaxaPorBairro());
        this.metodosDeDesconto.add(new FormaDescontoTaxaEntregaTipoItem());
        this.metodosDeDesconto.add(new FormaDescontoTaxaEntregaPorTipoCliente());
        this.metodosDeDesconto.add(new FormaDescontoTaxaEntregaValorPedido(2000.0, 0.15));
    }

    public void calcularDesconto(Pedido pedido){
        for (IFormaDescontoTaxaEntrega formaDesconto : metodosDeDesconto){
            formaDesconto.calcularDesconto(pedido);
        }
    }

    public void adicionar(IFormaDescontoTaxaEntrega formaDesconto){
        this.metodosDeDesconto.add(formaDesconto);
    }
}
