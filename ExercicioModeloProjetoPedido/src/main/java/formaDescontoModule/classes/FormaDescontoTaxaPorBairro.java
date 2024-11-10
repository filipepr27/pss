package formaDescontoModule.classes;

import classes.CupomDescontoEntrega;
import classes.Pedido;
import interfaces.IFormaDescontoTaxaEntrega;

public class FormaDescontoTaxaPorBairro implements IFormaDescontoTaxaEntrega {
    private String bairroCliente;

    @Override
    public CupomDescontoEntrega calcularDesconto(Pedido pedido) {
        validaPedido(pedido);
        double valorDesconto = 0;
        switch (this.bairroCliente){
            case "Centro":
                valorDesconto = 2.0;
                break;
            case "Bela Vista":
                valorDesconto = 3.0;
                break;
            case "Cidade Maravilhosa":
                valorDesconto = 1.5;
                break;
            default:
                break;
        }
        return new CupomDescontoEntrega("Desconto por Bairro", valorDesconto);
    }

    @Override
    public boolean seAplica(Pedido pedido) {
        validaPedido(pedido);
        boolean aplicavel = false;
        double totalDescontos = 0;
        for (CupomDescontoEntrega cupom : pedido.getCuponsDesconto()){
            totalDescontos += cupom.getValorDesconto();
        }
        if(totalDescontos <= 10){
            aplicavel = true;
        }
        return aplicavel;
    }

    private void validaPedido(Pedido pedido){
        if (pedido == null) {
            throw new RuntimeException("Falha ao calcular desconto por bairro. Informe um pedido valido!\n");
        }
        if (pedido.getCliente() == null){
            throw new RuntimeException("Falha ao calcular desconto por bairro. Informe um cliente valido para o pedido!\n");
        }
        this.bairroCliente = pedido.getCliente().getBairro();

    }
}
