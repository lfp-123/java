package com.sc.spmvc.until;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;


/**
 * @author ${林锋鹏}
 * @Title: MybatisUtil
 * @ProjectName Java
 * @Description: TODO
 * @date 2020/1/8 14:20
 */
public class MybatisUtil {
    static   SqlSessionFactory sf;
    static {
        try (InputStream is = Resources.getResourceAsStream("mybatis.xml")) {
         sf = new SqlSessionFactoryBuilder().build(is);
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    static  ThreadLocal<SqlSession> t1 = new ThreadLocal<>();
    public static SqlSession  getSession()  {
        SqlSession session = t1.get();
        if(session==null){
            session = sf.openSession();
        }
        return session;
    }
    public static void close(){
        getSession().close();
        t1.set(null);
    }
}
