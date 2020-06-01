package dao;

import entity.EUser;
import entity.Person;
import entity.User;
import sun.nio.cs.ext.EUC_CN;
import util.DButil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author ${林锋鹏}
 * @Title: UserDao 针对用户的数据访问层 负责执行持久化操作（CRUD）
 * @ProjectName Java
 * @Description: TODO
 * @date 2019/12/5 15:32
 */
public class UserDao {
    /*
    查询
     */
    public User queryByUsernamePassword(String username, String password){
        User u =null;
        String sql = "select * from users where username = ? and password = ?";
        ResultSet rs = DButil.show(sql, username, password);

        try {
            while (rs.next()){
                int id = rs.getInt("id");
                String us = rs.getString("username");
                String ps = rs.getString("password");
                Date date = rs.getDate("birthday");
                String sex = rs.getString("sex");
                int age = rs.getInt("age");
                u=new User(id,us,ps,date,sex,age);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        DButil.close(DButil.rs,DButil.pare, DButil.conn);
        return  u;
    }
    /*
    查询所有用户信息
     */
    public List<User> queryAll(){
        String sql="select * from users";
        ResultSet rs = DButil.show(sql);
        List<User> users = new ArrayList<>();
        try {
            while (rs.next()){
                int id = rs.getInt("id");
                String us = rs.getString("username");
                String ps = rs.getString("password");
                Date date = rs.getDate("birthday");
                String sex = rs.getString("sex");
                int age = rs.getInt("age");
                User user=new User(id,us,ps,date,sex,age);
                users.add(user);
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        DButil.close(DButil.rs,DButil.pare, DButil.conn);
        return users;
    }
    /*
    根据id
     */
    public User queryByid(String i){
        Integer ids = Integer.valueOf(i);
        String sql="select * from users where id =?" ;
        ResultSet rs = DButil.show(sql,ids);
       User user =null;
        try {
            while (rs.next()){
                int id = rs.getInt("id");
                String us = rs.getString("username");
                String ps = rs.getString("password");
                Date date = rs.getDate("birthday");
                String sex = rs.getString("sex");
                int age = rs.getInt("age");
                user=new User(id,us,ps,date,sex,age);

            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        DButil.close(DButil.rs,DButil.pare, DButil.conn);
        return  user;
    }
    /*
        通过id来修改数据
     */
    public int updateById(Integer id,String username,String password,String date,String sex,Integer age){
            String sql = "update users set username =?,password=?,birthday= to_date(?,'yyyy-MM-dd'),sex=?,age=? where id =? ";
        int update = DButil.update(sql, username, password,date, sex, age, id);
        return update;

    }
    /*'
    新增
     */
    public int insertUser(String username,String password,String date,String sex,Integer age){
        String sql ="insert into users values (id_seq.nextval,?,?,to_date(?,'yyyy-MM-dd'),?,?)";
        int update = DButil.update(sql, username, password, date, sex, age);

        return  update;
    }
    public int deleteUserById (Integer id){
        String sql = "delete from users where id =? ";
        int update = DButil.update(sql, id);
        return update;
    }
    public int register(String username,String password,String path){
        String sql = "insert into person(username,password,head) values (?,?,?)";
        int update = DButil.update(sql, username, password, path);
        return  update;

    }
    public EUser login(String uu, String pw){
        EUser person =null;
        String sql ="select * from person where username=? and password =?";
        ResultSet rs = DButil.show(sql, uu, pw);
        try {
            while (rs.next()){
                String username = rs.getString("username");
                String password = rs.getString("password");
                String head = rs.getString("head");
                person = new EUser(username,password,head);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return  person;
    }
    public List<EUser> queryListEUser(){
        EUser person;
        List<EUser> eUsers = new ArrayList<>();
        String sql ="select * from person ";
        ResultSet rs = DButil.show(sql);
        try {
            while (rs.next()){
                String username = rs.getString("username");
                String password = rs.getString("password");
                String head = rs.getString("head");
                person = new EUser(username,password,head);
                eUsers.add(person);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return eUsers;

    }
    //查询用户名是否存在
    public boolean checkUser(String username){
        String sql = "select * from person where username=?";
        ResultSet show = DButil.show(sql, username);
        boolean index =false;
        try {
            if(show.next()){
                index= true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        DButil.close(DButil.rs,DButil.pare,DButil.conn);
        return index;
    }

}
