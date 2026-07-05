package br.edu.cafeteria.servico;

public class PromocionalGeek implements Promocional {
    
    @Override
    public double aplicarDesconto(double valor){
        return valor - (valor * 0.10);
    }

}
