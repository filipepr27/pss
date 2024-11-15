package classes;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

public class Pedido {
    private double taxaEntrega = 10; // passar para o construtor (poder ser variável)
    // no diagrama não existe explicitamente as variáveis abaixo. pq?
    private Cliente cliente;
    private LocalDate data;
    private List<Item> itens;
    private List<CupomDescontoEntrega> cuponsDesconto;
    // tipos de relacionamentos no diagrama de classes


    public Pedido(LocalDate data, Cliente cliente) {
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
        return valorPedido;
    }

    public Cliente getCliente() {
        return this.cliente;
    }

    public List<Item> getItens(){
        return this.itens;
    }

    public double getTaxaEntrega(){

        return this.taxaEntrega - this.getDescontoConcedido();
    }

    public void aplicarDesconto(CupomDescontoEntrega cupomDesconto){
        this.cuponsDesconto.add(cupomDesconto);
    }

    public double getDescontoConcedido(){
        double valorDesconto = 0;
        for (CupomDescontoEntrega cupom : cuponsDesconto){
            valorDesconto += cupom.getValorDesconto();
        }

        if(valorDesconto >= this.taxaEntrega)
        {
            valorDesconto = this.taxaEntrega;
        }
        return valorDesconto;
    }

    public List<CupomDescontoEntrega> getCuponsDesconto(){
        return this.cuponsDesconto;
    }

    public String descreverCuponsUtilizados(){
        String descricaoCupons = "";
        for (CupomDescontoEntrega cupom : getCuponsDesconto()){
            descricaoCupons += "- " + cupom.getNomeMetodo() + " RS" + cupom.getValorDesconto() + "\n";
        }
        return descricaoCupons;
    }

    @Override public String toString(){
        return "Informacoes do pedido\nCliente: " + this.cliente.getNome() + "\nValor do pedido: " + getValorPedido() + "\nValor desconto: " + getDescontoConcedido() + "\n";
    }

}
