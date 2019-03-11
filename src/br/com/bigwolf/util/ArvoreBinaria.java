/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.bigwolf.util;

import br.com.bigwolf.intf.iArvores;

/**
 *
 * @author 1161151394
 * @param <T>
 * @param <K>
 */
public class ArvoreBinaria<T, K extends Comparable<K>> implements iArvores<T, K> {

    private No<T, K> raiz;
    private No<T, K>[] filhos;

    public ArvoreBinaria() {
        filhos = new No[2];
        raiz = null;
    }

    public ArvoreBinaria(No<T, K> raiz) {
        this();
        this.raiz = raiz;
    }

    @Override
    public No<T, K> buscar(K chave) {
        No<T, K> noAtual = this.raiz;

        while (noAtual.getChave().compareTo(chave) != 0) {
            if (noAtual.getChave().compareTo(chave) < 0) {
                noAtual = noAtual.getFilhos()[0];
            } else {
                noAtual = noAtual.getFilhos()[1];
            }
            if (noAtual == null) {
                return null;
            }
        }
        System.out.println(noAtual.getElemento());
        return noAtual;
    }

    @Override
    public void inserir(K chave, T elemento) {
        No<T, K> noNovo = new No<>(chave, elemento);

        if (raiz == null) {
            raiz = noNovo;
        } else {
            No<T, K> noAtual = raiz;
            No<T, K> noPrimo;

            while (true) {

                noPrimo = noAtual;
                if (chave.compareTo(noAtual.getChave()) < 0) {
                    noAtual = noAtual.getFilhos()[0];
                    if (noAtual == null) {
                        noPrimo.getFilhos()[0] = noNovo;
                        return;
                    }
                } else {
                    noAtual = noAtual.getFilhos()[1];
                    if (noAtual == null) {
                        noPrimo.getFilhos()[1] = noNovo;
                        return;
                    }
                }
            }
        }
    }

    @Override
    public void imprimir(K chave) {
        No<T, K> noAtual = buscar(chave);
        
        if (noAtual != null) {
            if(noAtual.getFilhos()[0] != null){
                imprimir(noAtual.getFilhos()[0].getChave());
            }
            if(noAtual.getFilhos()[0] != null){
                imprimir(noAtual.getFilhos()[1].getChave());
            }
            System.out.println(noAtual.getElemento());
        }       
    }
}
