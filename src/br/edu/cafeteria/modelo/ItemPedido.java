package br.edu.cafeteria.modelo;

public class ItemPedido {

	private Produto produto;
	private int quantidade;

	public ItemPedido(Produto produto, int quantidade) {
		this.produto = produto;
		this.quantidade = quantidade;

	}

	public ItemPedido(Produto produto) {
		this.produto = produto;

	}

	public Produto getProduto() {
		return produto;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	} 
	
}