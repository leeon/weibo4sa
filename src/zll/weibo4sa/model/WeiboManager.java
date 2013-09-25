package zll.weibo4sa.model;

import java.util.ArrayList;

import zll.weibo4sa.api.Observable;
import zll.weibo4sa.api.Observer;

public class WeiboManager implements Observable{
	
	// list 
	@Override
	public void registerObserver(Observer o) {
		//To change body of implemented methods use File | Settings | File Templates.
		
	}

	@Override
	public void removeObserver(Observer o) {
		//To change body of implemented methods use File | Settings | File Templates.
	}

	@Override
	public void notify(Object obeserver, Object data) {
		//To change body of implemented methods use File | Settings | File Templates.
	}
	
	//
	
	public WeiboItem createWeibo(String content,String author){
		//
		return new WeiboItem();
	}
	
	public void deleteWeibo(int ID){
		//
		
	}
	public ArrayList<WeiboItem> readWeibo(){
		
		return new ArrayList<WeiboItem>();
	}
	
	public WeiboItem updateWeibo(int ID){
		return new WeiboItem();
	}
}