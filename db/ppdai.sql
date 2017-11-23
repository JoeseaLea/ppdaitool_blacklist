DROP DATABASE IF EXISTS ppdai;

CREATE DATABASE ppdai DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;

USE ppdai;

/*
 * 黑名单
 */
DROP TABLE IF EXISTS blacklist;
CREATE TABLE blacklist (
    id INT(12) PRIMARY KEY AUTO_INCREMENT COMMENT '主键',
    username VARCHAR(32) NOT NULL COMMENT '用户名',
    full_username VARCHAR(32) COMMENT '完整用户名',
    listingid VARCHAR(12) NOT NULL COMMENT '手机app用户的闪电借款',
    overdue_capital DOUBLE(12,2) NOT NULL COMMENT '逾期本金',
    give_back_capital DOUBLE(12,2) NOT NULL COMMENT '已还本金',
    bid_capital DOUBLE(12,2) NOT NULL COMMENT '投标金额',
    over_days INT(6) COMMENT '逾期天数',
    max_over_days INT(6) COMMENT '最大逾期天数',
    percent_of_get_back DOUBLE(8,4) COMMENT '催收成功可能性',
    UNIQUE KEY username_listingid (username, listingid)
);

/*
 * 黑名单还款详情
 */
DROP TABLE IF EXISTS repayment_detail;
CREATE TABLE repayment_detail(
    id INT(12) PRIMARY KEY AUTO_INCREMENT COMMENT '主键',
    listingid VARCHAR(12) NOT NULL COMMENT '手机app用户的闪电借款',
    return_date VARCHAR(10) NOT NULL COMMENT '还款日',
    withdraw DOUBLE(12,2) NOT NULL COMMENT '已收本息',
    not_withdraw DOUBLE(12,2) NOT NULL COMMENT '未收本息',
    not_get_back_capital DOUBLE(12,2) NOT NULL COMMENT '未收本金',
    not_get_interest DOUBLE(12,2) NOT NULL COMMENT '未收利息',
    over_time_interest DOUBLE(12,2) NOT NULL COMMENT '逾期利息',
    over_days INT(5) COMMENT '逾期天数',
    `status` VARCHAR(32) COMMENT '状态',
    sequence int(8) NOT NULL COMMENT '排序',
    UNIQUE KEY unique_key (listingid, return_date)
);

/*
 * 借款详情页面数据
 */
DROP TABLE IF EXISTS borrow_detail_page;
CREATE TABLE borrow_detail_page(
	id INT(12) PRIMARY KEY AUTO_INCREMENT COMMENT '主键',
	listingid VARCHAR(12) NOT NULL COMMENT '手机app用户的闪电借款',
	`level` VARCHAR(6) NOT NULL COMMENT '魔镜等级',
	borrow_amount INT(10) NOT NULL COMMENT '借款金额',
	annual_interest_rate DOUBLE(6,4) NOT NULL COMMENT '年利率',
	time_limit VARCHAR(12) COMMENT '期限',
	return_method VARCHAR(16) COMMENT '还款方式',
	bid_number INT(8) COMMENT '投标人数',
	end_return_time VARCHAR(12) COMMENT '还款结束时间',
	sex VARCHAR(2) NOT NULL COMMENT '性别',
	age INT(4) NOT NULL COMMENT '年龄',
	register_time VARCHAR(12) COMMENT '注册时间',
	degree_of_education VARCHAR(12) COMMENT '文化程度',
	school_of_graduation VARCHAR(32) COMMENT '毕业院校',
	forms_of_learning VARCHAR(32) COMMENT '学习形式',
	success_number INT(6) COMMENT '成功借款次数',
	history_record VARCHAR(64) COMMENT '历史记录',
	borrow_first_time VARCHAR(12) COMMENT '第一次成功借款时间',
	return_success_num INT(6) COMMENT '成功还款次数',
	return_normal_num INT(6) COMMENT '正常还清次数',
	over_time_num1 INT(6) COMMENT '逾期(0-15天)还清次数',
	over_time_num2 INT(6) COMMENT '逾期(15天以上)还清次数',
	total_borrow_amount DOUBLE(12, 2) COMMENT '累计借款金额',
	total_need_return_amount DOUBLE(12, 2) COMMENT '待还金额',
	total_need_get_amount DOUBLE(12, 2) COMMENT '待收金额',
	max_borrow_amount DOUBLE(12, 2) COMMENT '单笔最高借款金额',
	max_debt_history DOUBLE(12, 2) COMMENT '历史最高负债',
	UNIQUE KEY listingid (listingid)
);

/*
 * 投标记录
 */
DROP TABLE IF EXISTS  bid_record;
CREATE TABLE bid_record (
	id INT(12) PRIMARY KEY AUTO_INCREMENT COMMENT '主键',
	full_username VARCHAR(32) COMMENT '完整用户名',
	listingid VARCHAR(12) NOT NULL COMMENT '手机app用户的闪电借款',
	bid_username VARCHAR(32) NOT NULL COMMENT '投标人',
	percent_of_interest DOUBLE(4,2) NOT NULL COMMENT '投标利率',
	time_long VARCHAR(12) NOT NULL COMMENT '标期限',
	valid_bid_capital INT(10) NOT NULL COMMENT '有效投标金额',
	bid_time VARCHAR(20) NOT NULL COMMENT '投标金额',
	UNIQUE KEY unique_key (full_username, bid_username, bid_time)
);

/*
 * 债权转让记录
 */
DROP TABLE IF EXISTS bid_debt_record;
CREATE TABLE bid_debt_record(
	id INT(12) PRIMARY KEY AUTO_INCREMENT COMMENT '主键',
	full_username VARCHAR(32) COMMENT '完整用户名',
	listingid VARCHAR(12) NOT NULL COMMENT '手机app用户的闪电借款',
	original_creditor_rights_username VARCHAR(32) NOT NULL COMMENT '原债权人',
	transfer_amount DOUBLE(12,2) NOT NULL COMMENT '转让金额',
	transfer_price DOUBLE(12,2) NOT NULL COMMENT '转让价格',
	transfer_username VARCHAR(32) NOT NULL COMMENT '转让人',
	transfer_time VARCHAR(20) NOT NULL COMMENT '转让时间',
	transer_method VARCHAR(20) NOT NULL COMMENT '转让方法',
	UNIQUE KEY unique_key (full_username, original_creditor_rights_username, transfer_time)
);

/*
 * 借款记录
 */
DROP TABLE IF EXISTS borrow_detail;
CREATE TABLE borrow_detail(
	id INT(12) PRIMARY KEY AUTO_INCREMENT COMMENT '主键',
	full_username VARCHAR(32) COMMENT '完整用户名',
	listingid VARCHAR(12) NOT NULL COMMENT '手机app用户的闪电借款',
	borrow_bid VARCHAR(64) NOT NULL COMMENT '借款标的',
	percent_of_interest DOUBLE(6,4) NOT NULL COMMENT '利率',
	time_limit VARCHAR(12) NOT NULL COMMENT '期限',
	amount INT(10) NOT NULL COMMENT '金额',
	date_issued VARCHAR(12) NOT NULL COMMENT '已发布',
	`status` VARCHAR(8) NOT NULL COMMENT '状态',
	UNIQUE KEY unique_key (full_username, borrow_bid, amount, date_issued)
);

/*
 * 未来6个月的待还记录
 */
DROP TABLE IF EXISTS need_return_record_next_6_month;
CREATE TABLE need_return_record_next_6_month(
	id INT(12) PRIMARY KEY AUTO_INCREMENT COMMENT '主键',
	full_username VARCHAR(32) COMMENT '完整用户名',
	listingid VARCHAR(12) NOT NULL COMMENT '手机app用户的闪电借款',
	date_time VARCHAR(12) NOT NULL COMMENT '时间',
	amount DOUBLE(12, 2) NOT NULL COMMENT '金额',
	UNIQUE KEY unique_key (listingid, full_username, date_time, amount)
);

/**
 * 过去6个月有回款记录的逾期天数
 */
DROP TABLE IF EXISTS over_time_record_last_6_month;
CREATE TABLE over_time_record_last_6_month(
	id INT(12) PRIMARY KEY AUTO_INCREMENT COMMENT '主键',
	full_username VARCHAR(32) COMMENT '完整用户名',
	listingid VARCHAR(12) NOT NULL COMMENT '手机app用户的闪电借款',
	date_time VARCHAR(12) NOT NULL COMMENT '时间',
	day_number INT(6) NOT NULL COMMENT '最大逾期天数',
	UNIQUE KEY unique_key (listingid, full_username, date_time, day_number)
);