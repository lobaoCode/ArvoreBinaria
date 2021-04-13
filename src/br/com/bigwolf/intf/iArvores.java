/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.bigwolf.intf;

import br.com.bigwolf.util.ArvoreBinaria;
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
    public boolean remover(K chave);
    public No<T,K> herdeiro(No<T,K> no);
    public int altura(No<T,K> no);
    public int quantidade(No<T,K> no);
    public int grau(No<T,K> no);
    public int profundidade(No<T,K> no);
    
}
