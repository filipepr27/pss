package classes;

public class Cliente {
    private String nome;
    private String tipo;
    private double fidelidade;
    private String logradouro;
    private String bairro;
    private String cidade;

    public Cliente(String nome, String tipo, double fidelidade, String logradouro, String bairro, String cidade){
        this.nome = nome;
        this.tipo = tipo;
        this.fidelidade = fidelidade;
        this.logradouro = logradouro;
        this.bairro = bairro;
        this.cidade = cidade;
    }

    public String getNome() {
        return this.nome;
    }

    public String getTipo() {
        return this.tipo;
    }

    public String getLogradouro() {
        return this.logradouro;
    }

    public String getBairro() {
        return this.bairro;
    }

    public String getCidade(){
        return this.cidade;
    }

    public double getFidelidade() {
        return this.fidelidade;
    }

    public void setFidelidade(double fidelidade){
        this.fidelidade = fidelidade;
    }

    @Override public String toString(){
        return "Informacoes do cliente\nNome: " + getNome() + "\nTipo: " + getTipo() + "\nBairro: " + getBairro() + "\n";
    }
}
