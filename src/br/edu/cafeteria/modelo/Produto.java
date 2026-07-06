package br.edu.cafeteria.modelo;

import br.edu.cafeteria.excecao.EstoqueInsuficienteException;

public abstract class Produto {
    private String codigo;
    private String nome;
    private double precoBase;
    private int quantidadeEstoque;

    public Produto(String codigo, String nome, double precoBase, int quantidadeEstoque) {
        this.codigo = codigo;
        this.nome = nome;
        this.precoBase = precoBase;
        this.quantidadeEstoque = quantidadeEstoque;
    }

    public void atualizarEstoque(int quantidade) throws EstoqueInsuficienteException {
        if (this.quantidadeEstoque + quantidade < 0) {
            throw new EstoqueInsuficienteException(
                "Estoque insuficiente para " + nome +
                ". Disponível: " + quantidadeEstoque
            );
        }
        this.quantidadeEstoque += quantidade;
    }

    public int consultarEstoque() {
        return this.quantidadeEstoque;
    }
    
    public String getCodigo() { return codigo; }
    public void setCodigo(String codigo) { this.codigo = codigo; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public double getPrecoBase() { return precoBase; }
    public void setPrecoBase(double precoBase) { this.precoBase = precoBase; }

    public int getQuantidadeEstoque() { return quantidadeEstoque; }
    public void setQuantidadeEstoque(int quantidadeEstoque) { this.quantidadeEstoque = quantidadeEstoque; }
}