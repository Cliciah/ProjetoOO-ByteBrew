package br.edu.cafeteria.modelo;

public class Comida extends Produto { 
    private int tempoPreparo; 
    private boolean isVeganoSemGluten; 

    public Comida(String codigo, String nome, double precoBase, int quantidadeEstoque) {
        super(codigo, nome, precoBase, quantidadeEstoque);
    }

    public Comida(String codigo, String nome, double precoBase, int quantidadeEstoque, int tempoPreparo, boolean isVeganoSemGluten) {
        super(codigo, nome, precoBase, quantidadeEstoque); 
        this.tempoPreparo = tempoPreparo;
        this.isVeganoSemGluten = isVeganoSemGluten;
    }

    public void alterarTempoPreparo(int novoTempo) {
        this.tempoPreparo = novoTempo;
    }

    public void definirVeganoSemGluten(boolean ePreferencia) {
        this.isVeganoSemGluten = ePreferencia;
    }

    public int getTempoPreparo() { return tempoPreparo; }
    public boolean isVeganoSemGluten() { return isVeganoSemGluten; }
}