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
public class ArvoreBinaria<T extends Comparable<T>, K extends Comparable<K>> implements iArvores<T, K> {

    public No<T, K> raiz;

    public ArvoreBinaria() {
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
            System.out.print(noAtual.getElemento() + " - " + noAtual.getChave() + " !!! nivel: " + noAtual.nivel + " altura: " + altura(noAtual) + " \n");
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
                
            } else {
                noRemover.getPai().getFilhos()[1] = noRemover.getFilhos()[0];
            }
        } else if (noRemover.getFilhos()[0] == null) {
            if (noRemover == raiz) {
                raiz = noRemover.getFilhos()[1];
            } else if (noRemover.isEsquerda()) {
                noRemover.getPai().getFilhos()[0] = noRemover.getFilhos()[1];
            } else {
                noRemover.getPai().getFilhos()[1] = noRemover.getFilhos()[1];
            }
        } else {
            No<T, K> herdeiro = herdeiro(noRemover);

            if (noRemover == raiz) {
                raiz = herdeiro;
            } else if (noRemover.isEsquerda()) {
                noRemover.getPai().getFilhos()[0] = herdeiro;
            } else {
                noRemover.getPai().getFilhos()[1] = herdeiro;
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

    @Override
    public int altura(No<T,K> no) {
        if (no == null) {
            return -1;
        } else {
            int alturaEsquerda = altura(no.getFilhos()[0]);
            int alturaDireita = altura(no.getFilhos()[1]);
            if (alturaEsquerda < alturaDireita) return alturaDireita + 1;
            else return alturaEsquerda + 1;
        }
    }

    @Override
    public int quantidade(No<T, K> no) {
        return (no == null) ? 0 : 1 + quantidade(no.getFilhos()[0]) + quantidade(no.getFilhos()[1]);
    }

    @Override
    public int grau(No<T, K> no) {
        int grau = 0;
        if (no != null) {
            if(no.getFilhos()[0] != null)
                grau++;
            
            if(no.getFilhos()[1] != null)
                grau++;
        }
        return grau;
    }

    @Override
    public int profundidade(No<T, K> no) {
        int profundidade = 0;
        if (no.getPai() != null) {
            return 1 + profundidade(no.getPai());
        }
        return profundidade;
    }

	@Override
	public void imprimirPosOrdem() {
		imprimirPosOrdem(raiz);
		System.out.println();
	}
	
	private void imprimirPosOrdem (No raiz) {
		if (raiz.getFilhos()[0] != null) {
			imprimirPosOrdem(raiz.getFilhos()[0]);
		} 
		if (raiz.getFilhos()[1] != null) {
			imprimirPosOrdem(raiz.getFilhos()[1]);
		}
		System.out.print(raiz.getElemento() + " ");
	}

	@Override
	public void imprimirPreOrdem() {
		imprimirPreOrdem(raiz);
		System.out.println();
	}
	
	private void imprimirPreOrdem (No raiz) {
		System.out.print(raiz.getElemento() + " ");
		if (raiz.getFilhos()[0] != null) {
			imprimirPreOrdem(raiz.getFilhos()[0]);
		} 
		if (raiz.getFilhos()[1] != null) {
			imprimirPreOrdem(raiz.getFilhos()[1]);
		}
	}

	@Override
	public void imprimirInOrdem() {
		imprimirInOrdem(raiz);
		System.out.println();
	}
	
	private void imprimirInOrdem(No raiz) {
		if (raiz.getFilhos()[0] != null) {
			imprimirInOrdem(raiz.getFilhos()[0]);
		}
		System.out.print(raiz.getElemento() + " ");
		if (raiz.getFilhos()[1] != null) {
			imprimirInOrdem(raiz.getFilhos()[1]);
		}
	}

	@Override
	public void imprimirMaiores() {
		imprimirMaiores(raiz);
		System.out.println();
	}
	
	private void imprimirMaiores(No<T,K> raiz) {
		No<T,K> maiorFilho = null;
		if (raiz.getFilhos()[0] != null && raiz.getFilhos()[0].getElemento().compareTo(raiz.getFilhos()[0].getElemento()) > 1) {
			maiorFilho = raiz.getFilhos()[0];
			imprimirMaiores(raiz.getFilhos()[0]);
		} else if (raiz.getFilhos()[1] != null) {
			maiorFilho = raiz.getFilhos()[1];
			imprimirMaiores(raiz.getFilhos()[1]);
		}
		if (maiorFilho != null) 
			System.out.println("No pai: " + raiz.getElemento() + " No maior Filho: " + maiorFilho.getElemento());
	}
}
