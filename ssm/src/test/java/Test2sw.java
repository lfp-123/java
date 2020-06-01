import com.seecen.ssm.service.impl.AdminServiceImpl;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

/**
 * @author ${林锋鹏}
 * @Title: Test2sw
 * @ProjectName Java
 * @Description: TODO
 * @date 2020/2/5 11:38
 */
@ContextConfiguration(locations = "classpath:spring.xml")
public class Test2sw {
    @Autowired
    public AdminServiceImpl as;

    @Test
    public void test(){
        
    }
}

