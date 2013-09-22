package zll.weibo4sa.api;

public interface Observable {

	public void registerObserver(Observer o);

	public void removeObserver(Observer o);

	public void notify(Object object, Object data);
}