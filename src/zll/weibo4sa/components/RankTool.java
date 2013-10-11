package zll.weibo4sa.components;

import java.util.ArrayList;

import zll.weibo4sa.dao.DAOUser;

/******************************************************************************
 * @author leeon
 * @version 2013-10-11
 * 
 *          <p>
 *          As a component of weibo4sa , provide all the ranking service
 *          </p>
 ******************************************************************************/
public class RankTool {

    /**
     * rank all the user by total weibo reads
     * @param N select N user
     * */
    public static ArrayList<Integer> getTopNUser(int N){
        DAOUser dao = new DAOUser();
        ArrayList<Integer> userList = dao.readTopN(N);
        for(int i =0;i < userList.size();i++){
            System.out.println(userList.get(i));
        }
         
        return null;

    }
    
    public static void main(String[] args){
        getTopNUser(5);
    }
}
