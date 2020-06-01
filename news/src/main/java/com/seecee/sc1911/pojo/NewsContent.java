package com.seecee.sc1911.pojo;

/**
 * @author ${林锋鹏}
 * @Title: newsContent
 * @ProjectName Java
 * @Description: TODO
 * @date 2019/12/23 22:04
 */
public class NewsContent {
    private int id;
    private String title;
    private String athor;
    private String zhaiyao;
    private String content;
    private String jpg;

    public NewsContent() {
    }

    public NewsContent(int id, String title, String athor, String zhaiyao, String content, String jpg) {
        this.id = id;
        this.title = title;
        this.athor = athor;
        this.zhaiyao = zhaiyao;
        this.content = content;
        this.jpg = jpg;
    }

    public int getId() {

        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAthor() {
        return athor;
    }

    public void setAthor(String athor) {
        this.athor = athor;
    }

    public String getZhaiyao() {
        return zhaiyao;
    }

    public void setZhaiyao(String zhaiyao) {
        this.zhaiyao = zhaiyao;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getJpg() {
        return jpg;
    }

    public void setJpg(String jpg) {
        this.jpg = jpg;
    }
}
