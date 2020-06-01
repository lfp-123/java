package com.seecee.sc1911.action;


import com.seecee.sc1911.pojo.HDept;
import com.seecee.sc1911.pojo.HUser;
import com.seecee.sc1911.service.UserService;
import org.apache.struts2.ServletActionContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author ${林锋鹏}
 * @Title: UserAction
 * @ProjectName Java
 * @Description: TODO
 * @date 2019/12/25 14:27
 */
public class UserAction {
    HUser u;
    List<HUser> list;
    UserService  service =  new  UserService();
    int pageIndex=1;
    long id;
    List<HDept> listDept;



    public String login(){
        u= service.login(u);
        if(u!=null){
            getSession().setAttribute("u",u);
            return  "success";
        }else {
            return "error";
        }
    }
    public String showOneUser(){
        HUser  ou =  service.showOneUser(id);
        getSession().setAttribute("ou",ou);
        return "success";
    }
    public String showUser(){
        int  pageCount=5;
        list = service.queryUser(pageIndex,pageCount);
        return  "success";
    }
    public String addUser(){
        if(service.addUser(u)){
            return "success";
        }
        return "error";
    }
    public String upUser(){
        u.setId(id);
        System.out.println(u.getId());
        System.out.println(u.getDept().getDeptname());
        System.out.println(u.getInfo());
            if(service.upUser(u)){
            return "success";
        }
        return "error";

    }
    public String delUser(){
        System.out.println(id);
        if(service.delUser(id)){
            return "success";
        }else{
            return  "error";
        }
    }
    public String delAll() {
        HttpServletRequest req = ServletActionContext.getRequest();
        String[] ids = req.getParameterValues("ck");

        service.delAllUser(ids);
        return "success";
    }

    public String showUpDept(){
        listDept = service.showDept();
        return "success";
    }










    public HttpSession getSession(){
        return ServletActionContext.getRequest().getSession();
    }
    public HUser getU() {
        return u;
    }
    public void setU(HUser u) {
        this.u = u;
    }
    public List<HUser> getList() {
        return list;
    }
    public void setList(List<HUser> list) {
        this.list = list;
    }
    public int getPageIndex() {
        return pageIndex;
    }
    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }
    public List<HDept> getListDept() {
        return listDept;
    }
    public void setListDept(List<HDept> listDept) {
        this.listDept = listDept;
    }
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }


}
