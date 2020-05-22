package com.newland.boss.cib.crmp.common.mq;

import org.testng.Assert;

import javax.jms.*;

/**
 * Created by weixc on 2018-07-17.
 */
public class MqSendImplTest {

//    @Test
    public void testCreate() {
        MqSendImpl.create();
        Assert.assertEquals("1","1");

    }

//    @Test
    public void testSend() {
    }


//    @Test
    public void testSendMessage() throws Exception {
        MqPooledConnectionFactory.setMqAddr("tcp://10.1.0.206:61616");
        MqPooledConnectionFactory.init();
        MqSendImpl mqSend = MqSendImpl.create();
        mqSend.sendMessage("testng", ConstantMq.TESTNGMG);
        MqPooledConnectionFactory.stop();

        // 创建消息消费者。获取之前发送的消息。
        MqPooledConnectionFactory.init();

        Connection connection = MqPooledConnectionFactory.getPooledConnectionFactory().createConnection();
        connection.start();
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Destination destination = session.createQueue("CollectOfflineApp_MSG_TESTNG");
        MessageConsumer messageConsumer = session.createConsumer(destination);
        TextMessage message = (TextMessage) messageConsumer.receive(10000);
        System.out.println("收到消息：  " + message.getText());
        Assert.assertEquals(message.getText(), "testng");
        message.clearBody();
        message.clearProperties();
        session.close();
        connection.close();
        MqPooledConnectionFactory.stop();

    }
}