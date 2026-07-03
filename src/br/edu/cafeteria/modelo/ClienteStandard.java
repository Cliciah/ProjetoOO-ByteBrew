package br.edu.cafeteria.modelo;

import br.edu.cafeteria.excecao.PontosInsuficienteException;

public class ClienteStandard extends ClienteCadastrado {
	
    public ClienteStandard(String nome, String cpf){
        super(nome,cpf);
    }

    @Override
	public void pagar(double valorCompra){
    	int pontosGanhos = adicionarXP(valorCompra);
    	System.out.printf("Pagamento de R$ %.2f realizado. +%d XP acumulado.%n", valorCompra, pontosGanhos);
	}
    
    @Override
    public int xp_a_Receber(double valorCompra) {
    	return (int) Math.floor(valorCompra);
    }
    
    @Override
    public void pagarXP(double valorCompra) {
    	double valorCobertoXP = valorCompra /2;
    	double valorDinheiro =  valorCompra -  valorCobertoXP;
    	int custoPontos = (int) Math.ceil(valorCobertoXP * TAXA_CONVERSAO_XP_PARA_REAL);
    	
    	if (this.saldoAcumuladoXP < custoPontos) {
    		throw new PontosInsuficienteException(String.format("Saldo insuficiente: necessario %d XP para cobrir metade da compra, voce tem disponivel: %d XP",custoPontos, saldoAcumuladoXP));	
    	}
    	
    	this.saldoAcumuladoXP -= custoPontos;
    	System.out.printf("Pedido de R$ %.2f pago: R$ %.2f em dinheiro + %d XP (equivalente a R$ %.2f). Saldo atual: %d XP.%n",
    	        valorCompra, valorDinheiro, custoPontos, valorCobertoXP, saldoAcumuladoXP);
    }
}