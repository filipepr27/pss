package classes;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

public class Pedido {
    private double taxaEntrega = 10;
    // no diagrama não existe explicitamente as variáveis abaixo. pq?
    private Cliente cliente;
    private Date data;
    private List<Item> itens;
    private List<CupomDescontoEntrega> cuponsDesconto;
//    private List<>


    public Pedido(Date data, Cliente cliente) {
        this.cliente = cliente;
        this.data = data;
        this.itens = new ArrayList<>(); // tem diferença entre criar o array aqui e na declaração da variável
        this.cuponsDesconto = new ArrayList<>();
    }

    public void adicionar(Item item){
        this.itens.add(item);
    }

    public double getValorPedido(){
        double valorPedido = 0;
        for (Item item : itens){
            valorPedido += item.getValorUnitario() * item.getQuantidade();
        }

        valorPedido -= getDescontoConcedido();
        return valorPedido;
    }

    public Cliente getCliente() {
        return this.cliente;
    }

    public List<Item> getItens(){
        return this.itens;
    }

    public double getTaxaEntrega(){
        return this.taxaEntrega;
    }

    public void aplicarDesconto(CupomDescontoEntrega cupomDesconto){
        this.cuponsDesconto.add(cupomDesconto);
    }

    public double getDescontoConcedido(){
        double valorDesconto = 0;
        for (CupomDescontoEntrega cupom : cuponsDesconto){
            valorDesconto += cupom.getValorDesconto();
        }
        return valorDesconto;
    }

    public List<CupomDescontoEntrega> getCuponsDesconto(){
        return this.cuponsDesconto;
    }

    @Override public String toString(){
        return "Informacoes do pedido\nValor do pedido: " + getValorPedido() + "\nValor desconto: " + getDescontoConcedido() + "\n";
    }

}
