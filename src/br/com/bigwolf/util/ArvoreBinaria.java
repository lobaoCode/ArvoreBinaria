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

        while (!noAtual.getChave().equals(chave)) {
            if (chave.compareTo(noAtual.getChave()) < 0) {
                noAtual = noAtual.getFilhos()[0];
            } else {
                noAtual = noAtual.getFilhos()[1];
            }
            if (noAtual == null) {
                return null;
            }
        }
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
                        noNovo.setPai(noPrimo);
                        noPrimo.getFilhos()[0] = noNovo;
                        return;
                    }
                } else {
                    noAtual = noAtual.getFilhos()[1];
                    if (noAtual == null) {
                        noNovo.setPai(noPrimo);
                        noPrimo.getFilhos()[1] = noNovo;
                        return;
                    }
                }
            }
        }
    }

    @Override
    public void imprimir(K chave) {
        No<T, K> noAtual = this.raiz;

        while (!noAtual.getChave().equals(chave)) {
            if (chave.compareTo(noAtual.getChave()) < 0) {
                noAtual = noAtual.getFilhos()[0];
            } else {
                noAtual = noAtual.getFilhos()[1];
            }
            if (noAtual == null) {
                System.out.println("Elemento não encontrado!");
            }
        }
        System.out.println(noAtual.getElemento());
    }

    @Override
    public void imprimirTodosCrescente(No<T, K> noAtual) {
        if (noAtual != null) {
            imprimirTodosCrescente(noAtual.getFilhos()[0]);
            System.out.print(noAtual.getElemento() + " - " + noAtual.getChave() + "\n");
            imprimirTodosCrescente(noAtual.getFilhos()[1]);
        }
    }

    @Override
    public void remover(K chave) {
        No<T, K> noRemove = buscar(chave);

        if (noRemove.getFilhos()[0] == null && noRemove.getFilhos()[1] == null) {
            if (noRemove.getPai().getFilhos()[0] != null) {
                if (noRemove.getPai().getFilhos()[0].equals(noRemove)) {
                    noRemove.getPai().getFilhos()[0] = null;
                } 
            }else{
                noRemove.getPai().getFilhos()[1] = null;
            }
        } else if (noRemove.getPai().getFilhos()[0].equals(noRemove)) {
            if (noRemove.getFilhos()[0] != null) {
                noRemove.getPai().getFilhos()[0] = noRemove.getFilhos()[0];
            } else {
                noRemove.getPai().getFilhos()[0] = noRemove.getFilhos()[1];
            }
        } else if (noRemove.getPai().getFilhos()[1].equals(noRemove)) {
            if (noRemove.getFilhos()[1] != null) {
                noRemove.getPai().getFilhos()[1] = noRemove.getFilhos()[1];
            } else {
                noRemove.getPai().getFilhos()[1] = noRemove.getFilhos()[0];
            }
        }
    }
}
