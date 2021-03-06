package zll.weibo4sa.model;

import java.sql.Timestamp;
import java.util.ArrayList;

import zll.weibo4sa.api.Observable;
import zll.weibo4sa.api.Observer;
import zll.weibo4sa.cache.RedisCacheClient;
import zll.weibo4sa.dao.DAOUser;
import zll.weibo4sa.dao.DAOWeiboItem;

/**
 * Model WeiboManager Manage all the basic weibo functions
 * 
 * @since 1.0
 * @version 1.0
 * */

public class WeiboManager implements Observable {

    private ArrayList<Observer> list;
    private ArrayList<WeiboItem> weiboList;
    private int currentWeibo = 0;
    private DAOWeiboItem dao;
    private DAOUser userdao;
    private RedisCacheClient client;

    public WeiboManager() {
        list = new ArrayList<Observer>();
        dao = new DAOWeiboItem();
        userdao = new DAOUser();
        weiboList = readWeibo();
        client = new RedisCacheClient();
    }

    @Override
    public void registerObserver(Observer o) {
        if (o != null) {
            list.add(o);
        }
    }

    @Override
    public void removeObserver(Observer o) {
        if (o != null) {
            list.remove(o);
        }
    }

    @Override
    public void notify(Object data) {
        for (int i = 0; i < list.size(); i++) {
            list.get(i).update(data);
        }
    }

    /**
     * Method : create a weibo
     * 
     * @param item
     * */
    public WeiboItem createWeibo(String content, User author) {

        WeiboItem item = new WeiboItem();
        item.setContent(content);
        item.setAuthor(author);
        item.setID(0);
        item.setDate(new Timestamp(System.currentTimeMillis()));
        item.setReads(0);

        dao.create(item); // excute DB

        Message msg = new Message();
        msg.setComponent(Message.COMPONENT_LOGGER);
        msg.setType(Message.TYPE_CREATE);
        msg.setWeiboItem(item);

        notify(msg);
        //TODO described
        //weiboList = readWeibo();
        return item;
    }

    /**
     * Method : read a list of weibo
     * */
    public ArrayList<WeiboItem> readWeibo() {
        currentWeibo = 0;
        return dao.read();

    }

    public WeiboItem readNext(boolean order) {
        // System.out.println(""+currentWeibo);

        Message msg = new Message();
        WeiboItem item = null;
        msg.setComponent(Message.COMPONENT_COUNTER);
        try {
            item = weiboList.get(currentWeibo);
        } catch (IndexOutOfBoundsException e) {
            // return null
        }

        msg.setWeiboItem(item);

        if (order) {
            currentWeibo++;
        } else {
            currentWeibo--;
        }

        notify(msg);

        return item;
    }

    public void deleteWeibo(WeiboItem item) {

        dao.delete(item.getID());
        Message msg = new Message();
        msg.setComponent(Message.COMPONENT_LOGGER);
        msg.setType(Message.TYPE_DELETE);
        msg.setWeiboItem(item);
        notify(msg);
    }

    public boolean updateWeibo(WeiboItem item) {

        dao.updateWeibo(item);
        Message msg = new Message();
        msg.setComponent(Message.COMPONENT_LOGGER);
        msg.setType(Message.TYPE_UPDATE);
        msg.setWeiboItem(item);
        notify(msg);
        // DB write
        weiboList = readWeibo();
        return true;

    }

    /**
     * method to read a weibo
     * 
     * @param id
     *            weibo id
     * @param useCache
     *            true if try to read weibo from redis
     * @return weibo item
     * */

    public WeiboItem readOne(int id, boolean useCache) {
        WeiboItem result = null;
        if (useCache) {
            result = client.FetchWeiboItem(id);
            if (result == null) {
                dao.readOne(id);
            }
        } else {
            dao.readOne(id);
        }
        return result;
    }

    private boolean updateUserCache() {        
        client.updateTopUsers(userdao.readTopN(5));
        return true;
    }
    
    private boolean updateWeiboCache(){
        return true;
    }
}