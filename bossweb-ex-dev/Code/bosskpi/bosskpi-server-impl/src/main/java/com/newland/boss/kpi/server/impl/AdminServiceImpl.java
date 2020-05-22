package com.newland.boss.kpi.server.impl;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.stereotype.Component;

import com.newland.boss.kpi.dao.OperAuthDao;
import com.newland.boss.kpi.dao.ResRoleDao;
import com.newland.boss.kpi.dao.UserDao;
import com.newland.boss.kpi.dao.UserRoleDao;
import com.newland.boss.kpi.entity.Resource;
import com.newland.boss.kpi.entity.ResourceRole;
import com.newland.boss.kpi.entity.Role;
import com.newland.boss.kpi.entity.User;
import com.newland.boss.kpi.server.AdminService;
import com.newland.boss.kpi.util.ResourceUtil;

@Component
public class AdminServiceImpl implements AdminService {
	
	private static final String TIME_OUT = "timeOut";
	private static final String RE_COUNT = "reCount";
	
	private Map<String, Collection<ConfigAttribute>> resourceRoleMap = new ConcurrentHashMap<String, Collection<ConfigAttribute>>();
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private UserRoleDao userRoleDao;
	
	@Autowired
	private ResRoleDao resRoleDao;
	
	@Autowired
	private OperAuthDao operAuthDao;
	
	/**
	 * 根据工号获取操作员信息
	 * @param operatorId
	 */
	@Override
	public User getOperatorById(Integer operatorId) {
		return userDao.selectOperatorById(operatorId);
	}

	@Override
	public void getOrganizationByOperatorId(Integer operatorId) {
		
	}
	
	/**
	 * 获取操作员归属角色
	 * @param operatorId
	 * @return
	 */
	@Override
	public List<Role> getRoleByOperatorId(Integer operatorId) {
		return userRoleDao.selectRoleByOperatorId(operatorId);
	}
	
	/**
	 * 获取角色拥有的资源列表
	 * @param roldId
	 * @return
	 */
	@Override
	public List<Resource> getResourceByRoleId(Integer roleId) {
		return operAuthDao.selectResourceByRoleId(roleId);
	}
	
	/**
	 * 获取操作员有权限的资源列表
	 * @param operatorId
	 * @return
	 */ 
	@Override
	public List<Resource> getResourceByOperatorId(Integer operatorId) {
		return operAuthDao.selectResourceByOperId(operatorId);
	}

	@Override
	public List<Resource> getAllRoleResourceRela() {
		return null;
	}
	
	/**
	 * 查找资源路径及其对应的角色
	 * @return Map<String, Collection<String>>
	 */ 
	@Override
	public List<ResourceRole> getRoleAliasByResourceUrl() {
		return resRoleDao.selectRoleAliasByResourceUrl();
	}

	@Override
	public int getOrgIdByOperatorId(int operatorId) {
		String orgNameFull = userDao.selectOrgNameFullByOperatorId(operatorId);
		return userDao.selectByFullName(orgNameFull);
	}
	
	@Override
	public String loginVerify(String password, String id, int loginType) {
		User user = null;
		if(loginType == 0) {
			//账户登录
			user = userDao.selectOperatorById(Integer.parseInt(id));
		} else if (loginType == 1) {
			//notes登录
			user = userDao.selectOperatorByLoginName(id);
		}
		String errMsg = "";
		if(user == null) {
			if(loginType == 0) {
				errMsg = "账户不存在";
			} else if (loginType == 1) {
				errMsg = "notes用户认证失败";
			}
			return errMsg;
		}
		
		return getErrMsg(user, loginType);
	}
	
	private String getErrMsg(User user, int loginType) {
		String lastErrTimeStr = user.getLastErrTime();
		long mini = isReset(lastErrTimeStr);
		String errMsg = "";
		int timeOut = ResourceUtil.getValue(TIME_OUT) == null ? 30 : Integer.parseInt(ResourceUtil.getValue(TIME_OUT));
		int reCount = ResourceUtil.getValue(RE_COUNT) == null ? 5 : Integer.parseInt(ResourceUtil.getValue(RE_COUNT));
		if(mini > timeOut) {
			user.setErrCount(1);
			userDao.resetErrCount(user);
			if(loginType == 0) {
				errMsg = "密码错误，还可以重试" + (reCount-1) + "次";
			} else if (loginType == 1) {
				errMsg = "notes用户认证失败，还可以重试" + (reCount-1) + "次";
			}
			return errMsg;
		}
		if(user.getErrCount() < reCount) {
			userDao.updateErrCount(user.getOperatorId());
		}
		if(user.getErrCount() + 1 < reCount) {
			if(loginType == 0) {
				errMsg = "密码错误，还可以重试" + (reCount - user.getErrCount() - 1) + "次";
			} else if (loginType == 1) {
				errMsg = "notes用户认证失败，还可以重试" + (reCount - user.getErrCount() - 1) + "次";
			}
			return errMsg;
		}
		if(loginType == 0) {
			errMsg = "密码错误，已重试5次， 请" + (timeOut - mini) + "分钟后再试！ ";
		} else if (loginType == 1) {
			errMsg = "notes用户认证失败，已重试5次， 请" + (timeOut - mini) + "分钟后再试！";
		}
		return errMsg;
	}
	
	private long isReset(String lastErrTimeStr) {
		long mini = 0;
		if(lastErrTimeStr != null && lastErrTimeStr.length() > 0) {
			LocalDateTime now = LocalDateTime.now();
			LocalDateTime lastErrTime = LocalDateTime.parse(lastErrTimeStr, DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
			mini = ChronoUnit.MINUTES.between(lastErrTime, now);
		}
		return mini;
	}
	
	@Override
	public void resetErrCount(String id, int loginType) {
		User user = null;
		if(loginType == 0) {
			//账户登录
			user = userDao.selectOperatorById(Integer.parseInt(id));
		} else if (loginType == 1) {
			//notes登录
			user = userDao.selectOperatorByLoginName(id);
		}
		if(user == null) {
			return;
		}
		user.setErrCount(0);
		userDao.resetErrCount(user);
	}

	@Override
	public String checkUserLock(String password, String id, int loginType) {
		User user = null;
		if(loginType == 0) {
			//账户登录
			user = userDao.selectOperatorById(Integer.parseInt(id));
		} else if (loginType == 1) {
			//notes登录
			user = userDao.selectOperatorByLoginName(id);
		}
		if(user == null) {
			return null;
		}
		String lastErrTimeStr = user.getLastErrTime();
		long mini = isReset(lastErrTimeStr);
		int timeOut = ResourceUtil.getValue(TIME_OUT) == null ? 30 : Integer.parseInt(ResourceUtil.getValue(TIME_OUT));
		int reCount = ResourceUtil.getValue(RE_COUNT) == null ? 5 : Integer.parseInt(ResourceUtil.getValue(RE_COUNT));
		if(user.getErrCount() >= reCount && mini <= timeOut) {
			return "账户已被锁，请" + (timeOut - mini) + "分钟后再试！";
		}
		return null;
	}

	@Override
	public User selectOperatorByLoginName(String name) {
		return userDao.selectOperatorByLoginName(name);
	}

	@Override
	public void initRoleAliasByResourceUrl() {
		resourceRoleMap.clear();
		List<ResourceRole> resAliasList = resRoleDao.selectRoleAliasByResourceUrl();
		
		for(ResourceRole resourceRole : resAliasList ) {
			Collection<ConfigAttribute> collection =  resourceRoleMap.get(resourceRole.getResourceUrl());
			if(null == collection) {
				collection = new ArrayList<ConfigAttribute>();
				resourceRoleMap.put(resourceRole.getResourceUrl(), collection);
			}
			collection.add(new SecurityConfig(resourceRole.getRoleAlias()));
		}
	}

	@Override
	public Map<String, Collection<ConfigAttribute>> getResourceRoleMap() {
		if(resourceRoleMap.isEmpty()) {
			initRoleAliasByResourceUrl();
		}
		return resourceRoleMap;
	}
}
