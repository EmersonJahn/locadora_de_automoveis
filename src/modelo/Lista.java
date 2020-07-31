package modelo;

import java.io.Serializable;

/**
 * Interface que implementa uma Lista de objetos do tipo Object.
 * 
 * @author Emerson
 * @version 03/07/2020
 */

public interface Lista<T> extends Serializable {
	
	public void inserir(T objeto);
	public T get(int indice);
	public T remover(int indice);
	public int getTamanho();

}
