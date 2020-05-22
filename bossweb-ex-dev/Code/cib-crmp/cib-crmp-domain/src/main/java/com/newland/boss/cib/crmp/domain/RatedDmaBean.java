package com.newland.boss.cib.crmp.domain;


/**
 * 用于处理重算
 * 
 * @author ylc
 *
 */
public class RatedDmaBean {

	private int billMonth;
	private int orgId;
	private int operatorId;
	
	private int version;
	private String type;
	private String confType;
	private String confCluster;
	private String confUUID;
	private String startTime;
	private String endTime;
	private String userId;// 用户ID
	private int roomId;
	private int partCount;
	private String serviceClass;
	private String userDataA;
	private String userDataB;
	private String userDataC;
	private String maxResourcesUsed;
	private String mcuNameList;
	private String confDisplayNameList;
	private String chairPasscode;
	private String confRequiresChair;
	private String afterChairDrops;
	private String chairJoinTime;
	private String mcuPromotionTime;
	public int getBillMonth() {
		return billMonth;
	}
	public void setBillMonth(int billMonth) {
		this.billMonth = billMonth;
	}
	public int getVersion() {
		return version;
	}
	public void setVersion(int version) {
		this.version = version;
	}
	public int getOrgId() {
		return orgId;
	}
	public void setOrgId(int orgId) {
		this.orgId = orgId;
	}
	public int getOperatorId() {
		return operatorId;
	}
	public void setOperatorId(int operatorId) {
		this.operatorId = operatorId;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getConfType() {
		return confType;
	}
	public void setConfType(String confType) {
		this.confType = confType;
	}
	public String getConfCluster() {
		return confCluster;
	}
	public void setConfCluster(String confCluster) {
		this.confCluster = confCluster;
	}
	public String getConfUUID() {
		return confUUID;
	}
	public void setConfUUID(String confUUID) {
		this.confUUID = confUUID;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public int getRoomId() {
		return roomId;
	}
	public void setRoomId(int roomId) {
		this.roomId = roomId;
	}
	public int getPartCount() {
		return partCount;
	}
	public void setPartCount(int partCount) {
		this.partCount = partCount;
	}
	public String getServiceClass() {
		return serviceClass;
	}
	public void setServiceClass(String serviceClass) {
		this.serviceClass = serviceClass;
	}
	public String getUserDataA() {
		return userDataA;
	}
	public void setUserDataA(String userDataA) {
		this.userDataA = userDataA;
	}
	public String getUserDataB() {
		return userDataB;
	}
	public void setUserDataB(String userDataB) {
		this.userDataB = userDataB;
	}
	public String getUserDataC() {
		return userDataC;
	}
	public void setUserDataC(String userDataC) {
		this.userDataC = userDataC;
	}
	public String getMaxResourcesUsed() {
		return maxResourcesUsed;
	}
	public void setMaxResourcesUsed(String maxResourcesUsed) {
		this.maxResourcesUsed = maxResourcesUsed;
	}
	public String getMcuNameList() {
		return mcuNameList;
	}
	public void setMcuNameList(String mcuNameList) {
		this.mcuNameList = mcuNameList;
	}
	public String getConfDisplayNameList() {
		return confDisplayNameList;
	}
	public void setConfDisplayNameList(String confDisplayNameList) {
		this.confDisplayNameList = confDisplayNameList;
	}
	public String getChairPasscode() {
		return chairPasscode;
	}
	public void setChairPasscode(String chairPasscode) {
		this.chairPasscode = chairPasscode;
	}
	public String getConfRequiresChair() {
		return confRequiresChair;
	}
	public void setConfRequiresChair(String confRequiresChair) {
		this.confRequiresChair = confRequiresChair;
	}
	public String getAfterChairDrops() {
		return afterChairDrops;
	}
	public void setAfterChairDrops(String afterChairDrops) {
		this.afterChairDrops = afterChairDrops;
	}
	public String getChairJoinTime() {
		return chairJoinTime;
	}
	public void setChairJoinTime(String chairJoinTime) {
		this.chairJoinTime = chairJoinTime;
	}
	public String getMcuPromotionTime() {
		return mcuPromotionTime;
	}
	public void setMcuPromotionTime(String mcuPromotionTime) {
		this.mcuPromotionTime = mcuPromotionTime;
	}
	
	

}
