package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entities.SubTarefa;

/**
 * SubTarefa Data Acess Object
 */
public class SubTarefaDAO extends BasicoDAO{
	

	/**
	 * Inserir uma nova SubTarefa
	 */
	public void insert(SubTarefa subTarefa) {

		String sql_insert_SubTarefa = "INSERT INTO sub_tarefa(Tarefa_idTarefa, itemSubTarefa, statusSubTarefa) VALUES(?, ?, ?)";
		try {
			
			// try with-resources
			
			try (Connection conn = getConnection();
				 PreparedStatement statement = conn.prepareStatement(sql_insert_SubTarefa)){
				
				statement.setInt(1, subTarefa.getTarefa_idTarefa());
				statement.setString(2, subTarefa.getItemSubTarefa());
				statement.setInt(3, subTarefa.getStatusSubTarefa());
				statement.execute();
			}
		}catch (Exception e){
			e.printStackTrace();
		}
		
	}
	
	/**
	 * Deletar uma SubTarefa pelo o seu id
	 */
	public void deleteByIdSubTarefa(int idSubTarefa) {
		
		String sql_del_SubTarefa = "DELETE FROM sub_tarefa WHERE idSubTarefa = ?";
		
		try(Connection conn = getConnection();
			PreparedStatement statement = conn.prepareStatement(sql_del_SubTarefa)) {
			statement.setInt(1, idSubTarefa);
			statement.execute();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Deletar uma SubTarefa pelo o id da tarefa Principal
	 */
	public void deleteByIdTarefa(int Tarefa_idTarefa) {
		
		String sql_del_SubTarefa = "DELETE FROM sub_tarefa WHERE Tarefa_idTarefa = ?";
		
		try(Connection conn = getConnection();
			PreparedStatement statement = conn.prepareStatement(sql_del_SubTarefa)) {
			statement.setInt(1, Tarefa_idTarefa);
			statement.execute();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Atualizar o status de uma SubTarefa pelo id
	 */
	public void updateStatus(int IdSubTarefa) {
		String sql = "UPDATE sub_tarefa SET statusSubTarefa = 2 WHERE idSub_Tarefa = ?";
		
		try (Connection conn = getConnection();
			 PreparedStatement statement = conn.prepareStatement(sql)){
			statement.setInt(1, IdSubTarefa);
			statement.execute();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * Atualizar toda uma SubTarefa pelo id
	 */
	public void updateAll(SubTarefa subTarefa) {
		String sql = "UPDATE sub_tarefa SET itemSubTarefa = ?, statusSubTarefa = ? WHERE idSubTarefa = ? AND Tarefa_idTarefa =?";
		
		try (Connection conn = getConnection();
			 PreparedStatement statement = conn.prepareStatement(sql)){
			statement.setString(1, subTarefa.getItemSubTarefa());
			statement.setInt(2, subTarefa.getStatusSubTarefa());
			statement.setInt(3, subTarefa.getIdSub_Tarefa());
			statement.setInt(4, subTarefa.getTarefa_idTarefa());
			statement.execute();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * Obter uma SubTarefa pelo id
	 */
	public SubTarefa getById(int idSubTarefa) {
		SubTarefa subTarefa = null;
		String sql_select_SubTarefa = "SELECT * FROM sub_tarefa WHERE idSubTarefa = ?";
		
		try(Connection conn = getConnection();
			PreparedStatement statement = conn.prepareStatement(sql_select_SubTarefa)) {
			statement.setInt(1, idSubTarefa);
			ResultSet resultSet = statement.executeQuery();
			if(resultSet.next()) {
				subTarefa = new SubTarefa(resultSet.getInt(1), resultSet.getInt(2), resultSet.getString(3), resultSet.getInt(4));
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return subTarefa;
	}
	
	/**
	 * Obter todas as SubTarefas da tabela por usuario
	 */
	public List<SubTarefa> getAllByIdTarefa(int idTarefa){
		List<SubTarefa> list = new ArrayList<>();
		String sql_select_SubTarefa = "SELECT * FROM sub_tarefa WHERE Tarefa_idTarefa = ?";
		
		try(Connection conn = getConnection();
			PreparedStatement statement = conn.prepareStatement(sql_select_SubTarefa)) {
			statement.setInt(1, idTarefa);
			ResultSet resultSet = statement.executeQuery();
			while(resultSet.next()) {
				SubTarefa subTarefa = new SubTarefa(resultSet.getInt(1), resultSet.getInt(2), resultSet.getString(3), resultSet.getInt(4));
				list.add(subTarefa);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
}
