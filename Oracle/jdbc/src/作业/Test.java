package 作业;

import java.sql.*;

/**
 * @author ${林锋鹏}
 * @Title: Test
 * @ProjectName Java
 * @date 2019/8/29 20:12
 */
public class Test {
    public static void main(String[] args) throws  Exception{

        Class.forName("oracle.jdbc.driver.OracleDriver");

        Connection conn =
                DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:xe","bigpig","tianxia");

        String sql ="select * from emp where empno= ? ";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setInt(1,9527);
        /*
        4，通过statement的方法查询sql发往数据库执行，查询结果封装到resultset返回
        */
        //PreparedStatement 可以自己set 值进去 ， 而 Statement 只是简单的拼 SQL。


        int x =9527;
        String sql1 = "select * from emp where empno= "+x;
        ResultSet rs = statement.executeQuery();
        Statement statement1 = conn.prepareStatement(sql1);

    }
}
