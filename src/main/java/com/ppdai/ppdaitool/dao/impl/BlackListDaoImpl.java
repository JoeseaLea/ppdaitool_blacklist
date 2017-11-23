package com.ppdai.ppdaitool.dao.impl;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.BatchPreparedStatementSetter;

import com.ppdai.ppdaitool.dao.BaseDao;
import com.ppdai.ppdaitool.dao.BlackListDao;
import com.ppdai.ppdaitool.vo.BlacklistVo;
import com.ppdai.ppdaitool.vo.RepaymentDetailVo;

public final class BlackListDaoImpl extends BaseDao implements BlackListDao {
	
	@Override
	public void addBlackList(BlacklistVo blacklistVo) {

	}

	@Override
	public void addBlackLists(List<BlacklistVo> blacklist) {
		try {
			if (null == blacklist || 0 >= blacklist.size()) {
				return;
			}
			
			final List<BlacklistVo> tempBlacklist = blacklist;
			String sql = "INSERT INTO `blacklist`" + 
					"(`username`," + 
					"`listingid`," +
					"`overdue_capital`," + 
					"`give_back_capital`," + 
					"`bid_capital`," + 
					"`over_days`," + 
					"`max_over_days`," + 
					"`percent_of_get_back`)" + 
					"VALUES(?, ?, ?, ?, ?, ?, ?, ?)" +
					"ON DUPLICATE KEY UPDATE" +
					"`listingid` = ?," +
					"`overdue_capital` = ?," + 
					"`give_back_capital` = ?," + 
					"`bid_capital` = ?," + 
					"`over_days` = ?," + 
					"`max_over_days` = ?," + 
					"`percent_of_get_back` = ?";
			
			this.jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
				
				@Override
				public void setValues(PreparedStatement ps, int i) throws SQLException {
					BlacklistVo blacklistVo = tempBlacklist.get(i);
					ps.setString(1, blacklistVo.getUsername());
					ps.setString(2, blacklistVo.getListingid());
					ps.setDouble(3, blacklistVo.getOverdueCapital());
					ps.setDouble(4, blacklistVo.getGiveBackCapital());
					ps.setDouble(5, blacklistVo.getBidCapital());
					ps.setInt(6, blacklistVo.getOverDays());
					ps.setInt(7, blacklistVo.getMaxOverDays());
					ps.setDouble(8, blacklistVo.getPercentOfGetBack());
					ps.setString(9, blacklistVo.getListingid());
					ps.setDouble(10, blacklistVo.getOverdueCapital());
					ps.setDouble(11, blacklistVo.getGiveBackCapital());
					ps.setDouble(12, blacklistVo.getBidCapital());
					ps.setInt(13, blacklistVo.getOverDays());
					ps.setInt(14, blacklistVo.getMaxOverDays());
					ps.setDouble(15, blacklistVo.getPercentOfGetBack());
				}
				
				@Override
				public int getBatchSize() {
					return tempBlacklist.size();
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void addRepaymentDetail(RepaymentDetailVo repaymentDetail) {
		
	}

	@Override
	public void addRepaymentDetails(List<RepaymentDetailVo> repaymentDetails) {
		try {
			if (null == repaymentDetails || 0 >= repaymentDetails.size()) {
				return;
			}
			
			final List<RepaymentDetailVo> tempRepaymentDetails = repaymentDetails;
			
			String sql = "INSERT INTO `ppdai`.`repayment_detail` " +
					"(`listingid`, " +
					"`return_date`, " +
					"`withdraw`, " +
					"`not_withdraw`, " +
					"`not_get_back_capital`, " +
					"`not_get_interest`, " +
					"`over_time_interest`, " +
					"`over_days`, " +
					"`status`, " +
					"`sequence`) " +
					"VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?) " +
					"ON DUPLICATE KEY UPDATE " +
					"`listingid` = ?, " +
					"`return_date` = ?, " +
					"`withdraw` = ?, " +
					"`not_withdraw` = ?, " +
					"`not_get_back_capital` = ?, " +
					"`not_get_interest` = ?, " +
					"`over_time_interest` = ?, " +
					"`over_days` = ?, " +
					"`status` = ?, " +
					"`sequence` = ? ";
			
			this.jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
				
				@Override
				public void setValues(PreparedStatement ps, int i) throws SQLException {
					RepaymentDetailVo repaymentDetailVo = tempRepaymentDetails.get(i);
					ps.setString(1, repaymentDetailVo.getListingid());
					ps.setString(2, repaymentDetailVo.getReturnDate());
					ps.setDouble(3, repaymentDetailVo.getWithdraw());
					ps.setDouble(4, repaymentDetailVo.getNotWithdraw());
					ps.setDouble(5, repaymentDetailVo.getNotGetBackCapital());
					ps.setDouble(6, repaymentDetailVo.getNotGetInterest());
					ps.setDouble(7, repaymentDetailVo.getOverTimeInterest());
					ps.setInt(8, repaymentDetailVo.getOverDays());
					ps.setString(9, repaymentDetailVo.getStatus());
					ps.setInt(10, repaymentDetailVo.getSequence());
					ps.setString(11, repaymentDetailVo.getListingid());
					ps.setString(12, repaymentDetailVo.getReturnDate());
					ps.setDouble(13, repaymentDetailVo.getWithdraw());
					ps.setDouble(14, repaymentDetailVo.getNotWithdraw());
					ps.setDouble(15, repaymentDetailVo.getNotGetBackCapital());
					ps.setDouble(16, repaymentDetailVo.getNotGetInterest());
					ps.setDouble(17, repaymentDetailVo.getOverTimeInterest());
					ps.setInt(18, repaymentDetailVo.getOverDays());
					ps.setString(19, repaymentDetailVo.getStatus());
					ps.setInt(20, repaymentDetailVo.getSequence());
				}
				
				@Override
				public int getBatchSize() {
					return tempRepaymentDetails.size();
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void updateFullUserName(String fullUsername, String listingid) {
		try {
			String sql = "UPDATE `ppdai`.`blacklist`"
					+ " SET `full_username` = '" + fullUsername
					+ "' WHERE `listingid` = '" + listingid + "'";
			this.jdbcTemplate.batchUpdate(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
}
