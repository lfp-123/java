package com.newland.boss.cib.crmp.code.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.newland.boss.cib.crmp.code.dao.ModelJDBC;
import com.newland.boss.cib.crmp.code.entity.Model;
import com.newland.boss.cib.crmp.code.service.ModelService;
import org.springframework.stereotype.Service;

import com.newland.boss.cib.crmp.code.tool.Tool;

@Service("Model")
public class ModelServiceImpl implements ModelService {
	//@Resource
	//private ModelDao modelDao;
	


	public List<Model> getStringList(String tablename, String type) {
		// TODO Auto-generated method stub
		return ModelJDBC.find(tablename,type);

	}

	/**
	 * 获取valueList对应的model模型
	 * 
	 * @param basemodel
	 * @param model
	 * @param valuelist
	 * @return
	 */
	public List<Model> getModel(List<Model> basemodel, List<String> valuelist) {
		List<Model> model = new ArrayList<Model>();
		if (basemodel.size() == valuelist.size()) {
			model = basemodel;
		} else {
			for (int i = 0; i < valuelist.size(); i++) {
				for (int j = 0; j < basemodel.size(); j++) {
					if (valuelist.get(i).equals(basemodel.get(j).getName())) {
						model.add(basemodel.get(j));
						break;
					}
				}

			}
		}
		return model;
	}

	@Override
	public String getModel(String tablename, String type, List<String> valuelist, List<String> valuelist2,String jdbctype) {
		// TODO Auto-generated method stub

		List<Model> basemodel = ModelJDBC.find(tablename,jdbctype);
		List<Model> model = getModel(basemodel, valuelist);
		List<Model> keymodel = getModel(basemodel, valuelist2);
		switch (type) {
		case "insert":
			return Tool.insertModel(model, tablename);
		case "delete":
			return Tool.deleteModel(model, tablename);
		case "update":
			return Tool.updateModel(model, keymodel, tablename);
		case "query":
			return Tool.queryModel(model, keymodel, tablename);
		case "entity":
			return Tool.entityModel(model, tablename);
		default:
			return "error";
		}
	}


}
