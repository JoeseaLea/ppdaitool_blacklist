package com.ppdai.ppdaitool.dao.impl;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.BatchPreparedStatementSetter;

import com.ppdai.ppdaitool.dao.BaseDao;
import com.ppdai.ppdaitool.dao.BorrowDetailDao;
import com.ppdai.ppdaitool.vo.BidDebtRecordVo;
import com.ppdai.ppdaitool.vo.BidRecordVo;
import com.ppdai.ppdaitool.vo.BorrowDetailPageVo;
import com.ppdai.ppdaitool.vo.BorrowDetailVo;
import com.ppdai.ppdaitool.vo.NeedReturnRecordNext6MonthVo;
import com.ppdai.ppdaitool.vo.OverTimeRecordLast6MonthVo;

public class BorrowDetailDaoImpl extends BaseDao implements BorrowDetailDao {
	
	@Override
	public void addBorrowDetailPageVo(BorrowDetailPageVo borrowDetailPageVo) {
		try {
			if (null == borrowDetailPageVo) {
				return;
			}
			
			StringBuilder sql = new StringBuilder();
			sql.append("INSERT INTO `ppdai`.`borrow_detail_page`")
			.append(" (")
			.append(" `listingid`,")
			.append(" `level`,")
			.append(" `borrow_amount`,")
			.append(" `annual_interest_rate`,")
			.append(" `time_limit`,")
			.append(" `return_method`,")
			.append(" `bid_number`,")
			.append(" `end_return_time`,")
			.append(" `sex`,")
			.append(" `age`,")
			.append(" `register_time`,")
			.append(" `degree_of_education`,")
			.append(" `school_of_graduation`,")
			.append(" `forms_of_learning`,")
			.append(" `success_number`,")
			.append(" `history_record`,")
			.append(" `borrow_first_time`,")
			.append(" `return_success_num`,")
			.append(" `return_normal_num`,")
			.append(" `over_time_num1`,")
			.append(" `over_time_num2`,")
			.append(" `total_borrow_amount`,")
			.append(" `total_need_return_amount`,")
			.append(" `total_need_get_amount`,")
			.append(" `max_borrow_amount`,")
			.append(" `max_debt_history`)")
			.append(" VALUES (")
			.append(" '" + borrowDetailPageVo.getListingid() + "',")
			.append(" '" + borrowDetailPageVo.getLevel() + "',")
			.append(" '" + borrowDetailPageVo.getBorrowAmount() + "',")
			.append(" '" + borrowDetailPageVo.getAnnualInterestRate() + "',")
			.append(" '" + borrowDetailPageVo.getTimeLimit() + "',")
			.append(" '" + borrowDetailPageVo.getReturnMethod() + "',")
			.append(" '" + borrowDetailPageVo.getBidNumber() + "',")
			.append(" '" + borrowDetailPageVo.getEndReturnTime() + "',")
			.append(" '" + borrowDetailPageVo.getSex() + "',")
			.append(" '" + borrowDetailPageVo.getAge() + "',")
			.append(" '" + borrowDetailPageVo.getRegisterTime() + "',")
			.append(" '" + borrowDetailPageVo.getDegreeOfEducation() + "',")
			.append(" '" + borrowDetailPageVo.getSchoolOfGraduation() + "',")
			.append(" '" + borrowDetailPageVo.getFormsOfLearning() + "',")
			.append(" '" + borrowDetailPageVo.getSuccessNumber() + "',")
			.append(" '" + borrowDetailPageVo.getHistoryRecord() + "',")
			.append(" '" + borrowDetailPageVo.getBorrowFirstTime() + "',")
			.append(" '" + borrowDetailPageVo.getReturnSuccessNum() + "',")
			.append(" '" + borrowDetailPageVo.getReturnNormalNum() + "',")
			.append(" '" + borrowDetailPageVo.getOverTimeNum1() + "',")
			.append(" '" + borrowDetailPageVo.getOverTimeNum2() + "',")
			.append(" '" + borrowDetailPageVo.getTotalBorrowAmount() + "',")
			.append(" '" + borrowDetailPageVo.getTotalNeedReturnAmount() + "',")
			.append(" '" + borrowDetailPageVo.getTotalNeedGetAmount() + "',")
			.append(" '" + borrowDetailPageVo.getMaxBorrowAmount() + "',")
			.append(" '" + borrowDetailPageVo.getMaxDebtHistory() + "')")
			.append(" ON DUPLICATE KEY UPDATE")
			.append(" `listingid` = " + borrowDetailPageVo.getListingid() + "");
			
			this.jdbcTemplate.execute(sql.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void addBidRecords(List<BidRecordVo> bidRecords) {
		try {
			if (null == bidRecords || 0 >= bidRecords.size()) {
				return;
			}
			
			final List<BidRecordVo> tempBidRecords = bidRecords;
			String sql = "INSERT INTO `ppdai`.`bid_record`"
					+ " (`full_username`,"
					+ " `listingid`,"
					+ " `bid_username`,"
					+ " `percent_of_interest`,"
					+ " `time_long`,"
					+ " `valid_bid_capital`,"
					+ " `bid_time`)"
					+ " VALUES (?, ?, ?, ?, ?, ?, ?)"
					+ " ON DUPLICATE KEY UPDATE"
					+ " `full_username` = ?,"
					+ " `listingid` = ?,"
					+ " `bid_username` = ?,"
					+ " `percent_of_interest` = ?,"
					+ " `time_long` = ?,"
					+ " `valid_bid_capital` = ?,"
					+ " `bid_time` = ?";
			this.jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
				
				@Override
				public void setValues(PreparedStatement ps, int i) throws SQLException {
					
					BidRecordVo bidRecordsVo = tempBidRecords.get(i);
					
					ps.setString(1, bidRecordsVo.getFullUsername());
					ps.setString(2, bidRecordsVo.getListingid());
					ps.setString(3, bidRecordsVo.getBidUsername());
					ps.setDouble(4, bidRecordsVo.getPercentOfInterest());
					ps.setString(5, bidRecordsVo.getTimeLong());
					ps.setInt(6, bidRecordsVo.getValidBidCapital());
					ps.setString(7, bidRecordsVo.getBidtime());
					ps.setString(8, bidRecordsVo.getFullUsername());
					ps.setString(9, bidRecordsVo.getListingid());
					ps.setString(10, bidRecordsVo.getBidUsername());
					ps.setDouble(11, bidRecordsVo.getPercentOfInterest());
					ps.setString(12, bidRecordsVo.getTimeLong());
					ps.setInt(13, bidRecordsVo.getValidBidCapital());
					ps.setString(14, bidRecordsVo.getBidtime());
					
				}
				
				@Override
				public int getBatchSize() {
					return tempBidRecords.size();
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void addBidDebtRecords(List<BidDebtRecordVo> bidDebtRecords) {
		try {
			if (null == bidDebtRecords || 0 >= bidDebtRecords.size()) {
				return;
			}
			
			final List<BidDebtRecordVo> tempBidDebtRecords = bidDebtRecords;
			
			String sql = "INSERT INTO `ppdai`.`bid_debt_record`"
					+ " ("
					+ " `full_username`,"
					+ " `listingid`,"
					+ " `original_creditor_rights_username`,"
					+ " `transfer_amount`,"
					+ " `transfer_price`,"
					+ " `transfer_username`,"
					+ " `transfer_time`,"
					+ " `transer_method`)"
					+ " VALUES (?, ?, ?, ?, ?, ?, ?, ?)"
					+ " ON DUPLICATE KEY UPDATE"
					+ " `full_username` = ?,"
					+ " `listingid` = ?,"
					+ " `original_creditor_rights_username` = ?,"
					+ " `transfer_amount` = ?,"
					+ " `transfer_price` = ?,"
					+ " `transfer_username` = ?,"
					+ " `transfer_time` = ?,"
					+ " `transer_method` = ?";
			
			this.jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
				
				@Override
				public void setValues(PreparedStatement ps, int i) throws SQLException {
					
					BidDebtRecordVo bidDebtRecordsVo = tempBidDebtRecords.get(i);
					
					ps.setString(1, bidDebtRecordsVo.getFullUsername());
					ps.setString(2, bidDebtRecordsVo.getListingid());
					ps.setString(3, bidDebtRecordsVo.getOriginalCreditorRightsUsername());
					ps.setDouble(4, bidDebtRecordsVo.getTransferAmount());
					ps.setDouble(5, bidDebtRecordsVo.getTransferPrice());
					ps.setString(6, bidDebtRecordsVo.getTransferUsername());
					ps.setString(7, bidDebtRecordsVo.getTransferTime());
					ps.setString(8, bidDebtRecordsVo.getTranserMethod());
					ps.setString(9, bidDebtRecordsVo.getFullUsername());
					ps.setString(10, bidDebtRecordsVo.getListingid());
					ps.setString(11, bidDebtRecordsVo.getOriginalCreditorRightsUsername());
					ps.setDouble(12, bidDebtRecordsVo.getTransferAmount());
					ps.setDouble(13, bidDebtRecordsVo.getTransferPrice());
					ps.setString(14, bidDebtRecordsVo.getTransferUsername());
					ps.setString(15, bidDebtRecordsVo.getTransferTime());
					ps.setString(16, bidDebtRecordsVo.getTranserMethod());
				}
				
				@Override
				public int getBatchSize() {
					return tempBidDebtRecords.size();
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void addBorrowDetails(List<BorrowDetailVo> borrowDetails) {
		try {
			if (null == borrowDetails || 0 >= borrowDetails.size()) {
				return;
			}
			
			final List<BorrowDetailVo> tempBorrowDetails = borrowDetails;
			
			String sql = "INSERT INTO `ppdai`.`borrow_detail`"
					+ " ("
					+ " `full_username`,"
					+ " `listingid`,"
					+ " `borrow_bid`,"
					+ " `percent_of_interest`,"
					+ " `time_limit`,"
					+ " `amount`,"
					+ " `date_issued`,"
					+ " `status`)"
					+ " VALUES (?, ?, ?, ?, ?, ?, ?, ?)"
					+ " ON DUPLICATE KEY UPDATE"
					+ " `full_username` = ?,"
					+ " `listingid` = ?,"
					+ " `borrow_bid` = ?,"
					+ " `percent_of_interest` = ?,"
					+ " `time_limit` = ?,"
					+ " `amount` = ?,"
					+ " `date_issued` = ?,"
					+ " `status` = ?";
			
			this.jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
				
				@Override
				public void setValues(PreparedStatement ps, int i) throws SQLException {
					
					BorrowDetailVo borrowDetailVo = tempBorrowDetails.get(i);
					ps.setString(1, borrowDetailVo.getFullUsername());
					ps.setString(2, borrowDetailVo.getListingid());
					ps.setString(3, borrowDetailVo.getBorrowBid());
					ps.setDouble(4, borrowDetailVo.getPercentOfInterest());
					ps.setString(5, borrowDetailVo.getTimeLimit());
					ps.setInt(6, borrowDetailVo.getAmount());
					ps.setString(7, borrowDetailVo.getDateIssued());
					ps.setString(8, borrowDetailVo.getStatus());
					ps.setString(9, borrowDetailVo.getFullUsername());
					ps.setString(10, borrowDetailVo.getListingid());
					ps.setString(11, borrowDetailVo.getBorrowBid());
					ps.setDouble(12, borrowDetailVo.getPercentOfInterest());
					ps.setString(13, borrowDetailVo.getTimeLimit());
					ps.setInt(14, borrowDetailVo.getAmount());
					ps.setString(15, borrowDetailVo.getDateIssued());
					ps.setString(16, borrowDetailVo.getStatus());
				}
				
				@Override
				public int getBatchSize() {
					return tempBorrowDetails.size();
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void addNeedReturnRecordNext6MonthVos(List<NeedReturnRecordNext6MonthVo> needReturnRecordNext6MonthVos) {
		try {
			if (null == needReturnRecordNext6MonthVos || 0 >= needReturnRecordNext6MonthVos.size()) {
				return;
			}
			
			final List<NeedReturnRecordNext6MonthVo> tempNeedReturnRecordNext6MonthVos = needReturnRecordNext6MonthVos;
			
			String sql = "INSERT INTO `ppdai`.`need_return_record_next_6_month`"
					+ " ("
					+ " `full_username`,"
					+ " `listingid`,"
					+ " `date_time`,"
					+ " `amount`)"
					+ " VALUES (?, ?, ?, ?)"
					+" ON DUPLICATE KEY UPDATE"
					+" `listingid` = ?";;
					this.jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
						
						@Override
						public void setValues(PreparedStatement ps, int i) throws SQLException {
							NeedReturnRecordNext6MonthVo needReturnRecordNext6MonthVo = tempNeedReturnRecordNext6MonthVos.get(i);
							
							ps.setString(1, needReturnRecordNext6MonthVo.getFullUsername());
							ps.setString(2, needReturnRecordNext6MonthVo.getListingid());
							ps.setString(3, needReturnRecordNext6MonthVo.getDateTime());
							ps.setDouble(4, needReturnRecordNext6MonthVo.getAmount());
							ps.setString(5, needReturnRecordNext6MonthVo.getListingid());
						}
						
						@Override
						public int getBatchSize() {
							return tempNeedReturnRecordNext6MonthVos.size();
						}
					});
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void addOverTimeRecordLast6MonthVos(List<OverTimeRecordLast6MonthVo> overTimeRecordLast6MonthVos) {
		try {
			if (null == overTimeRecordLast6MonthVos || 0 >= overTimeRecordLast6MonthVos.size()) {
				return;
			}
			
			final List<OverTimeRecordLast6MonthVo> tempOverTimeRecordLast6MonthVos = overTimeRecordLast6MonthVos;
			
			String sql = "INSERT INTO `ppdai`.`over_time_record_last_6_month`"
					+ " ("
					+ " `full_username`,"
					+ " `listingid`,"
					+ " `date_time`,"
					+ " `day_number`)"
					+ " VALUES (?, ?, ?, ?)"
					+" ON DUPLICATE KEY UPDATE"
					+" `listingid` = ?";
			
			this.jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
				
				@Override
				public void setValues(PreparedStatement ps, int i) throws SQLException {
					OverTimeRecordLast6MonthVo overTimeRecordLast6MonthVo = tempOverTimeRecordLast6MonthVos.get(i);
					
					ps.setString(1, overTimeRecordLast6MonthVo.getFullUsername());
					ps.setString(2, overTimeRecordLast6MonthVo.getListingid());
					ps.setString(3, overTimeRecordLast6MonthVo.getDateTime());
					ps.setInt(4, overTimeRecordLast6MonthVo.getDayNumber());
					ps.setString(5, overTimeRecordLast6MonthVo.getListingid());
				}
				
				@Override
				public int getBatchSize() {
					return tempOverTimeRecordLast6MonthVos.size();
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
}
