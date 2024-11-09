package classes;

public class CupomDescontoEntrega {
    private String nomeMetodo;
    private double valorDesconto;

    public CupomDescontoEntrega(String nomeMetodo, double valorDesconto){
        this.nomeMetodo = nomeMetodo;
        this.valorDesconto = valorDesconto;
    }

    public double getValorDesconto(){
        return this.valorDesconto;
    }

    public String getNomeMetodo(){
        return this.nomeMetodo;
    }

    @Override public String toString(){
        return "Informacoes do cupom:\nMetodo: " + getNomeMetodo() + "Valor: " + getValorDesconto() + "\n";
    }
}
