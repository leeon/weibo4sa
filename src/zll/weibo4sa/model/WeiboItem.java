package zll.weibo4sa.model;

import java.util.Date;

public class WeiboItem {

	private String author;
	private Date date;
	private String content;
	private int ID;
	private int reads;
	
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
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
