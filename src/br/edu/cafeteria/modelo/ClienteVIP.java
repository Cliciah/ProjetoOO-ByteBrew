package br.edu.cafeteria.modelo;
import br.edu.cafeteria.excecao.PontosInsuficientesException;

public class ClienteVIP extends ClienteCadastrado {
	
    public ClienteVIP(String nome, String cpf, int saldoXP){
        super(nome,cpf,saldoXP);
    }

	@Override
	public int calcularXP(double valorCompra) {
		return (int)(valorCompra * 2);   
	}
	
    public int calcularPontosNecessarios(double valor) {
        return (int) Math.ceil(valor * TAXA_CONVERSAO_XP);
    }

    public void pagarXP(Pedido pedido) {
        int pontosNecessarios = calcularPontosNecessarios(pedido.calcularTotal());

        if (getSaldoXP() < pontosNecessarios) {
            throw new PontosInsuficientesException("Saldo XP insuficiente.");
        }

        debitarXP(pontosNecessarios);
    }
}
