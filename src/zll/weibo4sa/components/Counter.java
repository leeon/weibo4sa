package zll.weibo4sa.components;

import com.mysql.jdbc.Connection;

import zll.weibo4sa.api.Observer;
import zll.weibo4sa.db.DB_Manager;
import zll.weibo4sa.model.Message;
import zll.weibo4sa.model.WeiboItem;

public class Counter implements Observer{
	
	private Message msg ;
	private WeiboItem item;
	private int id;
	@Override
	public void update(Object data) {
		
		msg = (Message)data;
		item = msg.getmWeiboItem();
		id = item.getID();
		
		
		
		//+1 >>>>db
		
		
		//To change body of implemented methods use File | Settings | File Templates.
	}
}