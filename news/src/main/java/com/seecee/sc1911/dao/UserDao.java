package com.seecee.sc1911.dao;

import com.seecee.sc1911.pojo.Users;
import com.seecee.sc1911.util.DButil;

import java.sql.ResultSet;
import java.sql.SQLException;


/**
 * @author ${林锋鹏}
 * @Title: UserDao
 * @ProjectName Java
 * @Description: TODO
 * @date 2019/12/20 16:18
 */
public class UserDao {
    public int insertUser(String name,String password){
        String sql = "INSERT INTO Newsuser VALUES(id_seq.nextval,?,?)";
        int update = DButil.update(sql, name, password);
        return update;
    }
    public Users queryAll(String name ,String password){
        Users user =null;
        String sql = "select *from newsUSer where name=? and pw =?";
        ResultSet rs = DButil.show(sql, name, password);
        try {
            while (rs.next()){
                String name1 = rs.getString("name");
                String pw = rs.getString("pw");
                user = new Users(name1, pw);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        DButil.close(DButil.rs,DButil.pare,DButil.conn);
        return  user;
    }


}
