package zll.weibo4sa.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import zll.weibo4sa.db.DB_Manager;
import zll.weibo4sa.model.User;
import zll.weibo4sa.model.WeiboItem;

/**
 * DAO DAOWeiboItem CURD for model weibo item
 * 
 * @since 1.0
 * @version 1.0
 * */
public class DAOWeiboItem {
    private Statement state = null;
    private PreparedStatement preState = null;
    private ResultSet resultset = null;
    private Connection connection = null;

    /**
     * Method: create
     * 
     * @param item
     * */
    public void create(WeiboItem item) {
        connection = DB_Manager.getConnection();
        
        try {
            //connection.setAutoCommit(false);
            state = connection.createStatement();
            String sqlState = "INSERT INTO `model_weibo_item` (`content`, `date`, `reads`, `author_id`) VALUES(?,?,?,?)";
            // System.out.println(sqlState);
            preState = connection.prepareStatement(sqlState);
            preState.setString(1, item.getContent());
            preState.setTimestamp(2, item.getDate());
            preState.setInt(3, item.getReads());
            preState.setInt(4, item.getAuthor().getID());
            
            preState.execute("begin");
            preState.executeUpdate();
            preState.execute("commit");
        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            DB_Manager.closeDB(connection, state, resultset);
        }

    }

    /**
     * Method:Delete
     * 
     * @param ID
     * */
    public void delete(int ID) {
        connection = DB_Manager.getConnection();
        try {
            state = connection.createStatement();
            String sqlState = "DELETE FROM model_weibo_item WHERE model_weibo_item.ID="
                    + ID;
            preState = connection.prepareStatement(sqlState);
            preState.executeUpdate(sqlState);

            preState.execute("begin");
            preState.executeUpdate();
            preState.execute("commit");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DB_Manager.closeDB(connection, state, resultset);
        }
    }

    /**
     * Method:update
     * 
     * @param item
     *            a weibo item info
     * */
    public void updateWeibo(WeiboItem item) {
        connection = DB_Manager.getConnection();
        try {
            state = connection.createStatement();
            String sqlState = "UPDATE model_weibo_item SET content ='"
                    + item.getContent();
            sqlState += "'WHERE ID= '" + item.getID() + "'";
            // System.out.println(sqlState);
            preState = connection.prepareStatement(sqlState);
            preState.executeUpdate(sqlState);

            preState.execute("begin");
            preState.executeUpdate();
            preState.execute("commit");
        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            DB_Manager.closeDB(connection, state, resultset);
        }

    }

    /**
     * Method:read
     * 
     * @param ID
     * @return list of weibo
     * */
    public ArrayList<WeiboItem> read() {
        connection = DB_Manager.getConnection();
        String sqlstate = "SELECT * FROM model_weibo_item weibo,model_user u WHERE weibo.author_id=u.id ORDER BY weibo.date DESC";
        ArrayList<WeiboItem> arraylist = new ArrayList<WeiboItem>();
        try {
            state = connection.createStatement();
            resultset = state.executeQuery(sqlstate);

            while (resultset.next()) {
                WeiboItem item = new WeiboItem();
                User u = new User();
                u.setID(resultset.getInt("u.id"));
                u.setName(resultset.getString("u.name"));
                u.setMail(resultset.getString("u.mail"));
                //TODO to keep temp safe
//                u.setPassword(resultset.getString("u.password"));
                item.setID(resultset.getInt("weibo.id"));
                item.setContent(resultset.getString("content"));
                item.setDate(resultset.getTimestamp("date"));
                item.setAuthor(u);
                item.setReads(resultset.getInt("reads"));
                arraylist.add(item);
            }
            return arraylist;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            DB_Manager.closeDB(connection, state, resultset);
        }
    }

    public WeiboItem readOne(int id ){
        connection = DB_Manager.getConnection();
        String sqlstate = "SELECT * FROM model_weibo_item weibo,model_user u WHERE weibo.author_id=u.id AND u.id="+id+" ORDER BY weibo.date DESC";
        WeiboItem item = null;
        try {
            state = connection.createStatement();
            resultset = state.executeQuery(sqlstate);

            while (resultset.next()) {
                item = new WeiboItem();
                User u = new User();
                u.setID(resultset.getInt("u.id"));
                u.setName(resultset.getString("u.name"));
                u.setMail(resultset.getString("u.mail"));
                //TODO to keep temp safe
//                u.setPassword(resultset.getString("u.password"));
                item.setID(resultset.getInt("weibo.id"));
                item.setContent(resultset.getString("content"));
                item.setDate(resultset.getTimestamp("date"));
                item.setAuthor(u);
                item.setReads(resultset.getInt("reads"));
                return item;
            }
            return item;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            DB_Manager.closeDB(connection, state, resultset);
        }  
        
    }
}
