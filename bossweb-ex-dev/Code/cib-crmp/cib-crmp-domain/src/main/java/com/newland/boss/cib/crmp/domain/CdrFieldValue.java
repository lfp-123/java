package com.newland.boss.cib.crmp.domain;

/**
 * 话单元素类型定义
 *
 */
public class CdrFieldValue {
	
	//列索引位置
	private int columnIndex;
	
	private String value;
	
	public CdrFieldValue() {
		this(-1);
	}
	
	public CdrFieldValue(int columnIndex) {
		this.columnIndex = columnIndex;
	}
	
	
	public void setValue(String value) {
		this.value = value;
	}
	
	public String getValueAsString() {
		return value;
	}
	
	public Integer getValueAsInteger() {
		return new Integer( value );
	}
	
	public Long getValueAsLong() {
		return new Long( value );
	}

	
	public int getColumnIndex() {
		return columnIndex;
	}

	public void setColumnIndex(int columnIndex) {
		this.columnIndex = columnIndex;
	}
	
	
}
