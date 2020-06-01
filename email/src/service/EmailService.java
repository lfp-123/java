package service;

import dao.emailUserDao;
import dao.mailboxDao;
import entity.mailbox;

import java.util.List;

/**
 * @author ${林锋鹏}
 * @Title: EmailService
 * @ProjectName Java
 * @Description: TODO
 * @date 2019/12/12 15:41
 */
public class EmailService {
   mailboxDao mailboxDao = new mailboxDao();
    public boolean addEmail(String fromname ,String toname,String title,String content){
        int i = mailboxDao.querySend(fromname,toname,title,content);
        int i1 = mailboxDao.querySendfj(toname, fromname, title, content);
        if(i>0&i1>0){
            return  true;
        }else {
            return false;
        }
    }
    public List<mailbox> queryFromEmail(String username){
        List<mailbox> mailboxes = mailboxDao.queryAllByFromname(username);
        return mailboxes;
    }
    public boolean del(String id){
        Integer ids = Integer.parseInt(id);
        boolean b = mailboxDao.deleteById(ids);
        return  b;
    }
}
