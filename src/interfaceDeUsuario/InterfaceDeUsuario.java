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
						System.out.println("CPF inválido!");
						System.out.println("");
						entrada.nextLine();
					}
        			break;
        			
        		case 2: // Cadastrar um Automovel
        	    	Calendar dataAlug = Calendar.getInstance();
        	    	Calendar prazoAlug = Calendar.getInstance();
        	    	try {
	        			System.out.println("Infome a placa do automóvel: ");
	        			entrada.nextLine();
	        			placa = entrada.nextLine();
	        			for (int i = 0; i < listaDeAutomoveis.getTamanho(); i++) {
	        				listaDeAutomoveis.get(i).validaAutomovelJaCadastrado(placa);
	        			}   			
	        			System.out.println("Infome o tipo do automóvel: 1-Popular, 2-Medio, 3-Grande");
	        			tipo = entrada.nextInt();
	        			System.out.println("Infome o ano de modelo do automóvel: ");
	        			ano = entrada.nextInt();
	        			System.out.println("Infome a capacidade máxima de pessoas do autómovel: ");
	        			capacPessoas = entrada.nextInt();
	        			System.out.println("Infome o valor base da diária do autómovel: ");
	        			valDiaria = entrada.nextDouble();
	        			System.out.println("Automóvel cadastrado com sucesso!");
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
    					System.out.println("Automóvel com essa placa já cadastrado!");
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
            			System.out.println("Informe o valor máximo da diária: ");
            			valDiaria = entrada.nextDouble();
            			System.out.println("Informe a capacidade mínima de pessoas: ");
            			capacPessoas = entrada.nextInt();
            			System.out.println("Informe o número de dias de locação: ");
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
		        						System.out.println("Valor da diária: " + listaDeAutomoveis.get(j).getValDiaria());
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
            						System.out.println("Automóvel alugado com sucesso!");
            						System.out.println("");
            						entrada.nextLine();
            					}
        					}
        					
        					if (!placaOk) {
        						System.out.println("Automóvel com essa placa não encontrado!");
        						System.out.println("");		
        						entrada.nextLine();
        					}
        					
        				} else {
        					System.out.println("Automóvel com esses requisitos não encontrado!");
        					System.out.println("");
        					entrada.nextLine();
        				}
        				
        			} else {
        				System.out.println("Cliente com esse CPF não encontrado!");
        				System.out.println("");
        				entrada.nextLine();
        			}
        			break;
        			
        		case 4: // Devolver um Automovel
        			System.out.println("Informe a placa do automóvel a ser devolvido: ");
        			entrada.nextLine();
        			placa = entrada.nextLine();
        			placaOk = false;
					for (int i = 0; i < listaDeAutomoveis.getTamanho(); i++) {		
    					if (listaDeAutomoveis.get(i).getPlaca().equals(placa)) {
    						placaOk = true;
    						
    						if (listaDeAutomoveis.get(i).getCarroAlug() == true) {
	    						System.out.println("Informe a data de devolução, no seguinte padrão DD/MM/YYYY: ");
	    						String dataDev = entrada.nextLine();
	    						Calendar dataDev2 = Calendar.getInstance();
	    						dataDev2.setTime(format.parse(dataDev));
	    						double valTotal = listaDeAutomoveis.get(i).calculaValTotalDiaria(dataDev2);
	    						if (valTotal > 0) {
		    						System.out.println("Valor total a pagar: " + valTotal);
		    						System.out.println("Automóvel devolvido com sucesso!");
		    						System.out.println("");
		    						listaDeAutomoveis.get(i).setCarroAlug(false);
		    						listaDeAutomoveis.get(i).setDiasAlug(0);
		    						Calendar dataAlug2 = Calendar.getInstance();			
		    						listaDeAutomoveis.get(i).setDataAlug(dataAlug2);
		    						entrada.nextLine();	
	    						} else {
	    							System.out.println("Erro no cálculo do valor a pagar, por favor tente novamente!");
		    						System.out.println("");
	    						}    						
    						} else {
    							System.out.println("Este automóvel não está alugado!");
    							System.out.println("");		
    							entrada.nextLine();
    						}
    					} 				
					}
					if (!placaOk) {
						System.out.println("Automóvel com essa placa não encontrado!");
						System.out.println("");		
						entrada.nextLine();
					}
        			break;
        			
        		case 5: // Apresentar automóveis locados
        			boolean autoLocado = false;
        			for (int i = 0; i < listaDeAutomoveis.getTamanho(); i++) {		
    					if (listaDeAutomoveis.get(i).getCarroAlug() == true) {
    						System.out.println("Placa: " + listaDeAutomoveis.get(i).getPlaca());
    						int diasDev = listaDeAutomoveis.get(i).calculaDiasDev();
    						System.out.println("Dias para a devolução: " + diasDev);
    						System.out.println("");
    						entrada.nextLine();
    						autoLocado = true;
    					}
					}
        			if (!autoLocado) {
        				System.out.println("No momento não há nenhum Automóvel locado!");
        				System.out.println("");
        				entrada.nextLine();
        			}
        			break;
        			
        		case 6: // Remover um Automóvel
        			try {
        				boolean autoEncontrado = false;
	        			System.out.println("Informe a placa do Automóvel que deseja remover: ");
	        			entrada.nextLine();
	        			placa = entrada.nextLine();
		        			for (int i = 0; i <listaDeAutomoveis.getTamanho(); i++) {
		        				if (listaDeAutomoveis.get(i).getPlaca().equals(placa)) {
									listaDeAutomoveis.get(i).validaCarroAlug();
									listaDeAutomoveis.remover(i);
									System.out.println("Automóvel removido com sucesso!");
									System.out.println("");		
									autoEncontrado = true;
									entrada.nextLine();	
									break;
		        				}
		        			}
		        			if (!autoEncontrado) {
		        				System.out.println("Automóvel não cadastrado!");
		        			}
		        			
						} catch (RemocaoDeAutomovelEmLocacao e) {
							System.out.println("Automóvel locado, não é possível remover!");
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
    	System.out.println("2 - Cadastrar um Autómovel");
    	System.out.println("3 - Alugar um Automóvel");
    	System.out.println("4 - Devolver um Autómovel");
    	System.out.println("5 - Apresentar automóveis locados");
    	System.out.println("6 - Remover um Automóvel");
    	System.out.println("7 - Sair");  
    	int opcao = entrada.nextInt();
    	return opcao;
    }

}
