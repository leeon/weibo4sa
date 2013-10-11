package zll.weibo4sa.db;

import java.sql.*;

public class DB_Manager {
    private static String default_url = "jdbc:mysql://127.0.0.1:3306/weibo?useUnicode=true&characterEncoding=UTF-8";
    private static String default_username = "root";
    private static String default_password = "123";
	private static String url = "jdbc:mysql://127.0.0.1:8066/weiboo?useUnicode=true&characterEncoding=UTF-8";
	private static String username = "test";
	private static String password = "test";

	public static Connection getConnection(){
		Connection connection = null;
		try{
			Class.forName("com.mysql.jdbc.Driver").newInstance();
//			connection = DriverManager.getConnection(url, username, password);
			connection = DriverManager.getConnection(default_url, default_username, default_password);
//			/System.out.println("connection successful");
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
