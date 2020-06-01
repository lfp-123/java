package entity;


import java.util.Date;

/**
 * @author ${林锋鹏}
 * @Title: User
 * @ProjectName Java
 * @Description: TODO
 * user实体类，用于描述数据库的类
 * 属性一般是和表中的字段是类似。
 * 实体类的属性名不一定和数据库的字段列相同
 * @date 2019/11/28 14:14
 */


public class User {
    private  Integer id;
    private  String username;
    private  String password;
    private  Date date;
    private  String sex="1";
    private  Integer age;

    public User() {
    }

    public User(Integer id, String username, String password, Date date, String sex, Integer age) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.date = date;
        this.sex = sex;
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", date=" + date +
                ", sex='" + sex + '\'' +
                ", age=" + age +
                '}';
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }


}
