package entity;

import dao.emailUserDao;
import dao.mailboxDao;

/**
 * @author ${林锋鹏}
 * @Title: test
 * @ProjectName Java
 * @Description: TODO
 * @date 2019/12/6 23:06
 */
public class test {
    public static void main(String[] args) {
        mailboxDao mailboxDao = new mailboxDao();
        int i = mailboxDao.querySendfj("1111", "admin", "ssss", "sssss");
        System.out.println(i);
        int a =0;
        int b=a++;
        System.out.println(a+" "+b);
        String s = null;
        if( (s!=null)&&(s.length()>0) ){

        }

    }
}
