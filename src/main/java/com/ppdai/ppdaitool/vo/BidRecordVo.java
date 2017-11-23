package com.ppdai.ppdaitool.vo;

public class BidRecordVo {
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
	 * 投标人
	 */
	private String bidUsername;
	/**
	 * 投标利率
	 */
	private double percentOfInterest;
	/**
	 * 标期限
	 */
	private String timeLong;
	/**
	 * 有效投标金额
	 */
	private int validBidCapital;
	/**
	 * 投标时间
	 */
	private String bidtime;
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
	public String getBidUsername() {
		return bidUsername;
	}
	public void setBidUsername(String bidUsername) {
		this.bidUsername = bidUsername;
	}
	public double getPercentOfInterest() {
		return percentOfInterest;
	}
	public void setPercentOfInterest(double percentOfInterest) {
		this.percentOfInterest = percentOfInterest;
	}
	
	public String getTimeLong() {
		return timeLong;
	}
	public void setTimeLong(String timeLong) {
		this.timeLong = timeLong;
	}
	public int getValidBidCapital() {
		return validBidCapital;
	}
	public void setValidBidCapital(int validBidCapital) {
		this.validBidCapital = validBidCapital;
	}
	public String getBidtime() {
		return bidtime;
	}
	public void setBidtime(String bidtime) {
		this.bidtime = bidtime;
	}
}
