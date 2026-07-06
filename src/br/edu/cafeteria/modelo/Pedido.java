package br.edu.cafeteria.modelo;
import br.edu.cafeteria.excecao.EstoqueInsuficienteException;
import br.edu.cafeteria.servico.Promocional;
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

    public void adicionarItem(Produto produto)throws EstoqueInsuficienteException{
        itens.add(new ItemPedido( produto,1));
        System.out.println("Item adicionado ao pedido!!");
    }

    public void adicionarItem(Produto produto, int quantidade)throws EstoqueInsuficienteException{
        produto.atualizarEstoque(-quantidade); 
        itens.add(new ItemPedido(produto, quantidade));
        System.out.println("Item adicionado ao pedido!");
    
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

    if (cliente instanceof ClienteCadastrado) {
        ClienteCadastrado cadastrado = (ClienteCadastrado) cliente;
        cadastrado.pagar(total);
    } else if (cliente instanceof ClienteCasual) {
        ClienteCasual casual = (ClienteCasual) cliente;
        casual.pagar(total);
    }
    }

    public void finalizarVendaXP() {
        
        double total = calcularTotal();

        if (cliente instanceof ClienteVIP) {
            ClienteVIP vip = (ClienteVIP) cliente;
            vip.pagarXP(total);

        } else if (cliente instanceof ClienteStandard) {
            ClienteStandard standard = (ClienteStandard) cliente;
            standard.pagarXP(total);
        }
    }

    public void removerItem(Produto produto, int quantidade) {
        for (int i = 0; i < itens.size(); i++) {
            if (itens.get(i).getProduto() == produto) {
                ItemPedido item = itens.get(i);

                if (quantidade >= item.getQuantidade()) {
                    produto.atualizarEstoque(item.getQuantidade());
                    itens.remove(i);
                    System.out.println("Item removido do pedido!");
                } else {
                    item.setQuantidade(item.getQuantidade() - quantidade);
                    produto.atualizarEstoque(quantidade);
                    System.out.println(quantidade + "x " + produto.getNome() + " removido(s) do pedido!");
                }
                return;
            }
        }
        System.out.println("Item não encontrado no pedido.");
    }

    public void removerItem(Produto produto) {
        for (int i = 0; i < itens.size(); i++) {
            if (itens.get(i).getProduto() == produto) {
                produto.atualizarEstoque(itens.get(i).getQuantidade());
                itens.remove(i);
                System.out.println("Item removido do pedido!");
                return;
            }
        }
        System.out.println("Item não encontrado no pedido.");
    }


    public int getNumPedido() {
        return numPedido;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public Atendente getAtendente() {
        return atendente;
    }

    public List<ItemPedido> getItens() {
        return itens;
    }

}