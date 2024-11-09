package classes;

public class Item {
    private  String nome;
    private int quantidade;
    private double valorUnitario;
    private String tipo;

    public Item(String nome, int quantidade, double valorUnitario, String tipo){
        this.nome = nome;
        this.quantidade = quantidade;
        this.valorUnitario = valorUnitario;
        this.tipo = tipo;
    }

    public String getNome(){
        return this.nome;
    }

    public double getValorUnitario(){
        return this.valorUnitario;
    }

    public String getTipo() {
        return this.tipo;
    }

    public int getQuantidade() {
        return this.quantidade;
    }

    @Override public String toString(){
        return "Informacoes do item\nNome: " + getNome() + "\nValor unitario: " + getValorUnitario() + "\nTipo: " + getTipo() + "\n";
    }
}
