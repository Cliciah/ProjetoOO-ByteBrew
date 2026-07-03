package br.edu.cafeteria.modelo;

//bebida
public class Bebida extends Produto { 

    public Bebida(int codigo, String nome, double precoBase, int quantidadeEstoque) {
        super(codigo, nome, precoBase, quantidadeEstoque);
    }

    public Bebida(int codigo, String nome, double precoBase, int quantidadeEstoque, char tamanho, int quantidadeCafeina) {
        super(codigo, nome, precoBase, quantidadeEstoque); 
        this.tamanho = tamanho;
        this.quantidadeCafeina = quantidadeCafeina;
    }
    
    public void alterarTamanho(char novoTamanho) {
        this.tamanho = novoTamanho;
    }

    public void alterarCafeina(int novaQuantidade) {
        this.quantidadeCafeina = novaQuantidade;
    }

    public char getTamanho() { return tamanho; }
    public int getQuantidadeCafeina() { return quantidadeCafeina; }

    @Override
    public String getTipo() {
        return "Bebida";
    }

    private char tamanho; 
    private int quantidadeCafeina; 
}