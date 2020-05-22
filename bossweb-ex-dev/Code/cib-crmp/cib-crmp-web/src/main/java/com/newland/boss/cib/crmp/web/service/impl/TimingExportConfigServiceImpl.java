package com.newland.boss.cib.crmp.web.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.newland.boss.cib.crmp.domain.TimingSchedulerCfg;
import com.newland.boss.cib.crmp.web.dao.TimingSchedulerCfgDao;
import com.newland.boss.cib.crmp.web.service.TimingExportConfigService;
import com.newland.boss.kpi.server.AppSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.regex.Pattern;

/**
 * Copyright (c) 2018,　新大陆软件工程公司 All rights reserved。
 *
 * @author cby
 * @version V1.0
 * @Title: TimingExportConfigServiceImpl
 * @Package com.newland.boss.cib.crmp.web.service.impl
 * @Description: 定时任务配置实现
 * @date 2018/7/10
 */
@Service
public class TimingExportConfigServiceImpl implements TimingExportConfigService {

    private Logger logger = LogManager.getLogger(getClass());

    @Autowired
    private TimingSchedulerCfgDao timingSchedulerCfgDao;

    @Autowired
    private AppSession appSession;


    @Override
    public void insertTimingExportConfig(TimingSchedulerCfg timingSchedulerCfg) {
        timingSchedulerCfg.setOperatorId(appSession.getUser().getOperatorId());
        timingSchedulerCfgDao.insert(timingSchedulerCfg);
    }

    @Override
    public void updateTimingExportConfig(TimingSchedulerCfg timingSchedulerCfg) {
        timingSchedulerCfg.setOperatorId(appSession.getUser().getOperatorId());
        timingSchedulerCfgDao.update(timingSchedulerCfg);
    }

    private static final Integer STATUS = 2;

    @Override
    public void deleteTimingExportConfig(List<TimingSchedulerCfg> timingSchedulerCfgs) {
        for (TimingSchedulerCfg timingSchedulerCfg : timingSchedulerCfgs) {
            timingSchedulerCfg.setStatus(STATUS);
            timingSchedulerCfgDao.delete(timingSchedulerCfg);
        }
    }

    @Override
    public List<TimingSchedulerCfg> queryTimingExportConfig() {
        logger.info("TimingExportConfigController : queryTimingSchedulerCfg start");
        List<TimingSchedulerCfg> timingSchedulerCfgs = timingSchedulerCfgDao.select();
        logger.info("Json result : " + JSONArray.toJSONString(timingSchedulerCfgs));
        logger.info("TimingExportConfigController : queryTimingSchedulerCfg end");
        return timingSchedulerCfgs;
    }

    @Override
    public boolean verification(TimingSchedulerCfg timingSchedulerCfg) throws Exception {
        Integer syncCycle = timingSchedulerCfg.getSyncCycle();
        String cycleValue = timingSchedulerCfg.getCycleValue();
        if (syncCycle.equals(6)) {
            return true;
        }
        if (judgeNumber(cycleValue)) {
            Integer cycleValueInt = Integer.parseInt(cycleValue);
            boolean flag1 = syncCycle.equals(1) && cycleValueInt >= 0 && cycleValueInt <= 60;
            boolean flag2 = syncCycle.equals(2) && cycleValueInt >= 0 && cycleValueInt <= 24;
            boolean flag3 = syncCycle.equals(3) && cycleValue.length() == 4 && cycleValueInt % 100 >= 0 && cycleValueInt % 100 <= 60 && cycleValueInt / 100 >= 0 && cycleValueInt / 100 <= 24;
            if (flag1 || flag2 || flag3) {
                return true;
            }
        } else {
            if (cycleValue.indexOf(",") != -1) {
                String[] str = cycleValue.split(",");
                Integer cycleValueIntFirst = Integer.parseInt(str[0]);
                Integer cycleValueIntLast = Integer.parseInt(str[1]);
                boolean flag1 = syncCycle.equals(4) && cycleValueIntFirst > 0 && cycleValueIntFirst <= 7 && cycleValueIntLast % 100 >= 0 && cycleValueIntLast % 100 <= 60 && cycleValueIntLast / 100 >= 0 && cycleValueIntLast / 100 <= 24;
                boolean flag2 = syncCycle.equals(5) && cycleValueIntFirst > 0 && cycleValueIntFirst <= 31 && cycleValueIntLast % 100 >= 0 && cycleValueIntLast % 100 <= 60 && cycleValueIntLast / 100 >= 0 && cycleValueIntLast / 100 <= 24;
                if (flag1 || flag2) {
                    return true;
                }
            }
        }

        return false;
    }

    private boolean judgeNumber(String str) {
        Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");
        return pattern.matcher(str).matches();
    }
}
