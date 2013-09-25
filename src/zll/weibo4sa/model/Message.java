package zll.weibo4sa.model;

public class Message {
	
	public static final int TYPE_CREATE = 0;
	public static final int TYPE_UPDATE = 1;
	public static final int TYPE_READ = 2;
	public static final int TYPE_DELETE = 3;
	
	//Attributes
	private int type;
	private WeiboItem mWeiboItem;
	
	
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public WeiboItem getmWeiboItem() {
		return mWeiboItem;
	}
	public void setmWeiboItem(WeiboItem mWeiboItem) {
		this.mWeiboItem = mWeiboItem;
	}
	
	

}
