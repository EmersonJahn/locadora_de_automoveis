package interfaceDeUsuario;

import java.util.Scanner;

import excecoes.ExcecaoDeAutomovelJaCadastrado;
import excecoes.ExcecaoDeCPFInvalido;
import excecoes.RemocaoDeAutomovelEmLocacao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import modelo.Automovel;
import modelo.AutomovelGrande;
import modelo.AutomovelMedio;
import modelo.AutomovelPopular;
import modelo.Cliente;
import modelo.Lista;
import modelo.ListaComArray;
import persistencia.Persistencia;

public class InterfaceDeUsuario {
	
	private Lista<Cliente> listaDeClientes = new ListaComArray<Cliente>(); 
	private Lista<Automovel> listaDeAutomoveis = new ListaComArray<Automovel>(); 
	
	private Scanner entrada = new Scanner(System.in);
	
	private Persistencia persistidorCliente;
	private Persistencia persistidorAutomovel;
	
    public InterfaceDeUsuario(Persistencia persistidor)
    {
    	this.persistidorCliente = persistidor;
    	this.persistidorAutomovel = persistidor;
    }
	
	public void mostrarMenuPrincipal() throws ParseException {
		
        String sair, nome, cpf, placa;
    	int tipo, ano, capacPessoas;
    	double valDiaria;
    	SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
    	
    	Cliente tempCliente;
    	Automovel tempAutomovel;
    	
    	listaDeClientes = persistidorCliente.recuperarClientes();
    	listaDeAutomoveis = persistidorAutomovel.recuperarAutomoveis();
		
        boolean continuar = true;
		int opcao = this.obterOpcao();
        while(continuar) {
        	
        	switch(opcao) {
        	
        		case 1: // Cadastrar um Cliente
        			try {
	        			System.out.println("Infome o nome do cliente: ");
	        			entrada.nextLine();
	        			nome = entrada.nextLine();
	        			System.out.println("Informe o CPF do cliente: ");
	        			cpf = entrada.nextLine();
	        			tempCliente = new Cliente(nome);
						tempCliente.setCpf(cpf);
	        			listaDeClientes.inserir(tempCliente);
	        			System.out.println("Cliente cadastrado com sucesso!");
	        			System.out.println("");
	        			entrada.nextLine();
	        			
					} catch (ExcecaoDeCPFInvalido e) {
						System.out.println("CPF inv�lido!");
						System.out.println("");
						entrada.nextLine();
					}
        			break;
        			
        		case 2: // Cadastrar um Automovel
        	    	Calendar dataAlug = Calendar.getInstance();
        	    	Calendar prazoAlug = Calendar.getInstance();
        	    	try {
	        			System.out.println("Infome a placa do autom�vel: ");
	        			entrada.nextLine();
	        			placa = entrada.nextLine();
	        			for (int i = 0; i < listaDeAutomoveis.getTamanho(); i++) {
	        				listaDeAutomoveis.get(i).validaAutomovelJaCadastrado(placa);
	        			}   			
	        			System.out.println("Infome o tipo do autom�vel: 1-Popular, 2-Medio, 3-Grande");
	        			tipo = entrada.nextInt();
	        			System.out.println("Infome o ano de modelo do autom�vel: ");
	        			ano = entrada.nextInt();
	        			System.out.println("Infome a capacidade m�xima de pessoas do aut�movel: ");
	        			capacPessoas = entrada.nextInt();
	        			System.out.println("Infome o valor base da di�ria do aut�movel: ");
	        			valDiaria = entrada.nextDouble();
	        			System.out.println("Autom�vel cadastrado com sucesso!");
	        			System.out.println("");
	        			if (tipo == 1) {
	        				tempAutomovel = new AutomovelPopular(placa, tipo, ano, capacPessoas, valDiaria, false, 0, dataAlug, prazoAlug);
	        				listaDeAutomoveis.inserir(tempAutomovel);
	        			} else if (tipo == 2) {
	        				tempAutomovel = new AutomovelMedio(placa, tipo, ano, capacPessoas, valDiaria, false, 0, dataAlug, prazoAlug);
	        				listaDeAutomoveis.inserir(tempAutomovel);
	        			} else if (tipo == 3) {
	        				tempAutomovel = new AutomovelGrande(placa, tipo, ano, capacPessoas, valDiaria, false, 0, dataAlug, prazoAlug);
	        				listaDeAutomoveis.inserir(tempAutomovel);
	        			}
    				} catch (ExcecaoDeAutomovelJaCadastrado e1) {
    					System.out.println("Autom�vel com essa placa j� cadastrado!");
    					System.out.println("");
    					entrada.nextLine();
    				}
        			break;
        			
        		case 3: // Alugar um Automovel
        			System.out.println("Informe o CPF do cliente: ");
        			entrada.nextLine();
        			cpf = entrada.nextLine();
        			boolean cpfOk = false;
        			boolean carroOk = false;
        			boolean placaOk = false;
        			for (int i = 0; i < listaDeClientes.getTamanho(); i++) {
	        			if(listaDeClientes.get(i).getCpf().equals(cpf)) {	
	        				cpfOk = true;	
	        			}			
        			}
        			if (cpfOk) {
        				int dias = 0;
            			System.out.println("Informe o valor m�ximo da di�ria: ");
            			valDiaria = entrada.nextDouble();
            			System.out.println("Informe a capacidade m�nima de pessoas: ");
            			capacPessoas = entrada.nextInt();
            			System.out.println("Informe o n�mero de dias de loca��o: ");
            			dias = entrada.nextInt();
        				for (int j = 0; j < listaDeAutomoveis.getTamanho(); j++) {
        					
        					if (listaDeAutomoveis.get(j).getCarroAlug() == false) {
        						
        						if (listaDeAutomoveis.get(j).getValDiaria() <= valDiaria) {
        							
        							if (listaDeAutomoveis.get(j).getCapacPessoas() >= capacPessoas) {
        								carroOk = true;     							
		        						System.out.println("Placa: " + listaDeAutomoveis.get(j).getPlaca());
		        						System.out.println("Tipo: " + listaDeAutomoveis.get(j).getDescTipo());
		        						System.out.println("Ano modelo: " + listaDeAutomoveis.get(j).getAno());
		        						System.out.println("Capacidade de pessoas: " + listaDeAutomoveis.get(j).getCapacPessoas());
		        						System.out.println("Valor da di�ria: " + listaDeAutomoveis.get(j).getValDiaria());
		        						System.out.println("");
        							}
        						}	
        					}	
        				}
        				if (carroOk) {
        					System.out.println("Selecione o carro a ser alugado, informando a sua placa: ");
        					entrada.nextLine();
        					placa = entrada.nextLine();
        					for (int i = 0; i < listaDeAutomoveis.getTamanho(); i++) {
        						
            					if (listaDeAutomoveis.get(i).getPlaca().equals(placa)) {
            						listaDeAutomoveis.get(i).setCarroAlug(true);  
            						listaDeAutomoveis.get(i).setDiasAlug(dias);
            						Calendar hoje = Calendar.getInstance();
            						Calendar praAlug = Calendar.getInstance();
            						listaDeAutomoveis.get(i).setDataAlug(hoje);
            						praAlug.add(Calendar.DATE, dias);
            						listaDeAutomoveis.get(i).setPrazoAlug(praAlug);
            						placaOk = true;
            						System.out.println("Autom�vel alugado com sucesso!");
            						System.out.println("");
            						entrada.nextLine();
            					}
        					}
        					
        					if (!placaOk) {
        						System.out.println("Autom�vel com essa placa n�o encontrado!");
        						System.out.println("");		
        						entrada.nextLine();
        					}
        					
        				} else {
        					System.out.println("Autom�vel com esses requisitos n�o encontrado!");
        					System.out.println("");
        					entrada.nextLine();
        				}
        				
        			} else {
        				System.out.println("Cliente com esse CPF n�o encontrado!");
        				System.out.println("");
        				entrada.nextLine();
        			}
        			break;
        			
        		case 4: // Devolver um Automovel
        			System.out.println("Informe a placa do autom�vel a ser devolvido: ");
        			entrada.nextLine();
        			placa = entrada.nextLine();
        			placaOk = false;
					for (int i = 0; i < listaDeAutomoveis.getTamanho(); i++) {		
    					if (listaDeAutomoveis.get(i).getPlaca().equals(placa)) {
    						placaOk = true;
    						
    						if (listaDeAutomoveis.get(i).getCarroAlug() == true) {
	    						System.out.println("Informe a data de devolu��o, no seguinte padr�o DD/MM/YYYY: ");
	    						String dataDev = entrada.nextLine();
	    						Calendar dataDev2 = Calendar.getInstance();
	    						dataDev2.setTime(format.parse(dataDev));
	    						double valTotal = listaDeAutomoveis.get(i).calculaValTotalDiaria(dataDev2);
	    						if (valTotal > 0) {
		    						System.out.println("Valor total a pagar: " + valTotal);
		    						System.out.println("Autom�vel devolvido com sucesso!");
		    						System.out.println("");
		    						listaDeAutomoveis.get(i).setCarroAlug(false);
		    						listaDeAutomoveis.get(i).setDiasAlug(0);
		    						Calendar dataAlug2 = Calendar.getInstance();			
		    						listaDeAutomoveis.get(i).setDataAlug(dataAlug2);
		    						entrada.nextLine();	
	    						} else {
	    							System.out.println("Erro no c�lculo do valor a pagar, por favor tente novamente!");
		    						System.out.println("");
	    						}    						
    						} else {
    							System.out.println("Este autom�vel n�o est� alugado!");
    							System.out.println("");		
    							entrada.nextLine();
    						}
    					} 				
					}
					if (!placaOk) {
						System.out.println("Autom�vel com essa placa n�o encontrado!");
						System.out.println("");		
						entrada.nextLine();
					}
        			break;
        			
        		case 5: // Apresentar autom�veis locados
        			boolean autoLocado = false;
        			for (int i = 0; i < listaDeAutomoveis.getTamanho(); i++) {		
    					if (listaDeAutomoveis.get(i).getCarroAlug() == true) {
    						System.out.println("Placa: " + listaDeAutomoveis.get(i).getPlaca());
    						int diasDev = listaDeAutomoveis.get(i).calculaDiasDev();
    						System.out.println("Dias para a devolu��o: " + diasDev);
    						System.out.println("");
    						entrada.nextLine();
    						autoLocado = true;
    					}
					}
        			if (!autoLocado) {
        				System.out.println("No momento n�o h� nenhum Autom�vel locado!");
        				System.out.println("");
        				entrada.nextLine();
        			}
        			break;
        			
        		case 6: // Remover um Autom�vel
        			try {
        				boolean autoEncontrado = false;
	        			System.out.println("Informe a placa do Autom�vel que deseja remover: ");
	        			entrada.nextLine();
	        			placa = entrada.nextLine();
		        			for (int i = 0; i <listaDeAutomoveis.getTamanho(); i++) {
		        				if (listaDeAutomoveis.get(i).getPlaca().equals(placa)) {
									listaDeAutomoveis.get(i).validaCarroAlug();
									listaDeAutomoveis.remover(i);
									System.out.println("Autom�vel removido com sucesso!");
									System.out.println("");		
									autoEncontrado = true;
									entrada.nextLine();	
									break;
		        				}
		        			}
		        			if (!autoEncontrado) {
		        				System.out.println("Autom�vel n�o cadastrado!");
		        			}
		        			
						} catch (RemocaoDeAutomovelEmLocacao e) {
							System.out.println("Autom�vel locado, n�o � poss�vel remover!");
							System.out.println("");		
							entrada.nextLine();
						}
        			break;
        			
                case 7: // Sair
	                System.out.println("Deseja realmente sair? S-Sim/N-Nao");
	                entrada.nextLine();
	                sair = entrada.nextLine();
	                if (sair.equals("S") || sair.equals("s")) {
	                    continuar = false;   
	                    persistidorCliente.gravarClientes(listaDeClientes);
	                    persistidorAutomovel.gravarAutomoveis(listaDeAutomoveis);
	                } 
	                break;
                
            }
        	
            if(continuar) {
                opcao = this.obterOpcao();
            }       			
        }		
	}
	
    private int obterOpcao() {
    	System.out.println("1 - Cadastrar um Cliente");
    	System.out.println("2 - Cadastrar um Aut�movel");
    	System.out.println("3 - Alugar um Autom�vel");
    	System.out.println("4 - Devolver um Aut�movel");
    	System.out.println("5 - Apresentar autom�veis locados");
    	System.out.println("6 - Remover um Autom�vel");
    	System.out.println("7 - Sair");  
    	int opcao = entrada.nextInt();
    	return opcao;
    }

}
