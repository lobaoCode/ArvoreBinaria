/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.bigwolf.app;

import br.com.bigwolf.util.ArvoreBinaria;

/**
 *
 * @author 1161151394
 */
public class App {

    public static void main(String[] args) {

        ArvoreBinaria<String, Integer> arvore = new ArvoreBinaria<String, Integer>();

        arvore.inserir(50, "Cidade A");
        arvore.inserir(110, "Cidade B");
        arvore.inserir(15, "Cidade C");
        arvore.inserir(42, "Cidade D");
        arvore.inserir(130, "Cidade E");
        arvore.inserir(45, "Cidade F");
        arvore.inserir(56, "Cidade G");
        arvore.inserir(128, "Cidade H");
        arvore.inserir(31, "Cidade I");
        arvore.inserir(44, "Cidade J");
        arvore.inserir(2, "Cidade K");
        arvore.inserir(73, "Cidade L");
        arvore.remover(15);

        arvore.imprimirMaiores();
        
        arvore.imprimirTodosCrescente(arvore.buscar(50));
        
        System.out.println(arvore.altura(arvore.raiz));
        System.out.println(arvore.quantidade(arvore.raiz));


        System.out.println(arvore.profundidade(arvore.buscar(110)));
    }

}
