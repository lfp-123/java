package com.newland.boss.cib.crmp.web.dao;


import com.newland.boss.cib.crmp.domain.TimingSchedulerCfg;

import java.util.List;

/**
 * Copyright (c) 2018,　新大陆软件工程公司 All rights reserved。
 *
 * @author cby
 * @version V1.0
 * @Title: TimingSchedulerCfgDao
 * @Package com.newland.boss.cib.crmp.web.dao
 * @Description: 定时调度器配置表
 * @date 2018/7/4
 */
public interface TimingSchedulerCfgDao {

    void insert(TimingSchedulerCfg timingSchedulerCfg);

    void update(TimingSchedulerCfg timingSchedulerCfg);

    List<TimingSchedulerCfg> select();

    void delete(TimingSchedulerCfg timingSchedulerCfg);
}
