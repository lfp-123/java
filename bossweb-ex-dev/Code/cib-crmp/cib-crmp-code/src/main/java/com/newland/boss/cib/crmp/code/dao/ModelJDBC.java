package com.newland.boss.cib.crmp.code.dao;

import com.newland.boss.cib.crmp.code.entity.Model;
import com.newland.boss.cib.crmp.code.tool.ResourceMap;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class ModelJDBC {

	
	public static List<Model>  find(String name, String type){
		Map<String, String> map = ResourceMap.getJdbcMap();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Model> list = new ArrayList<Model>();
		String driver =map.get("driver");
		String url = map.get("url");
		String user = map.get("username");
		String password = map.get("password");
		if(type.equals("OracleSID")||type.equals("OracleServiceName")){
			try {
				// 1.加载驱动程序
				Class.forName(driver); // com.mysql.jdbc.Driver
				// 2.获得数据库链接
				conn = DriverManager.getConnection(url, user, password);
				String sql = "select column_name,data_type,data_length from user_tab_columns where table_name='" + name.toUpperCase()+"'";
				// 3.通过数据库的连接操作数据库，实现增删改查
				ps = conn.prepareStatement(sql);
				rs = ps.executeQuery();
				while (rs.next()) {
					Model model = new Model(rs.getString("column_name"), rs.getString("data_type"),
							rs.getInt("data_length"));
					list.add(model);
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					ps.close();
					rs.close();
					conn.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		if(type.equals("MySQL")){
			try {
				// 1.加载驱动程序
				Class.forName(driver); // com.mysql.jdbc.Driver
				// 2.获得数据库链接
				conn = DriverManager.getConnection(url, user, password);
				String sql = "select column_name,data_type,character_maximum_length from information_schema.columns where table_name='" + name.toUpperCase()+"' and table_schema='"+map.get("name")+"'";
				// 3.通过数据库的连接操作数据库，实现增删改查
				ps = conn.prepareStatement(sql);
				rs = ps.executeQuery();
				while (rs.next()) {
					Model model = new Model(rs.getString("column_name"), rs.getString("data_type"),
							rs.getInt("character_maximum_length"));
					list.add(model);
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					ps.close();
					rs.close();
					conn.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		
		return list;
	}

}
