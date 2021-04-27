package br.com.bigwolf.util;

import br.com.bigwolf.intf.iArvoreBusca;

public class ArvoreBinariaBusca<T extends Comparable<T>, K extends Comparable<K>> implements iArvoreBusca<T, K> {
	private No<T,K> raiz;
	private int tamanho;
	
	public ArvoreBinariaBusca() {
		this.tamanho = 0;
	}

	@Override
	public void inserir(K chave, T elemento) {
		this.tamanho++;
		
		if (raiz == null) { 
			raiz = new No<T,K> (chave, elemento);
		} else {
			No<T,K> atual = raiz;

			while (true) {
				if (chave.compareTo(atual.getChave()) < 0) {
					if (atual.getFilhos()[0] == null) {
						atual.getFilhos()[0] = new No<T,K> (chave, elemento);
						break;
					} else {
						atual = atual.getFilhos()[0];
					}
				} else if (chave.compareTo(atual.getChave()) > 0) {
					if (atual.getFilhos()[1] == null) {
						atual.getFilhos()[1] = new No<T,K> (chave, elemento);
						break;
					} else {
						atual = atual.getFilhos()[1];
					}
				} else {
					atual.setElemento(elemento);;
					break;
				}
			}
			
		}
	}

	@Override
	public void imprimirEmOrdemSimetrica() {
		imprimirEmOrdemSimetrica(raiz);
		System.out.println();
	}

	@Override
	public void imprimirEmOrdemSimetrica(No<T, K> raiz) {
		if (raiz.getFilhos()[0] != null) {
			imprimirEmOrdemSimetrica(raiz.getFilhos()[0]);
		} 
		System.out.print(raiz.getElemento() + " ");

		if (raiz.getFilhos()[1] != null) {
			imprimirEmOrdemSimetrica(raiz.getFilhos()[1]);
		}
	}

	@Override
	public void imprimirMaiores() {
		imprimirMaiores(raiz.getFilhos()[0]);
		System.out.println();
	}
	
	private void imprimirMaiores(No<T,K> raiz) {
		if (raiz.getFilhos()[0] != null && raiz.getFilhos()[0].getElemento().compareTo(raiz.getFilhos()[0].getElemento()) > 1) {
			imprimirMaiores(raiz.getFilhos()[0]);
		} else if (raiz.getFilhos()[1] != null) {
			imprimirMaiores(raiz.getFilhos()[1]);
		}
		System.out.print(raiz.getElemento() + " ");
	}

	@Override
	public boolean isEstritaBinaria() {
		return isEstritaBinaria(raiz);
	}
	
	private boolean isEstritaBinaria(No<T,K> raiz) {
		if (raiz.getFilhos()[0] != null && raiz.getFilhos()[1] != null) {
			isEstritaBinaria(raiz.getFilhos()[0]);
			isEstritaBinaria(raiz.getFilhos()[1]);
			return true;
		} 
		return false;
	}

	@Override
	public void imprimirMenores() {
		imprimirMenores(raiz);
		System.out.println();
	}
	
	private void imprimirMenores(No<T,K> raiz) {
		imprimirMenores(raiz.getFilhos()[0]);
		System.out.print(raiz.getElemento() + " ");
	}
}
