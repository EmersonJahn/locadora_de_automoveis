package modelo;

/**
 * Classe para representar os automóveis do tipo médio da aplicação, extends Automovel.
 * 
 * @author Emerson
 * @version 03/07/2020
 */

import java.util.Calendar;

public class AutomovelMedio extends Automovel {
	
	Calendar cal = Calendar.getInstance();
	int anoAtual = cal.get(Calendar.YEAR);

    /**
     * Construtor que inicializa a placa, o tipo, o ano, a capacidade de pessoas, 
     * o valor da diária do Automóvel. Além de definir se o Automóvel está alugado, se sim, quantos dias e o prazo.
     * 
     * @param placa Um String que representa a placa do Automóvel.
     * @param tipo Um int que representa o tipo do Automóvel (Popular, Médio ou Grande).
     * @param ano Um int que representa o ano de modelo do Automóvel.
     * @param capacPessoas Um int que representa a capacidade de pessoas do Automóvel.
     * @param valDiaria Um double que representa o valor da diária do Automóvel.
     * @param carroAlug Um boolean que define se o Automóvel está alugado.
     * @param diasAlug Um int que representa o número de dias que o Automóvel está alugado.
     * @param dataAlug Um Calendar que representa a data de aluguel do Automóvel.
     * @param prazoAlug Um Calendar que representa o prazo de aluguel do Automóvel.
     * 
     */	
	public AutomovelMedio(String placa, int tipo, int ano, int capacPessoas, double valDiaria, boolean carroAlug, int diasAlug, Calendar dataAlug, Calendar prazoAlug) {
		super(placa, tipo, ano, capacPessoas, valDiaria, carroAlug, diasAlug, dataAlug, prazoAlug);
		this.valDiaria = calculaValDiaria();
	}

    /**
     * Metodo para calcular o valor da diária do Automóvel. 
     */	
	public double calculaValDiaria() {
		if ((this.anoAtual - this.ano) > 0) {
			if ((this.anoAtual - this.ano) >= 5) {
				this.valDiaria = this.valDiaria - (this.valDiaria * 0.1);
			} else {
				this.valDiaria = this.valDiaria - (this.valDiaria * ((this.anoAtual - this.ano) * 0.02));
			}	
		}
		return this.valDiaria;
	}

}

