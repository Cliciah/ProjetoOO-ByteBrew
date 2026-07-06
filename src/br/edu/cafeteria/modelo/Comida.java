package br.edu.cafeteria.modelo;

public class Comida extends Produto { 
    private int tempoPreparo;
    private boolean isVegano;
    private boolean isSemGluten;

    public Comida(String codigo, String nome, double precoBase, int quantidadeEstoque) {
        super(codigo, nome, precoBase, quantidadeEstoque);
    }

    public Comida(String codigo, String nome, double precoBase, int quantidadeEstoque, int tempoPreparo, boolean isVegano, boolean isSemGluten) {
        super(codigo, nome, precoBase, quantidadeEstoque); 
        this.tempoPreparo = tempoPreparo;
        this.isVegano = isVegano;
        this.isSemGluten = isSemGluten;
    }

    public void alterarTempoPreparo(int novoTempo) {
        this.tempoPreparo = novoTempo;
    }

    public void definirVegano(boolean vegano) {
        this.isVegano = vegano;
    }

    public void definirSemGluten(boolean semGluten) {
        this.isSemGluten = semGluten;
    }

    public int getTempoPreparo() { 
        return tempoPreparo; 
    }
    
    public boolean isVegano() { 
        return isVegano; 
    }
    
    public boolean isSemGluten() { 
        return isSemGluten; 
    }
}