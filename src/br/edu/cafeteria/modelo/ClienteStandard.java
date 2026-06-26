package br.edu.cafeteria.modelo;

public class ClienteStandard extends ClienteCadastrado {
	
    public ClienteStandard(String nome, String cpf, int saldoXP){
        super(nome,cpf,saldoXP);
    }

    @Override
	public int calcularXP(double valorCompra) {
		return (int)(valorCompra * 1);
	}
}
