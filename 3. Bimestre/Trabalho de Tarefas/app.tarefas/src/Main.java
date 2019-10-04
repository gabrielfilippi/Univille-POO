import java.util.Locale;
import java.util.Scanner;

import dao.SubTarefaDAO;
import dao.TarefaDAO;
import dao.UsuarioDAO;
import entities.SubTarefa;
import entities.Tarefa;
import entities.Usuario;
import functions.Funcoes;
import functions.InsertDb;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scan = new Scanner(System.in);
		scan.useLocale(Locale.US);
		
		//insere staticamente no banco
		InsertDb.inserirnoBD(); // comente esse usuario ap�s a primeira rodagem do programa. pois esta estatico os id estrangeiros
	
		//mostramos o usuario a ser escolhido		
		UsuarioDAO userDao = new UsuarioDAO();
		System.out.println("Escolha qual � o seu usuario digitando o ID:");
		System.out.println();
		for(Usuario user : userDao.getAll()) {
			System.out.println(user);
		}
		int idUserEscolhido = scan.nextInt();
		
		//instancia a classe TarefaDAO onde existem comandos SQL para a tarefa
		TarefaDAO tarefaDao = new TarefaDAO();
		//instancia a classe SubTarefaDAO onde existem comandos SQL para a tarefa
		SubTarefaDAO subTarefaDao = new SubTarefaDAO();
		
		//abrimos o menu
		int menu = 0;
		while(menu!=9) {
			System.out.println("======================================\n======================================");
			System.out.println("================ MENU ================");
			System.out.println("======================================\n======================================");
			System.out.println();
			System.out.println("Escolha a op��o Desejada (Digite o n�mero):");
			System.out.println();
			System.out.println("1 = Nova Lista de Tarefa");
			System.out.println("2 = Visualizar/Checar Lista de Tarefa");
			System.out.println("3 = Editar Lista de Tarefa");
			System.out.println("4 = Excluir Lista de Tarefa");
			System.out.println("9 = FECHAR APP!");
			menu = scan.nextInt();
			System.out.println();
			if(menu==1) {
				//pode ser uma tarefa direta: comprar tenis
				//ou
				//tarefa com sub-tarefas, ex: Ir ao mercado -> comprar ovos, comprar leite;
				System.out.println("Digite o nome da Tarefa caso uma, ou Titulo caso tenha Sub-Tarefas: ");
				String nomeTarefa = scan.next();
				scan.nextLine();
				System.out.println("Digite a descricao da Tarefa(Opcional): ");
				String decricaoTarefa = scan.next();
				scan.nextLine();
				//criar nova lista de tarefa
				Tarefa tarefa = new Tarefa(idUserEscolhido, nomeTarefa, 1, decricaoTarefa);
				tarefaDao.insert(tarefa);
				
				//verificamos se teremos sub tarefas para a tarefa recem cadastrada
				System.out.println();
				System.out.println(nomeTarefa+" ter� Sub-Tarefas? (1 = SIM; 2 = N�o): ");
				int yesOrNo= scan.nextInt();
				if(yesOrNo==1) {
					int insertSub = 1;
					while(insertSub==1) {
						System.out.println("Digite o Nome da Sub-Tarefa: ");
						String nomeSubTarefa = scan.next();
						scan.nextLine();
						//inserimos a sub tarefa
						SubTarefa subTarefa = new SubTarefa(9, nomeSubTarefa, 1);
						subTarefaDao.insert(subTarefa);
						System.out.println();
						System.out.println("Deseja continuar cadastrando sub-tarefas? (1 = SIM; 9 = N�o): ");
						insertSub = scan.nextInt();
					}
				}
				System.out.println("Tarefa Cadastrada Com Sucesso!");
				
			}else if(menu==2) {
				//Visualizar/checar lista de Tarefa
				Funcoes.mostrarTarefa(idUserEscolhido);
				System.out.println("SELECIONE UMA LISTA DE TAREFA DIGITANDO O SEU ID");
				int idListaPrincipalSelecionada = scan.nextInt();
				
				System.out.println("Voc� Selecionou: \n\n"+ tarefaDao.getById(idListaPrincipalSelecionada)+"\n");
				Funcoes.mostrarSubTarefa(idListaPrincipalSelecionada);
				int opcao = 1;
				int count=0;
				while(opcao!=0) {
					count++;
					System.out.println("Digite o Id da sub-tarefa para mudar seu status OU 0 para sair:");
					opcao = scan.nextInt();
					if(opcao!=0 && count!=0) {
						//mudar o status 
						subTarefaDao.updateStatus(opcao);
					}else {
						break;
					}
				}
				
			}else if(menu==3) {
				//editar Lista de Tarefa e sub-tarefa
				int opcao = 0;
				while(opcao!=9) {
					Funcoes.mostrarTarefa(idUserEscolhido);
					System.out.println("Deseja alterar a tarefa principal(1), sub-tarefa(2) ou sair(9):");
					opcao = scan.nextInt();
					if(opcao==1) {
						System.out.println("SELECIONE UMA LISTA DE TAREFA QUE DESEJA ALTERAR DIGITANDO O SEU ID");
						int idListaPrincipalSelecionada = scan.nextInt();
						System.out.println();
						
						//atualiza a tarefa principal
						System.out.println("Digite o nome da Tarefa:");
						String nomeTarefa = scan.next();
						System.out.println("Digite a Descri��o da Tarefa: ");
						String descricaoTarefa = scan.nextLine();
						scan.next();
						System.out.println("Digite o status da Tarefa 1 = N�o realizado 2 = Realizado: ");
						int statusTarefa = scan.nextInt();
						
						Tarefa tarefa = new Tarefa(idListaPrincipalSelecionada, idUserEscolhido, nomeTarefa, statusTarefa, descricaoTarefa);
						tarefaDao.update(tarefa, idUserEscolhido);
						System.out.println("Atualiza��o Realizada com Sucesso");
						System.out.println();
					}else if(opcao==2){
						//atualiza as sub-tarefas
						int opcaoSub = 0;
						while(opcaoSub!=9) {
							System.out.println("SELECIONE UMA LISTA DE TAREFA DIGITANDO O SEU ID");
							int idListaPrincipalSelecionada = scan.nextInt();
							
							Funcoes.mostrarSubTarefa(idListaPrincipalSelecionada);
							System.out.println("Digite o ID da sub tarefa que deseja alterar:");
							int idSubTarefa = scan.nextInt();
							
							System.out.println("Digite o nome da Sub-Tarefa:");
							String nomeSubTarefa = scan.next();
							
							System.out.println("Digite o status da Tarefa 1 = N�o realizado; 2 = Realizado: ");
							int statusSubTarefa = scan.nextInt();
							
							SubTarefa UpdtSubTarefa = new SubTarefa(idSubTarefa, idListaPrincipalSelecionada, nomeSubTarefa, statusSubTarefa);
							subTarefaDao.updateAll(UpdtSubTarefa);
							
							System.out.println("Atualiza��o Realizada com Sucesso");
							System.out.println();
							
							System.out.println("Deseja editar mais alguma Sub-Tarefa? Sim = 1 ou N�o = 9");
							opcaoSub = scan.nextInt();
						}
					}else if(opcao==9){
						break;
					}
				}
				
			}else if(menu==4) {
				int opcao = 0;
				while(opcao!=9) {
					System.out.println("Deseja excluir a tarefa principal(1), sub-tarefa(2) ou sair(9):");
					opcao = scan.nextInt();
					if(opcao==1) {
						//exclui a tarefa principal
						Funcoes.mostrarTarefa(idUserEscolhido);
						System.out.println("SELECIONE UMA LISTA DE TAREFA QUE DESEJA EXCLUIR DIGITANDO O SEU ID");
						int idListaPrincipalSelecionada = scan.nextInt();
						System.out.println();
						//excluimos as sub-tarefas daquela tarefa principal.
						subTarefaDao.deleteByIdTarefa(idListaPrincipalSelecionada);
						//excluimos a tarefa principal
						tarefaDao.delete(idListaPrincipalSelecionada);
						
						System.out.println("Exclus�o Realizada com Sucesso");
						System.out.println();
					}else if(opcao==2){
						//exclui as sub-tarefas
						int opcaoSub = 0;
						while(opcaoSub!=9) {
							Funcoes.mostrarTarefa(idUserEscolhido);
							System.out.println("SELECIONE UMA LISTA DE TAREFA DIGITANDO O SEU ID");
							int idListaPrincipalSelecionada = scan.nextInt();
							
							Funcoes.mostrarSubTarefa(idListaPrincipalSelecionada);
							System.out.println("Digite o ID da sub tarefa que deseja excluir:");
							int idSubTarefa = scan.nextInt();
							//excluimos a subTarefa
							subTarefaDao.deleteByIdTarefa(idSubTarefa);
							
							System.out.println("Exclus�o Realizada com Sucesso");
							System.out.println();
							
							System.out.println("Deseja excluir mais alguma Sub-Tarefa? Sim = 1 ou N�o = 9");
							opcaoSub = scan.nextInt();
						}
					}else if(opcao==9){
						break;
					}
				} 
			}else if(menu==9) {
				System.out.println("Fechando App... :D");
				break;
			}
		}// close while	
		scan.close();
	}// close main
}// close Main
