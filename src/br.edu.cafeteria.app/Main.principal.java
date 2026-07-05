package br.edu.cafeteria.app;

import br.edu.cafeteria.modelo.*;
import br.edu.cafeteria.servico.*;
import br.edu.cafeteria.excecao.*;

public class Main {

    public static void main(String[] args) {
        System.out.println("Sistema de vendas e fidelidade para a Cafeteria Geek Byte & Brew");

        Produto lembasBread = new Comida("COM-01", "Lembas Bread", 12.00, 15, 10, true, false);
        Produto portalCake = new Comida("COM-02", "Portal Cake", 18.00, 5, 15, false, false);
        
        Produto cafeProgramador = new Bebida("BEB-01", "Café do Programador", 8.50, 20, 'M', 180);
        Produto pocaoMana = new Bebida("BEB-02", "Poção de Mana", 10.00, 3, 'G', 0);

        Atendente atendente = new Atendente("Carlos", "123.456.789-00");
        ClienteService clientes = new ClienteService();
        
        ClienteCasual casual = new ClienteCasual();
        Cliente standard = new ClienteStandard("Carlos", "123.456.789-00");
        Cliente vip = new ClienteVIP("Ana", "987.654.321-11");
        
        clientes.criar(carlos);
        clientes.criar(ana);

        System.out.println("CENÁRIO 1: Fluxo de Venda - Cliente Casual");
        Pedido pedido1 = new Pedido(atendente, casual);
        
        pedido1.adicionarItem(cafeProgramador, 1); 
        pedido1.adicionarItem(lembasBread, 2); 

        double totalOriginal = pedido1.calcularTotal();
        pedido1.finalizarVenda();
        System.out.println("Valor do pedido: R$ " + pedido1.calcularTotal() + ".");


        System.out.println("CENÁRIO 2: Carlos Standard acumulando XP (1 para 1)");
        Pedido pedido2 = new Pedido(atendente, carlos);
        
        pedido2.adicionarItem(portalCake, 1); 
        pedido2.adicionarItem(pocaoMana, 1);  
        pedido2.finalizarVenda();
        System.out.println("XP Acumulado pelo Carlos: " + carlos.getSaldoAcumuladoXP() + " XP.");


        System.out.println("CENÁRIO 3: Testando Desconto de Evento Geek (10% em Bebidas)");
        Pedido pedido3 = new Pedido(atendente, carlos);
        pedido3.adicionarItem(lembas, 1);       
        pedido3.adicionarItem(pocaoMana, 2);    

        Promocional eventoGeek = new PromocionalGeek();
        double totalComDesconto = pedido3.calcularTotal(eventoGeek);
        System.out.printf("Valor Bruto: R$" + pedido3.calcularTotal() + " | Valor com Desconto de Evento: R$" + totalComDesconto + ".");


        System.out.println("CENÁRIO 4: Forçando Erro de Garantia de Estoque");
        try {
            Pedido pedido4 = new Pedido(atendente, ana);
            System.out.println("Estoque disponível de Portal Cake: " + portalCake.consultarEstoque());
            System.out.println("Ana tenta pedir 10 fatias de Portal Cake.");
            
            pedido4.adicionarItem(portalCake, 10); 
            
        } catch (EstoqueInsuficienteException e) {
            System.out.println("[ERRO DE SISTEMA CAPTURADO]: " + e.getMessage() + ".");
        }

        
        System.out.println("CENÁRIO 5: Cliente VIP - Multiplicador e Erro de Resgate");
        Pedido pedido5 = new Pedido(atendente, ana);
        pedido5.adicionarItem(lembas, 5);
        pedido5.finalizarVenda();
        System.out.println("Saldo da Ana (VIP ganha x2): " + ana.getSaldoAcumuladoXP() + " XP.");

        try {
            System.out.println("Ana tenta fazer uma compra nova de R$ 50,00 pagando INTEGRALMENTE com XP.");
            Pedido pedido6 = new Pedido(atendente, ana);
            pedido6.adicionarItem(cafeProgramador, 5);
            pedido6.adicionarItem(lembas, 2);
            
            System.out.println("Total necessário para resgatar: 590 XP. Saldo atual: " + ana.getSaldoAcumuladoXP() + ".");
            pedido6.finalizarVendaXP(); 
            
        } catch (PontosInsuficienteException e) {
            System.out.println("[ERRO DE RESGATE CAPTURADO]: " + e.getMessage() + ".");
        }
    }
}
