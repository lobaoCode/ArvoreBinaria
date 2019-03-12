/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.bigwolf.util;

import java.util.Comparator;

/**
 *
 * @author 1161151394
 */

/**
 *https://www.guj.com.br/t/comparando-dois-elementos-de-um-vetor-generico/93750/3
 * http://blog.caelum.com.br/ordenando-colecoes-com-comparable-e-comparator/
 * @param <T>
 * @param <K>
 */

public class No<T,K extends Comparable<K>> implements Comparable<K>{
    
    private T elemento;
    private K chave;
    private No<T,K>[] filhos;
    private No<T,K> pai;
    
    public No(){
        this.filhos = new No[2];
    }
    public No(K chave, T elemento){
        this();
        this.chave = chave;
        this.elemento = elemento;
    }

    /**
     * @return the elemento
     */
    public T getElemento() {
        return elemento;
    }

    /**
     * @param elemento the elemento to set
     */
    public void setElemento(T elemento) {
        this.elemento = elemento;
    }

   

    /**
     * @return the filhos
     */
    public No<T,K>[] getFilhos() {
        return filhos;
    }

    /**
     * @param filhos the filhos to set
     */
    public void setFilhos(No<T,K>[] filhos) {
        this.filhos = filhos;
    }

    /**
     * @return the pai
     */
    public No<T,K> getPai() {
        return pai;
    }

    /**
     * @param pai the pai to set
     */
    public void setPai(No<T,K> pai) {
        this.pai = pai;
    }

    /**
     * @return the chave
     */
    public K getChave() {
        return chave;
    }

    /**
     * @param chave the chave to set
     */
    public void setChave(K chave) {
        this.chave = chave;
    }

    @Override
    public int compareTo(K o) {
        return this.chave.compareTo(o);
    }


}