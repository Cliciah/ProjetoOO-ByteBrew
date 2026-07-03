package br.edu.cafeteria.modelo;

import br.edu.cafeteria.excecao.PontosInsuficienteException;
import java.util.List;
import java.util.ArrayList;

public class Pedido {

    private static int proximoNumero = 1;
    private int numPedido;
    private Cliente cliente;
    private Atendente atendente;
    private List<ItemPedido> itens = new ArrayList<>(); 

    
public Pedido(Atendente atendente) {
    this.numPedido = proximoNumero;
    proximoNumero++;
    this.atendente = atendente;
}

public Pedido(Atendente atendente, Cliente cliente) {
    this.numPedido = proximoNumero;
    proximoNumero++;
    this.atendente = atendente;
    this.cliente = cliente;
}

    public void adicionarItem(Produto produto){
        itens.add(new ItemPedido( produto));
        System.out.println("Item adicionado ao pedido!!");
    
    }

    public void adicionarItem(Produto produto, int quantidade){
        itens.add(new ItemPedido( produto, quantidade));
        System.out.println("Item adicionado ao pedido!!");
    
    }

    public double calcularTotal(){
        double total = 0;
        for (ItemPedido item : itens) {
            total += item.getProduto().getPrecoBase() * item.getQuantidade();
        }
       return total;
    }

    public double calcularTotal(Promocional promocao){
        double total = 0;
        for (ItemPedido item : itens) {
            double subtotal = item.getProduto().getPrecoBase() * item.getQuantidade();
            if (item.getProduto() instanceof Bebida) {
                subtotal = promocao.aplicarDesconto(subtotal);
            }
            total += subtotal;
        }
        return total;
    }

    public void finalizarVenda() {
        double total = calcularTotal();
        System.out.println("O preço total é " + total + " R$");

        if (cliente != null) {
            int pontosGanhos = cliente.adicionarXP(total);
            cliente.adicionarXP(pontosGanhos);
        }

        System.out.println("Pedido pago com sucesso");
        System.out.println("Pedido adicionado para preparo!!");
    }

    public void finalizarVendaXP() throws PontosInsuficienteException{
        
        double total = calcularTotal();

        if (cliente instanceof ClienteVIP) {
            ClienteVIP vip = (ClienteVIP) cliente;
            vip.pagarXP(total); 
            System.out.println("Pedido pago com XP! Saldo restante: " + vip.getSaldoXP() + " XP");

        } else if (cliente instanceof ClienteStandard) {
            ClienteStandard standard = (ClienteStandard) cliente;
            standard.pagarXP(total); 
            System.out.println("Pedido pago parcialmente com XP! Saldo restante: " + standard.getSaldoXP() + " XP");

        }
    
        System.out.println("Seu saldo de XP no momento é " + getSaldoXP());
        System.out.println("Pedido adicionado para preparo!!");
        System.out.println("Pedido pago com sucesso");


    }

   @Override
    public double aplicarDesconto(double valor){
        return valor - (valor * 0.10);
    }


    public Cliente getCliente() {
        return cliente;
    }

    public Atendente getAtendente() {
        return atendente;
    }


}