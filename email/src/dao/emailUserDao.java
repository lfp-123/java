package dao;

import entity.emailUser;
import util.DButil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author ${林锋鹏}
 * @Title: emailUserDao
 * @ProjectName Java
 * @Description: TODO
 * @date 2019/12/6 15:18
 */
public class emailUserDao {

   public emailUser queryByUsernamePassword(String username,String password){
       emailUser eu =null;
       String sql = "select * from emails where username = ? and password = ?";
       ResultSet show = DButil.show(sql,username,password);
       try {
           while (show.next()){
               String username1= show.getString("username");
               String password1= show.getString("password");
               String email = show.getString("email");
               eu = new emailUser(username1,password1,email);
           }
       } catch (SQLException e) {
           e.printStackTrace();
       }
        DButil.close(DButil.rs,DButil.pare,DButil.conn);
       return  eu;
   }

   public int queryInset(String user,String password,String email){
       String sql = "insert into emails values(?,?,?)";
       int update = DButil.update(sql, user, password, email);
       return  update;
   }
   public List<emailUser> queryAll(){
       ArrayList<emailUser> ema = new ArrayList<>();
       String sql = "select * from emails";
       ResultSet show = DButil.show(sql);
       try {
           while (show.next()){
               String username1= show.getString("username");
               String password1= show.getString("password");
               String email = show.getString("email");
               emailUser eu = new emailUser(username1,password1,email);
               ema.add(eu);
           }
       } catch (SQLException e) {
           e.printStackTrace();
       }
       DButil.close(DButil.rs,DButil.pare,DButil.conn);
       return  ema;
   }
}
