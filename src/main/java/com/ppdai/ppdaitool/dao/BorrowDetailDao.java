package com.ppdai.ppdaitool.dao;

import java.util.List;

import com.ppdai.ppdaitool.vo.BidDebtRecordVo;
import com.ppdai.ppdaitool.vo.BidRecordVo;
import com.ppdai.ppdaitool.vo.BorrowDetailPageVo;
import com.ppdai.ppdaitool.vo.BorrowDetailVo;
import com.ppdai.ppdaitool.vo.NeedReturnRecordNext6MonthVo;
import com.ppdai.ppdaitool.vo.OverTimeRecordLast6MonthVo;

/**
 * 借款详情DAO类
 * @author Joesea Lea
 */
public interface BorrowDetailDao {
	public void addBorrowDetailPageVo(BorrowDetailPageVo borrowDetailPageVo);

	public void addBidRecords(List<BidRecordVo> bidRecords);

	public void addBidDebtRecords(List<BidDebtRecordVo> bidDebtRecords);

	public void addBorrowDetails(List<BorrowDetailVo> borrowDetails);

	public void addNeedReturnRecordNext6MonthVos(List<NeedReturnRecordNext6MonthVo> needReturnRecordNext6MonthVos);

	public void addOverTimeRecordLast6MonthVos(List<OverTimeRecordLast6MonthVo> overTimeRecordLast6MonthVos);
}
