package com.newland.boss.cib.crmp.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;

public class JsonDateValueProcessor implements JsonValueProcessor {

	private String pattern = "yyyyMMdd hh:mm:ss";

	@Override
	public Object processArrayValue(Object value, JsonConfig config) {
		return process(value);
	}

	@Override
	public Object processObjectValue(String key, Object value, JsonConfig config) {
		return process(value);
	}

	private Object process(Object value) {
		if (value instanceof Date) {
			SimpleDateFormat sdf = new SimpleDateFormat(pattern, Locale.UK);
			return sdf.format(value);
		}
		return value == null ? "" : value.toString();
	}

}
