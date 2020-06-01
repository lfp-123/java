package test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * @author ${林锋鹏}
 * @Title: Tes2
 * @ProjectName Java
 * @date 2019/8/29 16:40
 */
public class Test2 {
    public static void main(String[] args) throws Exception {
        // 1 加载驱动类
        Class.forName("oracle.jdbc.driver.OracleDriver");
        //2,获取连接对象
        Connection connection =
                DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:xe", "bigpig", "tianxia");
        //3 创建stament
        String sql  ="select * from STUDENTSCORE";

        PreparedStatement statement = connection.prepareStatement(sql);
        //4 通过statement方法来发送sql，查询结果返回
      //  statement.setObject(1,10);

        ResultSet rs = statement.executeQuery();

        while (rs.next()){
            String 学号 = rs.getString("学号");
            String 姓名 = rs.getString("姓名");
            String 性别 = rs.getString("性别");
            int 年龄 = rs.getInt("年龄");

            System.out.println(学号+"\t"+姓名+"\t"+性别+年龄);
        }

        rs.close();
        statement.close();
        connection.close();

    }
}
