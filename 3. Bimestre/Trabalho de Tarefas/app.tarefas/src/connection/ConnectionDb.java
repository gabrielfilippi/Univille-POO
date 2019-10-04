package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDb {
	
	private static ConnectionDb instance;

	private static String banco = "app.tarefas.gf";
	private static String usuario = "root";
	private static String senha = "";
	
	private ConnectionDb() {}

	public static ConnectionDb getInstance() {
		if(instance == null) {
			instance = new ConnectionDb();
		}
		return instance;
	}
	
	public Connection getConnection(){
		try {
		return DriverManager.getConnection(  
				"jdbc:mysql://localhost:3306/"+banco+"?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",usuario,senha);
		}catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	

}
