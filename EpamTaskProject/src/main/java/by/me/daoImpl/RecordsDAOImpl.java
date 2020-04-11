package by.me.daoImpl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import by.me.dao.RecordsDAO;
import by.me.jdbc.JDBCConnector;

public class RecordsDAOImpl implements RecordsDAO{

	JDBCConnector dbConnector;
	
	public RecordsDAOImpl() {
		this.dbConnector = new JDBCConnector();
	}

	@Override
	public List<Record> getRecords() {
		Connection connection = null;
		Statement statement = null;
		List<Record> records = null;
		
		try {
			connection = this.dbConnector.getConnection();
			statement = connection.createStatement();
			records = new ArrayList<Record>();
			ResultSet resSet = statement.executeQuery("select * from records");
			while(resSet.next()) {
				int id = resSet.getInt("id");
				String playerName = resSet.getString("name");
				int steps = resSet.getInt("steps");
				Record record = new Record(playerName, steps);
				record.setId(id);
				records.add(record);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
		return records;
		
	}

	@Override
	public void add(Record record) {
		
		Connection connection = null;
		Statement statement = null;
		
		try {
			connection = this.dbConnector.getConnection();
			statement = connection.createStatement();
			
			String playerName = record.getPlayerName();
			int steps = record.getSteps();
			
			statement.execute("insert into records (name, steps) values ('"+playerName+"', '"+steps+"')");
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
	}

	@Override
	public void update(Record record, int id) {
		
		Connection connection = null;
		Statement statement = null;
		
		try {
			connection = this.dbConnector.getConnection();
			statement = connection.createStatement();
			connection.setAutoCommit(false);
			int newSteps = record.getSteps();
			String newName = record.getPlayerName();
			
			String setNewName = "update records set name = '"+newName+"' where id = '"+id+"'";
			String setNewSteps = "update records set steps = '"+newSteps+"' where id = '"+id+"'";
			statement.executeUpdate(setNewName);
			statement.executeUpdate(setNewSteps);
			connection.commit();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	@Override
	public void deleteAll() {
		
		Connection connection = null;
		Statement statement = null;
		
		try {
			connection = this.dbConnector.getConnection();
			statement = connection.createStatement();
			statement.execute("delete from records");
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
	}
	
}
