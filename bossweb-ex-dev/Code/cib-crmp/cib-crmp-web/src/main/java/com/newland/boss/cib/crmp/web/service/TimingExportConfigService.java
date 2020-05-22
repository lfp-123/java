package com.newland.boss.cib.crmp.web.service;


import com.newland.boss.cib.crmp.domain.TimingSchedulerCfg;

import java.util.List;

/**
 * Copyright (c) 2018,　新大陆软件工程公司 All rights reserved。
 *
 * @author cby
 * @version V1.0
 * @Title: TimingExportConfigService
 * @Package com.newland.boss.cib.crmp.web.service
 * @Description: 定时任务配置
 * @date 2018/7/10
 */
public interface TimingExportConfigService {
    void insertTimingExportConfig(TimingSchedulerCfg timingSchedulerCfg) throws Exception;

    void updateTimingExportConfig(TimingSchedulerCfg timingSchedulerCfg) throws Exception;

    void deleteTimingExportConfig(List<TimingSchedulerCfg> timingSchedulerCfgs) throws Exception;

    List<TimingSchedulerCfg> queryTimingExportConfig();

    boolean verification(TimingSchedulerCfg timingSchedulerCfg) throws Exception;
}
