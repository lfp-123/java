package entity;


import java.util.Date;

/**
 * @author ${林锋鹏}
 * @Title: Employee
 * @ProjectName Java
 * @Description: TODO
 * @date 2019/11/30 22:35
 */
public class Employee {
    private Integer id;
    private String username;
    private Date dates;
    private String sex;
    private Integer salary;
    private String department;

    public Employee(Integer id, String username, Date dates, String sex, Integer salary, String department) {
        this.id = id;
        this.username = username;
        this.dates = dates;
        this.sex = sex;
        this.salary = salary;
        this.department = department;
    }
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "侠士属性{" +
                "id=" + id +
                ", 姓名='" + username + '\'' +
                ", 生日=" + dates +
                ", 性别='" + sex + '\'' +
                ", 月薪=" + salary +
                ", 门派='" + department + '\'' +
                '}';
    }

    public Date getDates() {
        return dates;
    }

    public void setDates(Date date) {
        this.dates = dates;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Integer getSalary() {
        return salary;
    }

    public void setSalary(Integer salary) {
        this.salary = salary;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }
}
