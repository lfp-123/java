package entity;


import util.DButil;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author ${林锋鹏}
 * @Title: UserDemo
 * @ProjectName Java
 * @Description: TODO
 * @date 2019/11/28 14:22
 */
public class UserDemo {
    static Scanner sc= new Scanner(System.in);
    public static void main(String[] args) {
        boolean index =true;
        do {
        System.out.println("欢迎进入系统");
        System.out.println("1,注册");
        System.out.println("2,登陆");
        System.out.println("3,展示用户");
        System.out.println("4,修改用户");
        System.out.println("5,删除用户");
            int i = sc.nextInt();
            switch (i){
                case 1:

                    break;
                case 2:
                    Login();
                    break;
                case 3:
                    show();
                    break;
                case 4:
                    update();
                    break;
                case 5:
                    delete();
                    break;
                default:
                    index = false;
                    break;
            }
        } while (index);
    }

    private static void Login() {
        String usernames = null;
        String passwords = null;
        String sql = "select username,password from users  where username = ?";
        System.out.println("请输入你的账号：");
        String username = sc.next();
        System.out.println("请输入你的密码：");
        String password = sc.next();
        ResultSet rs = DButil.showUser(sql, username);
        try {
            while (rs.next()) {
                usernames = rs.getString("username");
                passwords = rs.getString("password");
            }
        }catch ( SQLException e){
            e.printStackTrace();
        }finally {
            DButil.close(DButil.rs,DButil.pare,DButil.conn);
        }
       if(username.equals(usernames)&&password.equals(passwords)){
           System.out.println("登陆成功！");
       }else{
           System.out.println("登陆失败！");
       }

    }

    private static void update() {
        System.out.println("进入修改业面！");
        show();
        System.out.println("请输入你要修改的id:");
        int id = sc.nextInt();
        System.out.println("输入修改用户：");
        String name = sc.next();
        System.out.println("请输入修改密码：");
        String password = sc.next();
        updateUser(id,name,password);
    }

    private static int  updateUser(int id, String name, String password) {
            String sql = "update users set username=?, password =? where id =?";
            return DButil.update(sql,name,password,id);
    }


    public static List<User>  queryAll(){
            List<User> users = new ArrayList<>();
            ResultSet   rs = DButil.showUser("select * from users");
        try {
            while (rs.next()){
                    int id = rs.getInt(1);
                    String username = rs.getString("username");
                    String password = rs.getString("password");
                    Date birthday = rs.getDate("birthday");
                    String sex = rs.getString("sex");
                    int age = rs.getInt("age");
                    User user = new User(id, username, password, birthday, sex, age);
                    users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DButil.close(DButil.rs,DButil.pare,DButil.conn);
        }
        return users;
    }
    public static void show(){
        List<User> list = queryAll();
        for (User user : list) {
            System.out.println(user);
        }

    }
    public static  void delete(){
        show();
        System.out.println("请输入你要删除的用户：");
        int i = sc.nextInt();
        int i1 = deleteUser(i);
        if(i1>0){
            System.out.println("删除成功！");
        }else {
            System.out.println("删除失败！");
        }
    }
    public static  int deleteUser(Object id){
    //        Connection conn=null;
//        Statement st =null;
//        PreparedStatement pare;
//        int result = 0;
//        try {
//            Class.forName("oracle.jdbc.driver.OracleDriver");
//             conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "admin", "tianxia");
//             pare = conn.prepareStatement("DELETE  from users where id=?");
//             /*
//             * 让sql语句预编译，让语句固定。
//             * */
//             pare.setString(1,id+"");
//             result = pare.executeUpdate();
//            System.out.println("User表有"+result+"条数据被修改！");
//
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }finally {
//            try {
//                if(st!=null) st.close();
//                if(conn!=null) conn.close();
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }

        String sql = "delete from users where id =?";
        return DButil.update(sql,id);
    }

}
