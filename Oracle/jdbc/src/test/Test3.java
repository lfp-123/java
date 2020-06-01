package test;


import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Timestamp;

/**
 * @author ${林锋鹏}
 * @Title: Test3
 * @ProjectName Java
 * @date 2019/8/29 17:30
 */
public class Test3 {
    public static void main(String[] args) throws  Exception {

        Class.forName("oracle.jdbc.driver.OracleDriver");

        Connection connection =
                DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:xe", "bigpig", "tianxia");
        String sql ="insert into demp(deptno,dname,loc) " +
              "values (?,?,?,?)";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setObject(1,40);
        statement.setObject(2,"aa");
        statement.setObject(3,"china");

        //java.util.Date -> java.sql.Timestamp
//        java.util.Date date = new java.util.Date();
//        long time = date.getTime();
//        Timestamp timestamp = new Timestamp(time);
//        statement.setObject(1,timestamp);
//        int i = statement.executeUpdate();
        //受影响的行数
        statement.close();
        connection.close();


    }
}
