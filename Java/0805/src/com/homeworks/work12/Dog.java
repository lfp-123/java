package com.homeworks.work12;

/**
 * @author ${林锋鹏}
 * @Title: Dog
 * @ProjectName Java
 * @date 2019/8/5 18:53
 */
public class Dog {
    private String name;
    private String id;
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


    Dog(String id,String name){
        this.id=id;
        this.name= name;
    }
}
