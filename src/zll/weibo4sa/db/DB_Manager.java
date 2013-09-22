package zll.weibo4sa.db;

import java.sql.*;

public class DB_Manager {
	private static String url = "jdbc:mysql://localhost:3306/weibo";
	private static String username = "root";
	private static String password = "123";

	public static Connection getConnection(){
		Connection connection = null;
		try{
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			connection = DriverManager.getConnection(url, username, password);
			System.out.println("connection successful");
		}catch(InstantiationException e){
			e.printStackTrace();
		}catch(IllegalAccessException e){
			e.printStackTrace();
		}catch(ClassNotFoundException e){
			e.printStackTrace();
		}catch(SQLException e){
			e.printStackTrace();
		}
		return connection;
	}

	/*
	* Functions:close the DB Connections
	*
	* */
	public static void closeDB(Connection connection,Statement statement,ResultSet resultset){
		try{
			if(resultset != null){
				resultset.close();
			}
			if(statement != null){
				statement.close();
			}
			if(connection != null){
				connection.close();
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
	}

}
