package br.edu.cafeteria.modelo;

import br.edu.cafeteria.excecao.PontosInsuficienteException;

public abstract class ClienteCadastrado extends Cliente{
	
	private String nome;
	private String cpf;
	protected int saldoAcumuladoXP;
	
	private static final int PONTOS_XP_PARA_VIP = 300;
	protected static final int TAXA_CONVERSAO_XP_PARA_REAL = 10;
	


	public ClienteCadastrado(String nome, String cpf){
		this.nome = nome;
		this.cpf = cpf;
		this.saldoAcumuladoXP = 0;
	}
	
public abstract int xp_a_Receber(double valorCompra); // sobrescreve o metodo: abstrata pra toddas as filhas
public abstract void pagarXP(double valorCompra);
	
	public int adicionarXP(double valorCompra) {
		int pontos = xp_a_Receber(valorCompra);
		this.saldoAcumuladoXP += pontos;
		return pontos;
	}
	

	public String getNome(){
		return nome;
	}
	
	public String getCpf(){
		return cpf;
	}

	public int getSaldoAcumuladoXP(){
		return saldoAcumuladoXP;
	}
	
	
	// conversao de vip <-> standard
	
	public static ClienteVIP promover(ClienteStandard c) {
		if(c.getSaldoAcumuladoXP() < PONTOS_XP_PARA_VIP) {
			throw new PontosInsuficienteException(String.format("XP insuficiente para promover a VIP: Precisa de %d e possui %d.",PONTOS_XP_PARA_VIP,c.getSaldoAcumuladoXP()));
		}
		
		ClienteVIP vip = new ClienteVIP(c.getNome(), c.getCpf());
		vip.saldoAcumuladoXP = c.getSaldoAcumuladoXP();
		return vip;
	}
	
	public static ClienteStandard rebaixar(ClienteVIP c) {
		ClienteStandard standard = new ClienteStandard(c.getNome(), c.getCpf());
		standard.saldoAcumuladoXP =  c.saldoAcumuladoXP;
		return standard;
	}
	


}
