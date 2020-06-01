import com.seecee.sc1911.pojo.HDept;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

/**
 * @author ${林锋鹏}
 * @Title: TestHibernate
 * @ProjectName Java
 * @Description: TODO
 * @date 2019/12/24 11:43
 */
public class TestHibernate {
    /*
 1，加载配置文件 会默认读取 根目录下的文件，固定好了 不能该文件名
 2，创建session工厂
 3，创建session
 4，开启事务
 5，执行持久化操作
 6，事务提交或回滚
 7，关闭资源
  */
    private static  Session session;
    private static Configuration conf;
    private static SessionFactory sf;
    Transaction tx;
@Test
 public void  insertTest(){
   HDept t=  new HDept();
    t.setId(201);
    t.setDeptname("乾坤大挪移");
    session.update(t);
    System.out.println("操作已完成");
}
@Test
public void quert(){
    /*
        session.get(HDept.class(代表查询数据的类型), id)
        get 和 load的区别：
        1 get 不支持延迟加载
        而load支持延迟加载，只有当使用的时候才会调用sql语句查询
        2 当查询数据不存在的时候，get 会返回null load 会抛出异常 objectnotfoundException 对象找不到异常

     */
    HDept hd = session.get(HDept.class, 1L);
    HDept load = session.load(HDept.class, 201L);
    System.out.println(hd);
    System.out.println(load);
}
@Test
public void hqlTest(){
    //通过session对象创建 参数是hql语句
    //hql是通过操作对象的
    //第一种方式：处理带参数的hql
  // Query q = session.createQuery("from HDept");

    Query q = session.createQuery("from HDept where deptname=?1 and id=?2");
    q.setParameter(1,"霍去病");
    q.setParameter(2,1);
        List<HDept> list = q.list();
    for (HDept a:list){
        System.out.println(a);
    }

}
@Test
public void hqltest(){
    /*
    第二种处理方式由于第一种：
        不用考虑顺序，命名参数可以重复。应用场景比如搜索 会根据各种情况任意搭配
        如果参数是个对象，命名参数可以根据对象自动传入

     */
    Query q = session.createQuery("from HDept where id=: a and deptname=: b");
    q.setParameter("a",201L);
    q.setParameter("b","乾坤大挪移");
    List list = q.list();
    for (Object a:list){
        System.out.println(a);
    }

}
@Before
public  void before(){
    conf= new Configuration().configure();
    sf = conf.buildSessionFactory();
    session = sf.openSession();
    tx= session.beginTransaction();

}
@After
public  void after(){
    //提交事务才会发送sql数据
    tx.commit();
    session.close();
}
}
