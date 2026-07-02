package br.edu.cafeteria.modelo;

public class Atendente {
	private String nome;
	private String cpf;
	
	
	public Atendente(String nome, String cpf) {
		this.nome = nome;
		this.cpf = cpf;
	}
	
	public String getNome() {
		return nome;
	}
	
	public String getCpf() {
		return cpf;
	}

}
