package org.example;

import classes.Cliente;
import classes.Item;
import classes.Pedido;
import formaDescontoModule.CalculadoraDeDescontoService;

import java.time.LocalDate;
import java.util.Date;

public class Main {
    public static void main(String[] args) {
        System.out.println("****** Minha loja vitual ******\n\n");

        Cliente cliente1 = new Cliente("Filipe", "Ouro", 10.0, "Rua A, Numero 19", "Centro", "Alegre");
        Cliente cliente2 = new Cliente("Joao", "Prata", 10.0, "Rua B, Numero 0", "Guararema", "Alegre");
        Cliente cliente3 = new Cliente("Antonio", "Bronze", 10.0, "Rua C, Numero 99", "Cidade Maravilhosa", "Celina");

        Item item1 = new Item("Caderno", 3, 12.50, "Educacao");
        Item item2 = new Item("Caneta", 20, 1.50, "Educacao");
        Item item3 = new Item("Mouse", 5, 45.99, "Eletrônicos");
        Item item4 = new Item("Garrafa Termica", 2, 60.00, "Lazer");
        Item item5 = new Item("Alcatra 1Kg", 1, 120.75, "Alimentacao");

        Pedido pedido1 = new Pedido(LocalDate.now(), cliente1);
        Pedido pedido2 = new Pedido(LocalDate.now(), cliente2);
        Pedido pedido3 = new Pedido(LocalDate.now(), cliente3);

        pedido1.adicionar(item1);
        pedido1.adicionar(item3);

        pedido2.adicionar(item2);
        pedido2.adicionar(item5);

        pedido3.adicionar(item1);
        pedido3.adicionar(item2);
        pedido3.adicionar(item3);
        pedido3.adicionar(item4);
        pedido3.adicionar(item5);

        System.out.println(pedido1.toString());
        System.out.println(pedido2.toString());
        System.out.println(pedido3.toString());

        CalculadoraDeDescontoService sCalculadoraDesconto = new CalculadoraDeDescontoService();

        sCalculadoraDesconto.calcularDesconto(pedido1);
        System.out.println("Pedido 1 com desconto aplicado:\n" + pedido1.toString() + pedido1.descreverCuponsUtilizados());

        sCalculadoraDesconto.calcularDesconto(pedido2);
        System.out.println("Pedido 2 com desconto aplicado:\n" + pedido2.toString() + pedido2.descreverCuponsUtilizados());

        sCalculadoraDesconto.calcularDesconto(pedido3);
        System.out.println("Pedido 3 com desconto aplicado:\n" + pedido3.toString() + pedido3.descreverCuponsUtilizados());
    }
}