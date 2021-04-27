package br.com.bigwolf.intf;

import br.com.bigwolf.util.No;

public interface iArvoreBusca <T,K extends Comparable<K>>{
	public void inserir (K chave, T elemento);
	public void imprimirEmOrdemSimetrica();
	public void imprimirEmOrdemSimetrica(No<T,K> raiz);
	public void imprimirMaiores();
	public void imprimirMenores();
	public boolean isEstritaBinaria();
}
