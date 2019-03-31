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
                noAtual.setEsquerda(true);
            } else {
                noAtual = noAtual.getFilhos()[1];
                noAtual.setEsquerda(false);
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
    public boolean remover(K chave) {
        if (raiz == null) {
            return false;
        }
        No<T, K> noRemover = buscar(chave);
        if (noRemover == null) {
            return false;
        }

        if (noRemover.getFilhos()[0] == null && noRemover.getFilhos()[1] == null) {
            if (noRemover == raiz) {
                raiz = null; // se raiz
            } else if (noRemover.isEsquerda()) {
                noRemover.getPai().getFilhos()[0] = null;
            } else {
                noRemover.getPai().getFilhos()[1] = null;
            }
        } else if (noRemover.getFilhos()[1] == null) {
            if (noRemover == raiz) {
                raiz = noRemover.getFilhos()[0];
            } else if (noRemover.isEsquerda()) {
                noRemover.getPai().getFilhos()[0] = noRemover.getFilhos()[0];
                //noRemover.getPai().getFilhos()[0].setPai(noRemover.getPai());
                
            } else {
                noRemover.getPai().getFilhos()[1] = noRemover.getFilhos()[0];
                //noRemover.getPai().getFilhos()[1].setPai(noRemover.getPai());
            }
        } else if (noRemover.getFilhos()[0] == null) {
            if (noRemover == raiz) {
                raiz = noRemover.getFilhos()[1];
            } else if (noRemover.isEsquerda()) {
                noRemover.getPai().getFilhos()[0] = noRemover.getFilhos()[1];
                //noRemover.getPai().getFilhos()[0].setPai(noRemover.getPai());
            } else {
                noRemover.getPai().getFilhos()[1] = noRemover.getFilhos()[1];
                //noRemover.getPai().getFilhos()[1].setPai(noRemover.getPai());
            }
        } else {
            No<T, K> herdeiro = herdeiro(noRemover);

            if (noRemover == raiz) {
                raiz = herdeiro;
            } else if (noRemover.isEsquerda()) {
                noRemover.getPai().getFilhos()[0] = herdeiro;
                //noRemover.getPai().getFilhos()[0].setPai(herdeiro.getPai());
            } else {
                noRemover.getPai().getFilhos()[1] = herdeiro;
                //noRemover.getPai().getFilhos()[1].setPai(herdeiro.getPai());
            }
            herdeiro.getFilhos()[0] = noRemover.getFilhos()[0];
        }

        return true;
    }

    @Override
    public No<T, K> herdeiro(No<T, K> no) {
        No<T, K> paiHerdeiro = no;
        No<T, K> herdeiro = no;
        No<T, K> noAtual = no.getFilhos()[1];

        while (noAtual != null) {
            paiHerdeiro = herdeiro;
            herdeiro = noAtual;
            noAtual = noAtual.getFilhos()[0];
        }
        if (herdeiro != no.getFilhos()[1]) {
            paiHerdeiro.getFilhos()[0] = herdeiro.getFilhos()[1];
            herdeiro.getFilhos()[1] = no.getFilhos()[1];
        }
        return herdeiro;
    }

}
