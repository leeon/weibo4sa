package zll.weibo4sa.model;

import java.io.Serializable;

/******************************************************************************
 * Author: Li Yang Date: 2013-10-09
 * 
 * Data type for User Model
 ******************************************************************************/

public class User implements Serializable{
    private int ID;
    private String name;
    private String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    private String mail;

    public int getID() {
        return ID;
    }

    public void setID(int iD) {
        ID = iD;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }
}
