package services;

import classes.CupomDescontoEntrega;
import classes.CupomDescontoPedido;
import classes.Item;
import classes.Pedido;
import logModule.interfaces.ILog;

public class PedidoService {
    private final ILog log;
    private final String usuarioLogado;

    // pode ser que nem todos estes métodos sejam necessários de estarem na service

    public PedidoService(ILog log, String usuarioLogado){
        this.log = log;
        this.usuarioLogado = usuarioLogado;
    }

    public void adicionar(Pedido pedido, Item item){
        pedido.adicionar(item);
    }

    public double getValorPedido(Pedido pedido){
        log.escrever("Mensagem " + usuarioLogado);
        return pedido.getValorPedido();
    }

    public double getTaxaEntrega(Pedido pedido){ return pedido.getTaxaEntrega(); }

    public void aplicarDescontoEntrega(Pedido pedido, CupomDescontoEntrega cupomDescontoEntrega) {
        pedido.aplicarDescontoEntrega(cupomDescontoEntrega);
    }

    public void aplicarDescontoValorPedido(Pedido pedido, CupomDescontoPedido cupomDescontoPedido) {
        pedido.aplicarDescontoValorPedido(cupomDescontoPedido);
    }

    public double getDescontoConcedidoEntrega(Pedido pedido) {return pedido.getDescontoConcedidoEntrega(); }

    public double getDescontoConcedidoValorPedido(Pedido pedido) {return pedido.getDescontoConcedidoValorPedido(); }

    public String descreverCuponsEntregaUtilizados(Pedido pedido) { return pedido.descreverCuponsEntregaUtilizados(); }

    public String descreverCuponsValorPedidoUtilizados(Pedido pedido) { return pedido.descreverCuponsValorPedidoUtilizados(); }

    public void setCodigoCupom(Pedido pedido, String codigoDeCupom){ pedido.setCodigoDeCupom(codigoDeCupom); }

    public String getCodigoDeCupom(Pedido pedido) { return pedido.getCodigoDeCupom(); }

    public String detalharPedido(Pedido pedido){
        return "Informacoes do pedido\nCliente: " + pedido.getCliente().getNome() + "\nTipo: " + pedido.getCliente().getTipo() + "\nBairro: " + pedido.getCliente().getBairro() +
                "\nValor do pedido: " + this.getValorPedido(pedido) + "\nValor desconto pedido: " + this.getDescontoConcedidoValorPedido(pedido)  +
                "\nValor entrega: " + this.getTaxaEntrega(pedido) + "\nValor desconto entrega: " + this.getDescontoConcedidoEntrega(pedido)  + "\n";
    }

}
