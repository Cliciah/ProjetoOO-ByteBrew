class Main {
    public static void main(String[] args) {
        System.out.println("Iniciando o sistema Byte & Brew...");
    }
}

// produtos
abstract class Produto {
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

//bebida
class Bebida extends Produto { 

    // atributos necessários para puxar os dados de Produto
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

//comida
class Comida extends Produto { 

    public Comida(int codigo, String nome, double precoBase, int quantidadeEstoque) {
        super(codigo, nome, precoBase, quantidadeEstoque);
    }

    public Comida(int codigo, String nome, double precoBase, int quantidadeEstoque, float tempoPreparo, boolean semGluten, boolean vegano) {
        super(codigo, nome, precoBase, quantidadeEstoque); 
        this.tempoPreparo = tempoPreparo;
        this.semGluten = semGluten;
        this.vegano = vegano;
    }

    public void alterarTempoPreparo(float novoTempo) {
        this.tempoPreparo = novoTempo;
    }

    public void definirVegano(boolean eVegano) {
        this.vegano = eVegano;
    }

    public void definirSemGluten(boolean eSemGluten) {
        this.semGluten = eSemGluten;
    }

    public float getTempoPreparo() { return tempoPreparo; }
    public boolean isSemGluten() { return semGluten; }
    public boolean isVegano() { return vegano; }

    @Override
    public String getTipo() {
        return "Comida";
    }

    private float tempoPreparo; 
    private boolean semGluten;
    private boolean vegano;
}