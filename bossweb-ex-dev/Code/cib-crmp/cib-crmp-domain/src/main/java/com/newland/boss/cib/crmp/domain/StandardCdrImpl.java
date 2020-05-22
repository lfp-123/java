package com.newland.boss.cib.crmp.domain;

/**
 * 话单定义类
 */
public class StandardCdrImpl implements StandardCdr {

    private int cdrFlag;
    private int cdrType;
    private String fileName;

    public String getFileName() {
        return fileName;
    }

    public int getCdrFlag() {
		return cdrFlag;
	}

	public void setCdrFlag(int cdrFlag) {
		this.cdrFlag = cdrFlag;
	}

	public int getCdrType() {
		return cdrType;
	}

	public void setCdrType(int cdrType) {
		this.cdrType = cdrType;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}


    private CdrFieldValue[] aOrigCFieldValue;
    private CdrFieldValue[] aUInfoCFieldValue;
    private CdrFieldValue[] aRateCFieldValue;
    private CdrFieldValue[] aPretreatmentFieldValue;

    public CdrFieldValue[] getaOrigCFieldValue() {
        return aOrigCFieldValue;
    }

    public CdrFieldValue[] getaUInfoCFieldValue() {
        return aUInfoCFieldValue;
    }

    public CdrFieldValue[] getaRateCFieldValue() {
        return aRateCFieldValue;
    }

    public CdrFieldValue[] getaPretreatmentFieldValue() {
        return aPretreatmentFieldValue;
    }

    public StandardCdrImpl() {

        //原始话单字段
        aOrigCFieldValue = new CdrFieldValue[CdrFieldDef.ORIG_FIELD_COUNT];
        for (int index = 0; index < CdrFieldDef.ORIG_FIELD_COUNT; index++) {
            aOrigCFieldValue[index] = new CdrFieldValue();
        }

        //用户资料字段
        aUInfoCFieldValue = new CdrFieldValue[CdrFieldDef.UINFO_FIELD_COUNT];
        for (int index = 0; index < CdrFieldDef.UINFO_FIELD_COUNT; index++) {
            aUInfoCFieldValue[index] = new CdrFieldValue();
        }

        //批价信息字段
        aRateCFieldValue = new CdrFieldValue[CdrFieldDef.RATE_FIELD_COUNT];
        for (int index = 0; index < CdrFieldDef.RATE_FIELD_COUNT; index++) {
            aRateCFieldValue[index] = new CdrFieldValue();
        }

        // 预处理新增字段
        aPretreatmentFieldValue = new CdrFieldValue[CdrFieldDef.PRETREATMENT_COUNT];
        for (int index = 0; index < CdrFieldDef.PRETREATMENT_COUNT; index++) {
            aPretreatmentFieldValue[index] = new CdrFieldValue();
        }
    }

    public CdrFieldValue at(int index) {
        if ((index >= CdrFieldDef.FID_ORIG_BASE) && (index < CdrFieldDef.FID_UINFO_BASE)) {
            return aOrigCFieldValue[index - CdrFieldDef.FID_ORIG_BASE];
        } else if ((index >= CdrFieldDef.FID_UINFO_BASE) && (index < CdrFieldDef.FID_RATE_BASE)) {
            return aUInfoCFieldValue[index - CdrFieldDef.FID_UINFO_BASE];
        } else if (index >= CdrFieldDef.FID_RATE_BASE && index < CdrFieldDef.FID_PRETREATMENT_BASE) {
            return aRateCFieldValue[index - CdrFieldDef.FID_RATE_BASE];
        } else if (index >= CdrFieldDef.FID_PRETREATMENT_BASE) {
            return aPretreatmentFieldValue[index - CdrFieldDef.FID_PRETREATMENT_BASE];
        }
        //不在定义的索引范围内,返回null
        return null;
    }


    public String toString() {

        StringBuilder sb = new StringBuilder(2048);

        sb.append(this.getCdrFlag());
        sb.append(",");


        if (null != aOrigCFieldValue) {
            for (int index = 0; index < CdrFieldDef.ORIG_FIELD_COUNT; index++) {
                sb.append(",");
                sb.append(aOrigCFieldValue[index].getValueAsString());
            }
        }

        //.......

        return sb.toString();

    }


   

}
