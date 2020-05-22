package com.newland.boss.cib.crmp.domain;

/**
 * 常量定义
 */
public class CdrFieldDef {


    // 话单存储容器大小
    public static final int ORIG_FIELD_COUNT = 100;
    public static final int UINFO_FIELD_COUNT = 2;
    public static final int RATE_FIELD_COUNT = 2;
    public static final int PRETREATMENT_COUNT = 8;
    // 原始话单字段定义
    public static final int FID_ORIG_BASE = 0;
    // 用户资料字段定义
    public static final int FID_UINFO_BASE = 100;
    public static final int FID_UINFO_ORG_ID = FID_UINFO_BASE; // 用户归属机构
    public static final int FID_UINFO_USER_ID = FID_UINFO_BASE + 1; // 用户
    // 批价字段定义
    public static final int FID_RATE_BASE = 200;
    public static final int FID_RATE_FEE = FID_RATE_BASE; //批价费用
    public static final int FID_RATE_RATING_TIME = FID_RATE_BASE + 1; //批价时间
    // 预处理字段定义
    public static final int FID_PRETREATMENT_BASE = 300;
    public static final int FID_PRETREATMENT_CALLTIME = FID_PRETREATMENT_BASE; // 开打时间
    public static final int FID_PRETREATMENT_BILLTIME = FID_PRETREATMENT_BASE + 1; // 账期
    public static final int FID_PRETREATMENT_GUIDING_TIME = FID_PRETREATMENT_BASE + 2; // 预处理时间
    public static final int FID_PRETREATMENT_END_TIME = FID_PRETREATMENT_BASE + 3; // 结束时间
    public static final int FID_PRETREATMENT_SEC_DUR = FID_PRETREATMENT_BASE + 4; // 通话时长
    public static final int FID_PRETREATMENT_IS_PASSIVE = FID_PRETREATMENT_BASE + 5; // 主被叫0，主叫，1是被叫
    public static final int FID_PRETREATMENT_CALL_NUM_LOGING = FID_PRETREATMENT_BASE + 6; // 主叫logigname
    public static final int FID_PRETREATMENT_CALLED_NUM_LOGING = FID_PRETREATMENT_BASE + 7; // 被叫loginname
    /**
     * 0为主动
     */
    public static final String IS_PASSIVE_0 = "0";

    //预处理后的主被叫定义
    /**
     * 1为被动
     */
    public static final String IS_PASSIVE_1 = "1";
    // 话单类型定义字段
    public static final int CDR = 1;
    public static final int CDR_MEETING = 2;
    public static final int DMA = 3;
    // 话单处理是否成功
    public static final int SUCCESS = 0;
    public static final int FAIL = 1;
    /**
     * 黑户
     */
    public static final int BLACK = 2;
    /**
     * 重单
     */
    public static final int REPEAT = 3;
    // 呼叫类型所在字段
    public static final String COND_CODE = CDR + "_cond-code";
    public static final String START_TIME = DMA + "_startTime";
    public static final String END_TIME = DMA + "_endTime";
    public static final String CHAIR_JOIN_TIME = DMA + "_chairJoinTime";
    public static final String MCU_PROMOTION_TIME = DMA + "_mcuPromotionTime";
    public static final String CALLINGPARTY = CDR + "_clg-num/in-tac";
    public static final String CALLEDGPARTY = CDR + "_dialed-num";
    /**
     * CDR话单，呼叫类型为C的为电话会议
     */
    public static final String C = "C";
    // 呼叫类型定义
    public static final String CALLTYPE4 = "4";
    public static final String CALLTYPE7 = "7";
    public static final String CALLTYPE9 = "9";
    /**
     * 主叫号码规则
     */
    public static final Integer CALLNUMBERRULE = 0;
    /**
     * 被叫号码规则。
     */
    public static final Integer CALLEDNUMBERRULE = 1;
    public static final String TIMEZONE = "Asia/Shanghai";
    public static final String BILLTIMEKEY = "billTime";

    private CdrFieldDef() {

    }
    /**
     * 步骤信息枚举
     */
    public enum setpEnum {
        PARSE_CDR(1, "话单解析"),
        GUIDING(2, "预处理"),
        CHECK_REPEAT(3, "查重"),
        RATING(4, "批价"),
        LOAD_CDR(5, "入库"),
        UNKNOWN(6, "未知");

        private int step;
        private String stepName;

        setpEnum(int step, String stepName) {
            this.step = step;
            this.stepName = stepName;
        }

        public int getStep() {
            return step;
        }

        public String getStepName() {
            return stepName;
        }

    }

}
