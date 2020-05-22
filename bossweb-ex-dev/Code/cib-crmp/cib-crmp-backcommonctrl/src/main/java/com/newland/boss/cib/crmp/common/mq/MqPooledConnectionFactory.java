package com.newland.boss.cib.crmp.common.mq;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.pool.PooledConnectionFactory;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


/**
 * 链接工厂管理类
 * 自己工厂定义成了单例模式，连接池是静态块进行初始化，具体实现自己看着办
 */
public class MqPooledConnectionFactory {

    private static final Log log = LogFactory.getLog(MqPooledConnectionFactory.class);

    private MqPooledConnectionFactory() {

    }

    private static String mqAddr;
    private static PooledConnectionFactory pooledConnectionFactory;

    public static void setMqAddr(String mq) {
        mqAddr = mq;
    }

    public static void init() {
        try {
            // 需要创建一个链接工厂然后设置到连接池中
            ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory();
            activeMQConnectionFactory.setUserName(ActiveMQConnection.DEFAULT_USER);
            activeMQConnectionFactory.setPassword(ActiveMQConnection.DEFAULT_PASSWORD);
            activeMQConnectionFactory.setBrokerURL(mqAddr);
            pooledConnectionFactory = new PooledConnectionFactory(activeMQConnectionFactory);
        } catch (Exception e) {
            log.error("MqPooledConnectionFactory init error", e);
        }
    }


    /**
     * 获得链接池工厂
     */
    public static PooledConnectionFactory getPooledConnectionFactory() {
        return pooledConnectionFactory;
    }

    /**
     * 停止链接
     */
    public static void stop() {
        if (pooledConnectionFactory != null) {
            pooledConnectionFactory.stop();
        }
    }
}
