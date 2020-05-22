package com.newland.boss.cib.crmp.code.dao;

import com.newland.boss.cib.crmp.code.entity.AppConfig;
import com.newland.boss.cib.crmp.code.entity.BaseAppConfig;
import com.newland.boss.cib.crmp.code.entity.Dict;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author ${林锋鹏}
 * @Title: AppConfigDao
 * @ProjectName Code
 * @Description: TODO
 * @date 2020/5/7 10:00
 */

public interface AppConfigDao {

    List<AppConfig> selectAllAppConfig();

    void addConfig(@Param("config") AppConfig config);

    void deleteAppConfig(@Param("seq") Integer seq);

    List<BaseAppConfig> searchConfig(@Param("systemName") String systemId,@Param("appName") String appName, @Param("appInstanceName")String appInstanceName);

    void editConfig(@Param("c")AppConfig config,@Param("id") Integer id);
}
