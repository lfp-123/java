package cn.sc.work;

import java.io.Serializable;

/**
 * @author ${林锋鹏}
 * @Title: User
 * @ProjectName Java
 * @Description: TODO
 * @date 2019/11/19 15:36
 */
public class User implements Serializable {
    private Integer id;
    private String Username;
    private String Password;
    private String role;
    public User(){

    }
   public User(Integer id, String username, String password, String role) {
        this.id = id;
        Username = username;
        Password = password;
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
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }


}
