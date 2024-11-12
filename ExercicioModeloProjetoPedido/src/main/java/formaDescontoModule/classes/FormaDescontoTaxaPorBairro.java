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
        String nomeMetodoDesconto = "Desconto por Bairro nao aplicavel";
        if(seAplica(pedido)){
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

            nomeMetodoDesconto = "Desconto por Bairro";
            if(pedido.getDescontoConcedido() + valorDesconto > 10.0){
                valorDesconto = 10.0 - pedido.getDescontoConcedido();
                nomeMetodoDesconto = "Desconto parcial por Bairro";
            }
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
            throw new RuntimeException("Falha ao calcular desconto por bairro. Informe um pedido valido!\n");
        }
        if (pedido.getCliente() == null){
            throw new RuntimeException("Falha ao calcular desconto por bairro. Informe um cliente valido para o pedido!\n");
        }
        this.bairroCliente = pedido.getCliente().getBairro();

    }
}
