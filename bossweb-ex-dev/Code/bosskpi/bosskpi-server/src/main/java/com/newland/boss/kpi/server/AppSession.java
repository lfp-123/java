package com.newland.boss.kpi.server;

import com.newland.boss.kpi.entity.User;

public interface AppSession {
	
	void setValue(String key, Object value);
	
	Object getValue(String key);
	
	User getUser();
	
	void setUser(User user);
}
