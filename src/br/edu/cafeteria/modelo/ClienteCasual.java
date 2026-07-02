package br.edu.cafeteria.modelo;

public class ClienteCasual extends Cliente{
	
	@Override
	public void pagar(double valorCompra) {
		
		System.out.printf("Pagamento de R$ %.2f realizado como cliente casual.%n", valorCompra);
		
	}
}
