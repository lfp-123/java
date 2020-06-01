package com.sc.spmvc.service.Impl;

import com.sc.spmvc.mapper.SUserMapper;
import com.sc.spmvc.pojo.SUser;
import com.sc.spmvc.service.UserService;
import com.sc.spmvc.until.MybatisUtil;
import org.apache.ibatis.session.SqlSession;


import java.util.List;

/**
 * @author ${林锋鹏}
 * @Title: UserServiceImpl
 * @ProjectName Java
 * @Description: TODO
 * @date 2020/1/8 11:52
 */
public class UserServiceImpl implements UserService {

    @Override
    public SUser login(SUser user) {
        SqlSession session = MybatisUtil.getSession();
        SUserMapper mapper = session.getMapper(SUserMapper.class);
        SUser u =null;
        try {
            u = mapper.selectByUsernamePassword(user);
            session.commit();
        }catch (Exception e){
            e.printStackTrace();
            session.rollback();
        }
        return u;
    }


    @Override
    public int register(SUser user) {
        SqlSession session = MybatisUtil.getSession();
        SUserMapper mapper = session.getMapper(SUserMapper.class);
        int i=0;
       try {
            i = mapper.insertUser(user);
            session.commit();
       }catch (Exception e){
           e.printStackTrace();
           session.rollback();
            return  i;
       }finally {
           MybatisUtil.close();
       }
        return i;
    }

    @Override
    public SUser checkUser(String username) {
        SqlSession session = MybatisUtil.getSession();
        SUserMapper mapper = session.getMapper(SUserMapper.class);
        SUser u = mapper.checkUser(username);
        session.commit();
        MybatisUtil.close();
        return  u;

    }

    @Override
    public List<SUser> showUserList() {
        SqlSession session = MybatisUtil.getSession();
        SUserMapper mapper = session.getMapper(SUserMapper.class);
        List<SUser> list = mapper.showListUser();
        return list;
    }

    @Override
    public int delUser(SUser user) {
        SqlSession session = MybatisUtil.getSession();
        SUserMapper mapper = session.getMapper(SUserMapper.class);
        int i = mapper.delUSer(user);
        session.commit();
        MybatisUtil.close();
        return i;
    }
}
