package com.newland.boss.cib.crmp.util;

import java.util.Date;
import net.sf.json.JsonConfig;

public class ArrayToJson {
	
	private ArrayToJson() {}
	
	public static JsonConfig getJsonConfig() {
		JsonConfig config = new JsonConfig();
		JsonDateValueProcessor jsonValueProcessor = new JsonDateValueProcessor();
		config.registerJsonValueProcessor(Date.class, jsonValueProcessor);
		return config;
	}
}
