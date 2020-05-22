package com.newland.boss.cib.crmp.code.tool;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import com.newland.boss.cib.crmp.code.entity.Model;

public class Tool {
	/**
	 * 下划线改为驼峰式
	 * 
	 * @author LR
	 * @param str
	 * @param flag
	 *            判断改为大驼峰还是小驼峰（true为小驼峰，false为大驼峰）
	 * @return String
	 */
	public static   String getName(String str, boolean flag) {
		if (str == null || "".equals(str)) {
			return "";
		}
		StringBuffer sb = new StringBuffer();
		Pattern pattern = Pattern.compile("([A-Za-z\\d]+)(_)?");
		Matcher matcher = pattern.matcher(str);
		// 匹配正则表达式
		while (matcher.find()) {
			String word = matcher.group();
			// 当是true 或则是空的情况
			if (flag && matcher.start() == 0) {
				sb.append(Character.toLowerCase(word.charAt(0)));
			} else {
				sb.append(Character.toUpperCase(word.charAt(0)));
			}

			int index = word.lastIndexOf('_');
			if (index > 0) {
				sb.append(word.substring(1, index).toLowerCase());
			} else {
				sb.append(word.substring(1).toLowerCase());
			}
		}
		return sb.toString();
	}

	/**
	 * 
	 * 查询语句模板
	 * 
	 * @param model
	 * @param keymodel
	 * @param tablename
	 * @return
	 */

	public static   String queryModel(List<Model> model, List<Model> keymodel, String tablename) {
		StringBuffer strB = new StringBuffer();
		List<String> newStr = new ArrayList<String>();
		newStr.add(getName(tablename, false));
		strB.append("public List<" + newStr.get(0) + "> get" + newStr.get(0) + "ByKey(");
		for (int i = 1; i <model.size()+keymodel.size()+1; i++) {
			newStr.add(getName(keymodel.get(i - 1).getName(), true));
			if (i == keymodel.size()) {
				strB.append(keymodel.get(i - 1).changeType() + " " + newStr.get(i) + " ");
				break;
			} else {
				strB.append(keymodel.get(i - 1).changeType() + " " + newStr.get(i) + ", ");
			}

		}
		strB.append("){\n\tStringBuffer sbSql = new StringBuffer();\n\tsbSql.append(\"select ");
		for (int i = 0; i < model.size(); i++) {
			if (i + 1 == model.size()) {
				strB.append(model.get(i).getName());
			} else {
				strB.append(model.get(i).getName() + ",");
			}
		}
		strB.append(" from " + tablename + " where ");
		for (int i = 1; i < newStr.size(); i++) {
			if (i + 1 == newStr.size()) {
				strB.append(keymodel.get(i - 1).getName() + " = :" + newStr.get(i) + " \");");
			} else {
				strB.append(keymodel.get(i - 1).getName() + " = :" + newStr.get(i) + " and ");
			}
		}
		strB.append(getvlue(1, newStr));
		return strB.toString();
	}

	/**
	 * 修改语句模板
	 * 
	 * @param model
	 * @param keymodel
	 * @param tablename
	 * @return
	 */
	public static   String updateModel(List<Model> model, List<Model> keymodel, String tablename) {
		StringBuffer strB = new StringBuffer();
		List<String> newStr = new ArrayList<String>();
		newStr.add(getName(tablename, false));
		strB.append("public Integer update" + newStr.get(0) + "ByKey(");
		for (int i = 1; i < model.size()+keymodel.size()+1; i++) {
			if (i >= model.size() + 1) {
				newStr.add(getName(keymodel.get(i - model.size() - 1).getName(), true));
				if (i + 1 == keymodel.size()) {
					strB.append(keymodel.get(i - model.size() - 1).changeType() + " " + newStr.get(i) + " ");
				} else {
					strB.append(keymodel.get(i - model.size() - 1).changeType() + " " + newStr.get(i) + ", ");
				}
			} else {
				newStr.add(getName(model.get(i - 1).getName(), true));
				strB.append(model.get(i - 1).changeType() + " " + newStr.get(i) + ", ");
			}

		}
		strB.append("){\n\tStringBuffer sbSql = new StringBuffer();\n\tsbSql.append(\"update " + tablename + " set ");
		for (int i = 1; i <= model.size(); i++) {
			if (i == model.size()) {
				strB.append(model.get(i - 1).getName() + " = :" + newStr.get(i) + " where ");
			} else {
				strB.append(model.get(i - 1).getName() + " = :" + newStr.get(i) + ",");
			}
		}
		for (int i = model.size() + 1; i < newStr.size(); i++) {
			if (i + 1 == newStr.size()) {
				strB.append(keymodel.get(i - model.size() - 1).getName() + " = :" + newStr.get(i) + " \");");
			} else {
				strB.append(keymodel.get(i - model.size() - 1).getName() + " = :" + newStr.get(i) + " and ");
			}
		}
		strB.append(getvlue(0, newStr));
		return strB.toString();
	}

	/**
	 * 插入语句模板
	 * 
	 * @param model
	 * @param tablename
	 * @return
	 */
	public static   String insertModel(List<Model> model, String tablename) {
		StringBuffer strB = new StringBuffer();
		List<String> newStr = new ArrayList<String>();
		newStr.add(getName(tablename, false));
		strB.append("public Integer insert" + newStr.get(0) + "ByKey(");
		for (int i = 1; i < model.size()+1; i++) {
			newStr.add(getName(model.get(i - 1).getName(), true)) ;
			if (i + 1 == newStr.size()) {
				strB.append(model.get(i - 1).changeType() + " " + newStr.get(i) + " ");
			} else {
				strB.append(model.get(i - 1).changeType() + " " + newStr.get(i) + ", ");
			}
		}
		strB.append("){\n\tStringBuffer sbSql = new StringBuffer();\n\tsbSql.append(\"insert into  " + tablename + "(");
		for (int i = 1; i < newStr.size(); i++) {
			if (i + 1 == newStr.size()) {
				strB.append(model.get(i - 1).getName() + ") values(");
			} else {
				strB.append(model.get(i - 1).getName() + ",");
			}
		}
		for (int i = 1; i < newStr.size(); i++) {
			if (i + 1 == newStr.size()) {
				strB.append(":" + newStr.get(i) + ") nowait\");");
			} else {
				strB.append(":" + newStr.get(i) + ",");
			}
		}
		strB.append(getvlue(0, newStr));
		return strB.toString();
	}

	/**
	 * 删除语句模板
	 * 
	 * @param model
	 * @param tablename
	 * @return
	 */
	public static   String deleteModel(List<Model> model, String tablename) {
		StringBuffer strB = new StringBuffer();
		List<String> newStr = new ArrayList<String>();
		newStr.add(getName(tablename, false));
		strB.append("public Integer get" + newStr.get(0) + "ByKey(");
		for (int i = 1; i < model.size()+1; i++) {
			newStr.add( getName(model.get(i - 1).getName(), true));
			if (i + 1 == newStr.size()) {
				strB.append(model.get(i - 1).changeType() + " " + newStr.get(i) + " ");
			} else {
				strB.append(model.get(i - 1).changeType() + " " + newStr.get(i) + ", ");
			}
		}
		strB.append("){\n\tStringBuffer sbSql = new StringBuffer();\n\tsbSql.append(\"delete from " + tablename
				+ " where ");
		for (int i = 1; i < newStr.size(); i++) {
			if (i + 1 == newStr.size()) {
				strB.append(model.get(i - 1).getName() + " = :" + newStr.get(i) + " \");");
			} else {
				strB.append(model.get(i - 1).getName() + " = :" + newStr.get(i) + " and ");
			}
		}
		strB.append(getvlue(0, newStr));
		return strB.toString();
	}

	/**
	 * 后半部分
	 * 
	 * @param num
	 *            1:query 0:其他
	 * @param newStr
	 * @return
	 */
	public static   String getvlue(int num, List<String> valueList) {
		String str = "";
		List<String> newStr=(List)valueList.stream().distinct().collect(Collectors.toList());
		str += "\n\tMap<String, Object> paramMap = new HashMap<String, Object>();";
		for (int i = 1; i < newStr.size(); i++) {
			str += "\n\tparamMap.put(\"" + newStr.get(i) + "\", " + newStr.get(i) + ");";
		}
		switch (num) {
		case 1:
			str += "\n\treturn this.getJdbcOperation().queryForList(sbSql.toString(), paramMap, " + newStr.get(0)
					+ ".class);\n}";
			break;
		case 0:
			str += "\n\treturn this.getJdbcOperation().queryForList(sbSql.toString(), paramMap);\n}";
			break;
		default:
			break;
		}
		return str;
	}

	public static String entityModel(List<Model> model, String tablename) {
		StringBuffer strB = new StringBuffer();
		List<String> newStr = new ArrayList<String>();
		List<String> newStrB = new ArrayList<String>();
		strB.append("public class "+getName(tablename, false)+"{\n\t");
		for (int i = 0; i < model.size(); i++) {
			newStr.add( getName(model.get(i).getName(), true));
			newStrB.add( getName(model.get(i).getName(), false));
			strB.append("private "+model.get(i).changeType()+" "+newStr.get(i)+";\n\t");
		}
		for (int i = 0; i < newStr.size(); i++) {
			strB.append("public "+model.get(i).changeType()+" set"+newStrB.get(i)+"() {\n\t\t");
			strB.append("return "+newStr.get(i)+";\n\t}\n\t");
			strB.append("public void set"+newStrB.get(i)+"("+model.get(i).changeType()+" "+newStr.get(i)+") {\n\t\t");
			strB.append("this."+newStr.get(i)+" = "+newStr.get(i)+";\n\t}\n\t");
		}
		strB.append("\n}");
		return strB.toString();
	}

	

}
