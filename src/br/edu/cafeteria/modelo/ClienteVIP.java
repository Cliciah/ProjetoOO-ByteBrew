package br.edu.cafeteria.modelo;

import br.edu.cafeteria.excecao.PontosInsuficienteException;

public class ClienteVIP extends ClienteCadastrado {
	
    public ClienteVIP(String nome, String cpf){
        super(nome,cpf);
    }

	@Override
	public void pagar(double valorCompra) {
		int pontosGanhos = adicionarXP(valorCompra);
		System.out.printf("Pagamento de R$ %.2f realizado. +%d XP acumulado (bonus VIP).%n",valorCompra, pontosGanhos);
	}
	
	@Override
	public int xp_a_Receber(double valorCompra) {
		return (int) Math.floor(valorCompra) * 2;
	}
	
	@Override
	public void pagarXP(double valorCompra) {
		int custoPontos = (int) Math.ceil(valorCompra * TAXA_CONVERSAO_XP_PARA_REAL);
		
		if(this.saldoAcumuladoXP < custoPontos) {
			throw new PontosInsuficienteException(String.format("Saldo insuficiente: necessario %d XP para cobrir o valor total da compra, voce tem disponivel: %d XP",custoPontos, saldoAcumuladoXP));	
    	}
		
		this.saldoAcumuladoXP -= custoPontos;
		System.out.printf("Pedido de R$ %.2f pago com %d XP. Saldo atual: %d XP. %n", valorCompra, custoPontos, saldoAcumuladoXP);
	}
}
