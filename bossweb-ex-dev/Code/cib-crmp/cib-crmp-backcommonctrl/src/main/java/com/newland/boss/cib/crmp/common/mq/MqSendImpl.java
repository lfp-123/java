package com.newland.boss.cib.crmp.common.mq;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.jms.*;

/**
 * MQ消息发送类
 */
public class MqSendImpl {
    private static final Log log = LogFactory.getLog(MqSendImpl.class);
    private static ThreadLocal<MqSendImpl> mqServiceThreadLocal
            = new ThreadLocal<>();

    private MqSendImpl() {

    }

    public static MqSendImpl create() {
        MqSendImpl mqSendImpl = mqServiceThreadLocal.get();
        if (mqSendImpl != null) {
            return mqSendImpl;
        }
        mqSendImpl = new MqSendImpl();
        mqServiceThreadLocal.set(mqSendImpl);
        return mqSendImpl;
    }

    /**
     * msg发送方法
     * CollectOfflineApp_MSG
     * CollectOfflineApp_MSG_TESTNG
     *
     * @param msg
     * @throws Exception
     */
    public boolean send(String msg, String mqQueueName) {
        Connection connection = null;
        Session session = null;
        Queue queue;
        MessageProducer producer;
        TextMessage textMessage = null;

        try {
            connection = MqPooledConnectionFactory.getPooledConnectionFactory().createConnection();
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            if ("testng".equals(msg)) {
                // testng用的消息队列
                queue = session.createQueue(ConstantMq.TESTNGMG);
            } else {
                queue = session.createQueue(mqQueueName);
            }
            producer = session.createProducer(queue);
            connection.start();
            textMessage = session.createTextMessage();
            textMessage.setText(msg);
            producer.send(textMessage);
            log.debug(session.toString() + "," + connection.getClientID());
            return true;
        } catch (Exception e) {
            log.error("send msg error", e);
            return false;
        } finally {
            if (textMessage != null) {
                try {
                    textMessage.clearBody();
                } catch (JMSException e) {
                    log.error("textMessage.clearBody", e);
                }
                try {
                    textMessage.clearProperties();
                } catch (JMSException e) {
                    log.error("textMessage.clearProperties", e);
                }
                textMessage = null;
            }
            if (session != null) {
                try {
                    session.close();
                } catch (JMSException e) {
                    log.error("session.close", e);
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (JMSException e) {
                    log.error("connection.close", e);
                }
            }
        }
    }

    public void sendMessage(String msg, String mqQueueName) {
        this.send(msg, mqQueueName);
    }
}
