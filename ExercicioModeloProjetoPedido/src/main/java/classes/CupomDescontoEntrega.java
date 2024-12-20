package classes;

import interfaces.ICupomDescontoEntrega;

public class CupomDescontoEntrega implements ICupomDescontoEntrega {
    private String nomeMetodo;
    private double valorDesconto;

    public CupomDescontoEntrega(String nomeMetodo, double valorDesconto){
        this.nomeMetodo = nomeMetodo;
        this.valorDesconto = valorDesconto;
    }

    @Override
    public double getValorDesconto(){
        return this.valorDesconto;
    }

    @Override
    public String getNomeMetodo(){
        return this.nomeMetodo;
    }

    @Override public String toString(){
        return "Informacoes do cupom:\nMetodo: " + getNomeMetodo() + "Valor: " + getValorDesconto() + "\n";
    }
}
