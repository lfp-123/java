package entity;

/**
 * @author ${林锋鹏}
 * @Title: EUser
 * @ProjectName Java
 * @Description: TODO
 * @date 2019/12/16 11:22
 */
public class EUser {
    private  Integer id;
    private String username;
    private String password;
    private String path;

    public EUser() {
    }

    public EUser(String username, String password, String path) {
        this.username = username;
        this.password = password;
        this.path = path;
    }

    public EUser(Integer id, String username, String password, String path) {

        this.id = id;
        this.username = username;
        this.password = password;
        this.path = path;
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

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
