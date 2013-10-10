package zll.weibo4sa.components;

import zll.weibo4sa.dao.DAOUser;
import zll.weibo4sa.model.User;

/******************************************************************************
 * @author leeon
 * @version 2013-10-09
 * 
 *          <p>
 *          A client for user to login
 *          </p>
 ******************************************************************************/
public class AuthClient {

    private User mUser;
    private DAOUser mDAOUser;

    public AuthClient() {
        mDAOUser = new DAOUser();
    }

    /**
     * <p>
     * Method: return a Login User
     * </p>
     * 
     * @param username
     * @param password
     * @return null if not success
     * */
    public User getLoginUser(String username, String password) {
        mUser = mDAOUser.readOne(username);

        if (mUser == null) {
            return null; // user not exists
        }

        if (password.equals(mUser.getPassword())) {
            return mUser;
        }
        return null; // password doesn't match
    }

}
