package test;



import java.sql.*;
import java.sql.Date;

/**
 * @author ${林锋鹏}
 * @Title: Test1
 * @ProjectName Java
 * @date 2019/8/29 15:21
 */
public class Test1 {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        int eno= 9527;
        //查询所有的emp员工信息
        /*
        * 1，加载驱动类
        * ORACLE 驱动类 oracle.jdbc.driver.OracleDriver
        * MYSQL  驱动类 com.jdbc.mysql.driver
        */
        Class.forName("oracle.jdbc.driver.OracleDriver");
        /*
        * 2，获取数据库连接对象Connection
        * 1 url
        *  jdbc: oracle:thin:@127.0.0.1:1521:xe;
        *
        */
        Connection conn =
                DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:xe","bigpig","tianxia");
        System.out.println(conn);
        /**
         *3,创建Statement (有安全隐患）
         * 使用prepareStatement
         */
        String sql ="select * from emp where empno= ? ";
        PreparedStatement statement = conn.prepareStatement(sql);

        statement.setInt(1,eno);
        /*
        4，通过statement的方法查询sql发往数据库执行，查询结果封装到resultset返回
        */
        ResultSet rs = statement.executeQuery();
        while (rs.next()){
            int empno = rs.getInt("empno");
            String ename = rs.getString("ename");
            String job = rs.getString("job");
            int mgr = rs.getInt("mgr");
            /*
            * 与数据库时间数据类型对应的Java类型
            * 父类
            * java.util.DataJava程序常用 年月日 时分秒
            * 子类
            * Java SQL Data 只能保存年月日
            * java sql Time 只能保存时分秒
            * Java sql Timestamp 年月日 时分秒*/
            Date hiredate = rs.getDate("hiredate");
            System.out.println(empno+"==>"+ename+"==>"+job+"==>"+mgr+"==>"+hiredate);

        }
        //5,释放资源，按照后来先关的原则
        rs.close();
        statement.close();
        conn.close();


    }
}
