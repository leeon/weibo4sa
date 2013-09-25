package test;

import java.util.ArrayList;

import zll.weibo4sa.api.Observer;
import zll.weibo4sa.components.Counter;
import zll.weibo4sa.components.Logger;
import zll.weibo4sa.model.WeiboItem;
import zll.weibo4sa.model.WeiboManager;

public class TestCase1 {
	public static void main(String[] args){
		System.out.println("rhis s");
		
		WeiboManager m = new WeiboManager();
		Observer mLogger = new Logger(); 
		Observer mCounter = new Counter();
		
		m.registerObserver(mLogger);
		m.registerObserver(mCounter);
		
		//m.createWeibo("我的第6条微博","leeon");
	
		WeiboItem temp = m.readNext(true);
		temp.setContent("更新的微博");
		m.updateWeibo(temp);
	}
}
