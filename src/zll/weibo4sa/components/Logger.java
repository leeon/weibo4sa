package zll.weibo4sa.components;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Session;

import org.apache.activemq.ActiveMQConnectionFactory;

import zll.weibo4sa.api.Observer;
import zll.weibo4sa.model.AppLog;
import zll.weibo4sa.model.Message;
import zll.weibo4sa.model.WeiboItem;

/**
 * Component : Logger component for LOG
 * 
 * @since 1.0
 * @version 1.0
 * */

public class Logger implements Observer {

    private static final String HOST = "tcp://localhost:61616";
    private static final String QUEUE_NAME = "log4weibo4sa";

    private Message msg;
    private WeiboItem item;
    private AppLog log;
    private Connection connection;
    private Session session;
    private Destination destination;
    private MessageProducer producer;
    private ConnectionFactory connectionFactory;

    /**
     * Method: update the observer
     * 
     * @param data
     *            Message
     * */
    @Override
    public void update(Object data) {
        log = new AppLog();
        msg = (Message) data;
        if (Message.COMPONENT_LOGGER == msg.getComponent()) {
            item = msg.getWeiboItem();
            log.setUser(item.getAuthor().getName());
            log.setDate(new Timestamp(System.currentTimeMillis()));// get current time 
            
            if (Message.TYPE_CREATE == msg.getType()) {
                log.setOperation("CREATE");
                log.setContent("create a weibo");

            } else if (Message.TYPE_READ == msg.getType()) {
                log.setOperation("READ");
                log.setContent("read a weibo");
            } else if (Message.TYPE_DELETE == msg.getType()) {
                log.setOperation("DELETE");
                log.setContent("delete a weibo");
            } else {// TYPE_UPDATE
                log.setOperation("UPDATE");
                log.setContent("update a weibo");

            }

            // ActiveMQ send message
            try {
                initMQSender();
                connection.start();
                ObjectMessage message = session
                        .createObjectMessage((Serializable) log);
                producer.send(message);
                session.commit();
                connection.close();
            } catch (JMSException e) {
                // TODO Auto-generated catch block
                System.err.println("Sending message to QUEUE: "+QUEUE_NAME+" FAILED!");
            }

        }

    }

    /**
     * initialize the MQ Sender
     * */
    private final void initMQSender() throws JMSException {
        connectionFactory = new ActiveMQConnectionFactory(
                ActiveMQConnectionFactory.DEFAULT_USER,
                ActiveMQConnectionFactory.DEFAULT_PASSWORD, HOST);
        connection = connectionFactory.createConnection();
        session = connection.createSession(Boolean.TRUE,
                Session.AUTO_ACKNOWLEDGE);
        destination = session.createQueue(QUEUE_NAME);
        producer = session.createProducer(destination);
        producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);

    }

}
