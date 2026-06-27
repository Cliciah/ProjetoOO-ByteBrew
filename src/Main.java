public class Main {
    public static void main(String[] args) {
        System.out.println("Iniciando o sistema Byte & Brew...");
    }
}

// cardapio
public abstract class Produto {


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
}