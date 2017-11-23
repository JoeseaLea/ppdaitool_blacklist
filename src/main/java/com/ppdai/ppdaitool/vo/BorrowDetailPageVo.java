package com.ppdai.ppdaitool.vo;

public class BorrowDetailPageVo {
	/**
	 * listingid
	 */
	private String listingid;
	/**
	 * 魔镜等级
	 */
	private String level;
	/**
	 * 借款金额
	 */
	private int borrowAmount;
	/**
	 * 年利率
	 */
	private double annualInterestRate;
	/**
	 * 期限
	 */
	private String timeLimit;
	/**
	 * 还款方式
	 */
	private String returnMethod;
	/**
	 * 投标人数
	 */
	private int bidNumber;
	/**
	 * 还款结束时间
	 */
	private String endReturnTime;
	/**
	 * 性别
	 */
	private String sex;
	/**
	 * 年龄
	 */
	private int age;
	/**
	 * 注册时间
	 */
	private String registerTime;
	/**
	 * 文化程度
	 */
	private String degreeOfEducation;
	/**
	 * 毕业院校
	 */
	private String schoolOfGraduation;
	/**
	 * 学习形式
	 */
	private String formsOfLearning;
	/**
	 * 成功借款次数
	 */
	private int successNumber;
	/**
	 * 历史记录
	 */
	private String historyRecord;
	/**
	 * 第一次成功借款时间
	 */
	private String borrowFirstTime;
//	private String FIELD12", "最后", 60));
//	private String FIELD13", "短借", 25));
	/**
	 * 成功还款次数
	 */
	private int returnSuccessNum;
	/**
	 * 正常还清次数
	 */
	private int returnNormalNum;
	/**
	 * 逾期(0-15天)还清次数
	 */
	private int overTimeNum1;
	/**
	 * 逾期(15天以上)还清次数
	 */
	private int overTimeNum2;
	//todo 未来6个月的待还记录
	//todo 过去6个月有回款记录的逾期天数
	/**
	 * 累计借款金额
	 */
	private double totalBorrowAmount;
	/**
	 * 待还金额
	 */
	private double totalNeedReturnAmount;
	/**
	 * 待收金额
	 */
	private double totalNeedGetAmount;
	/**
	 * 单笔最高借款金额
	 */
	private double maxBorrowAmount;
	/**
	 * 历史最高负债
	 */
	private double maxDebtHistory;
	public String getListingid() {
		return listingid;
	}
	public void setListingid(String listingid) {
		this.listingid = listingid;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	public int getBorrowAmount() {
		return borrowAmount;
	}
	public void setBorrowAmount(int borrowAmount) {
		this.borrowAmount = borrowAmount;
	}
	public double getAnnualInterestRate() {
		return annualInterestRate;
	}
	public void setAnnualInterestRate(double annualInterestRate) {
		this.annualInterestRate = annualInterestRate;
	}
	public String getTimeLimit() {
		return timeLimit;
	}
	public void setTimeLimit(String timeLimit) {
		this.timeLimit = timeLimit;
	}
	public String getReturnMethod() {
		return returnMethod;
	}
	public void setReturnMethod(String returnMethod) {
		this.returnMethod = returnMethod;
	}
	public int getBidNumber() {
		return bidNumber;
	}
	public void setBidNumber(int bidNumber) {
		this.bidNumber = bidNumber;
	}
	public String getEndReturnTime() {
		return endReturnTime;
	}
	public void setEndReturnTime(String endReturnTime) {
		this.endReturnTime = endReturnTime;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getRegisterTime() {
		return registerTime;
	}
	public void setRegisterTime(String registerTime) {
		this.registerTime = registerTime;
	}
	public String getDegreeOfEducation() {
		return degreeOfEducation;
	}
	public void setDegreeOfEducation(String degreeOfEducation) {
		this.degreeOfEducation = degreeOfEducation;
	}
	public String getSchoolOfGraduation() {
		return schoolOfGraduation;
	}
	public void setSchoolOfGraduation(String schoolOfGraduation) {
		this.schoolOfGraduation = schoolOfGraduation;
	}
	public String getFormsOfLearning() {
		return formsOfLearning;
	}
	public void setFormsOfLearning(String formsOfLearning) {
		this.formsOfLearning = formsOfLearning;
	}
	public int getSuccessNumber() {
		return successNumber;
	}
	public void setSuccessNumber(int successNumber) {
		this.successNumber = successNumber;
	}
	public String getHistoryRecord() {
		return historyRecord;
	}
	public void setHistoryRecord(String historyRecord) {
		this.historyRecord = historyRecord;
	}
	public String getBorrowFirstTime() {
		return borrowFirstTime;
	}
	public void setBorrowFirstTime(String borrowFirstTime) {
		this.borrowFirstTime = borrowFirstTime;
	}
	public int getReturnSuccessNum() {
		return returnSuccessNum;
	}
	public void setReturnSuccessNum(int returnSuccessNum) {
		this.returnSuccessNum = returnSuccessNum;
	}
	public int getReturnNormalNum() {
		return returnNormalNum;
	}
	public void setReturnNormalNum(int returnNormalNum) {
		this.returnNormalNum = returnNormalNum;
	}
	public int getOverTimeNum1() {
		return overTimeNum1;
	}
	public void setOverTimeNum1(int overTimeNum1) {
		this.overTimeNum1 = overTimeNum1;
	}
	public int getOverTimeNum2() {
		return overTimeNum2;
	}
	public void setOverTimeNum2(int overTimeNum2) {
		this.overTimeNum2 = overTimeNum2;
	}
	public double getTotalBorrowAmount() {
		return totalBorrowAmount;
	}
	public void setTotalBorrowAmount(double totalBorrowAmount) {
		this.totalBorrowAmount = totalBorrowAmount;
	}
	public double getTotalNeedReturnAmount() {
		return totalNeedReturnAmount;
	}
	public void setTotalNeedReturnAmount(double totalNeedReturnAmount) {
		this.totalNeedReturnAmount = totalNeedReturnAmount;
	}
	public double getTotalNeedGetAmount() {
		return totalNeedGetAmount;
	}
	public void setTotalNeedGetAmount(double totalNeedGetAmount) {
		this.totalNeedGetAmount = totalNeedGetAmount;
	}
	public double getMaxBorrowAmount() {
		return maxBorrowAmount;
	}
	public void setMaxBorrowAmount(double maxBorrowAmount) {
		this.maxBorrowAmount = maxBorrowAmount;
	}
	public double getMaxDebtHistory() {
		return maxDebtHistory;
	}
	public void setMaxDebtHistory(double maxDebtHistory) {
		this.maxDebtHistory = maxDebtHistory;
	}
}
