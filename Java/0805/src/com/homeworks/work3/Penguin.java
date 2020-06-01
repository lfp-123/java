package com.homeworks.work3;

/**
 * @author ${林锋鹏}
 * @Title: Penguin
 * @ProjectName Java
 * @date 2019/8/5 19:15
 */
public class Penguin {
    private  String name;

    public Penguin(String name, String id) {
        this.name = name;
        this.id = id;
    }

    private  String id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
