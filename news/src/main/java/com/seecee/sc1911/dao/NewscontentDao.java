package com.seecee.sc1911.dao;

import com.seecee.sc1911.pojo.NewsContent;
import com.seecee.sc1911.util.DButil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author ${林锋鹏}
 * @Title: NewscontentDao
 * @ProjectName Java
 * @Description: TODO
 * @date 2019/12/23 22:12
 */
public class NewscontentDao {

    public List<NewsContent> queyrAll(){
        List<NewsContent> List = new ArrayList<>();
        NewsContent news = new NewsContent();
        String sql ="select *from newscontent";
        ResultSet rs = DButil.show(sql);
        try {
            while (rs.next()){
                String id = rs.getString("id");
                String title = rs.getString("title");
                String athor = rs.getString("athor");
                String zhaiyao = rs.getString("zhaiyao");
                String content = rs.getString("content");
                String jpg = rs.getString("jpg");
                news = new NewsContent(Integer.parseInt(id),title,athor,zhaiyao,content,jpg);
                List.add(news);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        DButil.close(DButil.rs,DButil.pare,DButil.conn);
        return  List;
    }

}
