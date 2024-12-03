package org.example;

import classes.Cliente;
import classes.Item;
import classes.Pedido;
import decontoValorPedidoModule.CalculadoraDeDescontoPedidoService;
import descontoTaxaEntregaModule.CalculadoraDeDescontoEntregaService;
import logModule.RegistradorDeLogService;
import services.PedidoService;
import services.UsuarioLogadoService;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {

        System.out.println("Configurando sistema...\n\n");
        RegistradorDeLogService.definirTipoLog("DBLog");
        
        System.out.println("****** Minha loja vitual ******\n\n");

        Cliente cliente1 = new Cliente("Filipe", "Ouro", 10.0, "Rua A, Numero 19", "Centro", "Alegre");
        Cliente cliente2 = new Cliente("Pedro Vitor", "Prata", 10.0, "Rua B, Numero 0", "Guararema", "Alegre");
        Cliente cliente3 = new Cliente("Antonio", "Platina", 10.0, "Rua C, Numero 99", "Cidade Maravilhosa", "Celina");

        Item item1 = new Item("Caderno", 3, 12.50, "Educacao");
        Item item2 = new Item("Caneta", 20, 1.50, "Educacao");
        Item item3 = new Item("Mouse", 5, 45.99, "Eletrônicos");
        Item item4 = new Item("Garrafa Termica", 2, 60.00, "Lazer");
        Item item5 = new Item("Alcatra 1Kg", 1, 120.75, "Alimentacao");

        Pedido pedido1 = new Pedido(1,LocalDate.now(), cliente1, 10);
        Pedido pedido2 = new Pedido(2,LocalDate.now(), cliente2, 20);
        Pedido pedido3 = new Pedido(3,LocalDate.now(), cliente3, 20);

        PedidoService pedidoService = new PedidoService(RegistradorDeLogService.getTipoLogSelecionado(), UsuarioLogadoService.getNomeUsuario());

        pedidoService.adicionar(pedido1, item1);
        pedidoService.adicionar(pedido2, item2);
//        pedido1.adicionar(item1);
//        pedido1.adicionar(item3);

        pedidoService.adicionar(pedido2, item2);
        pedidoService.adicionar(pedido2, item5);
//        pedido2.adicionar(item2);
//        pedido2.adicionar(item5);

        pedidoService.adicionar(pedido3, item1);
        pedidoService.adicionar(pedido3, item2);
        pedidoService.adicionar(pedido3, item3);
        pedidoService.adicionar(pedido3, item4);
        pedidoService.adicionar(pedido3, item5);
//        pedido3.adicionar(item1);
//        pedido3.adicionar(item2);
//        pedido3.adicionar(item3);
//        pedido3.adicionar(item4);
//        pedido3.adicionar(item5);

        pedidoService.setCodigoCupom(pedido1, "DESC10");
        pedidoService.setCodigoCupom(pedido2, "DESC20");
        pedidoService.setCodigoCupom(pedido3, "DESC70");
//        pedido1.setCodigoDeCupom("DESC10");
//        pedido2.setCodigoDeCupom("DESC20");
//        pedido3.setCodigoDeCupom("DESC70");

        System.out.println(pedido1.toString());
        System.out.println(pedido2.toString());
        System.out.println(pedido3.toString());

        CalculadoraDeDescontoEntregaService sCalculadoraDescontoEntrega = new CalculadoraDeDescontoEntregaService();

        sCalculadoraDescontoEntrega.calcularDesconto(pedido1);
        System.out.println("Pedido 1 com desconto de entrega aplicado:\n" + pedidoService.detalharPedido(pedido1) + pedidoService.descreverCuponsEntregaUtilizados(pedido1));

        sCalculadoraDescontoEntrega.calcularDesconto(pedido2);
        System.out.println("Pedido 2 com desconto de entrega aplicado:\n" + pedidoService.detalharPedido(pedido2) + pedidoService.descreverCuponsEntregaUtilizados(pedido2));

        sCalculadoraDescontoEntrega.calcularDesconto(pedido3);
        System.out.println("Pedido 3 com desconto de entrega aplicado:\n" + pedidoService.detalharPedido(pedido3) + pedidoService.descreverCuponsEntregaUtilizados(pedido3));

        CalculadoraDeDescontoPedidoService sCalculadoraDescontoPedido = new CalculadoraDeDescontoPedidoService();

        sCalculadoraDescontoPedido.calcularDesconto(pedido1);
        System.out.println("Pedido 1 com desconto de valor pedido aplicado:\n" + pedidoService.detalharPedido(pedido1) + pedidoService.descreverCuponsValorPedidoUtilizados(pedido1));

        sCalculadoraDescontoPedido.calcularDesconto(pedido2);
        System.out.println("Pedido 2 com desconto de valor pedido aplicado:\n" + pedidoService.detalharPedido(pedido2) + pedidoService.descreverCuponsValorPedidoUtilizados(pedido2));

        sCalculadoraDescontoPedido.calcularDesconto(pedido3);
        System.out.println("Pedido 3 com desconto de valor pedido aplicado:\n" + pedidoService.detalharPedido(pedido3) + pedidoService.descreverCuponsValorPedidoUtilizados(pedido3));
    }
}