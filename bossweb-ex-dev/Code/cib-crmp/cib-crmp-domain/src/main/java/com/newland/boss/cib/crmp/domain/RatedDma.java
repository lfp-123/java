package com.newland.boss.cib.crmp.domain;

import java.util.Date;

public class RatedDma {

	private String confUuid;
	private String userId;//用户ID
	private String ip;// 用户IP
	private String terminalName;// 终端名
	private int conferenceNumber;// 会议号
	private Date startTime;
	private Date endTime;
	private long holdingTime;//通话时长
	private String partCount;
	private String roomId;
	private long fee;
	private long otherFee;
	private int orgId;
	private int operatorId;
	private String operatorName;
	private String orgName;
	private long count;
	private int billMonth;
	public String getConfUuid() {
		return confUuid;
	}
	public void setConfUuid(String confUuid) {
		this.confUuid = confUuid;
	}
	public long getOtherFee() {
		return otherFee;
	}
	public void setOtherFee(long otherFee) {
		this.otherFee = otherFee;
	}
	public int getOrgId() {
		return orgId;
	}
	public void setOrgId(int orgId) {
		this.orgId = orgId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getTerminalName() {
		return terminalName;
	}
	public void setTerminalName(String terminalName) {
		this.terminalName = terminalName;
	}
	public int getConferenceNumber() {
		return conferenceNumber;
	}
	public void setConferenceNumber(int conferenceNumber) {
		this.conferenceNumber = conferenceNumber;
	}
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	public long getHoldingTime() {
		return holdingTime;
	}
	public void setHoldingTime(long holdingTime) {
		this.holdingTime = holdingTime;
	}
	public String getPartCount() {
		return partCount;
	}
	public void setPartCount(String partCount) {
		this.partCount = partCount;
	}
	public String getRoomId() {
		return roomId;
	}
	public void setRoomId(String roomId) {
		this.roomId = roomId;
	}
	public long getFee() {
		return fee;
	}
	public void setFee(long fee) {
		this.fee = fee;
	}
	public String getOperatorName() {
		return operatorName;
	}
	public void setOperatorName(String operatorName) {
		this.operatorName = operatorName;
	}
	public String getOrgName() {
		return orgName;
	}
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	public int getOperatorId() {
		return operatorId;
	}
	public void setOperatorId(int operatorId) {
		this.operatorId = operatorId;
	}
	public long getCount() {
		return count;
	}
	public void setCount(long count) {
		this.count = count;
	}
	public int getBillMonth() {
		return billMonth;
	}
	public void setBillMonth(int billMonth) {
		this.billMonth = billMonth;
	}
	
}
