package modelo;

/**
 * Classe para representar os autom�veis do tipo popular da aplica��o, extends Automovel.
 * 
 * @author Emerson
 * @version 03/07/2020
 */

import java.util.Calendar;

public class AutomovelPopular extends Automovel {

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
	public AutomovelPopular(String placa, int tipo, int ano, int capacPessoas, double valDiaria, boolean carroAlug, int diasAlug, Calendar dataAlug, Calendar prazoAlug) {
		super(placa, tipo, ano, capacPessoas, valDiaria, carroAlug, diasAlug, dataAlug, prazoAlug);
	}

}
