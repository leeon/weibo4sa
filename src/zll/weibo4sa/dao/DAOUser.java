package zll.weibo4sa.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import zll.weibo4sa.db.DB_Manager;
import zll.weibo4sa.model.User;

/******************************************************************************
 * @author leeon
 * @version 2013-10-09
 * 
 *          operations for User.class
 ******************************************************************************/
public class DAOUser {
    private Statement state = null;
    private PreparedStatement preState = null;
    private ResultSet resultset = null;
    private Connection connection = null;

    /**
     * <p>
     * Read one user from datebase
     * </p>
     * 
     * @param name
     *            username
     * @return User
     * 
     * */
    public User readOne(String name) {

        connection = DB_Manager.getConnection();
        String sqlstate = "SELECT * FROM model_user WHERE name = '" + name
                + "'";
        User mUser = null;
        try {
            state = connection.createStatement();
            resultset = state.executeQuery(sqlstate);

            while (resultset.next()) {
                mUser = new User();
                mUser.setMail(resultset.getString("mail"));
                mUser.setName(resultset.getString("name"));
                mUser.setID(resultset.getInt("ID"));
                mUser.setPassword(resultset.getString("password"));
                return mUser;
            }
            return mUser;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            DB_Manager.closeDB(connection, state, resultset);
        }
    }

    
    /**
     * <p>
     * create a new user in database
     * </p>
     * 
     * @param user
     *            a User object
     * @return boolean
     *            if the op succeeds
     * 
     * */
    public boolean create(User user) {
        connection = DB_Manager.getConnection();
        try {
            state =connection.createStatement();
            String sqlState = "INSERT INTO `model_user` (`name`, `mail`,`password`) VALUES(?,?,?)";
            preState = connection.prepareStatement(sqlState);
            preState.setString(1, user.getName());
            preState.setString(2, user.getMail());
            preState.setString(3, user.getPassword());
            
            preState.execute("begin");
            preState.executeUpdate();
            preState.execute("commit");
    } catch (Exception e) {
        e.printStackTrace();

    } finally {
        DB_Manager.closeDB(connection, state, resultset);
    }
        return true;
    }
}
