package service;

import dao.CommentDao;
import entity.comments;

import java.util.List;

/**
 * @author ${林锋鹏}
 * @Title: commentService
 * @ProjectName Java
 * @Description: TODO
 * @date 2019/12/17 11:37
 */
public class commentService {
    CommentDao cd=new CommentDao();
    public List<comments> showComments(){

        return   cd.showComments();
    }
    public List<comments> comment(String name,String text){
        cd.insert(name,text);
        List<comments> list = cd.showComments();
        return  list;
    }
}

