package com.newland.boss.cib.crmp.code.service;

import com.newland.boss.cib.crmp.code.entity.AppConfig;
import com.newland.boss.cib.crmp.code.entity.BaseAppConfig;
import com.newland.boss.cib.crmp.code.entity.Dict;
import org.codehaus.jackson.map.Serializers;

import java.util.List;

/**
 * @author ${林锋鹏}
 * @Title: AppConfigService
 * @ProjectName Code
 * @Description: TODO
 * @date 2020/5/7 9:57
 */

public interface AppConfigService {
     List<AppConfig> findAllAppConfig();
     List<Dict> findDictTree();
     List<Dict> showDictTypeTree();
     void addConfig(AppConfig config);
     void deleteAppConfig(List<BaseAppConfig> appConfigs);
     List<BaseAppConfig> searchConfig(String systemId,String appName,String appInstanceName);
     void editConfig(AppConfig appConfig,Integer id);

}
