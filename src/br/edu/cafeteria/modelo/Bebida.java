package br.edu.cafeteria.modelo;

public class Bebida extends Produto { 
    private String tamanho;
    private int quantidadeCafeina;

    public Bebida(String codigo, String nome, double precoBase, int quantidadeEstoque) {
        super(codigo, nome, precoBase, quantidadeEstoque);
    }

    public Bebida(String codigo, String nome, double precoBase, int quantidadeEstoque, String tamanho, int quantidadeCafeina) {
        super(codigo, nome, precoBase, quantidadeEstoque); 
        this.tamanho = tamanho;
        this.quantidadeCafeina = quantidadeCafeina;
    }
    
    public void alterarTamanho(String novoTamanho) {
        this.tamanho = novoTamanho;
    }

    public void alterarCafeina(int novaQuantidade) {
        this.quantidadeCafeina = novaQuantidade;
    }

    public String getTamanho() { return tamanho; }
    public int getQuantidadeCafeina() { return quantidadeCafeina; }
}