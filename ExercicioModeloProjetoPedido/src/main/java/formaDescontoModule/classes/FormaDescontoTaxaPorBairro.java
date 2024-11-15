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
            double porcentagemDesconto = 0;
            String nomeMetodoDesconto = "Desconto por Bairro";
            switch (this.bairroCliente){
                case "Centro":
                    porcentagemDesconto = 0.20;
                    break;
                case "Bela Vista":
                    porcentagemDesconto = 0.30;
                    break;
                case "Cidade Maravilhosa":
                    porcentagemDesconto = 0.15;
                    break;
                default:
                    break;
            }

            if (porcentagemDesconto > 0) {
                pedido.aplicarDesconto(new CupomDescontoEntrega(nomeMetodoDesconto + " " + this.bairroCliente, porcentagemDesconto * pedido.getTaxaEntrega()));
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
