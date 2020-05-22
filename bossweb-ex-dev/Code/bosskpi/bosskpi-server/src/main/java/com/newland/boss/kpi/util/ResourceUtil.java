package com.newland.boss.kpi.util;

import java.util.ResourceBundle;

public class ResourceUtil {
	
	private static final ResourceBundle bundle = ResourceBundle.getBundle("bosskpi");
	
	public static String getValue(String key) {
		return bundle.getString(key);
	}
	
}
