package zll.weibo4sa.model;


public class Message{
	
    
    public static final int TYPE_CREATE = 0;
	public static final int TYPE_UPDATE = 1;
	public static final int TYPE_READ = 2;
	public static final int TYPE_DELETE = 3;
	public static final int COMPONENT_LOGGER = 0;
	public static final int COMPONENT_COUNTER = 1;
	
	//Attributes
	private int type;
	private WeiboItem WeiboItem;
	private int component;
	
	
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public WeiboItem getWeiboItem() {
		return WeiboItem;
	}
	public void setWeiboItem(WeiboItem WeiboItem) {
		this.WeiboItem = WeiboItem;
	}
	public int getComponent() {
		return component;
	}
	public void setComponent(int component) {
		this.component = component;
	}
	
	

}
