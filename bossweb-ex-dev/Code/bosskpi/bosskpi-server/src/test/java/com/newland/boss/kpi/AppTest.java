package com.newland.boss.kpi;

import org.testng.Assert;
import org.testng.annotations.Test;
import com.newland.boss.kpi.entity.Role;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
	@Test
    public void test(  )
    {
		Role role = new Role();
		role.setRoleAlias("a");
		role.setRoleId(123);
		role.setRoleName("b");
		role.setRoleStatus(1);
		Assert.assertEquals(role.getRoleAlias(), "a");
    }


}
