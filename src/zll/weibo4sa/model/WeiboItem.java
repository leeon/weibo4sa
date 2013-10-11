package zll.weibo4sa.model;
import java.io.Serializable;
/******************************************************************************
 * Author: leeon
 * Date: 2013-09-27
 *
 * Data type for a item of Weibo
 ******************************************************************************/
import java.sql.Timestamp;

public class WeiboItem implements Serializable{

    private User author;
	private Timestamp date;
	private String content;
	private int ID;
	private int reads;
	
	public User getAuthor() {
		return author;
	}
	public void setAuthor(User author) {
		this.author = author;
	}
	public Timestamp getDate() {
		return date;
	}
	public void setDate(Timestamp date) {
		this.date = date;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public int getReads() {
		return reads;
	}
	public void setReads(int reads) {
		this.reads = reads;
	}
	
	
}
