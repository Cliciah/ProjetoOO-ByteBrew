package br.edu.cafeteria.app;

import br.edu.cafeteria.modelo.*;
import br.edu.cafeteria.servico.*;
import br.edu.cafeteria.excecao.EstoqueInsuficienteException;
import br.edu.cafeteria.excecao.PontosInsuficienteException;

public class Main {

    public static void main(String[] args) {
        System.out.println("Sistema de vendas e fidelidade para a Cafeteria Geek Byte & Brew");

        Produto lembasBread = new Comida("COM-01", "Lembas Bread", 12.00, 15, 10, true, false);
        Produto portalCake = new Comida("COM-02", "Portal Cake", 18.00, 5, 15, false, false);
        
        Produto cafeProgramador = new Bebida("BEB-01", "Café do Programador", 8.50, 20, 'M', 180);
        Produto pocaoMana = new Bebida("BEB-02", "Poção de Mana", 10.00, 3, 'G', 0);

        Cliente standard = new ClienteStandard("Ana", "123.456.789-00");
        Cliente vip = new ClienteVIP("João", "987.654.321-11");


        System.out.println("CENÁRIO 1: Pedido Regular com Desconto de Evento Geek");
        
        Pedido pedido1 = new Pedido("Atendente Clara", standard);
        
        pedido1.adicionarItem(cafeProgramador, 1); 
        
        pedido1.adicionarItem(lembasBread, 2); 

        double totalOriginal = pedido1.calcularTotal();
        System.out.println("Valor do pedido: R$ " + totalOriginal);

        Promocional eventoGeek = new Promocional() {
            @Override
            public double aplicarDesconto(Pedido p) {
                double totalBebidas = 0;
                for (ItemPedido item : p.getItens()) {
                    if (item.getProduto() instanceof Bebida) {
                        totalBebidas += item.getSubtotal();
                    }
                }
                return totalBebidas * 0.10; 
            }
        };

        double descontoCalculado = eventoGeek.aplicarDesconto(pedido1);
        double valorFinalPedido1 = totalOriginal - descontoCalculado;
        
        System.out.println("Desconto aplicado (10% nas Bebidas): R$ " + descontoCalculado);
        System.out.println("Valor total a pagar: R$ " + valorFinalPedido1);
        
        pedido1.finalizarVenda(false);
        
        System.out.println("Saldo de XP do Cliente " + standard.getNome() + ": " + standard.getSaldoXP() + " XP\n");


        System.out.println("CENÁRIO 2: Testando Restrição de Garantia de Estoque");
        try {
            Pedido pedido2 = new Pedido("Atendente Eduardo", null);
            
            System.out.println("Tentando adicionar 10 unidades de Poção de Mana (Estoque atual: 3).");
            pedido2.adicionarItem(pocaoMana, 10); 
            
            pedido2.finalizarVenda(false);
        } catch (EstoqueInsuficienteException e) {
            System.err.println("[ERRO DE OPERAÇÃO]: " + e.getMessage() + "\n");
        }


        System.out.println("CENÁRIO 3: Cliente VIP Acumulando XP com Multiplicador");
        Pedido pedido3 = new Pedido("Atendente Gabriel", vip);
        pedido3.adicionarItem(portalCake, 2); // R$ 36.00
        
        pedido3.finalizarVenda(false); // Pagamento normal para gerar pontos
        
        System.out.println("Saldo atual de XP da VIP " + vip.getNome() + ": " + vip.getSaldoXP() + " XP\n");


        System.out.println("CENÁRIO 4: Testando Restrição de Resgate de Pontos VIP");
        try {
            Pedido pedido4 = new Pedido("Atendente Ester", vip);
            pedido4.adicionarItem(portalCake, 3); // Valor total: R$ 54.00
            
            System.out.println("Valor do Pedido: R$ 54.00 | XP Necessário para resgate: 540 XP");
            System.out.println("Tentando pagar o pedido INTEIRAMENTE com pontos de XP (Saldo atual: " + vip.getSaldoXP() + ").");
            
            pedido4.finalizarVenda(true); 
            
        } catch (PontosInsuficienteException e) {
            System.err.println("[ERRO DE OPERAÇÃO]: " + e.getMessage() + "\n");
        }
    }
}