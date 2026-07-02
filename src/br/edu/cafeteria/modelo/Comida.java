package br.edu.cafeteria.modelo;

public class Comida extends Produto { 

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
