package com.ppdai.ppdaitool.vo;

/**
 * 黑名单Vo
 *
 */
public class BlacklistVo {

	/**
	 * 主键
	 */
	private int id;
	/**
	 * 用户名
	 */
	private String username;
	/**
	 * 手机app用户的第1次闪电借款
	 */
	private String listingid;
	/**
	 * 逾期本金
	 */
	private double overdueCapital;
	/**
	 * 已还金额
	 */
	private double giveBackCapital;
	/**
	 * 投标金额
	 */
	private double bidCapital;
	/**
	 * 逾期天数
	 */
	private int overDays;
	/**
	 * 最大逾期天数
	 */
	private int maxOverDays;
	/**
	 * 催收成功可能性
	 */
	private double percentOfGetBack;
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
	public String getListingid() {
		return listingid;
	}
	public void setListingid(String listingid) {
		this.listingid = listingid;
	}
	public double getOverdueCapital() {
		return overdueCapital;
	}
	public void setOverdueCapital(double overdueCapital) {
		this.overdueCapital = overdueCapital;
	}
	public double getGiveBackCapital() {
		return giveBackCapital;
	}
	public void setGiveBackCapital(double giveBackCapital) {
		this.giveBackCapital = giveBackCapital;
	}
	public double getBidCapital() {
		return bidCapital;
	}
	public void setBidCapital(double bidCapital) {
		this.bidCapital = bidCapital;
	}
	public int getOverDays() {
		return overDays;
	}
	public void setOverDays(int overDays) {
		this.overDays = overDays;
	}
	public int getMaxOverDays() {
		return maxOverDays;
	}
	public void setMaxOverDays(int maxOverDays) {
		this.maxOverDays = maxOverDays;
	}
	public double getPercentOfGetBack() {
		return percentOfGetBack;
	}
	public void setPercentOfGetBack(double percentOfGetBack) {
		this.percentOfGetBack = percentOfGetBack;
	}
}
