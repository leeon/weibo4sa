package zll.weibo4sa.components;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;

import zll.weibo4sa.api.Observer;
import zll.weibo4sa.db.DB_Manager;
import zll.weibo4sa.model.AppLog;
import zll.weibo4sa.model.Message;
import zll.weibo4sa.model.WeiboItem;
/**
 * Component : Logger
 * component for LOG
 * @since 1.0
 * @version 1.0
 * */

public class Logger implements Observer{

	private Message msg;
	private WeiboItem item;
	private AppLog log;
	
	private Statement state = null;
	private PreparedStatement preState = null;
	private ResultSet resultset = null;
	private Connection connection = null;
	
	/**
	 * Method : createLog2db 
	 * @param log a log entity
	 * */
	public boolean createLog2db(AppLog log ){
		connection =  DB_Manager.getConnection(); 
		try {
			    state =connection.createStatement();
				String sqlState = "INSERT INTO app_log(date,user,operations,content) VALUES(?,?,?,?)";
				preState = connection.prepareStatement(sqlState);
				preState.setDate(1,log.getDate()); 
				preState.setString(2, log.getUser());
				preState.setString(3, log.getOperation());
				preState.setString(4,log.getContent());

				preState.execute("begin");
				preState.executeUpdate();
				preState.execute("commit");
				return true;

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			DB_Manager.closeDB(connection, state, resultset);
		}
		
	}
	
	/**
	 * Method: update the observer
	 * @param data Message
	 * */
	@Override
	public void update(Object data) {
		log = new AppLog();
		msg = (Message)data;
		if(Message.COMPONENT_LOGGER == msg.getComponent()){
			item = msg.getWeiboItem();
			log.setUser(item.getAuthor()); 
			log.setDate(new Date(System.currentTimeMillis()));// get current time
			
			if(Message.TYPE_CREATE == msg.getType()){
				log.setOperation("CREATE");
				log.setContent("create a weibo");
				
			}else if(Message.TYPE_READ == msg.getType()){
				log.setOperation("READ");
				log.setContent("read a weibo");
			}else if(Message.TYPE_DELETE == msg.getType()){
				log.setOperation("DELETE");
				log.setContent("delete a weibo");
			}else{// TYPE_UPDATE
				log.setOperation("UPDATE");
				log.setContent("update a weibo");
			
			}
			// write the log to db
		   createLog2db(log);
			
		}

	}
}
