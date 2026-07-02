package br.edu.cafeteria.modelo;

// produtos
public abstract class Produto {
    protected int codigo;
    protected double precoBase;
    protected String nome;
    protected int quantidadeEstoque;

    public Produto(int codigo, String nome, double precoBase, int quantidadeEstoque) {
        this.codigo = codigo;
        this.nome = nome;
        this.precoBase = precoBase;
        this.quantidadeEstoque = quantidadeEstoque;
    }

    public void atualizarEstoque(int quantidade) {
        this.quantidadeEstoque += quantidade;
    }

    public void atualizarEstoque() {
    }

    public int consultarEstoque() {
        return this.quantidadeEstoque;
    }
    
    public int getCodigo() { return codigo; }
    public double getPrecoBase() { return precoBase; }
    public String getNome() { return nome; }
    
    public abstract String getTipo(); 
}