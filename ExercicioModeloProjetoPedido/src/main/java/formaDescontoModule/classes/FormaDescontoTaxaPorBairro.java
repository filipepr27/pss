package formaDescontoModule.classes;

import classes.CupomDescontoEntrega;
import classes.Pedido;
import interfaces.IFormaDescontoTaxaEntrega;

public class FormaDescontoTaxaPorBairro implements IFormaDescontoTaxaEntrega {
    private String bairroCliente;

    @Override
    public void calcularDesconto(Pedido pedido) {
        validaPedido(pedido);
        if(seAplica(pedido)){
            double valorDesconto = 0;
            String nomeMetodoDesconto = "Desconto por Bairro";
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

            if(pedido.getDescontoConcedido() + valorDesconto > 10.0){
                valorDesconto = 10.0 - pedido.getDescontoConcedido();
                nomeMetodoDesconto = "Desconto parcial por Bairro";
            }

            if (valorDesconto > 0) {
                pedido.aplicarDesconto(new CupomDescontoEntrega(nomeMetodoDesconto + " " + this.bairroCliente, valorDesconto));
            }
        }
    }

    @Override
    public boolean seAplica(Pedido pedido) {
        validaPedido(pedido);
        boolean aplicavel = false;
        if(pedido.getCliente().getBairro() != null){
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
