package com.ppdai.ppdaitool.dao;

import java.util.List;

import com.ppdai.ppdaitool.vo.BlacklistVo;
import com.ppdai.ppdaitool.vo.RepaymentDetailVo;

/**
 * 黑明单DAO类
 * @author Joesea Lea
 *
 */
public interface BlackListDao {
	/**
	 * 保存一个黑名单
	 * @param blacklistVo 黑名单
	 */
	public void addBlackList(BlacklistVo blacklistVo);
	
	/**
	 * 添加多个黑名单
	 * @param blacklist 黑名单列表
	 */
	public void addBlackLists(List<BlacklistVo> blacklist);
	
	/**
	 * 保存还款详情
	 * @param repaymentDetail 还款详情
	 */
	public void addRepaymentDetail(RepaymentDetailVo repaymentDetail);
	
	/**
	 * 保存黑名单还款详情
	 * @param repaymentDetails 黑名单还款详情
	 */
	public void addRepaymentDetails(List<RepaymentDetailVo> repaymentDetails);

	public void updateFullUserName(String fullUsername, String listingid);
}
