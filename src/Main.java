public class Main {
    public static void main(String[] args) {
        System.out.println("Iniciando o sistema Byte & Brew...");
    }
}

// produtos
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

//atributo para a Interface de produtos
public abstract class Produto {
    public abstract String getTipo();
}

public class Bebida extends Produto {
    @Override
    public String getTipo() {
        return "Bebida";
    }
}

public class Comida extends Produto {
    @Override
    public String getTipo() {
        return "Comida";
    }
}