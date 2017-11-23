package com.ppdai.ppdaitool.vo;

/**
 * 还款详情Vo
 *
 */
public class RepaymentDetailVo {
	/**
	 * 主键
	 */
	private int id;
	/**
	 * 手机app用户的第1次闪电借款
	 */
	private String listingid;
	/**
	 * 还款日
	 */
	private String returnDate;
	/**
	 * 已收本息
	 */
	private double withdraw;
	/**
	 * 未收本息
	 */
	private double notWithdraw;
	/**
	 * 未收本金
	 */
	private double notGetBackCapital;
	/**
	 * 未收利息
	 */
	private double notGetInterest;
	/**
	 * 逾期利息
	 */
	private double overTimeInterest;
	/**
	 * 逾期天数
	 */
	private int overDays;
	/**
	 * 状态
	 */
	private String status;
	/**
	 * 排序
	 */
	private int sequence;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getListingid() {
		return listingid;
	}
	public void setListingid(String listingid) {
		this.listingid = listingid;
	}
	public String getReturnDate() {
		return returnDate;
	}
	public void setReturnDate(String returnDate) {
		this.returnDate = returnDate;
	}
	public double getWithdraw() {
		return withdraw;
	}
	public void setWithdraw(double withdraw) {
		this.withdraw = withdraw;
	}
	public double getNotWithdraw() {
		return notWithdraw;
	}
	public void setNotWithdraw(double notWithdraw) {
		this.notWithdraw = notWithdraw;
	}
	public double getNotGetBackCapital() {
		return notGetBackCapital;
	}
	public void setNotGetBackCapital(double notGetBackCapital) {
		this.notGetBackCapital = notGetBackCapital;
	}
	public double getNotGetInterest() {
		return notGetInterest;
	}
	public void setNotGetInterest(double notGetInterest) {
		this.notGetInterest = notGetInterest;
	}
	public double getOverTimeInterest() {
		return overTimeInterest;
	}
	public void setOverTimeInterest(double overTimeInterest) {
		this.overTimeInterest = overTimeInterest;
	}
	public int getOverDays() {
		return overDays;
	}
	public void setOverDays(int overDays) {
		this.overDays = overDays;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public int getSequence() {
		return sequence;
	}
	public void setSequence(int sequence) {
		this.sequence = sequence;
	}
}
