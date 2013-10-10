package test;


import zll.weibo4sa.api.Observer;
import zll.weibo4sa.components.AuthClient;
import zll.weibo4sa.components.Counter;
import zll.weibo4sa.components.Logger;
import zll.weibo4sa.model.User;
import zll.weibo4sa.model.WeiboItem;
import zll.weibo4sa.model.WeiboManager;

public class Main {
	public static void main(String [] args){
	    
      WeiboManager m = new WeiboManager();
      Observer mLogger = new Logger(); 
      Observer mCounter = new Counter();
      
      m.registerObserver(mLogger);
      m.registerObserver(mCounter);
      
      AuthClient auth  = new AuthClient();
      User u = auth.getLoginUser("liyang", "123");
      System.out.println(u.getName());
      
      m.createWeibo("sadaadasdaa111111", u);
      WeiboItem item = m.readNext(true);
      System.out.println(item.getContent());
      
      
	}

}
