package cn.sc.mor;

import java.io.Serializable;

/**
 * @author ${林锋鹏}
 * @Title: User
 * @ProjectName Java
 * @Description: TODO
 * @date 2019/11/19 14:33
 */
public class User implements Serializable {
    //1,提供一个隐式属性 Uid
    private static final long serialVersionUID = 140560382;
    //2,通过static 修饰的属性不会被序列化
    //通过transient修饰的属性不会被序列化
    private Integer id;
    private String Username;
    private String password;
    private String role; //用户角色



    public User(){

    }
    public User(Integer id, String username, String password, String role) {
        this.id = id;
        Username = username;
        this.password = password;
        this.role = role;
    }



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", Username='" + Username + '\'' +
                ", password='" + password + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}
