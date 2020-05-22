package com.newland.boss.cib.crmp.code.service;

import java.util.List;

import com.newland.boss.cib.crmp.code.entity.Model;

public interface ModelService {
	public List<Model> getStringList(String tablename,String type);
	String getModel(String tablename, String type, List<String> valuelist, List<String> valuelist2, String jdbctype);

}
