<!--page指令-->
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.*" %>
<%@ page import="java.sql.DriverManager" %>
<%@ page import="java.sql.*" %>
<html>
        <head>
        </head>
        <body>
        <h2>jdbc代码</h2>
       <%
           String url = "jdbc:oracle:thin:@localhost:1521:xe";
           String username = "admin";
           String password = "tianxia";
           String sql = "DELETE from STUDENTS where SNO =?";
           Connection conn ;
           PreparedStatement st ;
           Class.forName("oracle.jdbc.driver.OracleDriver");
               conn = DriverManager.getConnection(url, username, password);
               st = conn.prepareStatement(sql);
                st.setString(1,"s003");
                int rs = st.executeUpdate();
                if(rs>0){
                    out.print("删除成功！");
                }else {
                    out.print("删除失败！");
                }
       %>
        </body>
</html>