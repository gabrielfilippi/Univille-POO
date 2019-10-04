package dao;

import java.sql.Connection;

import connection.ConnectionDb;

/**
 * 
 * @author leandersonandre
 *
 */
public abstract class BasicoDAO {
	
	
	public Connection getConnection() {
		return ConnectionDb.getInstance().getConnection();
	}

}
