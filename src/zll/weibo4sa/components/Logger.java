package zll.weibo4sa.components;

import java.util.Date;

import zll.weibo4sa.api.Observer;
import zll.weibo4sa.model.Message;
import zll.weibo4sa.model.WeiboItem;

public class Logger implements Observer{

	private Message msg;
	private WeiboItem item;
	private String who;
	private Date when;
	private String operation;
	private String log;
	
	@Override
	public void update(Object data) {
		//To change body of implemented methods use File | Settings | File Templates.
		//sql
		msg = (Message)data;
		item = msg.getmWeiboItem();
		who = item.getAuthor();
		when  = new Date();
		
		if(Message.TYPE_CREATE == msg.getType()){
			operation = "create a weibo";
		}else if(){
			
		}
		
		
		log =  who + when + operation;
		
		///DB LOG.>>>>db
		
		
		
		//具体功能
	}
}
