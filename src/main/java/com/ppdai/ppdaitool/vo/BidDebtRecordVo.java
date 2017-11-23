package com.ppdai.ppdaitool.vo;

public class BidDebtRecordVo {
	/**
	 * 主键
	 */
	private int id;
	/**
	 * 用户名
	 */
	private String userName;
	/**
	 * 完整用户名
	 */
	private String fullUsername;
	/**
	 * 手机app用户的闪电借款
	 */
	private String listingid;
	/**
	 * 原债权人
	 */
	private String originalCreditorRightsUsername;
	/**
	 * 转让金额
	 */
	private double transferAmount;
	/**
	 * 转让价格
	 */
	private double transferPrice;
	/**
	 * 转让人
	 */
	private String transferUsername;
	/**
	 * 转让时间
	 */
	private String transferTime;
	/**
	 * 转让方法
	 */
	private String transerMethod;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
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
	public String getOriginalCreditorRightsUsername() {
		return originalCreditorRightsUsername;
	}
	public void setOriginalCreditorRightsUsername(String originalCreditorRightsUsername) {
		this.originalCreditorRightsUsername = originalCreditorRightsUsername;
	}
	public double getTransferAmount() {
		return transferAmount;
	}
	public void setTransferAmount(double transferAmount) {
		this.transferAmount = transferAmount;
	}
	public double getTransferPrice() {
		return transferPrice;
	}
	public void setTransferPrice(double transferPrice) {
		this.transferPrice = transferPrice;
	}
	public String getTransferUsername() {
		return transferUsername;
	}
	public void setTransferUsername(String transferUsername) {
		this.transferUsername = transferUsername;
	}
	public String getTransferTime() {
		return transferTime;
	}
	public void setTransferTime(String transferTime) {
		this.transferTime = transferTime;
	}
	public String getTranserMethod() {
		return transerMethod;
	}
	public void setTranserMethod(String transerMethod) {
		this.transerMethod = transerMethod;
	}
}
