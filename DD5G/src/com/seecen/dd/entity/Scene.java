package com.seecen.dd.entity;

import java.io.Serializable;

/**
 * @author ${林锋鹏}
 * @Title: Scene
 * @ProjectName Java
 * @date 2019/8/17 15:44
 */
public class Scene implements Serializable {
    private String type; //场景类型
    private int data; //场景消费数据
    private String description; //场景描述
    public Scene() {
        super();
    }
    public Scene(String type, int data, String description) {
        this.type = type;
        this.data = data;
        this.description = description;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public int getData() {
        return data;
    }
    public void setData(int data) {
        this.data = data;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }


}
