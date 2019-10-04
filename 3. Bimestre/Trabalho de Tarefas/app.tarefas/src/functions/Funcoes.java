package functions;

import dao.SubTarefaDAO;
import dao.TarefaDAO;
import entities.SubTarefa;
import entities.Tarefa;

public class Funcoes {
	public static void mostrarTarefa(int idUsuario) {
		//instancia a classe TarefaDAO onde existem comandos SQL para a tarefa
		TarefaDAO tarefaDao = new TarefaDAO();
		for(Tarefa trf : tarefaDao.getAllByIdUsuario(idUsuario)) {
			System.out.println(trf+"\n");
		}
	}
	
	public static void mostrarSubTarefa(int idListaPrincipal) {
		//instancia a classe SubTarefaDAO onde existem comandos SQL para a tarefa
		SubTarefaDAO subTarefaDao = new SubTarefaDAO();
		for(SubTarefa subTrf : subTarefaDao.getAllByIdTarefa(idListaPrincipal)) {
			System.out.println(subTrf+"\n");
		}
	}
}
