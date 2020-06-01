package entity;

/**
 * @author ${林锋鹏}
 * @Title: Person
 * @ProjectName Java
 * @Description: TODO
 * @date 2019/12/11 11:15
 */
public class Person {
    String username;
    String password;
    String id;

    public Person() {
    }

    public Person(String id, String username, String password) {
        this.username = username;
        this.password = password;
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
