package br.edu.cafeteria.modelo;
 
import br.edu.cafeteria.excecao.EstoqueInsuficienteException;
 
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
        if (this.quantidadeEstoque + quantidade < 0) {
            throw new EstoqueInsuficienteException(
                String.format("Estoque insuficiente para %s: disponivel %d, solicitado %d.",
                        nome, quantidadeEstoque, -quantidade));
        }
        this.quantidadeEstoque += quantidade;
    }
 
    public int consultarEstoque() {
        return this.quantidadeEstoque;
    }
    
    public int getCodigo() { return codigo; }
    public double getPrecoBase() { return precoBase; }
    public String getNome() { return nome; }
    
    public abstract String getTipo(); 
}
 
