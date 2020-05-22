package com.newland.boss.kpi.entity;

/**
 * 数据字典javabean
 * @author jwk
 */
public class DictDef {
	
	private Integer dictClass;
	private String dictName;
	private Integer entryId;
	private String entryName;
	private String entryDesc;

	public Integer getDictClass() {
		return dictClass;
	}
	public void setDictClass(Integer dictClass) {
		this.dictClass = dictClass;
	}
	public String getDictName() {
		return dictName;
	}
	public void setDictName(String dictName) {
		this.dictName = dictName;
	}
	public Integer getEntryId() {
		return entryId;
	}
	public void setEntryId(Integer entryId) {
		this.entryId = entryId;
	}
	public String getEntryName() {
		return entryName;
	}
	public void setEntryName(String entryName) {
		this.entryName = entryName;
	}
	public String getEntryDesc() {
		return entryDesc;
	}
	public void setEntryDesc(String entryDesc) {
		this.entryDesc = entryDesc;
	}

}
