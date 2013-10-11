package test;

import zll.weibo4sa.components.AuthClient;
import zll.weibo4sa.model.User;
import zll.weibo4sa.model.WeiboManager;

public class TestUser {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        testInsertUser();


    }
    
    
    public static void testTopUsers(){
        WeiboManager manager = new WeiboManager();
        
    }
    public static void testInsertUser(){
        AuthClient client  = new AuthClient();
        // Add 1000 new users to the db
        for (int i = 10; i < 10000; i++) {
            User u = new User();
            u.setMail("user"+i+"@test.com");
            u.setName("user"+i);
            u.setPassword("password"+i);
            
            client.registerUser(u);
            System.out.println("add user "+u.getName());
        }
    }

}
