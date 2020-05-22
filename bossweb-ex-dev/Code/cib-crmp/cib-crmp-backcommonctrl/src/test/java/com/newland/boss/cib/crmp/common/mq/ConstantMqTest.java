package com.newland.boss.cib.crmp.common.mq;

import org.testng.Assert;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

import java.lang.reflect.Constructor;

/**
 * Created by weixc on 2018-07-18.
 */
public class ConstantMqTest {
    @Test
    public void testCreate() throws Exception {
        String testngmg = ConstantMq.TESTNGMG;
        String cdrmq = ConstantMq.CDRMQ;
        Assert.assertEquals("CollectOfflineApp_MSG_TESTNG",testngmg);
        Assert.assertEquals("CollectOfflineApp_MSG",cdrmq);
        
         Constructor<ConstantMq> declaredConstructor = ConstantMq.class.getDeclaredConstructor();
         declaredConstructor.setAccessible(true);
         ConstantMq newInstance = declaredConstructor.newInstance(null);
    }
}