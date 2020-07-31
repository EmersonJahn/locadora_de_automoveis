package modelo;

import java.io.Serializable;

import excecoes.ExcecaoDeCPFInvalido;

/**
 * Classe para representar os clientes da aplicação, implements Serializable.
 * 
 * @author Emerson
 * @version 03/07/2020
 */

public class Cliente implements Serializable {
	
	private String nome, cpf;
	
    /**
     * Construtor que inicializa o nome e o cpf do Cliente.
     * 
     * @param nome Um String que representa o nome do Cliente.
     * @param cpf Um String que representa o cpf do Cliente.
     * 
     */
	public Cliente(String nome, String cpf) {
		this.nome = nome;
		this.cpf = cpf;
	}
	
    /**
     * Construtor que inicializa o nome do Cliente.
     * 
     * @param nome Um String que representa o nome do Cliente.
     * 
     */
   	public Cliente(String nome) {
		this.nome = nome;
	}
	
	public String getCpf() {
		return this.cpf;
	}
	
 	public void setCpf(String cpf) throws ExcecaoDeCPFInvalido
 	{
 		if(cpf.length() == 11)
		{
 			this.cpf = cpf;
		}
 		else
 		{
 			throw (new ExcecaoDeCPFInvalido()) ;
 		}
	}

}
