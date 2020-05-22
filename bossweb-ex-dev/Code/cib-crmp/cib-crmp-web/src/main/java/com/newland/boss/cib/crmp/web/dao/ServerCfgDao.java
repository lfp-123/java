package com.newland.boss.cib.crmp.web.dao;


import com.newland.boss.cib.crmp.domain.ServerCfg;

import java.util.List;

/**
 * Copyright (c) 2018,　新大陆软件工程公司 All rights reserved。
 *
 * @author cby
 * @version V1.0
 * @Title: ServerCfgDao
 * @Package com.newland.boss.cib.crmp.web.dao
 * @Description: 上传服务器配置表
 * @date 2018/7/17
 */
public interface ServerCfgDao {
    void insert(ServerCfg serverCfg);

    void update(ServerCfg serverCfg);

    List<ServerCfg> select();

    void delete(ServerCfg serverCfg);
}
