package br.edu.cafeteria.servico;

import br.edu.cafeteria.modelo.ClienteCadastrado;
import java.util.HashMap;
import java.util.Map;


public class ClienteService {
	
	private Map <String, ClienteCadastrado> clientes = new HashMap<>();
	
	public void criar (ClienteCadastrado cliente) {
		if(cpfCadastrado(cliente.getCpf())) {
			throw new IllegalArgumentException("CPF ja cadastrado: " + cliente.getCpf());
		}
		
		clientes.put(cliente.getCpf(), cliente);
	}
	
	public ClienteCadastrado buscarCpf(String cpf) {
		return clientes.get(cpf);
	}
	
	public void atualizar(ClienteCadastrado cliente) {
		clientes.put(cliente.getCpf(), cliente);
	}
	
	public void remover(String cpf) {
		clientes.remove(cpf);
	}
	
	public boolean cpfCadastrado(String cpf) {
		return clientes.containsKey(cpf);
	}

}
