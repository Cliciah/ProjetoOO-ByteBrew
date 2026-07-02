package br.edu.cafeteria.modelo;

class Main {
    public static void main(String[] args) {
        System.out.println("Iniciando o sistema Byte & Brew...");
    }
}

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

class Bebida extends Produto { 
    public Bebida(int codigo, String nome, double precoBase, int quantidadeEstoque) {
        super(codigo, nome, precoBase, quantidadeEstoque);
    }

    @Override
    public String getTipo() {
        return "Bebida";
    }
}

//bebida

    
    // atributos necessários para puxar os dados de Produto
    public Bebida(int codigo, String nome, double precoBase, int quantidadeEstoque) {
        super(codigo, nome, precoBase, quantidadeEstoque);
    }

    @Override
    public String getTipo() {
        return "Bebida";
    }
}

class Comida extends Produto { 
    public Comida(int codigo, String nome, double precoBase, int quantidadeEstoque) {
        super(codigo, nome, precoBase, quantidadeEstoque);
    }

    @Override
    public String getTipo() {
        return "Comida";
    }
}