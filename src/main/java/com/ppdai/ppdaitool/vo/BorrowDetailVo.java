package com.ppdai.ppdaitool.vo;

public class BorrowDetailVo {
	/**
	 * 主键
	 */
	private int id;
	/**
	 * 用户名
	 */
	private String username;
	/**
	 * 完整用户名
	 */
	private String fullUsername;
	/**
	 * 手机app用户的闪电借款
	 */
	private String listingid;
	/**
	 * 借款标的
	 */
	private String borrowBid;
	/**
	 * 利率
	 */
	private double percentOfInterest;
	/**
	 * 期限
	 */
	private String timeLimit;
	/**
	 * 金额
	 */
	private int amount;
	/**
	 * 已发布
	 */
	private String dateIssued;
	/**
	 * 状态
	 */
	private String status;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
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
	public String getBorrowBid() {
		return borrowBid;
	}
	public void setBorrowBid(String borrowBid) {
		this.borrowBid = borrowBid;
	}
	public double getPercentOfInterest() {
		return percentOfInterest;
	}
	public void setPercentOfInterest(double percentOfInterest) {
		this.percentOfInterest = percentOfInterest;
	}
	public String getTimeLimit() {
		return timeLimit;
	}
	public void setTimeLimit(String timeLimit) {
		this.timeLimit = timeLimit;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public String getDateIssued() {
		return dateIssued;
	}
	public void setDateIssued(String dateIssued) {
		this.dateIssued = dateIssued;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
}
