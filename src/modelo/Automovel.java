package modelo;

import java.io.Serializable;

/**
 * Classe para representar os autom�veis da aplica��o.
 * 
 * @author Emerson
 * @version 26/06/2020
 */

import java.time.Duration;
import java.util.Calendar;

import excecoes.ExcecaoDeAutomovelJaCadastrado;
import excecoes.RemocaoDeAutomovelEmLocacao;

/**
 * Classe para representar os autom�veis da aplica��o, extends Serializable.
 * 
 * @author Emerson
 * @version 03/07/2020
 */

public class Automovel implements Serializable {
	
	String placa, descTipo;
	int tipo,ano, capacPessoas, diasAlug, diferenca, diasAlug2, diasDev;
	double valDiaria, valTotal;
	boolean carroAlug;
	Calendar dataAlug, prazoAlug;
	
	private Lista<Automovel> listaDeAutomoveis = new ListaComArray<Automovel>(); 
	
    /**
     * Construtor que inicializa a placa, o tipo, o ano, a capacidade de pessoas, 
     * o valor da di�ria do Autom�vel. Al�m de definir se o Autom�vel est� alugado, se sim, quantos dias e o prazo.
     * 
     * @param placa Um String que representa a placa do Autom�vel.
     * @param tipo Um int que representa o tipo do Autom�vel (Popular, M�dio ou Grande).
     * @param ano Um int que representa o ano de modelo do Autom�vel.
     * @param capacPessoas Um int que representa a capacidade de pessoas do Autom�vel.
     * @param valDiaria Um double que representa o valor da di�ria do Autom�vel.
     * @param carroAlug Um boolean que define se o Autom�vel est� alugado.
     * @param diasAlug Um int que representa o n�mero de dias que o Autom�vel est� alugado.
     * @param dataAlug Um Calendar que representa a data de aluguel do Autom�vel.
     * @param prazoAlug Um Calendar que representa o prazo de aluguel do Autom�vel.
     * 
     */	
	public Automovel(String placa, int tipo, int ano, int capacPessoas, double valDiaria, boolean carroAlug, int diasAlug, Calendar dataAlug, Calendar prazoAlug) {
		this.placa = placa;
		this.tipo = tipo;
		this.ano = ano;
		this.capacPessoas = capacPessoas;
		this.valDiaria = valDiaria;
		this.carroAlug = carroAlug;
		this.diasAlug = diasAlug;
		this.dataAlug = dataAlug;
		this.prazoAlug = prazoAlug;
	}
	
    /**
     * Metodo para calcular o valor total do aluguel do Autom�vel.
     * 
     * @param dataDev Um Calendar que representa a data de devolu��o do Autom�vel.
     * 
     */		
	public double calculaValTotalDiaria(Calendar dataDev) {
		this.diferenca = 0;
		this.diasAlug  = 0;
		this.diasAlug2 = 0;
		this.valTotal  = 0;
		this.diasAlug  = ((int) Duration.between(this.dataAlug.toInstant(), dataDev.toInstant()).toDays()) + 1;
		this.diasAlug2 = ((int) Duration.between(this.dataAlug.toInstant(), this.prazoAlug.toInstant()).toDays());
		
		if (this.prazoAlug.get(Calendar.DAY_OF_MONTH) + this.prazoAlug.get(Calendar.MONTH) + this.prazoAlug.get(Calendar.YEAR) != dataDev.get(Calendar.DAY_OF_MONTH) + dataDev.get(Calendar.MONTH) + dataDev.get(Calendar.YEAR)) {
			this.diferenca = ((int) Duration.between(this.prazoAlug.toInstant(), dataDev.toInstant()).toDays()) + 1;
		}
		
		if (this.diferenca > 0) {
			this.valTotal = this.valDiaria * this.diasAlug2;
			this.valTotal = this.valTotal + (this.diferenca * (this.valDiaria + (this.valDiaria * 0.2)));		
		} else {
			this.valTotal = this.valDiaria * this.diasAlug;
		}
		
		return valTotal;
	}
	
    /**
     * Metodo para calcular o n�mero de dias restantes para o fim da loca��o.
     */	
	public int calculaDiasDev() {
		Calendar hoje = Calendar.getInstance();
		this.diasDev = ((int) Duration.between(hoje.toInstant(), this.prazoAlug.toInstant()).toDays()) + 1;
		return this.diasDev;
	}
	
    /**
     * Metodo para validar se o automovel a ser removido est� locado.
     */	
	public void validaCarroAlug() throws RemocaoDeAutomovelEmLocacao {
		if (carroAlug == true) {
			throw (new RemocaoDeAutomovelEmLocacao());
		}
	}
	
    /**
     * Metodo para validar se o automovel a ser cadastrado j� existe.
     * 
     * @param placa Um String que representa a placa do Autom�vel.
     */	
	public void validaAutomovelJaCadastrado(String placa) throws ExcecaoDeAutomovelJaCadastrado {
		if (this.placa.equals(placa)) {
			throw (new ExcecaoDeAutomovelJaCadastrado());
		}
	}
	
	public String getPlaca() {
		return this.placa;
	}
	
	public int getTipo() {
		return this.tipo;
	}
	
	public String getDescTipo() {
		if (this.tipo == 1) {
			this.descTipo = "Popular";		
		} else if (this.tipo == 2) {
			this.descTipo = "Normal";
		} else if (this.tipo == 3) {
			this.descTipo = "Grande";
		}
		return this.descTipo;
	}
	
	public int getAno() {
		return this.ano;
	}
	
	public int getCapacPessoas() {
		return this.capacPessoas;
	}
	
	public double getValDiaria() {
		return this.valDiaria;
	}
	
	public boolean getCarroAlug() {
		return this.carroAlug;
	}
	
	public void setCarroAlug(boolean carroAlug) {
		this.carroAlug = carroAlug;
	}
	
	public int getDiasAlug() {
		return this.diasAlug;
	}
	
	public void setDiasAlug(int diasAlug) {
		this.diasAlug = diasAlug;
	}
	
	public Calendar getDataAlug() {
		return this.dataAlug;
	}
	
	public void setDataAlug(Calendar dataAlug) {
		this.dataAlug = dataAlug;
	}
	
	public Calendar getPrazoAlug() {
		return this.prazoAlug;
	}
	
	public void setPrazoAlug(Calendar prazoAlug) {
		this.prazoAlug = prazoAlug;
	}

}
