package classes;

import interfaces.ICupomDescontoEntrega;
import interfaces.ICupomDescontoPedido;

import java.time.LocalDate;
import java.util.*;

public class Pedido {
    private double taxaEntrega = 10;
    private Cliente cliente;
    private LocalDate data;
    private String codigoDeCupom;
    private List<Item> itens;
    private List<CupomDescontoEntrega> cuponsDescontoEntrega;
    private List<CupomDescontoPedido> cuponsDescontoPedido;
    private int codigo;

    public Pedido(int codigo, LocalDate data, Cliente cliente, double taxaEntrega) {
        this.codigo = codigo;
        this.cliente = cliente;
        this.data = data;
        this.taxaEntrega = taxaEntrega;
        this.itens = new ArrayList<>();
        this.cuponsDescontoEntrega = new ArrayList<>();
        this.cuponsDescontoPedido = new ArrayList<>();
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
        return this.taxaEntrega;
    }

    public void aplicarDescontoEntrega(CupomDescontoEntrega cupomDescontoEntrega){
        this.cuponsDescontoEntrega.add(cupomDescontoEntrega);
    }

    public void aplicarDescontoValorPedido(CupomDescontoPedido cupomDescontoPedido){
        this.cuponsDescontoPedido.add(cupomDescontoPedido);
    }

    public double getDescontoConcedidoEntrega(){
        double valorDesconto = 0;
        for (ICupomDescontoEntrega cupom : cuponsDescontoEntrega){
            valorDesconto += cupom.getValorDesconto();
        }

        return Math.min(valorDesconto, this.taxaEntrega);
    }

    public double getDescontoConcedidoValorPedido(){
        double valorDesconto = 0;
        for (ICupomDescontoPedido cupom : cuponsDescontoPedido){
            valorDesconto += cupom.getValorDesconto();
        }

        return valorDesconto;
    }

    public List<CupomDescontoEntrega> getCuponsDescontoEntrega(){ return this.cuponsDescontoEntrega; }

    public List<CupomDescontoPedido> getCuponsDescontoPedido() { return this.cuponsDescontoPedido; }

    public String descreverCuponsEntregaUtilizados(){
        String descricaoCupons = "";
        for (ICupomDescontoEntrega cupom : getCuponsDescontoEntrega()){
            descricaoCupons = descricaoCupons + ("- " + cupom.getNomeMetodo() + " RS" + cupom.getValorDesconto() + "\n");
        }
        return descricaoCupons;
    }

    public String descreverCuponsValorPedidoUtilizados(){
        String descricaoCupons = "";
        for (ICupomDescontoPedido cupom : getCuponsDescontoPedido()){
            descricaoCupons = descricaoCupons + ("- " + cupom.getNomeMetodo() + " RS" + cupom.getValorDesconto() + "\n");
        }
        return descricaoCupons;
    }

    public void setCodigoDeCupom(String codigoDeCupom){
        this.codigoDeCupom = codigoDeCupom;
    }

    public String getCodigoDeCupom() {
        return codigoDeCupom;
    }

    public List<String> getTipoItens() {
        List<String> tipos = new ArrayList<>();
        for (Item item : itens){
            if (!tipos.contains(item.getTipo())){
                tipos.add(item.getTipo());
            }
        }
        return tipos;
    }
    public int getCodigo() {
        return this.codigo;
    }

    @Override public String toString(){
        return "Informacoes do pedido\nCodigo de Pedido:" + getCodigo() +"\nCliente: " + this.cliente.getNome() + "\nTipo: " + this.cliente.getTipo() + "\nBairro: " + this.cliente.getBairro() +
                "\nValor do pedido: " + getValorPedido() + "\nValor desconto pedido: " + getDescontoConcedidoValorPedido()  +
                "\nValor entrega: " + getTaxaEntrega() + "\n" + "\nValor desconto entrega: " + getDescontoConcedidoEntrega()  + "\n";
    }

}
