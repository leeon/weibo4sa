package test;

import java.util.Random;

import zll.weibo4sa.components.AuthClient;
import zll.weibo4sa.model.User;
import zll.weibo4sa.model.WeiboManager;

public class TestActivemq {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        testRandomCreateWeibo(12,100);
    }

    /**
     * select N of M users to post a weibo
     * @param M means range of user
     * @param N means select N user
     * */
    public static void testRandomCreateWeibo(int N, int M) {
        AuthClient auth = new AuthClient();
        WeiboManager manager = new WeiboManager();
        Random random = new Random();
        for (int i = 0; i < N; i++) {
            int userID = random.nextInt(M);
            String name = "user"+userID;
            String password = "password"+userID;
            User user = auth.getLoginUser(name, password);
            manager.createWeibo("test weibo content by "+userID+4, user); //4 is offset
            System.out.println(userID);
        }
    }
}
