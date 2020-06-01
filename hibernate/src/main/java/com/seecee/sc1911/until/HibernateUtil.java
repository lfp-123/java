package com.seecee.sc1911.until;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * @author ${林锋鹏}
 * @Title: HibernateUtil
 * @ProjectName Java
 * @Description: TODO
 * @date 2019/12/25 14:43
 */
public class HibernateUtil  {
    /*
    1，创建session
    2，关闭session
     */
    static SessionFactory sessionFactory;
    /*
    通过本地线程保证前后功能是一样的 保证一定要一个session
     */
    static   ThreadLocal<Session> t1 = new ThreadLocal<>();
    static {
        Configuration configure = new Configuration().configure();
        sessionFactory  = configure.buildSessionFactory();
    }
    /*
        通过本地线程，获取session
     */
    public static Session getSessin(){
        Session session = t1.get();
        if(session==null) {
            session = sessionFactory.openSession();
            t1.set(session);
        }
        return  session;
    }
    public  static  void close(){
        getSessin().close();
        t1.set(null);
    }

}
