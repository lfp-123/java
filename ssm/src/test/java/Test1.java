import com.seecen.ssm.mapper.OAdminMapper;
import com.seecen.ssm.pojo.OAdmin;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author ${林锋鹏}
 * @Title: Test
 * @ProjectName Java
 * @Description: TODO
 * @date 2020/1/17 16:39
 */
public class Test1{
    @Test
    public void a() throws IOException {
        InputStream rs = Resources.getResourceAsStream("mybatis.xml");
        SqlSessionFactory build = new SqlSessionFactoryBuilder().build(rs);
        SqlSession session = build.openSession();
        OAdminMapper mapper = session.getMapper(OAdminMapper.class);
        OAdmin oAdmin = new OAdmin();
        oAdmin.setAccount("admin");
        oAdmin.setPassword("123");
        OAdmin oAdmin1 = mapper.queryAccountPassword(oAdmin);
        System.out.println(oAdmin1);


        session.commit();
        rs.close();

    }
}
