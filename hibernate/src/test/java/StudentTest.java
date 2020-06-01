import com.seecee.sc1911.pojo.HStudent;
import com.seecee.sc1911.pojo.HTeacher;
import com.seecee.sc1911.until.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ${林锋鹏}
 * @Title: StudentTest
 * @ProjectName Java
 * @Description: TODO
 * @date 2019/12/29 17:00
 */
public class StudentTest {
    @Test
    public void manyTomany(){
        Session sessin = HibernateUtil.getSessin();
        Transaction tx = sessin.beginTransaction();

        HStudent stu = new HStudent();
        stu.setName("张三");
        HTeacher ht1 = sessin.get(HTeacher.class, 1L);//维护老师的关系
        HTeacher ht2 = sessin.get(HTeacher.class, 2L);//维护老师的关系
        HTeacher ht3 = sessin.get(HTeacher.class, 3L);//维护老师的关系
        List<HTeacher> hts = new ArrayList<>();
        hts.add(ht1);
        hts.add(ht2);
        hts.add(ht3);
        stu.setListTeacher(hts);
        sessin.save(stu);

        tx.commit();
        sessin.close();
    }
    @Test
    public void del(){
        Session sessin = HibernateUtil.getSessin();
        Transaction tx = sessin.beginTransaction();
        HStudent hStudent = sessin.get(HStudent.class, 333L);
        sessin.delete(hStudent);
        tx.commit();
        sessin.close();
    }
}
