package zll.weibo4sa.components;

import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import zll.weibo4sa.api.Observer;
import zll.weibo4sa.db.DB_Manager;
import zll.weibo4sa.model.Message;
import zll.weibo4sa.model.WeiboItem;

/**
 * Component : Counter
 * component supporting counter for weibo items
 * @since 1.0
 * @version 1.0
 * */
public class Counter implements Observer{
	
	private Message msg ;
	private WeiboItem item;
	
	private Statement state = null;
	private Connection connection = null;

	
	/**
	 * Method: update
	 * @param data Message
	 * */
	@Override
	public void update(Object data) {
		
		msg = (Message)data;
		if(Message.COMPONENT_COUNTER == msg.getComponent()){
			item = msg.getWeiboItem();
			if(item != null){
				int id = item.getID();
				//actually this mode can be service for more complicated read counters
				int counter = item.getReads()+1;
				connection = DB_Manager.getConnection();

				String sqlState = "UPDATE model_weibo_item SET `reads`=" + counter;
				sqlState += " WHERE ID="+id;
//				System.out.println(sqlState); 
				
				try {
					state = connection.createStatement();
					state.execute("begin");
					state.executeUpdate(sqlState);
					state.execute("commit");
				
				} catch (SQLException e) {
					try {
						state.execute("rollback");
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
					e.printStackTrace();
				}
			}

		}
		
	}
}