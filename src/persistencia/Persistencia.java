package persistencia;

import modelo.Automovel;
import modelo.Cliente;
import modelo.Lista;

/**
 * Interface que implementa uma Persistencia de objetos dos tipos Cliente e Automovel.
 * 
 * @author Emerson
 * @version 03/07/2020
 */

public interface Persistencia {

	public void gravarClientes(Lista<Cliente> listaCliente);
	public Lista<Cliente> recuperarClientes();
	
	public void gravarAutomoveis(Lista<Automovel> listaAutomovel);
	public Lista<Automovel> recuperarAutomoveis();
	
}

