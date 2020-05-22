package com.newland.boss.cib.crmp.code.service.impl;

import com.newland.boss.cib.crmp.code.dao.AppConfigDao;
import com.newland.boss.cib.crmp.code.dao.DictTreeDao;
import com.newland.boss.cib.crmp.code.entity.AppConfig;
import com.newland.boss.cib.crmp.code.entity.BaseAppConfig;
import com.newland.boss.cib.crmp.code.entity.Dict;
import com.newland.boss.cib.crmp.code.service.AppConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author ${林锋鹏}
 * @Title: AppConfigServiceImpl
 * @ProjectName Code
 * @Description: TODO
 * @date 2020/5/7 9:58
 */
@Service
public class AppConfigServiceImpl implements AppConfigService {
    @Autowired
    private AppConfigDao configDao;
    @Autowired
    private DictTreeDao dictTreeDao;

    @Override
    public List<AppConfig> findAllAppConfig() {
      return   configDao.selectAllAppConfig();
    }

    @Override
    public List<Dict> findDictTree() {
      return   dictTreeDao.findDictTree();
    }

    @Override
    public List<Dict> showDictTypeTree() {
     return    dictTreeDao.showDictTypeTree();
    }

    @Override
    public void addConfig(AppConfig config) {
        configDao.addConfig(config);
    }

    @Override
    public void deleteAppConfig(List<BaseAppConfig> appConfigs) {
        for (BaseAppConfig appConfig : appConfigs) {
            configDao.deleteAppConfig(appConfig.getConfigSeq());
        }
    }

    @Override
    public List<BaseAppConfig> searchConfig(String systemId, String appName, String appInstanceName) {
        return configDao.searchConfig(systemId, appName, appInstanceName);
    }

    @Override
    public void editConfig(AppConfig appConfig, Integer id) {
        configDao.editConfig(appConfig,id);
    }
}
