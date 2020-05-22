package com.newland.boss.cib.crmp.code.dao;



import com.newland.boss.cib.crmp.code.entity.Model;

import java.util.List;


public interface ModelDao {
	public List<Model> find(String name);
	

}
