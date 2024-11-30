package classes;

import interfaces.ICupomDescontoPedido;

public class CupomDescontoPedido implements ICupomDescontoPedido {

    private String nomeMetodo;
    private double valorDesconto;

    public CupomDescontoPedido(String nomeMetodo, double valorDesconto){
        this.nomeMetodo = nomeMetodo;
        this.valorDesconto = valorDesconto;
    }

    @Override
    public double getValorDesconto() {
        return valorDesconto;
    }

    @Override
    public String getNomeMetodo() {
        return nomeMetodo;
    }

    @Override public String toString(){
        return "Informacoes do cupom:\nMetodo: " + getNomeMetodo() + "Valor: " + getValorDesconto() + "\n";
    }
}
