import dao.SubTarefaDAO;
import dao.TarefaDAO;
import dao.UsuarioDAO;
import entities.SubTarefa;
import entities.Tarefa;
import entities.Usuario;

public class MainInsert {

	public static void main(String[] args) {
		
		//instancia a classe UsuarioDAO onde existem comandos SQL para o Usuario
        UsuarioDAO userDao = new UsuarioDAO();
        //Instancia e Cria o Usuario
        Usuario user = new Usuario("Gabriel", "99900199");
        //Insere o Usuario ao BD
        userDao.insert(user);
         
         
        //instancia a classe TarefaDAO onde existem comandos SQL para a tarefa
        TarefaDAO tarefaDao = new TarefaDAO();
         
        //instancia a classe SubTarefaDAO onde existem comandos SQL para a tarefa
        SubTarefaDAO subTarefaDao = new SubTarefaDAO();
         
         
        /* Instancia e Cria uma Tarefa
         * 1 Param: idUsuario
         * 2 Param: Nome da Tarefa
         * 3 Param: Status
         * 4 Param: Descricao
         * 
         * */
        Tarefa tarefaPrincipal1 = new Tarefa(7, "Comprar Tenis", 1, "Ir no Fort Atacadista");
        tarefaDao.insert(tarefaPrincipal1);
         
        /* Instancia e Cria uma Sub-Tarefa
         * 1 Param: idTarefa
         * 2 Param: Nome da Sub-Tarefa
         * 3 Param: Status
         * 
         * */
        SubTarefa subTarefa1 = new SubTarefa(9, "Comprar P�o", 1);
        subTarefaDao.insert(subTarefa1);
        SubTarefa subTarefa2 = new SubTarefa(10, "Comprar Leite", 1);
        subTarefaDao.insert(subTarefa2);
         
        /* Instancia e Cria uma Tarefa
         * 1 Param: idUsuario
         * 2 Param: Nome da Tarefa
         * 3 Param: Status
         * 4 Param: Descricao
         * 
         * */
        Tarefa tarefaPrincipal2 = new Tarefa(7, "Ir ao Shopping", 1, null);
        tarefaDao.insert(tarefaPrincipal2);
         
        /* Instancia e Cria uma Sub-Tarefa
         * 1 Param: idTarefa
         * 2 Param: Nome da Sub-Tarefa
         * 3 Param: Status
         * 
         * */
        SubTarefa subTarefa3 = new SubTarefa(10, "Ir ao Banco", 1);
        subTarefaDao.insert(subTarefa3);
        SubTarefa subTarefa4 = new SubTarefa(10, "Comprar Jaqueta", 1);
        subTarefaDao.insert(subTarefa4);
		
		
		
		
		
	}

}