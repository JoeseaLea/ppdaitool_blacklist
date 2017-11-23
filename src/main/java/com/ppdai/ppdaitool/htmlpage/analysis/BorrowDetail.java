package com.ppdai.ppdaitool.htmlpage.analysis;

import java.util.ArrayList;
import java.util.List;

import org.htmlcleaner.HtmlCleaner;
import org.htmlcleaner.TagNode;

import com.gargoylesoftware.htmlunit.html.HtmlBody;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.ppdai.ppdaitool.utils.HtmlCleanerUtil;
import com.ppdai.ppdaitool.utils.StringUtil;
import com.ppdai.ppdaitool.vo.BidDebtRecordVo;
import com.ppdai.ppdaitool.vo.BidRecordVo;
import com.ppdai.ppdaitool.vo.BorrowDetailPageVo;
import com.ppdai.ppdaitool.vo.BorrowDetailVo;
import com.ppdai.ppdaitool.vo.NeedReturnRecordNext6MonthVo;
import com.ppdai.ppdaitool.vo.OverTimeRecordLast6MonthVo;

/**
 * 借款详情分析
 * @author Joesea Lea
 *
 */
public class BorrowDetail {
//	private static final String listnameSuffix = "37825"; //投标人名称的后缀
	
	private HtmlCleaner cleaner = null;
	private TagNode rootNode = null;
	private HtmlBody htmlBody = null;
	private String listingid = null;
//	private String userName = null;
	private String fullUsername = null;
//	private HtmlPage htmlPage = null;
	
	/**
	 * 加载页面对象<br>
	 * @param htmlPage 要加载的网页
	 * @param listingid
	 */
	public void loadPage(HtmlPage htmlPage, String listingid) {
		
//		this.htmlPage = htmlPage;
		this.listingid = listingid;
		
		try {
			cleaner = new HtmlCleaner();
			rootNode = cleaner.clean(htmlPage.asXml());
			htmlBody = (HtmlBody)htmlPage.getElementsByTagName("body").get(0);
			HtmlElement divNewLendDetailbox = htmlBody.getElementsByAttribute("div", "class", "newLendDetailbox").get(0);
			fullUsername = divNewLendDetailbox.getElementsByAttribute("div", "class", "newLendDetailInfo clearfix").get(0)
					.getElementsByAttribute("div", "class", "wrapNewLendDetailInfoLeft").get(0)
					.getElementsByAttribute("div", "class", "newLendDetailInfoLeft").get(0)
					.getElementsByAttribute("a", "class", "username").get(0).asText().trim();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 获取投标记录数据
	 * @return bidRecords 投标记录数据
	 */
	public List<BidRecordVo> getBidRecords() {
		List<BidRecordVo> bidRecords = new ArrayList<BidRecordVo>();
		try {
			List<HtmlElement> bidRecordsElement = htmlBody.getElementsByAttribute("div", "id", "bidTable_div").get(0)
					.getElementsByAttribute("div", "class", "scroll-area").get(0).getElementsByTagName("ol");
			
			for (HtmlElement htmlElement : bidRecordsElement) {
				try {
					BidRecordVo bidRecordVo = new BidRecordVo();
					bidRecordVo.setListingid(listingid);
					bidRecordVo.setFullUsername(fullUsername);
//				bidRecordVo.setUserName(userName);
					
					List<HtmlElement> liElement = htmlElement.getElementsByTagName("li");
					
					bidRecordVo.setBidUsername(liElement.get(0).asText().trim());
					bidRecordVo.setPercentOfInterest(Double.valueOf(liElement.get(1).asText().trim().replaceAll("%", ""))/100);
					bidRecordVo.setTimeLong(liElement.get(2).asText().trim());
					bidRecordVo.setValidBidCapital(Integer.valueOf(liElement.get(3).asText().trim().replaceAll("¥", "").replaceAll(",", "")));
					bidRecordVo.setBidtime(liElement.get(4).asText().trim());
					
					bidRecords.add(bidRecordVo);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return bidRecords;
	}

	/**
	 * 获取债权转让记录数据<br>
	 * @return bidDebtRecords 债权转让记录数据
	 */
	public List<BidDebtRecordVo> getBidDebtRecords() {
		List<BidDebtRecordVo> bidDebtRecords = new ArrayList<BidDebtRecordVo>();
		
		try {
			List<HtmlElement> bidRecordsElement = htmlBody.getElementsByAttribute("div", "id", "bidTableDebt_div").get(0)
					.getElementsByAttribute("div", "class", "scroll-area").get(0).getElementsByTagName("ol");
			
			for (HtmlElement htmlElement : bidRecordsElement) {
				try {
					BidDebtRecordVo bidDebtRecordVo = new BidDebtRecordVo();
					bidDebtRecordVo.setListingid(listingid);
					bidDebtRecordVo.setFullUsername(fullUsername);
//				bidDebtRecordVo.setUserName(userName);
					
					List<HtmlElement> liElement = htmlElement.getElementsByTagName("li");
					
					bidDebtRecordVo.setOriginalCreditorRightsUsername(liElement.get(0).asText().trim());
					bidDebtRecordVo.setTransferAmount(Double.parseDouble(liElement.get(1).asText().trim().replaceAll("¥", "").replaceAll(",", "")));
					bidDebtRecordVo.setTransferPrice(Double.parseDouble(liElement.get(2).asText().trim().replaceAll("¥", "").replaceAll(",", "")));
					bidDebtRecordVo.setTransferUsername(liElement.get(3).asText().trim());
					bidDebtRecordVo.setTransferTime(liElement.get(4).asText().trim());
					bidDebtRecordVo.setTranserMethod(liElement.get(5).asText().trim());
					
					bidDebtRecords.add(bidDebtRecordVo);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return bidDebtRecords;
	}

	public List<BorrowDetailVo> getBorrowDetails() {
		List<BorrowDetailVo> borrowDetails = new ArrayList<BorrowDetailVo>();
		
		try {
			List<HtmlElement> divTabContainElements = htmlBody.getElementsByAttribute("div", "class", "tab-contain");
			HtmlElement divTabContainElement = null;
			for (HtmlElement htmlElement : divTabContainElements) {
				if (htmlElement.asText().contains("统计信息") && htmlElement.asText().contains("历史成功借款列表")) {
					divTabContainElement = htmlElement;
					break;
				}
			}
			
			if (null != divTabContainElement) {
				List<HtmlElement> tableElements = divTabContainElement.getElementsByAttribute("table", "class", "lendDetailTab_tabContent_table1 normal");
				
				HtmlElement tableElement = null;
				for (HtmlElement htmlElement : tableElements) {
					try {
						if (htmlElement.asText().contains("借款标的") && htmlElement.asText().contains("借款标的")) {
							tableElement = htmlElement;
							break;
						}
					} catch (Exception e) {
						break;
					}
				}
				
				List<HtmlElement> trElements = tableElement.getElementsByAttribute("tr", "class", "tab-list");
				List<HtmlElement> tdElements = null;
				for (HtmlElement htmlElement : trElements) {
					try {
						BorrowDetailVo borrowDetailVo = new BorrowDetailVo();
//					borrowDetailVo.setUsername(userName);
						borrowDetailVo.setFullUsername(fullUsername);
						borrowDetailVo.setListingid(listingid);
						
						tdElements = htmlElement.getElementsByTagName("td");
						
						borrowDetailVo.setBorrowBid(tdElements.get(0).asText().trim());
						borrowDetailVo.setPercentOfInterest(Double.valueOf(tdElements.get(1).asText().trim().replaceAll("%", ""))/100);
						borrowDetailVo.setTimeLimit(tdElements.get(2).asText().trim());
						borrowDetailVo.setAmount(Integer.valueOf(tdElements.get(3).asText().trim().replaceAll(",", "")));
						borrowDetailVo.setDateIssued(tdElements.get(4).asText().trim());
						borrowDetailVo.setStatus(tdElements.get(5).asText().trim());
						
						borrowDetails.add(borrowDetailVo);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return borrowDetails;
	}
	
	/**
	 * 获取借款详情页面对象<br>
	 * @return
	 */
	public BorrowDetailPageVo getBorrowDetailPageVo(){
		BorrowDetailPageVo bean = new BorrowDetailPageVo();
		try{
			bean.setListingid(this.listingid);
			
			//魔镜等级
			TagNode tagNode = HtmlCleanerUtil.getSingleNode(rootNode, "//div[@class='newLendDetailInfoLeft']/a[@class='altQust']/span");
			if(tagNode != null){
				String creditRating = StringUtil.trimToEmpty(tagNode.getAttributeByName("class")).replace("creditRating", "").trim();
				bean.setLevel(creditRating);
			}

			
			TagNode[] tagNodes = HtmlCleanerUtil.getMultiNode(rootNode, "//div[@class='newLendDetailMoneyLeft']/dl");
			if(tagNodes != null){
				TagNode node;
				
				//借款金额
				node = HtmlCleanerUtil.getSingleElementByName(tagNodes[0], "dd");
				if(node != null){
					bean.setBorrowAmount(Integer.valueOf(node.getText().toString().replace("¥", "").replace(",", "").trim()));
				}
				
				//年利率
				node = HtmlCleanerUtil.getSingleElementByName(tagNodes[1], "dd");
				if(node != null){
					bean.setAnnualInterestRate(Double.valueOf(node.getText().toString().replace("%", "").trim())/100);
				}
				
				//期限
				node = HtmlCleanerUtil.getSingleElementByName(tagNodes[2], "dd");
				if(node != null){
					bean.setTimeLimit(node.getText().toString().replaceAll(" ", "").replaceAll("\n\r", "").replaceAll("\n", "").replaceAll("\r", "").trim());
				}
			}
			
			//还款方式
			TagNode repayMethodNode = HtmlCleanerUtil.getSingleNode(rootNode, "//div[@class='newLendDetailRefundLeft']//div[@class='part mb16 clearfix']//div[@class='item w260']");
			bean.setReturnMethod(repayMethodNode.getText().toString().replaceAll("\"", "").split(": ")[1].trim());
			
			//投标人数
			TagNode bidNumberNode = HtmlCleanerUtil.getSingleNode(rootNode, "//div[@class='newLendDetailRefundLeft']//div[@class='part clearfix']//div[@class='item w164']");
			bean.setBidNumber(Integer.valueOf(bidNumberNode.getText().toString().split("：")[1].replaceAll("人", "").trim()));
			
			
			
			//还款结束时间
			TagNode repayEndTimeNode = HtmlCleanerUtil.getSingleNode(rootNode, "//div[@class='newLendDetailRefundLeft']//div[@class='item']");
			if(repayEndTimeNode != null){
				if(StringUtil.trimToEmpty(repayEndTimeNode.getText().toString()).indexOf("结束时间") >= 0){
					String leftTime = HtmlCleanerUtil.getSingleNodeText(repayEndTimeNode, "//span[@id='leftTime']/text()");
					bean.setEndReturnTime(leftTime);
				}
			}
			
			tagNodes = HtmlCleanerUtil.getMultiNode(rootNode, "//div[@class='main']//div[@class='tab-contain']");
			if(tagNodes != null){
				for(TagNode tabNode : tagNodes){
					TagNode h3Node = HtmlCleanerUtil.getSingleElementByName(tabNode, "h3");
					if(h3Node != null){
						String h3Text = h3Node.getText().toString().trim();
						
						//借款人信息
						if(h3Text.indexOf("借款人信息") >= 0){
							TagNode[] spanArr = HtmlCleanerUtil.getMultiNode(tabNode, "//span");
							if(spanArr != null){
								//性别
								bean.setSex(spanArr[0].getText().toString().replaceAll("\"", "").trim());
								
								//年龄
								bean.setAge(Integer.valueOf(spanArr[1].getText().toString().replaceAll("\"", "").trim()));
								
								//注册时间
								bean.setRegisterTime(spanArr[2].getText().toString().replaceAll("\"", "").trim());
								
								//文化程度
								bean.setDegreeOfEducation(spanArr[3].getText().toString().replaceAll("\"", "").trim());
								
								//毕业院校
								bean.setSchoolOfGraduation(spanArr[4].getText().toString().replaceAll("\"", "").trim());
								
								//学习形式
								bean.setFormsOfLearning(spanArr[5].getText().toString().replaceAll("\"", "").trim());
							}
						}
						
						//统计信息
						if(h3Text.indexOf("统计信息") >= 0){
							TagNode[] spanArr = HtmlCleanerUtil.getMultiNode(tabNode, "//span[@class='num']");
							if(spanArr != null){
								//成功借款次数
								bean.setSuccessNumber(Integer.valueOf(spanArr[0].getText().toString().replace("次", "").trim()));
								
								//第一次成功借款时间
								bean.setBorrowFirstTime(spanArr[1].getText().toString().replaceAll(" ", "").replaceAll("\n\r", "").replaceAll("\n", "").replaceAll("\r", ""));
								
								//历史记录
								bean.setHistoryRecord(spanArr[2].getText().toString().trim());
								
								//成功还款次数
								bean.setReturnSuccessNum(Integer.valueOf(spanArr[3].getText().toString().replace("次", "").trim()));
								//正常还清次数
								bean.setReturnNormalNum(Integer.valueOf(spanArr[4].getText().toString().replace("次", "").trim()));
								//逾期(0-15天)还清次数
								bean.setOverTimeNum1(Integer.valueOf(spanArr[5].getText().toString().replace("次", "").trim()));
								//逾期(15天以上)还清次数
								bean.setOverTimeNum2(Integer.valueOf(spanArr[6].getText().toString().replace("次", "").trim()));
								
								//累计借款金额
								bean.setTotalBorrowAmount(Double.valueOf(spanArr[7].getText().toString().replace("¥", "").replace(",", "").trim()));
								//待还金额
								bean.setTotalNeedReturnAmount(Double.valueOf(spanArr[8].getText().toString().replace("¥", "").replace(",", "").trim()));
								//待收金额
								bean.setTotalNeedGetAmount(Double.valueOf(spanArr[9].getText().toString().replace("¥", "").replace(",", "").trim()));
								//单笔最高借款金额
								bean.setMaxBorrowAmount(Double.valueOf(spanArr[10].getText().toString().replace("¥", "").replace(",", "").trim()));
								//历史最高负债
								bean.setMaxDebtHistory(Double.valueOf(spanArr[11].getText().toString().replace("¥", "").replace(",", "").trim()));
							}
						}
					}
					
				}
			}
			
			return bean;
		}catch(Exception ex){
			ex.printStackTrace();
			return null;
		}
	}
	
	
	public String getListingid() {
		return listingid;
	}

	public String getFullUsername() {
		return fullUsername;
	}

	public List<NeedReturnRecordNext6MonthVo> getNeedReturnRecordNext6MonthVos() {
		List<NeedReturnRecordNext6MonthVo> needReturnRecordNext6MonthVos = new ArrayList<NeedReturnRecordNext6MonthVo>();
		try {
			TagNode[] tableArr = HtmlCleanerUtil.getMultiNode(rootNode, "//div[@class='main']//div[@class='lendDetailTab_tabContent w1000center']//div[@class='tab-contain']//table[@class='lendDetailTab_tabContent_table1 normal']");
			if(tableArr != null){
				if(tableArr.length > 1){
					//未来6个月的待还记录
					TagNode[] tdArr = HtmlCleanerUtil.getMultiNode(tableArr[1], "//tr[1]/td");
					for (TagNode tagNode : tdArr) {
						try {
							NeedReturnRecordNext6MonthVo needReturnRecordNext6MonthVo = new NeedReturnRecordNext6MonthVo();
							
							needReturnRecordNext6MonthVo.setListingid(this.listingid);
							needReturnRecordNext6MonthVo.setFullUsername(this.fullUsername);
							needReturnRecordNext6MonthVo.setDateTime(tagNode.getText().toString().trim());
							
							needReturnRecordNext6MonthVos.add(needReturnRecordNext6MonthVo);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
					
					tdArr = HtmlCleanerUtil.getMultiNode(tableArr[1], "//tr[2]/td");
					for (int i = 0; i < tdArr.length; i++) {
						try {
							double amount = Double.valueOf(tdArr[i].getText().toString().replace("¥", "").replace(",", "").trim());
							needReturnRecordNext6MonthVos.get(i).setAmount(amount);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return needReturnRecordNext6MonthVos;
	}

	public List<OverTimeRecordLast6MonthVo> getOverTimeRecordLast6MonthVos() {
		List<OverTimeRecordLast6MonthVo> overTimeRecordLast6MonthVos = new ArrayList<OverTimeRecordLast6MonthVo>();
		
		try {
				TagNode[] tableArr = HtmlCleanerUtil.getMultiNode(rootNode, "//div[@class='main']//div[@class='lendDetailTab_tabContent w1000center']//div[@class='tab-contain']//table[@class='lendDetailTab_tabContent_table1 normal']");
				if(tableArr != null){
					if(tableArr.length > 2){
						//过去6个月有回款记录的逾期天数
						TagNode[] tdArr = HtmlCleanerUtil.getMultiNode(tableArr[2], "//tr[1]/td");
						for(TagNode tdNode : tdArr){
							try {
								OverTimeRecordLast6MonthVo overTimeRecordLast6MonthVo = new OverTimeRecordLast6MonthVo();
								
								overTimeRecordLast6MonthVo.setListingid(this.listingid);
								overTimeRecordLast6MonthVo.setFullUsername(this.fullUsername);
								overTimeRecordLast6MonthVo.setDateTime(tdNode.getText().toString().trim());
								
								overTimeRecordLast6MonthVos.add(overTimeRecordLast6MonthVo);
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
						
						tdArr = HtmlCleanerUtil.getMultiNode(tableArr[2], "//tr[2]/td");
						for (int i = 0; i < tdArr.length; i++) {
							try {
								int dayNumber = Integer.valueOf(tdArr[i].getText().toString().trim());
								overTimeRecordLast6MonthVos.get(i).setDayNumber(dayNumber);
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
					}
				}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return overTimeRecordLast6MonthVos;
	}
}
