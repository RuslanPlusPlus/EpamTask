package by.me.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCConnector {

	private final String DRIVER = "org.postgresql.Driver";
	private final String URL = "jdbc:postgresql://localhost/EpamTask";
	private final String LOGIN = "postgres";
	private final String PASSWORD = "RNSQL";
	
	{
		try {
			Class.forName(DRIVER);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
	}
	
	public Connection getConnection() throws SQLException {
		return DriverManager.getConnection(URL, LOGIN, PASSWORD);
	}
}
