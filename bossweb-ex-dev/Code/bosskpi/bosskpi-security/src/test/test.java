import org.testng.annotations.Test;

import static com.newland.boss.kpi.util.MD5Util.Encrypt;

/**
 * @author ${林锋鹏}
 * @Title: test
 * @ProjectName cib-crmp
 * @Description: TODO
 * @date 2020/4/21 17:10
 */
public class test {
    @Test
    public void test (){
        String encrypt = Encrypt("W%gowZ#t$8");
        System.out.println(encrypt);
        String encrypt1 = Encrypt("7D35B762488640ECFA43463FFE73BEFB");
        System.out.println(encrypt1);

    }
}
