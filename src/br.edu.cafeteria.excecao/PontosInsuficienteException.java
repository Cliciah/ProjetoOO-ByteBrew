package br.edu.cafeteria.excecao;

public class PontosInsuficienteException extends RuntimeException {
    public PontosInsuficienteException(String mensagem) {
        super(mensagem);
    }
}