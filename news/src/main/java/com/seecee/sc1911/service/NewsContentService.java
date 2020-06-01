package com.seecee.sc1911.service;
import com.seecee.sc1911.dao.NewscontentDao;
import com.seecee.sc1911.pojo.NewsContent;

import java.util.List;


/**
 * @author ${林锋鹏}
 * @Title: NewsContentService
 * @ProjectName Java
 * @Description: TODO
 * @date 2019/12/23 22:14
 */
public class NewsContentService {
    NewscontentDao ntd=  new NewscontentDao();
    public List<NewsContent> queyrAll(){
      return   ntd.queyrAll();
    }

}
