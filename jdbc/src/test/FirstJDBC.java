package test;

import java.sql.*;

/**
 * @author ${林锋鹏}
 * @Title: test.FirstJDBC
 * @ProjectName Java
 * @Description: TODO
 * @date 2019/11/28 10:49
 */
public class FirstJDBC {
    public static void main(String[] args) {
        String url = "jdbc:oracle:thin:@localhost:1521:xe";
        String username = "admin";
        String password = "tianxia";
        String sle = "select * from users";

        try {
            /*
            1,加载驱动类
            2,通过 DriverManager创建连接，获取connection对象
            3,通过连接对象,创建statement对象
            4,通过statement对象执行sql语句 返回一个结果集resultset
            5,处理结果集
            */
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection conn = DriverManager.getConnection(url, username, password);
            System.out.println("连接数据库成功");
            Statement sta = conn.createStatement();
            ResultSet rs = sta.executeQuery(sle);
            while (rs.next()){
                int anInt = rs.getInt(1);
                String us = rs.getString("username");
                String ps = rs.getString("password");
                System.out.println(anInt+" "+us+" "+ps);
            }
            rs.close();
            sta.close();

            /*
            sta.execute(); 执行增删改查操作
            sta.executeUpdate();   执行增删改操作 返回int类型 代表受影响行数
            sta.executeQuery()   查询语句 返回值 查询所有数据 通过会保存结果集resultSet
           */


        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();

        }

    }
}
