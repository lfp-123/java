import com.sc.spring.controller.UserController;
import com.sc.spring.pojo.User;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author ${林锋鹏}
 * @Title: TestSpring
 * @ProjectName Java
 * @Description: TODO
 * @date 2020/1/12 15:00
 */
public class TestSpring {
    @Test
    public void testIOC(){
        //加载Spring 配置文件 ac就是springIOC容器的对象
        ApplicationContext ac =
                new ClassPathXmlApplicationContext("applicationContext.xml");
        UserController uc =(UserController) ac.getBean("uc");
        System.out.println("模拟");
        uc.login();


    }
    @Test
    public void testUser(){
        ClassPathXmlApplicationContext ac =
                new ClassPathXmlApplicationContext("applicationContext.xml");
        User user =(User) ac.getBean("user");
        System.out.println(user);
    }
    @Test
    public void testSprings() {
        ClassPathXmlApplicationContext ac = new ClassPathXmlApplicationContext("applicationConfig");
        UserController user = (UserController) ac.getBean("uc");
        user.login();
    }
}
