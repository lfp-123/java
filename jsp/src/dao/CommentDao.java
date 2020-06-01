package dao;

import entity.comments;

import util.DButil;

import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author ${林锋鹏}
 * @Title: CommentDao
 * @ProjectName Java
 * @Description: TODO
 * @date 2019/12/17 11:37
 */
public class CommentDao {
    public List<comments> showComments(){
        List<comments> list = new ArrayList<>();
        String sql ="select * from comments order by time desc";
        ResultSet rs = DButil.show(sql);
        try {
            while (rs.next()){
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String content = rs.getString("content");
                Date time = rs.getTimestamp("time");
                comments comment = new comments(id, name, content, time);
                list.add(comment);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        DButil.close(DButil.rs,DButil.pare,DButil.conn);
        return list;
    }
    public int insert(String un,String text){
        String sql = "insert into comments values(id_seq.nextval,?,?,sysdate)";
        int update = DButil.update(sql, un, text);
        return  update;
    }
}
