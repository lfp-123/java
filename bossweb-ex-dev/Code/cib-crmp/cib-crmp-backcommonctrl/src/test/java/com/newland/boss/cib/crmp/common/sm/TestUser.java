package com.newland.boss.cib.crmp.common.sm;

import org.testng.annotations.Test;

/**
 * @author ${林锋鹏}
 * @Title: TestUser
 * @ProjectName Code
 * @Description: TODO
 * @date 2020/4/27 11:32
 */
public class TestUser {
    @Test
  public void testUser(){
      SM4Utils sm4Utils = new SM4Utils();
      String password = sm4Utils.decryptDataECB("E10ADC3949BA59ABBE56E057F20F883E");
      System.out.println(password);
  }
}
