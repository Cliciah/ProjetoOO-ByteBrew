package br.edu.cafeteria.app;

import br.edu.cafeteria.modelo.Produto;
import br.edu.cafeteria.modelo.Bebida;
import br.edu.cafeteria.modelo.Comida;

public class CafeteriaApp {
    public static void main(String[] args) {
        System.out.println("Bem vindo a Byte & Brew");

        System.out.println("=== Iniciando Testes do Sistema Byte & Brew ===\n");

        // Teste de Bebida
        Bebida café = new Bebida(101, "Espresso Tradicional", 6.50, 50, 'M', 120);
        System.out.println("--- Teste de Bebida ---");
        System.out.println("Nome: " + café.getNome());
        System.out.println("Tipo: " + café.getTipo());
        System.out.println("Tamanho: " + café.getTamanho());
        System.out.println("Estoque Inicial: " + café.consultarEstoque());
        
        café.atualizarEstoque(10);
        System.out.println("Estoque após reposição: " + café.consultarEstoque());
        System.out.println();

        // Teste de Comida
        Comida muffin = new Comida(201, "Muffin de Chocolate", 8.00, 15, 2.5f, false, false);
        System.out.println("--- Teste de Comida ---");
        System.out.println("Nome: " + muffin.getNome());
        System.out.println("Tipo: " + muffin.getTipo());
        System.out.println("Tempo de Preparo: " + muffin.getTempoPreparo() + " min");
        System.out.println("É Vegano? " + (muffin.isVegano() ? "Sim" : "Não"));
        System.out.println();
    }
}