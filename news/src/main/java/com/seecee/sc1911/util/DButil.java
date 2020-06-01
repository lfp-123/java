package com.seecee.sc1911.util;


import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

/**
 * @author ${林锋鹏}
 * @Title: DButil
 * @ProjectName Java
 * @Description: TODO
 * @date 2019/11/28 16:48
 */
public class DButil {
    public static Connection conn = null;
    public static PreparedStatement pare;
    public static int result;
    public static ResultSet rs = null;
    private static String url="jdbc:oracle:thin:@localhost:1521:xe";
    private static String driver="oracle.jdbc.driver.OracleDriver";
    private static String username="admin";
    private static String password="tianxia";

    static {

        /*类加载一次配置文件获取结果 给变量赋值
        会把经过修改的内容通过配置文件的形式进行存储
        读取配置文件
        */
       try {
//            InputStream is = DButil.class.getClassLoader().getResourceAsStream("config/jdbc.properties");
//            //创建配置文件对象 properties对象
//            Properties pr = new Properties();
//            //通过输入流读取
//            pr.load(is);
//            username = pr.getProperty("un");
//            password = pr.getProperty("ps");
//            driver = pr.getProperty("driver");
//            url = pr.getProperty("url");
            Class.forName(driver);
        } catch (Exception e) {
           e.printStackTrace();
       }

    }
    static   ThreadLocal<Connection> t1 = new ThreadLocal<>();
    //获取连接方法
    public static Connection getConnection() {
        try {
              if(t1.get()==null) {
                conn = DriverManager.getConnection(url, username, password);
                 t1.set(conn);
                }else {
                  conn = t1.get();
              }
            } catch (SQLException e) {
                e.printStackTrace();
            }

        return conn;
    }
    /*关闭资源方法
     AutoCloseable是Result Connection PreparedStatement 父类
     可以使用...这样可以使用任意个参数
    */
    public static void close(AutoCloseable... able) {
        //able就是一个AutoCloseable 数组
        try {
            for (AutoCloseable autoCloseable : able) {
                if (autoCloseable != null) {
                    autoCloseable.close();
                    t1.set(null);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
    //增删改通用方法
    public static int update(String sql, Object... o) {
        conn = getConnection();
        try {
            pare = conn.prepareStatement(sql);
            if (o != null) {
                for (int i = 0; i < o.length; i++) {
                    //通过传入的参数给问号赋值。由于之前的顺序和参数的顺序是一致的
                    pare.setObject(i + 1, o[i]);
                }
            }
            result = pare.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        close(conn, pare);
        return result;
    }
    //查询通用方法 （因为都有一个返回值 resultset）
    public static ResultSet show(String sql, Object... o) {
        conn = getConnection();
        try {
            pare = conn.prepareStatement(sql);
            if (o != null) {
                for (int i = 0; i < o.length; i++) {
                    pare.setObject(i + 1, o[i]);
                }
            }
            rs = pare.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //这里不能关闭资源，关闭后结果集就没了
        return rs;
    }
}

