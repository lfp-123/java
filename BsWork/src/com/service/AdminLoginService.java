package com.service;

import com.dao.DB;
import com.orm.TAdmin;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author ${林锋鹏}
 * @Title: AdminLoginService
 * @ProjectName Java
 * @Description: TODO
 * @date 2020/1/23 18:01
 */
public class AdminLoginService {
    TAdmin admin = new TAdmin();
    public TAdmin login(String userName, String userPw) {
        String sql = "select * from t_admin where userName=? and userPw=?";
        Object[] params = {userName, userPw};
        DB mydb = new DB();
        mydb.doPstm(sql, params);
        try {
            ResultSet rs = mydb.getRs();
            while (rs.next()) {
                admin.setUserId(rs.getInt("userId"));
                admin.setUserName(rs.getString("userName"));
                admin.setUserPw(rs.getString("userPw"));
            }

        } catch (SQLException e) {
            System.out.println("查询失败！！");
            e.printStackTrace();
        } finally {
            mydb.closed();
        }

        return  admin;
    }
}
