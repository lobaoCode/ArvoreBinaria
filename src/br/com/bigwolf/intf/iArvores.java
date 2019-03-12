/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.bigwolf.intf;

import br.com.bigwolf.util.No;

/**
 *
 * @author 1161151394
 * @param <T>
 * @param <K>
 */
public interface iArvores<T,K extends Comparable<K>> {
    
    public void inserir(K chave, T elemento);
    public No<T,K> buscar(K chave);
    public void imprimir(K chave);
    public void imprimirTodosCrescente(No<T,K> noAtual);
    public void remover(K chave);
    
}
