package br.com.bigwolf.util;

public class No<T,K extends Comparable<K>> implements Comparable<K>{
    
    private T elemento;
    private K chave;
    private No<T,K>[] filhos;
    private No<T,K> pai;
    private boolean esquerda;
    public int nivel = 0;
    
    public No(){
        this.filhos = new No[2];
    }
    public No(K chave, T elemento){
        this();
        this.chave = chave;
        this.elemento = elemento;
        this.nivel = 1;
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
        this.nivel = pai.nivel + 1;
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

    /**
     * @return the esquerda
     */
    public boolean isEsquerda() {
        return esquerda;
    }

    /**
     * @param esquerda the esquerda to set
     */
    public void setEsquerda(boolean esquerda) {
        this.esquerda = esquerda;
    }
}