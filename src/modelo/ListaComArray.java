package modelo;

/**
 * Classe que implementa uma colecao de objetos do tipo Object como uma lista com array, implements Lista.
 * Uma ListaComArray, insere, gerencia os objetos e mantem-os no array.
 * 
 * @author Emerson
 * @version 06/07/2020
 */

public class ListaComArray<T> implements Lista<T> {
	
    private T[] osObjetos = (T[]) new Object[100];   
    private int tamanho = 0;
    private int capacidade = 100;
    
    public ListaComArray() {  	
    }
	
    /**
     * Construtor para instanciar uma ListaComArray com uma capacidade específica.
     * 
     * @param capacidade Um int que representa a capacidade da lista. 
     * 
     */
	public ListaComArray(int capacidade) {  	
    	this.capacidade = capacidade;
    	this.osObjetos = (T[]) new Object[capacidade];
    }
	
    /**
     * Método para inserir um Object na lista
     * 
     * @param objeto Um Object que será inserido na lista.
     */
	public void inserir(T objeto) {
		this.osObjetos[tamanho] = objeto;
		this.tamanho++;	
    }
	
    /**
     * Método para remover um Object na lista
     * 
     * @param indice Um int que define a posição do objeto que será removido da lista.
     */
    public T remover(int indice) {
    	for(int j = indice; j <= this.tamanho-1 ; j++) {
    		osObjetos[j] = osObjetos[j+1];
    		this.tamanho--;
    	}
		return null;
    }

	public int getTamanho() {
		return this.tamanho;
	}

    public T get(int indice) {
    	
		return osObjetos[indice];
    }
    
    

}
