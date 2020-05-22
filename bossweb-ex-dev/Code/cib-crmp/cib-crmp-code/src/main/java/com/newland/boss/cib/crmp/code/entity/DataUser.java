package com.newland.boss.cib.crmp.code.entity;

public class DataUser {
	private String jdbcDriver;
	private String jdbcPort;
	private String jdbcName;
	private String userName;
	private String password;
	public String getJdbcDriver() {
		return jdbcDriver;
	}
	public void setJdbcDriver(String jdbcDriver) {
		this.jdbcDriver = jdbcDriver;
	}
	public String getJdbcPort() {
		return jdbcPort;
	}
	public void setJdbcPort(String jdbcPort) {
		this.jdbcPort = jdbcPort;
	}
	public String getJdbcName() {
		return jdbcName;
	}
	public void setJdbcName(String jdbcName) {
		this.jdbcName = jdbcName;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "DataUser{" +
				"jdbcDriver='" + jdbcDriver + '\'' +
				", jdbcPort='" + jdbcPort + '\'' +
				", jdbcName='" + jdbcName + '\'' +
				", userName='" + userName + '\'' +
				", password='" + password + '\'' +
				'}';
	}
}
