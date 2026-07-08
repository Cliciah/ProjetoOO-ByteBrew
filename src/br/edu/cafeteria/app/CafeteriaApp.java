package br.edu.cafeteria.app;

import br.edu.cafeteria.modelo.*;
import br.edu.cafeteria.servico.*;
import br.edu.cafeteria.excecao.*;

public class CafeteriaApp {

    public static void main(String[] args) {
        System.out.println("Bem Vindos ao Sistema de vendas e fidelidade para a Cafeteria Geek Byte & Brew");
        System.out.println();

        Produto lembasBread     = new Comida("COM-01", "Lembas Bread", 12.00, 200, 10, true, false);
        Produto portalCake      = new Comida("COM-02", "Portal Cake", 18.00, 5, 15, false, false);
        Produto cafeProgramador = new Bebida("BEB-01", "Café do Programador", 8.50, 200, "M", 180);
        Produto pocaoMana       = new Bebida("BEB-02", "Poção de Mana", 10.00, 30, "G", 0);

        Atendente atendente = new Atendente("João", "123.456.789-00");
        ClienteService clientes = new ClienteService();

        Cliente[] c = new Cliente[] {
            new ClienteCasual(),
            new ClienteStandard("Carlos", "111.222.333-44"),
            new ClienteVIP("Ana", "987.654.321-11")
        };

        
        System.out.println("CENÁRIO 1: Cadastro e consulta de clientes (ClienteService)");
        System.out.println("-----------------");
        clientes.criar((ClienteCadastrado) c[1]); 
        clientes.criar((ClienteCadastrado) c[2]); 

        System.out.println("CPF 111.222.333-44 cadastrado? " + clientes.cpfCadastrado("111.222.333-44"));
        System.out.println("CPF 000.000.000-00 cadastrado? " + clientes.cpfCadastrado("000.000.000-00"));

        ClienteCadastrado encontrado = clientes.buscarCpf("987.654.321-11");
        System.out.println("buscarCpf(987.654.321-11) -> " + encontrado.getNome()
                + " | saldo: " + encontrado.getSaldoAcumuladoXP() + " XP");
        System.out.println();

        System.out.println("CENÁRIO 2: Fluxo de venda - Cliente Casual");
        System.out.println("-----------------");
        Pedido pedido1 = new Pedido(atendente, c[0]);
        pedido1.adicionarItem(cafeProgramador, 1);
        pedido1.adicionarItem(lembasBread, 2);
        pedido1.finalizarVenda();
        System.out.println("Valor do pedido: R$ " + pedido1.calcularTotal());
        System.out.println();

        System.out.println("CENÁRIO 3: Carlos Standard acumulando XP (1 para 1)");
        System.out.println("-----------------");
        Pedido pedido2 = new Pedido(atendente, c[1]);
        pedido2.adicionarItem(portalCake, 1);
        pedido2.adicionarItem(pocaoMana, 1);
        pedido2.finalizarVenda();
        System.out.println("XP acumulado pelo Carlos: " + ((ClienteStandard) c[1]).getSaldoAcumuladoXP() + " XP");
        System.out.println();

        System.out.println("CENÁRIO 4: Removendo itens da comanda (sobrecarga de removerItem)");
        System.out.println("-----------------");
        Pedido pedido3 = new Pedido(atendente, c[0]);
        pedido3.adicionarItem(lembasBread, 3);
        pedido3.adicionarItem(cafeProgramador, 2);
        System.out.println("Total com todos os itens: R$ " + pedido3.calcularTotal());

        pedido3.removerItem(cafeProgramador);    
        pedido3.removerItem(lembasBread, 1);  
        System.out.println("Total após remoções: R$ " + pedido3.calcularTotal());
        System.out.println("Estoque de café restaurado: " + cafeProgramador.consultarEstoque());
        System.out.println();

        System.out.println("CENÁRIO 5: Desconto de Evento Geek (10% em bebidas)");
        System.out.println("-----------------");
        Pedido pedido4 = new Pedido(atendente, c[1]);
        pedido4.adicionarItem(lembasBread, 1);
        pedido4.adicionarItem(pocaoMana, 2);
        Promocional eventoGeek = new PromocionalGeek();
        System.out.printf("Valor bruto: R$ %.2f | Com desconto de evento: R$ %.2f%n",
                pedido4.calcularTotal(), pedido4.calcularTotal(eventoGeek));
        System.out.println();

        System.out.println("CENÁRIO 6: Forçando erro de garantia de estoque");
        System.out.println("-----------------");
        try {
            Pedido pedido5 = new Pedido(atendente, c[2]);
            System.out.println("Estoque de Portal Cake: " + portalCake.consultarEstoque());
            System.out.println("Ana tenta pedir 10 fatias de Portal Cake...");
            pedido5.adicionarItem(portalCake, 10);
        } catch (EstoqueInsuficienteException e) {
            System.out.println("[ERRO CAPTURADO]: " + e.getMessage());
        }
        System.out.println();

        System.out.println("CENÁRIO 7: Cliente VIP - multiplicador e erro de resgate");
        System.out.println("-----------------");
        Pedido pedido6 = new Pedido(atendente, c[2]);
        pedido6.adicionarItem(lembasBread, 5);
        pedido6.finalizarVenda();
        System.out.println("Saldo da Ana (VIP ganha x2): " + ((ClienteVIP) c[2]).getSaldoAcumuladoXP() + " XP");

        try {
            System.out.println("Ana tenta pagar uma nova compra INTEGRALMENTE com XP...");
            Pedido pedido7 = new Pedido(atendente, c[2]);
            pedido7.adicionarItem(cafeProgramador, 5);
            pedido7.adicionarItem(lembasBread, 2);
            System.out.println("Total do pedido: R$ " + pedido7.calcularTotal()
                    + " | Saldo atual: " + ((ClienteVIP) c[2]).getSaldoAcumuladoXP() + " XP");
            pedido7.finalizarVendaXP();
        } catch (PontosInsuficienteException e) {
            System.out.println("[ERRO CAPTURADO]: " + e.getMessage());
        }
        System.out.println();

        System.out.println("CENÁRIO 8: Carlos Standard resgatando XP (paga metade em pontos)");
        System.out.println("-----------------");
        
        Pedido pedido8 = new Pedido(atendente, c[1]);
        pedido8.adicionarItem(cafeProgramador, 50);
        pedido8.finalizarVenda();
        System.out.println("Saldo do Carlos após a compra: " + ((ClienteStandard) c[1]).getSaldoAcumuladoXP() + " XP");

        Pedido pedido9 = new Pedido(atendente, c[1]);
        pedido9.adicionarItem(cafeProgramador, 2);
        pedido9.finalizarVendaXP();
        System.out.println("Saldo do Carlos após resgate parcial: " + ((ClienteStandard) c[1]).getSaldoAcumuladoXP() + " XP");
        System.out.println();

        System.out.println("CENÁRIO 9: Promoção de nível (Standard -> VIP)");
        System.out.println("-----------------");
        System.out.println("Saldo do Carlos: " + ((ClienteStandard) c[1]).getSaldoAcumuladoXP()
                + " XP (necessário 300 para VIP)");
        try {
            ClienteVIP carlosVip = ClienteCadastrado.promover((ClienteStandard) c[1]);
            clientes.atualizar(carlosVip);

            ClienteCadastrado agora = clientes.buscarCpf("111.222.333-44");
            System.out.println("Carlos agora é: " + agora.getClass().getSimpleName()
                    + " com " + agora.getSaldoAcumuladoXP() + " XP");
                                                                                                                                                                                                                                                                                         
            Pedido pedido10 = new Pedido(atendente, carlosVip);
            pedido10.adicionarItem(cafeProgramador, 1);
            pedido10.finalizarVenda();
            System.out.println("Saldo do Carlos VIP (ganho em dobro): " + carlosVip.getSaldoAcumuladoXP() + " XP");
        } catch (PontosInsuficienteException e) {  
            System.out.println("[PROMOÇÃO NEGADA]: " + e.getMessage());
        }
        System.out.println();

        System.out.println("CENÁRIO 10: Rebaixamento de nível (VIP -> Standard)");
        System.out.println("-----------------");
        ClienteStandard anaStandard = ClienteCadastrado.rebaixar((ClienteVIP) c[2]);
        clientes.atualizar(anaStandard); 

        ClienteCadastrado anaAgora = clientes.buscarCpf("987.654.321-11");
        System.out.println("Ana agora é: " + anaAgora.getClass().getSimpleName()
                + " com " + anaAgora.getSaldoAcumuladoXP() + " XP");

        Pedido pedido11 = new Pedido(atendente, anaStandard);
        pedido11.adicionarItem(cafeProgramador, 1);
        pedido11.finalizarVenda();
        System.out.println("Saldo da Ana Standard (ganho simples): " + anaStandard.getSaldoAcumuladoXP() + " XP");
        System.out.println();

        System.out.println("CENÁRIO 11: Remoção de cadastro no ClienteService");
        System.out.println("-----------------");
        System.out.println("Ana cadastrada antes da remoção? " + clientes.cpfCadastrado("987.654.321-11"));
        clientes.remover("987.654.321-11");
        System.out.println("Ana cadastrada após a remoção?  " + clientes.cpfCadastrado("987.654.321-11"));
        System.out.println("buscarCpf(987.654.321-11) agora retorna: " + clientes.buscarCpf("987.654.321-11"));
    }
}