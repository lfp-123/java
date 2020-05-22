package com.newland.boss.cib.crmp.web.service;


import com.newland.boss.cib.crmp.domain.ServerCfg;

import java.util.List;

/**
 * Copyright (c) 2018,　新大陆软件工程公司 All rights reserved。
 *
 * @author cby
 * @version V1.0
 * @Title: UploadServerConfigSerivce
 * @Package com.newland.boss.cib.crmp.web.service
 * @Description: 上传服务器配置
 * @date 2018/7/17
 */
public interface UploadServerConfigService {
    void insertServerCfg(ServerCfg serverCfg) throws Exception;

    void updateServerCfg(ServerCfg serverCfg) throws Exception;

    void deleteServerCfg(List<ServerCfg> serverCfgs) throws Exception;

    List<ServerCfg> queryServerCfg();
}
