package br.edu.cafeteria.modelo;

public abstract class ClienteCadastrado extends Cliente{
	
	protected static final int TAXA_CONVERSAO_XP = 10;
	protected String nome;
	protected String cpf;
	protected int saldoXP;


	public ClienteCadastrado(String nome, String cpf, int saldoXP){
		this.nome = nome;
		this.cpf = cpf;
		this.saldoXP = saldoXP;
	}
	
	public void creditarXP(int pontos){
		this.saldoXP = this.saldoXP + pontos;
	}
	public void debitarXP(int pontos){
		this.saldoXP = this.saldoXP - pontos;
	}

	public String getNome(){
		return nome;
	}
	
	public String getCpf(){
		return cpf;
	}

	public int getSaldoXP(){
		return saldoXP;
	}

}
