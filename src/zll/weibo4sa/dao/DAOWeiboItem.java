package zll.weibo4sa.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import zll.weibo4sa.db.DB_Manager;
import zll.weibo4sa.model.WeiboItem;

/**
 * DAO DAOWeiboItem
 * CURD for model weibo item
 * @since 1.0
 * @version 1.0
 * */
public class DAOWeiboItem {
	private Statement state = null;
	private PreparedStatement preState = null;
	private ResultSet resultset = null;
	private Connection connection = null;
	
	/**
	 * Method: create
	 * @param item 
	 * */
	public void create(WeiboItem item){
		connection =  DB_Manager.getConnection(); 
		try {
			    state =connection.createStatement();
				String sqlState = "INSERT INTO `model_weibo_item` (`content`, `date`, `author`, `reads`) VALUES(?,?,?,?)";
				System.out.println(sqlState);
				preState = connection.prepareStatement(sqlState);
				preState.setString(1, item.getContent());
				preState.setDate(2, item.getDate());
				preState.setString(3,item.getAuthor()); 
				preState.setInt(4, item.getReads());
			
				preState.execute("begin");
				preState.executeUpdate();
				preState.execute("commit");
		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			DB_Manager.closeDB(connection, state, resultset);
		}
		
		
	}
	
	/**
	 * Method:Delete
	 * @param ID 
	 * */
	public void delete(int ID){
		connection =  DB_Manager.getConnection(); 
		try {
			    state =connection.createStatement();
				String sqlState = "DELETE FROM model_weibo_item WHERE model_weibo_item.ID="+ID;
				preState = connection.prepareStatement(sqlState);
				preState.executeUpdate(sqlState);
			
				preState.execute("begin");
				preState.executeUpdate();
				preState.execute("commit");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DB_Manager.closeDB(connection, state, resultset);
		}
	}
	
	/**
	 * Method:update
	 * @param item  a weibo item info
	 * */
	public void updateWeibo(WeiboItem item){
			connection =  DB_Manager.getConnection(); 
			try {
				    state =connection.createStatement();
					String sqlState = "UPDATE model_weibo_item SET content ='"+item.getContent();
					sqlState += "'WHERE ID= '" + item.getID()+"'";
				    System.out.println(sqlState);
					preState = connection.prepareStatement(sqlState);
					preState.executeUpdate(sqlState);
				
					preState.execute("begin");
					preState.executeUpdate();
					preState.execute("commit");
			} catch (Exception e) {
				e.printStackTrace();

			} finally {
				DB_Manager.closeDB(connection, state, resultset);
			}
		
			
	
		}	
	
	/**
	 * Method:read
	 * @param ID 
	 * @return list of weibo
	 * */
	public ArrayList<WeiboItem> read(){
		connection = DB_Manager.getConnection();
		  String sqlstate ="SELECT * FROM model_weibo_item ORDER by ID DESC ";
		  ArrayList<WeiboItem> arraylist = new ArrayList<WeiboItem>();
		  try{
			  state = connection.createStatement();
			  resultset = state.executeQuery(sqlstate);
			  
			  while (resultset.next()){
				  WeiboItem item = new WeiboItem();
				  item.setID(resultset.getInt("ID"));
				  item.setContent(resultset.getString("content"));
				  item.setDate(resultset.getDate("date"));
				  item.setAuthor(resultset.getString("author"));
				  item.setReads(resultset.getInt("reads"));
				  arraylist.add(item);
			  }
			  return arraylist;
		  }catch(SQLException e){
			  e.printStackTrace();
			  return null;
		  }finally{
			  DB_Manager.closeDB(connection, state, resultset);
		  }
	}

		
	}


