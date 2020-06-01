package entity;

import java.util.Date;

/**
 * @author ${林锋鹏}
 * @Title: mailbox
 * @ProjectName Java
 * @Description: TODO
 * @date 2019/12/6 17:51
 */
public class mailbox {
    private int id;
    private String fromname;
    private  String title;
    private String content ="null";
    private int  state;
    private String toname;
    private Date creatdate;
    public mailbox(){

    }
    public mailbox(int id,String title,String content){
        this.id= id;
        this.title= title;
        this.content= content;
    }
    public mailbox(int id, String fromname, String title, String content, int state, String toname, Date creatdate) {
        this.id = id;
        this.fromname = fromname;
        this.title = title;
        this.content = content;
        this.state = state;
        this.toname = toname;
        this.creatdate = creatdate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFromname() {
        return fromname;
    }

    public void setFromname(String fromname) {
        this.fromname = fromname;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getToname() {
        return toname;
    }

    public void setToname(String toname) {
        this.toname = toname;
    }

    public Date getCreatdate() {
        return creatdate;
    }

    public void setCreatdate(Date creatdate) {
        this.creatdate = creatdate;
    }
}
