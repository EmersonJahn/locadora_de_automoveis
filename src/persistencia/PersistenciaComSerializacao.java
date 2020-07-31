package persistencia;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import modelo.Automovel;
import modelo.Cliente;
import modelo.Lista;
import modelo.ListaComArray;

/**
 * Classe que implementa uma persistência com serialização de objetos dos tipos Cliente e Automovel, implements Persistencia.
 * Uma PersistenciaComSerializacao, grava e recupera os objetos.
 * 
 * @author Emerson
 * @version 10/07/2020
 */

public class PersistenciaComSerializacao implements Persistencia {
		
    /**
     * Método para gravar uma lista de objetos do tipo Cliente em um arquivo.
     * 
     * @param listaCliente a lista de objetos do tipo Cliente que será inserida no arquivo.
     */
	@Override
	public void gravarClientes(Lista<Cliente> listaCliente) {
		try(FileOutputStream out = new FileOutputStream("clientes.obj");)
		{
			ObjectOutputStream escreveObjeto = new ObjectOutputStream(out);
			escreveObjeto.writeObject(listaCliente);
			escreveObjeto.flush();
		} 
		catch (FileNotFoundException e) {
			 System.out.println("Arquivo não encontrado");
		} 
		catch (IOException e1) {
			 System.out.println("Erro de IO");
		}
	}

    /**
     * Método para recuperar uma lista de objetos do tipo Cliente gravada em um arquivo.
     */
	@Override
	public Lista<Cliente> recuperarClientes() {
		
		Lista<Cliente> listaCliente = new ListaComArray<Cliente>();
		
		try(FileInputStream out = new FileInputStream("clientes.obj");)
		{
			ObjectInputStream leObjeto = new ObjectInputStream(out);
			listaCliente = (Lista<Cliente>) leObjeto.readObject();
		} 
		catch (FileNotFoundException e) {
			 System.out.println("Arquivo não encontrado");
		} 
		catch (IOException e1) {
			 System.out.println("Erro de IO");
		} 
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		}	
		return listaCliente;
	}
	
	
    /**
     * Método para gravar uma lista de objetos do tipo Automovel em um arquivo.
     * 
     * @param listaAutomovel a lista de objetos do tipo Automovel que será inserida no arquivo.
     */
	@Override
	public void gravarAutomoveis(Lista<Automovel> listaAutomovel) {
		try(FileOutputStream out = new FileOutputStream("automoveis.obj");)
		{
			ObjectOutputStream escreveObjeto = new ObjectOutputStream(out);
			escreveObjeto.writeObject(listaAutomovel);
			escreveObjeto.flush();
		} 
		catch (FileNotFoundException e) {
			 System.out.println("Arquivo não encontrado");
		} 
		catch (IOException e1) {
			 System.out.println("Erro de IO");
		}
	}

    /**
     * Método para recuperar uma lista de objetos do tipo Automovel gravada em um arquivo.
     */
	@Override
	public Lista<Automovel> recuperarAutomoveis() {
		
		Lista<Automovel> listaAutomovel = new ListaComArray<Automovel>();
		
		try(FileInputStream out = new FileInputStream("automoveis.obj");)
		{
			ObjectInputStream leObjeto = new ObjectInputStream(out);
			listaAutomovel = (Lista<Automovel>) leObjeto.readObject();
		} 
		catch (FileNotFoundException e) {
			 System.out.println("Arquivo não encontrado");
		} 
		catch (IOException e1) {
			 System.out.println("Erro de IO");
		} 
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		}	
		return listaAutomovel;
	}

}
