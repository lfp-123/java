package entity;

import util.DButil;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;



/**
 * @author ${林锋鹏}
 * @Title: EmployeeDemo
 * @ProjectName Java
 * @Description: TODO
 * @date 2019/11/30 22:38
 */
public class EmployeeDemo {
static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        boolean index =true;
        do {
            System.out.println("******员工信息系统******");
            System.out.println("1,展示员工信息");
            System.out.println("2,修改员工信息");
            System.out.println("3,添加员工信息");
            System.out.println("4,员工加薪");
            System.out.println("5,开除员工");
            System.out.println("请输入你要选择的功能：");
            int i = sc.nextInt();
            switch (i){
                case 1:
                    showEmpyee();
                    break;
                case 2:
                    modify();
                    break;
                case 3:
                    insert();
                    break;
                case 4:
                    raise();
                    break;
                case 5:
                    deleteEmploy();
                    break;
                default:
                    index = false;
                    break;
            }
        } while (index);
    }
    private static void showEmpyee() {
        ArrayList<Employee> employees = new ArrayList<>();
        String sql = "select *from Employee";
        ResultSet rs = DButil.showUser(sql);
        try {
            while (rs.next()){
                int id = rs.getInt("id");
                String username = rs.getString("username");
                Date dates = rs.getDate("dates");
                String sex = rs.getString("sex");
                int salary = rs.getInt("salary");
                String department = rs.getString("department");
                Employee employee = new Employee(id, username, dates, sex, salary, department);
                employees.add(employee);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        for (Employee employee : employees) {
            System.out.println(employee);
        }
        DButil.close(DButil.rs,DButil.pare,DButil.conn);

    }
    private static void modify(){
        showEmpyee();
        System.out.print("请输入你要修改的id:");
        int id = sc.nextInt();
        System.out.print("请输入你要的修改的姓名：");
        String name = sc.next();
        System.out.print("请输入你要修改的性别:");
        String sex = sc.next();
        System.out.print("请输入你要修改的门派:");
        String dep = sc.next();
        System.out.println("请输入要修改的生日:");
        String date = sc.next();
        String sql = "update Employee set username=?, sex =?,department =?,dates = to_date(?,'yyyy-mm-dd') where id =? ";
        int update = DButil.update(sql, name, sex, dep,date, id);
        if(update>0){
            System.out.println("修改成功！");
        }else{
            System.out.println("修改失败！");
        }

    }
    private static void raise(){
        showEmpyee();
        System.out.println("请输入你要加薪的弟子：");
        int id = sc.nextInt();
        System.out.println("请输入你要为加薪的数额或扣款的数额：");
        int money = sc.nextInt();
        Connection conn = DButil.getConnection();
        int update = 0;
        try {
            conn.setAutoCommit(false);
            String sql ="update Employee set salary = salary+? where id = ?";
            update = DButil.update(sql, money, id);
            conn.commit();
            System.out.println("修改薪水成功！");
        } catch (SQLException e) {
            try {
                conn.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        }
    }
    private static void insert(){
        System.out.println("欢迎进入添加员工界面：");
        System.out.println("请输入武林弟子姓名:");
        String name = sc.next();
        System.out.println("请输入武林弟子生日:");
        String dates = sc.next();
        System.out.println("请输入武林弟子性别:");
        String sex = sc.next();
        System.out.println("请输入武林弟子所属部门:");
        String dep = sc.next();
        System.out.println("请输入弟子薪水:");
        int salary = sc.nextInt();
        String sql = "insert into Employee values(id_seq.nextval,?,to_date(?,'yyyy-mm-dd'),?,?,?)";
        int update = DButil.update(sql, name, dates,sex, salary, dep);
        if(update>0){
            System.out.println("插入成功！");
        }else {
            System.out.println("插入失败！");
        }
    }
    private static void deleteEmploy() {
        System.out.println("花名册如下：");
        showEmpyee();
        System.out.println("请选择你要除名的侠士:");
        String sql= "delete from Employee where id = ?";
        int id = sc.nextInt();
        int update = DButil.update(sql, id);
        if(update>0){
            System.out.println("删除成功！");
        }else{
            System.out.println("删除失败！");
        }

    }


}
