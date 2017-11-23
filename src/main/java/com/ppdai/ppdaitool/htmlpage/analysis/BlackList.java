package com.ppdai.ppdaitool.htmlpage.analysis;

import java.util.ArrayList;
import java.util.List;

import com.gargoylesoftware.htmlunit.html.HtmlBody;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlTable;
import com.ppdai.ppdaitool.utils.PPDUtil;
import com.ppdai.ppdaitool.vo.BlacklistVo;
import com.ppdai.ppdaitool.vo.RepaymentDetailVo;

/**
 * 黑名单分析
 */
public class BlackList{
	
	/**
	 * 获取黑名单信息<br>
	 * 说明：提取网页中所有黑名单信息，包含黑名单的用户名、逾期本金、已还金额、投标金额、逾期天数、最大逾期天数、催收成功可能性等数据，
	 * @param htmlPage 黑名单网页
	 * @return blacklist 黑名单信息
	 */
	public List<BlacklistVo> getBlackListInfo(HtmlPage htmlPage) {
		if (null == htmlPage.getElementsByTagName("table") || htmlPage.getElementsByTagName("table").size() <= 0) {
			return null;
		}
		
		HtmlTable htmlTable = (HtmlTable)htmlPage.getElementsByTagName("table").get(0);
		List<HtmlElement> trList = htmlTable.getElementsByTagName("tr");
		
		List<HtmlElement> tdList;
		List<BlacklistVo> blacklist = new ArrayList<BlacklistVo>();
		for (int i = 1; i < trList.size() - 1; i++) {
			try {
				BlacklistVo blacklistVo = new BlacklistVo();
				
				tdList = trList.get(i).getElementsByTagName("td");
				if (tdList.size() == 6) {
					blacklistVo.setUsername(tdList.get(0).asText());
					blacklistVo.setListingid(tdList.get(0).getElementsByTagName("span").get(1).getAttribute("listingid"));
					String[] capitals = tdList.get(1).asText().replaceAll(" ", "").replaceAll("¥", "").split("/");
					blacklistVo.setOverdueCapital(Double.valueOf(capitals[0]));
					blacklistVo.setGiveBackCapital(Double.valueOf(capitals[1]));
					blacklistVo.setBidCapital(Double.valueOf(capitals[2]));
					String[] overdays = tdList.get(3).asText().replaceAll(" ", "").replaceAll("天", "").replaceAll("）", "").split("（");
					blacklistVo.setOverDays(Integer.valueOf(overdays[0]));
					blacklistVo.setMaxOverDays(Integer.valueOf(overdays[1]));
					blacklistVo.setPercentOfGetBack(Integer.valueOf(tdList.get(4).asText().trim().replaceAll("%", ""))/100);
					blacklist.add(blacklistVo);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return blacklist;
	}
	
	/**
	 * 获取黑名单信息(新版黑名单网页)<br>
	 * 说明：提取网页中所有黑名单信息，包含黑名单的用户名、逾期本金、已还金额、投标金额、逾期天数、最大逾期天数、催收成功可能性等数据，
	 * @param htmlPage 黑名单网页
	 * @return blacklist 黑名单信息
	 */
	public List<BlacklistVo> getBlackListInfoNew(HtmlPage htmlPage) {
		if (null == htmlPage.getElementsByTagName("table") || htmlPage.getElementsByTagName("table").size() <= 0) {
			return null;
		}
		
		HtmlTable htmlTable = (HtmlTable)htmlPage.getElementsByTagName("table").get(0);
		List<HtmlElement> trList = htmlTable.getElementsByTagName("tr");
		
//		System.out.println(htmlTable.asText());
		List<HtmlElement> tdList;
		List<BlacklistVo> blacklist = new ArrayList<BlacklistVo>();
		for (int i = 1; i < trList.size() - 1; i++) {
			try {
				BlacklistVo blacklistVo = new BlacklistVo();
				
				tdList = trList.get(i).getElementsByTagName("td");
				if (tdList.size() == 6) {
					blacklistVo.setUsername(tdList.get(0).asText());
					blacklistVo.setListingid(tdList.get(0).getElementsByTagName("i").get(0).getAttribute("listingid"));
					String[] capitals = tdList.get(1).asText().replaceAll(" ", "").replaceAll("¥", "").split("/");
					blacklistVo.setOverdueCapital(Double.valueOf(capitals[0]));
					blacklistVo.setGiveBackCapital(Double.valueOf(capitals[1]));
					blacklistVo.setBidCapital(Double.valueOf(capitals[2]));
					String[] overdays = tdList.get(3).asText().replaceAll(" ", "").replaceAll("天", "").replaceAll("）", "").split("（");
					blacklistVo.setOverDays(Integer.valueOf(overdays[0]));
					blacklistVo.setMaxOverDays(Integer.valueOf(overdays[1]));
					blacklistVo.setPercentOfGetBack(Double.valueOf(tdList.get(4).asText().trim().replaceAll("%", ""))/100);
					blacklist.add(blacklistVo);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return blacklist;
	}
	
	/**
	 * 获取黑名单还款详情页面<br>
	 * @param htmlPage 黑名单网页
	 * @return htmlPage 黑名单还款详情页面
	 */
	public HtmlPage getRepaymentDetailsHtmlPage(HtmlPage htmlPage){
		if (null == htmlPage.getElementsByTagName("table") || htmlPage.getElementsByTagName("table").size() <= 0) {
			return null;
		}
		
		HtmlTable htmlTable = (HtmlTable)htmlPage.getElementsByTagName("table").get(0);
		List<HtmlElement> trList = htmlTable.getElementsByTagName("tr");
	
		List<HtmlElement> tdList;
		/*
		 * 展开所有黑名单还款详情
		 */
		for (int i = 1; i < trList.size() - 1; i++) {
			try {
				tdList = trList.get(i).getElementsByTagName("td");
				if (tdList.size() == 6) {
					htmlPage = tdList.get(0).getElementsByTagName("span").get(1).click();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			PPDUtil.sleep();
		}
		
		return htmlPage;
	}
	
	/**
	 * 获取黑名单还款详情页面<br>
	 * @param htmlPage 黑名单网页
	 * @return htmlPage 黑名单还款详情页面
	 */
	public HtmlPage getRepaymentDetailsHtmlPageNew(HtmlPage htmlPage){
		if (null == htmlPage.getElementsByTagName("table") || htmlPage.getElementsByTagName("table").size() <= 0) {
			return null;
		}
		
		HtmlTable htmlTable = (HtmlTable)htmlPage.getElementsByTagName("table").get(0);
		List<HtmlElement> trList = htmlTable.getElementsByTagName("tr");
		
		List<HtmlElement> tdList;
		/*
		 * 展开所有黑名单还款详情
		 */
		for (int i = 1; i < trList.size() - 1; i++) {
			try {
				tdList = trList.get(i).getElementsByTagName("td");
				if (tdList.size() == 6) {
					htmlPage = tdList.get(0).getElementsByTagName("i").get(0).click();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			PPDUtil.sleep();
		}
		
		return htmlPage;
	}
	
	/**
	 * 获取黑名单还款详情<br>
	 * 说明：获取黑明单用户的还款详情信息，包含还款日、已收本息、未收本息、未收本金、未收利息、逾期利息、逾期天数、状态等信息
	 * @param htmlPage 黑名单还款详情网页
	 * @return
	 */
	public List<RepaymentDetailVo> getRepaymentDetails(HtmlPage htmlPage) {
		List<RepaymentDetailVo> repaymentDetails =  new ArrayList<RepaymentDetailVo>();
		
		/*
		 * 解析黑名单还款详情
		 */
		HtmlBody htmlBody = (HtmlBody)htmlPage.getElementsByTagName("body").get(0);
		List<HtmlElement> trRepaymentDetailslist = htmlBody.getElementsByAttribute("tr", "class", "inner-data");
		List<HtmlElement> trTemp;
		List<HtmlElement> tdTemp;
		for (HtmlElement htmlElement : trRepaymentDetailslist) {
			//没有还款详情则跳过分析继续循环
			if (null == htmlElement.getElementsByTagName("table")
					|| htmlElement.getElementsByTagName("table").size() <= 0) {
				continue;
			}
			
			trTemp = htmlElement.getElementsByTagName("table").get(0).getElementsByTagName("tr");
			int sequence = 1;
			for (int i = 1; i < trTemp.size(); i++) {
				try {
					RepaymentDetailVo repaymentDetailVo = new RepaymentDetailVo();
					tdTemp = trTemp.get(i).getElementsByTagName("td");
					repaymentDetailVo.setListingid(htmlElement.getAttribute("id").split("_")[1]);
					repaymentDetailVo.setReturnDate(tdTemp.get(0).asText());
					repaymentDetailVo.setWithdraw(Double.valueOf(tdTemp.get(1).asText().replaceAll("¥", "")));
					repaymentDetailVo.setNotWithdraw(Double.valueOf(tdTemp.get(2).asText().replaceAll("¥", "")));
					repaymentDetailVo.setNotGetBackCapital(Double.valueOf(tdTemp.get(3).asText().replaceAll("¥", "")));
					repaymentDetailVo.setNotGetInterest(Double.valueOf(tdTemp.get(4).asText().replaceAll("¥", "")));
					repaymentDetailVo.setOverTimeInterest(Double.valueOf(tdTemp.get(5).asText().split("/")[0].replaceAll("¥", "")));
					repaymentDetailVo.setOverDays(Integer.valueOf(tdTemp.get(5).asText().split("/")[1]));
					repaymentDetailVo.setStatus(tdTemp.get(6).asText());
					repaymentDetailVo.setSequence(sequence ++);
					
					repaymentDetails.add(repaymentDetailVo);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		
		return repaymentDetails;
	}
	
	/**
	 * 获取所有手机app用户的URL<br>
	 * 说明：获取每个黑名单用户手机app的闪电借款URL
	 * @param htmlPage 黑名单还款详情网页
	 * @return aButtonList 所有手机app用户的闪电借款URL
	 */
	public List<HtmlElement> getBlacklistdetailURL(HtmlPage htmlPage) {
		List<HtmlElement> trRepaymentDetailslist = new ArrayList<HtmlElement>();
		
		/*
		 * 解析黑名单还款详情
		 */
		HtmlBody htmlBody = (HtmlBody)htmlPage.getElementsByTagName("body").get(0);
		trRepaymentDetailslist = htmlBody.getElementsByAttribute("tr", "class", "inner-data");
		
		return trRepaymentDetailslist;
	}
	
	/**
	 * 是否是最后一页<br>
	 * 说明：判断当前htmlPage页是否为最后一页<br>
	 *     当前页值大于等于总页数值则是最后一页，否则不是最后一页
	 * @param htmlPage 黑名单网页
	 * @return
	 */
	public boolean isLastPage(HtmlPage htmlPage) {
		try {
			HtmlBody htmlBody = (HtmlBody)htmlPage.getElementsByTagName("body").get(0);
			List<HtmlElement> spans = htmlBody.getElementsByAttribute("span", "class", "pagerstatus");
			List<HtmlElement> alist = htmlBody.getElementsByAttribute("a", "class", "currentpage");
			
			if (Integer.valueOf(spans.get(0).asText().trim().replaceAll("共", "").replaceAll("页", ""))
					> Integer.valueOf(alist.get(0).asText().trim())) {
				return false;
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}
	
	/**
	 * 获取下一页URL地址
	 * 说明：当前页为最后一页则返回""，否则返回下一页的访问url
	 * @param htmlPage 黑名单网页
	 * @return
	 */
	public String getNextHtmlPageURL(HtmlPage htmlPage) {
		try {
			HtmlBody htmlBody = (HtmlBody)htmlPage.getElementsByTagName("body").get(0);
			List<HtmlElement> spans = htmlBody.getElementsByAttribute("span", "class", "pagerstatus");
			List<HtmlElement> alist = htmlBody.getElementsByAttribute("a", "class", "currentpage");
			
			if (Integer.valueOf(spans.get(0).asText().trim().replaceAll("共", "").replaceAll("页", ""))
					> Integer.valueOf(alist.get(0).asText().trim())) {
				String nextUrl = "http://invest.ppdai.com" + htmlBody.getElementsByAttribute("a", "class", "nextpage").get(0).getAttribute("href");
				return nextUrl;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}
}
