package com.ppdai.ppdaitool.vo;

public class OverTimeRecordLast6MonthVo {
	/**
	 * 主键
	 */
	private int id;
	/**
	 * 完整用户名
	 */
	private String fullUsername;
	/**
	 * 手机app用户的闪电借款
	 */
	private String listingid;
	/**
	 * 时间
	 */
	private String dateTime;
	/**
	 * 最大逾期天数
	 */
	private int dayNumber;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFullUsername() {
		return fullUsername;
	}
	public void setFullUsername(String fullUsername) {
		this.fullUsername = fullUsername;
	}
	public String getListingid() {
		return listingid;
	}
	public void setListingid(String listingid) {
		this.listingid = listingid;
	}
	public String getDateTime() {
		return dateTime;
	}
	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}
	public int getDayNumber() {
		return dayNumber;
	}
	public void setDayNumber(int dayNumber) {
		this.dayNumber = dayNumber;
	}
	
}
