import com.seecee.sc1911.pojo.HDept;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.NativeQuery;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

/**
 * @author ${林锋鹏}
 * @Title: TestSqlHIbernate
 * @ProjectName Java
 * @Description: TODO
 * @date 2019/12/25 11:05
 */
public class TestSqlHIbernate {
    Configuration conf;
    SessionFactory s;
    Session session;
    Transaction tx;
    @Before
    public void a(){
        conf =   new Configuration().configure();
        s =  conf.buildSessionFactory();
        session = s.openSession();
      tx  = session.beginTransaction();

    }
    @After
    public void b(){
        tx.commit();
        session.close();
    }
    @Test
    public void querySql(){
        NativeQuery sqlQuery = session.createSQLQuery("SELECT *from H_DEPT WHERE ID > 1");
        sqlQuery.addEntity(HDept.class);
        List<HDept> list = sqlQuery.list();
        for ( HDept o : list) {
            System.out.println(o);
        }
        /*
            一级缓存是属于session 级别缓存 当第一次查询数据库时
            会查旬数据库，同时会将对象存入一级缓存
            下一次如果需要相同的对象 同时也会将对象存入一级缓存

         */
    }
    @Test
    public void testQusery(){
        HDept hDept = session.get(HDept.class, 1L);
        hDept.setDeptname("修改");
        session.update(hDept);
        /*
            也会先查询在更新 设计到持久状态的更新的特点。
         */
    }

}

