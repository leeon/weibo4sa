package zll.weibo4sa.model;

import java.io.Serializable;
import java.sql.Timestamp;


public class AppLog implements Serializable{

    private static final long serialVersionUID = 1L;
    private String content;
	private String user;
	private Timestamp date;
	private String operation;

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public Timestamp getDate() {
		return date;
	}

	public void setDate(Timestamp date) {
		this.date = date;
	}

	public String getOperation() {
		return operation;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}

}
