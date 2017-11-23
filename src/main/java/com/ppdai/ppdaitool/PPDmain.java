package com.ppdai.ppdaitool;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.ppdai.ppdaitool.dao.BlackListDao;
import com.ppdai.ppdaitool.dao.BorrowDetailDao;
import com.ppdai.ppdaitool.dao.impl.BlackListDaoImpl;
import com.ppdai.ppdaitool.dao.impl.BorrowDetailDaoImpl;
import com.ppdai.ppdaitool.htmlpage.analysis.BlackList;
import com.ppdai.ppdaitool.htmlpage.analysis.BorrowDetail;
import com.ppdai.ppdaitool.utils.DownloadHtmlPageUtil;
import com.ppdai.ppdaitool.utils.PPDUtil;
import com.ppdai.ppdaitool.utils.PropertiesUtil;
import com.ppdai.ppdaitool.vo.BidDebtRecordVo;
import com.ppdai.ppdaitool.vo.BidRecordVo;
import com.ppdai.ppdaitool.vo.BlacklistVo;
import com.ppdai.ppdaitool.vo.BorrowDetailPageVo;
import com.ppdai.ppdaitool.vo.BorrowDetailVo;
import com.ppdai.ppdaitool.vo.NeedReturnRecordNext6MonthVo;
import com.ppdai.ppdaitool.vo.OverTimeRecordLast6MonthVo;
import com.ppdai.ppdaitool.vo.RepaymentDetailVo;

public class PPDmain {
	
	private static BlackListDao blackListDao = null;
	private static BorrowDetailDao borrowDetailDao = null;
	
	public static void main(String[] args) {
		/**
		 * 加载spring配置文件
		 */
		ConfigurableApplicationContext ctx = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
		blackListDao = ctx.getBean("blackListDao", BlackListDaoImpl.class);
		borrowDetailDao = ctx.getBean("borrowDetailDao", BorrowDetailDaoImpl.class);
		
		try {
			HtmlPage htmlPage = null;
			
			String username = PropertiesUtil.getInstance().getProperty("username", "zhangsan");
			String password = PropertiesUtil.getInstance().getProperty("password", "******");
			
			//登录
			htmlPage = PPDUtil.login(username, password);
			
			// 获取黑名单页面
			htmlPage = PPDUtil.getUrlPage("http://invest.ppdai.com/account/blacklistnew");
			
			List<BlacklistVo> blacklist = null;
			List<RepaymentDetailVo> repaymentDetails = null;
			BorrowDetailPageVo borrowDetailPageVo = null;
			List<BidRecordVo> bidRecords = null;
			List<BidDebtRecordVo> bidDebtRecords = null;
			List<BorrowDetailVo> borrowDetails = null;
			List<NeedReturnRecordNext6MonthVo> needReturnRecordNext6MonthVos = null;
			List<OverTimeRecordLast6MonthVo> overTimeRecordLast6MonthVos = null;
			
			
			BlackList bk = new BlackList();
			BorrowDetail bd = new BorrowDetail();
			
			HtmlPage htmlDetailPage = null;
			
			String url = null;
			String listingid = null;
			
			while (true) {
				try {
					//获取黑名单信息
					blacklist = bk.getBlackListInfoNew(htmlPage);
					//保存黑名单信息
					blackListDao.addBlackLists(blacklist);
					
					//获取黑名单还款详情页面
					htmlPage = bk.getRepaymentDetailsHtmlPageNew(htmlPage);
					//TODO 删除
					DownloadHtmlPageUtil.saveHtmlPage("http://invest.ppdai.com/account/blacklistnewDetails", htmlPage.asXml());
					
					//获取黑名单还款详情
					repaymentDetails = bk.getRepaymentDetails(htmlPage);
					//保存黑名单还款详情
					blackListDao.addRepaymentDetails(repaymentDetails);
					
					//获取所有手机app用户的闪电借款按钮
					List<HtmlElement> trRepaymentDetailslist = bk.getBlacklistdetailURL(htmlPage);
					for (HtmlElement htmlElement : trRepaymentDetailslist) {
						try {
							url = htmlElement.getElementsByTagName("a").get(0).getAttribute("href");
							listingid = url.split("/")[url.split("/").length-1];
							
							while (true) {
								htmlDetailPage = PPDUtil.getUrlPage(url);
								if (!htmlDetailPage.asXml().contains("502错误")) {
									break;
								}
							}
							
							//保存htmlDetailPage文件
							DownloadHtmlPageUtil.saveHtmlPage(url, htmlDetailPage.asXml());
							
							//加载网页
							bd.loadPage(htmlDetailPage, listingid);
							
							borrowDetailPageVo = bd.getBorrowDetailPageVo();
							borrowDetailDao.addBorrowDetailPageVo(borrowDetailPageVo);
							
							//获取投标记录数据
							bidRecords = bd.getBidRecords();
							//插入投标记录数据到数据库
							borrowDetailDao.addBidRecords(bidRecords);
							
							//获取债权转让记录数据
							bidDebtRecords = bd.getBidDebtRecords();
							//插入债权转让记录数据到数据库
							borrowDetailDao.addBidDebtRecords(bidDebtRecords);
							
							//获取借款记录数据
							borrowDetails = bd.getBorrowDetails();
							//插入借款记录数据到数据库
							borrowDetailDao.addBorrowDetails(borrowDetails);
							
							//获取未来6个月的待还记录数据
							needReturnRecordNext6MonthVos = bd.getNeedReturnRecordNext6MonthVos();
							//插入未来6个月的待还记录数据到数据库
							borrowDetailDao.addNeedReturnRecordNext6MonthVos(needReturnRecordNext6MonthVos);
							
							//获取过去6个月有回款记录的逾期天数数据
							overTimeRecordLast6MonthVos = bd.getOverTimeRecordLast6MonthVos();
							//插入过去6个月有回款记录的逾期天数到数据库
							borrowDetailDao.addOverTimeRecordLast6MonthVos(overTimeRecordLast6MonthVos);
							
							blackListDao.updateFullUserName(bd.getFullUsername(), bd.getListingid());
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
					
					if (bk.isLastPage(htmlPage)) {
						break;
					} else {
						htmlPage = PPDUtil.getUrlPage(bk.getNextHtmlPageURL(htmlPage));
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
		} catch (FailingHttpStatusCodeException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			ctx.close();
		}
	}
}
