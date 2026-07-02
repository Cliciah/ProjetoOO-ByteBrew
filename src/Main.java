public class Main {
    public static void main(String[] args) {
        System.out.println("Iniciando o sistema Byte & Brew...");
    }
}


package br.edu.cafeteria.modelo;

public abstract class Produto {
    protected int codigo;
    protected double precoBase;
    protected String nome;
    protected int quantidadeEstoque;
} 

    public Produto(int codigo, String nome, double precoBase, int quantidadeEstoque) {
        this.codigo = codigo;
        this.nome = nome;
        this.precoBase = precoBase;
        this.quantidadeEstoque = quantidadeEstoque;
    }
    
// produtos
abstract class Produto {

    private int codigo;
    private double precoBase;
    private String nome;
    private int quantidadeEstoque;

    public Produto(int codigo, String nome, double precoBase, int quantidadeEstoque) {
        this.codigo = codigo;
        this.nome = nome;
        this.precoBase = precoBase;
        this.quantidadeEstoque = quantidadeEstoque;
    }

    public void atualizarEstoque() {
    }

    public int consultarEstoque() {
        return this.quantidadeEstoque;
    }
    public abstract String getTipo(); 
}

class Bebida extends Produto { 
    
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