package com.newland.boss.kpi.server;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.springframework.security.access.ConfigAttribute;

import com.newland.boss.kpi.entity.Resource;
import com.newland.boss.kpi.entity.ResourceRole;
import com.newland.boss.kpi.entity.Role;
import com.newland.boss.kpi.entity.User;

public interface AdminService {
	
	/**
	 * 根据工号获取操作员信息
	 * @param operatorId
	 */
	public User getOperatorById(Integer operatorId);
	
	/**
	 * 获取操作员归属机构
	 * @param operatorId
	 */
	public void getOrganizationByOperatorId(Integer operatorId);
	
	/**
	 * 获取操作员归属角色
	 * @param operatorId
	 * @return
	 */
	public List<Role> getRoleByOperatorId(Integer operatorId);
	
	/**
	 * 获取角色拥有的资源列表
	 * @param roldId
	 * @return
	 */
	public List<Resource> getResourceByRoleId(Integer roldId);
	
	/**
	 * 获取操作员有权限的资源列表
	 * @param operatorId
	 * @return
	 */
	public List<Resource> getResourceByOperatorId(Integer operatorId);
	
	/**
	 * 获取所有角色资源关系
	 * @return
	 */
	public List<Resource> getAllRoleResourceRela();
	
	/**
	 * 查找资源路径及其对应的角色
	 * @return
	 */
	public List<ResourceRole> getRoleAliasByResourceUrl();

	public int getOrgIdByOperatorId(int operatorId);

	public String checkUserLock(String password, String id, int loginType);
	
	public String loginVerify(String password, String id, int loginType);
	
	public void resetErrCount(String id, int loginType);

	public User selectOperatorByLoginName(String name);

	public void initRoleAliasByResourceUrl();
	
	public Map<String, Collection<ConfigAttribute>> getResourceRoleMap();
}
