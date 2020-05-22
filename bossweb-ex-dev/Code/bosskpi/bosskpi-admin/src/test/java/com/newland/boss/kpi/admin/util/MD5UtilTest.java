package com.newland.boss.kpi.admin.util;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.newland.boss.kpi.util.MD5Util;


public class MD5UtilTest {
  @Test
  public void f() {
 	  Assert.assertEquals(MD5Util.Encrypt("10086"), "6412121CBB2DC2CB9E460CFEE7046BE2");  
  }
}
