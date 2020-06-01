package test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * @author ${林锋鹏}
 * @Title: Test4
 * @ProjectName Java
 * @date 2019/8/29 17:39
 */
public class Test4 {
    public static void main(String[] args) throws  Exception {
        // 1 加载驱动类
        Class.forName("oracle.jdbc.driver.OracleDriver");
        //2,获取连接对象
        Connection connection =
                DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:xe", "bigpig", "tianxia");
        //3 创建stament
        String sql  ="select * from dept  ";

        PreparedStatement statement = connection.prepareStatement(sql);
        //4 通过statement方法来发送sql，查询结果返回
        ResultSet rs = statement.executeQuery();

        while (rs.next()){
            int deptno = rs.getInt("deptno");
            String dname = rs.getString("dname");
            String loc = rs.getString("loc");
            System.out.println(deptno+" "+dname +" "+ loc);
        }

        rs.close();
        statement.close();
        connection.close();

    }
}
