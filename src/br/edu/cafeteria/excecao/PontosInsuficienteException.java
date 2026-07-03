package br.edu.cafeteria.excecao;

public class PontosInsuficienteException extends RuntimeException {
    
    private static final long serialVersionUID = 1L;
    public PontosInsuficienteException(String mensagem) {
        super(mensagem);
    }
}