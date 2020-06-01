package entity;

import org.omg.CORBA.INTERNAL;

import java.util.Date;

/**
 * @author ${林锋鹏}
 * @Title: comments
 * @ProjectName Java
 * @Description: TODO
 * @date 2019/12/17 11:30
 */
public class comments {
    private Integer id;
    private String name;
    private String content;
    private Date time;

    public comments() {
    }

    public comments(Integer id, String name, String content, Date time) {
        this.id = id;
        this.name = name;
        this.content = content;
        this.time = time;
    }

    public Integer getId() {

        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}
