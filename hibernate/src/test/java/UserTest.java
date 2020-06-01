import com.seecee.sc1911.pojo.HDept;
import com.seecee.sc1911.pojo.HRole;
import com.seecee.sc1911.pojo.HUser;
import com.seecee.sc1911.pojo.HUserinfo;
import com.seecee.sc1911.until.HibernateUtil;
import org.hibernate.LockOptions;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * @author ${林锋鹏}
 * @Title: UserTest
 * @ProjectName Java
 * @Description: TODO
 * @date 2019/12/27 11:00
 */
public class UserTest {
    @Test
    public void manyToOne(){
        Session sessin = HibernateUtil.getSessin();
        Transaction tx = sessin.beginTransaction();
        HUser u = sessin.get(HUser.class, 1L);
        System.out.println(u);
        System.out.println(u.getDept());


    }
    //测试部门把部门对应的员工查询出来
    @Test
    public void oneToMany(){
        Session sessin = HibernateUtil.getSessin();
        Transaction tx = sessin.beginTransaction();
        HDept hDept = sessin.get(HDept.class, 201L);
        System.out.println(hDept);
        System.out.println(hDept.getListUser());
        tx.commit();
        sessin.close();
    }
    @Test
    public void manyToOneadd(){
        Session sessin = HibernateUtil.getSessin();
        Transaction tx = sessin.beginTransaction();
        HUser hUser = new HUser();
        hUser.setUsername("迪丽热巴");
        hUser.setPassword("12adggg");
        hUser.setCreatetime(new Date());
        //还要新增部门对象。所以查出一个部门进行赋值
        HDept hDept = sessin.get(HDept.class, 1L);
        //设置部门
        hUser.setDept(hDept);
        sessin.save(hUser);
        tx.commit();
        sessin.close();
    }
    @Test
    public void oneToManyAdd(){
        Session sessin = HibernateUtil.getSessin();
        Transaction tx = sessin.beginTransaction();
        HDept hDept = new HDept();
        hDept.setDeptname("大学");
        //做了关联映射之后 ，关联的属性也要赋值
        List<HUser> list = new ArrayList<>();
        //根据id来查出user
        HUser user1 = sessin.get(HUser.class, 324L);
        list.add(user1);
        hDept.setListUser(list);
        sessin.save(hDept);
        tx.commit();
        sessin.close();

    }
    //级联操作 把对应的员工也删除
    @Test
    public void del(){
        Session sessin = HibernateUtil.getSessin();
        Transaction tx = sessin.beginTransaction();
        HDept hDept = sessin.get(HDept.class, 11L);

        sessin.delete(hDept);

    }

    @Test
    public void queryInfo(){
        Session sessin = HibernateUtil.getSessin();
        Transaction tx = sessin.beginTransaction();
        HUser hUser = sessin.get(HUser.class, 324L);
        System.out.println(hUser);
        System.out.println(hUser.getInfo());
        HUserinfo in = sessin.get(HUserinfo.class, 324L);
        System.out.println(in);
        System.out.println(in.getUser());
        tx.commit();
        sessin.close();
    }
    @Test
    public void  addOneToOne(){
        Session sessin = HibernateUtil.getSessin();
        Transaction tx = sessin.beginTransaction();
        HUser hUser = new HUser();
        hUser.setUsername("妖王");
        hUser.setPassword("123");

        HDept hDept = sessin.get(HDept.class, 327L);
        hUser.setDept(hDept);

        HUserinfo hUserinfo = new HUserinfo();
        hUserinfo.setAge(99L);
        hUserinfo.setSex("1");
        hUserinfo.setMess("测试！！！！！");
        hUserinfo.setUser(hUser);
        hUser.setInfo(hUserinfo);
        sessin.save(hUser);
        tx.commit();
        sessin.close();


    }
    @Test
    public void delOneToOne(){
        Session sessin = HibernateUtil.getSessin();
        Transaction tx = sessin.beginTransaction();

        HUser hUser = sessin.get(HUser.class, 329L);
        sessin.delete(hUser);


        tx.commit();
        sessin.close();

    }
    @Test
    public void test(){
        Session sessin = HibernateUtil.getSessin();
        HUser hRole = sessin.get(HUser.class, 1L);
        HUser hRole1 = sessin.get(HUser.class, 1L, LockOptions.UPGRADE);
        HibernateUtil.close();


    }
    @Test
    public void showOneUser(){
        System.out.println(HibernateUtil.getSessin().get(HUser.class,1L));
        System.out.println(HibernateUtil.getSessin().get(HUser.class,1L).getUsername());

    }

}
