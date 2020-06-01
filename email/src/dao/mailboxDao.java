package dao;


import entity.mailbox;
import sun.security.pkcs11.Secmod;
import util.DButil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author ${林锋鹏}
 * @Title: mailboxDao
 * @ProjectName Java
 * @Description: TODO
 * @date 2019/12/6 18:04
 */
public class mailboxDao {
    public List<mailbox> queryAll() {
            String sql = "select * from mailbox ";
            ResultSet rs = DButil.show(sql);
            List<mailbox> mail = new ArrayList<>();
            try {
                while (rs.next()) {
                    int id = rs.getInt("id");
                    String fromname = rs.getString("fromname");
                    String title = rs.getString("title");
                    String content = rs.getString("content");
                    int state = rs.getInt("state");
                    String toname = rs.getString("toname");
                    Date date = rs.getDate("date");
                    mailbox mails = new mailbox(id,fromname,title,content,state,toname,date);
                    mail.add(mails);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            DButil.close(DButil.rs, DButil.pare, DButil.conn);
            return mail;
    }
    //通过收件人判断是不是自己的邮件内容
    public List<mailbox> queryAllByToname(String username) {
        String sql = "select * from mailbox where toname = ? ";
        ResultSet rs = DButil.show(sql,username);
        List<mailbox> mail = new ArrayList<>();
        try {
            while (rs.next()) {
                int id = rs.getInt("id");
                String title = rs.getString("title");
                String content = rs.getString("content");
                mailbox mails = new mailbox(id,title, content);
                mail.add(mails);
            }
        } catch (SQLException e){
            e.printStackTrace();
        }

        DButil.close(DButil.rs, DButil.pare, DButil.conn);
        return mail;
    }
    public List<mailbox> queryAllByFromname(String username) {
        String sql = "select * from mailbox where fromname = ? ";
        ResultSet rs = DButil.show(sql,username);
        List<mailbox> mail = new ArrayList<>();
        try {
            while (rs.next()) {
                int id = rs.getInt("id");
                String title = rs.getString("title");
                String content = rs.getString("content");
                mailbox mails = new mailbox(id,title, content);
                mail.add(mails);
            }
        } catch (SQLException e){
            e.printStackTrace();
        }

        DButil.close(DButil.rs, DButil.pare, DButil.conn);
        return mail;
    }
    public mailbox queryByTitle(int id) {
        String sql = "select * from mailbox  where id =?";
            ResultSet rs = DButil.show(sql,id);
            mailbox mails= null;
            try {
                while (rs.next()) {
                    int ids = rs.getInt("id");
                    String fromname = rs.getString("fromname");
                    String titles = rs.getString("title");
                    String content = rs.getString("content");
                    int state = rs.getInt("state");
                    String toname = rs.getString("toname");
                    Date date = rs.getDate("createdate");
                    mails = new mailbox(ids,fromname,titles,content,state,toname,date);

                }
            } catch (SQLException e) {
                e.printStackTrace();
        }

        DButil.close(DButil.rs, DButil.pare, DButil.conn);
        return mails;
    }
    public int querySend(String fromname,String toname,String title,String content){
        String sql = "insert into mailbox values(id_seq.nextval,?,?,?,0,?,sysdate)";
        int update = DButil.update(sql, fromname, title, content, toname);
        return  update;
    }

    public int querySendfj(String toname,String fromname,String title,String content){
        String sql = "insert into mailboxfj values(id_seq.nextval,?,?,?,0,?,sysdate)";
        int update = DButil.update(sql, toname, title, content, fromname);
        return  update;
    }
    public boolean deleteById(Integer id){
        String sql = "delete from mailbox where id =?";
        int update = DButil.update(sql, id);
        if(update>0){
            return  true;
        }else {
            return  false;
        }
    }

}
