package com.newland.boss.cib.crmp.code.entity;

public class Model {
	private String name;
	private String type;
	private Integer length;
	public Integer getLength() {
		return length;
	}
	public void setLength(Integer length) {
		this.length = length;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String changeType() {
		String temp = null;
		if(type.toLowerCase().contains("char")||type.toLowerCase().contains("date")){
			temp="String";
			return temp;
		}else if(type.toLowerCase().contains("num")||type.toLowerCase().contains("int")){
			if(length<=8){
				temp="Integer";
			}else{
				temp="Long";
			}
			return temp;
			
		}
		return temp;
	}
	public Model(String name, String type, Integer length) {
		super();
		this.name = name;
		this.type = type;
		this.length = length;
	}
	public Model() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	

}
