package zll.weibo4sa.cache;

import java.sql.Timestamp;
import java.util.ArrayList;

import redis.clients.jedis.Jedis;
import zll.weibo4sa.components.AuthClient;
import zll.weibo4sa.components.SerializeUtil;
import zll.weibo4sa.model.User;
import zll.weibo4sa.model.WeiboItem;

/******************************************************************************
 * @author leeon
 * @version 2013-10-10
 * 
 *          <p>
 *          A client for user to login
 *          </p>
 ******************************************************************************/
public class RedisCacheClient {

    private Jedis jedis;

    public RedisCacheClient() {
        jedis = new Jedis("localhost");
    }

    /**
     * Push one weibo item intp redis cache<br>
     * In the form of weibo:1001:content ["some text"]
     * 
     * @param WeiboItem
     * @return boolean if the push succeed;
     * */
    public boolean PushOneWeiboItem(WeiboItem item) {
        int id = item.getID();
        System.out.println(id);
        String keyStr = "weibo:" + id + ":";
        jedis.set(keyStr.getBytes(), SerializeUtil.serialize(item));
        return true;
    }

    /**
     * Push a set of weibo items intp redis cache<br>
     * In the form of weibo:1001:content ["some text"]
     * 
     * @param ArrayList
     * @return boolean if the push succeed;
     * */
    public boolean PushWeiboSet(ArrayList<WeiboItem> list) {
        WeiboItem item;
        for (int i = 0; i < list.size(); i++) {
            item = list.get(i);
            this.PushOneWeiboItem(item);
        }
        return true;
    }

    /**
     * read a weibo from redis
     * 
     * @param id
     * @return weibo item   null if not in
     * */
    public WeiboItem FetchWeiboItem(int id) {
        String keyStr = "weibo:" + id + ":";
        byte[] weibobyte = jedis.get(keyStr.getBytes());
        WeiboItem item = (WeiboItem) SerializeUtil.unserialize(weibobyte);
        
        return item;
    }

    public boolean updateTopUsers(ArrayList<Integer> list){
        jedis.del("top:user"); //TODO better way?
        for (int i =0;i<list.size();i++){
            jedis.sadd("top:user", list+"");
        }
        return true;
    }
    
    // Test for Redis Client
    public static void main(String[] args) {
        RedisCacheClient client = new RedisCacheClient();
        WeiboItem item = new WeiboItem();
        AuthClient auth = new AuthClient();
        User u = auth.getLoginUser("liyang", "123");

        item.setAuthor(u);
        item.setContent("测试redis");
        item.setDate(new Timestamp(System.currentTimeMillis()));

        client.PushOneWeiboItem(item);

        System.out.println(client.FetchWeiboItem(3).getContent());

    }
}
