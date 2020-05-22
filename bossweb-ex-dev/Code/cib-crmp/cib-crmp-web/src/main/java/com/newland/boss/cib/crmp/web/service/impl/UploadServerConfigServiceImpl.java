package com.newland.boss.cib.crmp.web.service.impl;

import com.newland.boss.cib.crmp.domain.ServerCfg;
import com.newland.boss.cib.crmp.web.dao.ServerCfgDao;
import com.newland.boss.cib.crmp.web.service.UploadServerConfigService;
import com.newland.boss.kpi.server.AppSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Copyright (c) 2018,　新大陆软件工程公司 All rights reserved。
 *
 * @author cby
 * @version V1.0
 * @Title: UploadServerConfigServiceImpl
 * @Package com.newland.boss.cib.crmp.web.service.impl
 * @Description: 上传服务器配置服务实现类
 * @date 2018/7/17
 */
@Service
public class UploadServerConfigServiceImpl implements UploadServerConfigService {

    @Autowired
    private ServerCfgDao serverCfgDao;

    @Autowired
    private AppSession appSession;

    @Override
    public void insertServerCfg(ServerCfg serverCfg) {
        serverCfg.setOperatorId(appSession.getUser().getOperatorId());
        serverCfgDao.insert(serverCfg);
    }

    @Override
    public void updateServerCfg(ServerCfg serverCfg) {
        serverCfgDao.update(serverCfg);
    }

    @Override
    public void deleteServerCfg(List<ServerCfg> serverCfgs) {
        for (ServerCfg serverCfg : serverCfgs) {
            serverCfgDao.delete(serverCfg);
        }
    }

    @Override
    public List<ServerCfg> queryServerCfg() {
        return serverCfgDao.select();
    }
}
