package com.newland.boss.cib.crmp.domain;

/**
 * 描述:
 * 话单字段描述
 *
 * @author weixc
 * create 2018-06-11 15:07
 */
public class CdrDescDef {
    private Integer cdrType;
    private String fieldName;
    private Integer fieldSeq;
    private Integer fieldType;
    private Integer fieldOffset;
    private Integer fieldLength;
    private String delimiter;
    private String fieldDesc;

    public Integer getCdrType() {
        return cdrType;
    }

    public void setCdrType(Integer cdrType) {
        this.cdrType = cdrType;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public Integer getFieldSeq() {
        return fieldSeq;
    }

    public void setFieldSeq(Integer fieldSeq) {
        this.fieldSeq = fieldSeq;
    }

    public Integer getFieldType() {
        return fieldType;
    }

    public void setFieldType(Integer fieldType) {
        this.fieldType = fieldType;
    }

    public Integer getFieldOffset() {
        return fieldOffset;
    }

    public void setFieldOffset(Integer fieldOffset) {
        this.fieldOffset = fieldOffset;
    }

    public Integer getFieldLength() {
        return fieldLength;
    }

    public void setFieldLength(Integer fieldLength) {
        this.fieldLength = fieldLength;
    }

    public String getDelimiter() {
        return delimiter;
    }

    public void setDelimiter(String delimiter) {
        this.delimiter = delimiter;
    }

    public String getFieldDesc() {
        return fieldDesc;
    }

    public void setFieldDesc(String fieldDesc) {
        this.fieldDesc = fieldDesc;
    }
}
