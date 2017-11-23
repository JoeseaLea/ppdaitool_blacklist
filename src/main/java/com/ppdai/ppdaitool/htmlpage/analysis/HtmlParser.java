package com.ppdai.ppdaitool.htmlpage.analysis;
import java.io.File;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.htmlcleaner.HtmlCleaner;
import org.htmlcleaner.TagNode;

import com.ppdai.ppdaitool.utils.HtmlCleanerUtil;
import com.ppdai.ppdaitool.utils.StringUtil;
import com.ppdai.ppdaitool.vo.HtmlParseVo;

/**
 * HTML页面解析器类
 */
public class HtmlParser {
	private static final String listnameSuffix = "37825"; //投标人名称的后缀
//	private static final int fullSeconds = 5; //秒满的条件秒数
	
	public static HtmlParseVo parse(String filename){
		HtmlParseVo bean = new HtmlParseVo();
		try{
			File file = new File(filename);
			
			//文件名
			bean.setField1(file.getName().toLowerCase().replace(".html", ""));
			
			HtmlCleaner cleaner = new HtmlCleaner();
			TagNode rootNode = cleaner.clean(file);
			
			
			//魔镜等级
			TagNode tagNode = HtmlCleanerUtil.getSingleNode(rootNode, "//div[@class='newLendDetailInfoLeft']/a[@class='altQust']/span");
			if(tagNode != null){
				String creditRating = StringUtil.trimToEmpty(tagNode.getAttributeByName("class")).replace("creditRating", "").trim();
				bean.setField2(creditRating);
			}

			
			TagNode[] tagNodes = HtmlCleanerUtil.getMultiNode(rootNode, "//div[@class='newLendDetailMoneyLeft']/dl");
			if(tagNodes != null){
				TagNode node;
				
				//借款金额
				node = HtmlCleanerUtil.getSingleElementByName(tagNodes[0], "dd");
				if(node != null){
					bean.setField3(node.getText().toString().replace("¥", "").replace(",", "").trim());
				}
				
				//年利率
				node = HtmlCleanerUtil.getSingleElementByName(tagNodes[1], "dd");
				if(node != null){
					bean.setField4(node.getText().toString().replace("%", "").trim());
				}
				
				//期限
				node = HtmlCleanerUtil.getSingleElementByName(tagNodes[2], "dd");
				if(node != null){
					bean.setField5(node.getText().toString().replace("个月", "").trim());
				}
			}
			
			//还款结束时间
			TagNode repayNode = HtmlCleanerUtil.getSingleNode(rootNode, "//div[@class='newLendDetailRefundLeft']//div[@class='item']");
			if(repayNode != null){
				if(StringUtil.trimToEmpty(repayNode.getText().toString()).indexOf("结束时间") >= 0){
					String leftTime = HtmlCleanerUtil.getSingleNodeText(repayNode, "//span[@id='leftTime']/text()");
					bean.setField5_1(leftTime);
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
								String sex = spanArr[0].getText().toString();
								bean.setField6((sex.equals("男") ? "M" : "F"));
								
								//年龄
								bean.setField7(spanArr[1].getText().toString());
								
								//注册时间
								bean.setField7_1(spanArr[2].getText().toString());
								
								//文化程度
								bean.setField8(spanArr[3].getText().toString());
								
								//毕业院校
								bean.setField8_1(spanArr[4].getText().toString());
								
								//学习形式
								bean.setField8_2(spanArr[5].getText().toString());
							}
						}
						
						//统计信息
						if(h3Text.indexOf("统计信息") >= 0){
							TagNode[] spanArr = HtmlCleanerUtil.getMultiNode(tabNode, "//span[@class='num']");
							if(spanArr != null){
								//成功借款次数
								bean.setField9(spanArr[0].getText().toString().replace("次", "").trim());
								
								//第一次成功借款时间
								bean.setField11(spanArr[1].getText().toString());
								
								//历史记录
								bean.setField10(spanArr[2].getText().toString().replace("次流标", "").replace("次撤标", "").replace("次失败", "").trim().replace("，", "-"));
								
								//成功还款次数
								bean.setField14(spanArr[3].getText().toString().replace("次", "").trim());
								
								//逾期
								bean.setField15(spanArr[5].getText().toString().replace("次", "").trim() +  "-" + ((TagNode)spanArr[6]).getText().toString().replace("次", "").trim());
								
								//累计借款金额
								bean.setField18(spanArr[7].getText().toString().replace("¥", "").replace(",", "").trim());
								
								//待还金额
								bean.setField19(spanArr[8].getText().toString().replace("¥", "").replace(",", "").trim());

								//单笔最高借款金额
								bean.setField20(spanArr[10].getText().toString().replace("¥", "").replace(",", "").trim());

								//历史最高负债
								bean.setField21(spanArr[11].getText().toString().replace("¥", "").replace(",", "").trim());
							}

							TagNode[] tableArr = HtmlCleanerUtil.getMultiNode(tabNode, "//table[@class='lendDetailTab_tabContent_table1 normal']");
							if(tableArr != null){
								if(tableArr.length > 0){
									//历史成功借款列表
									TagNode trNode = HtmlCleanerUtil.getSingleNode(tableArr[0], "//tr[@class='tab-list'][1]");
									if(trNode != null){
										TagNode[] tdArr = HtmlCleanerUtil.getMultiElementByName(trNode, "td");
										
										//是否短借
										String qx = tdArr[2].getText().toString().trim();
										if(qx.indexOf("天") > 0){
											bean.setField13("Y");
										}else{
											bean.setField13("N");
										}
										
										//最后一次成功借款时间
										bean.setField12(tdArr[4].getText().toString().trim());
									}
								}
								
								if(tableArr.length > 1){
									//未来6个月的待还记录
									double repay = 0.0;
									TagNode[] tdArr = HtmlCleanerUtil.getMultiNode(tableArr[1], "//tr[2]/td");
									for(TagNode tdNode : tdArr){
										String value = tdNode.getText().toString().replace("¥", "").replace(",", "").trim();
										if(Double.parseDouble(value) > repay){
											repay = Double.parseDouble(value);
										}
									}
									
									//未来6个月的最大待还金额
									bean.setField16(String.valueOf(repay));
								}
								
								if(tableArr.length > 2){
									//过去6个月有回款记录的逾期天数
									int days = 0;
									TagNode[] tdArr = HtmlCleanerUtil.getMultiNode(tableArr[2], "//tr[2]/td");
									for(TagNode tdNode : tdArr){
										String value = tdNode.getText().toString().trim();
										days += Integer.parseInt(value);
									}
									
									//过去6个月的逾期总天数
									bean.setField17(String.valueOf(days));
								}
							}
						}
						
						//投标记录
						if(h3Text.indexOf("投标记录") >= 0){
							TagNode[] olArr = HtmlCleanerUtil.getMultiNode(tabNode, "//div[@class='scroll-area']/ol");
							if(olArr != null){
								String currentTime = "";
								String lastTime = "";
								String amount = "0";
								boolean exists = false;
								
								for(int i=0; i<olArr.length; i++){
									TagNode olNode = olArr[i];
									
									//name
									String name = HtmlCleanerUtil.getSingleNodeText(olNode, "//li[1]/a[@class='listname']/text()");
									
									//time
									currentTime = HtmlCleanerUtil.getSingleNodeText(olNode, "//li[5]/text()");
									if(i == 0){
										lastTime = currentTime;
									}
									
									if(name.endsWith(listnameSuffix) && !exists){
										exists = true;
										
										//amount
										amount = HtmlCleanerUtil.getSingleNodeText(olNode, "//li[4]/text()").toString().replace("¥", "").trim();
									}
								}
								
								if(exists){
									//鱼投
									bean.setField22(String.valueOf(amount));
								}else{
									//鱼投
									bean.setField22("");
								}
								
								bean.setField22_1(currentTime);
								bean.setField22_2(lastTime);
								
								//投标耗时秒数
								SimpleDateFormat sdf = new SimpleDateFormat("yyyy/M/dd HH:mm:ss");
								Date date2 = sdf.parse(lastTime);
								Date date1 = sdf.parse(currentTime);
								long seconds = (date2.getTime() - date1.getTime()) / 1000;
								bean.setField22_3(String.valueOf(seconds));
							}
						}
						
						//还款记录
						if(h3Text.indexOf("还款记录") >= 0){
							TagNode[] trArr = HtmlCleanerUtil.getMultiNode(tabNode, "//table[@class='lendDetailTab_tabContent_table1 pay-record']//tr");
							if(trArr != null){
								for(int i=1; i<trArr.length; i++){ //行
									TagNode trNode = trArr[i];
									TagNode[] tdArr = HtmlCleanerUtil.getMultiElementByName(trNode, "td");
									String repayStatus = HtmlCleanerUtil.getSingleNodeText(tdArr[6], "//span/text()");
									if(repayStatus.indexOf("等待还款") >= 0){
										//逾期的期次
										bean.setField22_4(String.valueOf(i));
										
										//逾期天数
										String overdueDays = HtmlCleanerUtil.getSingleNodeText(tdArr[5], "//span/text()");
										bean.setField22_5(overdueDays.replace("/", "").replace("天", "").trim());
									}
								}
							}
						}
						
					}
					
				}
			}
			
			bean.setField24(""); //gen
			
			DecimalFormat df1 = new DecimalFormat("####");
			DecimalFormat df2 = new DecimalFormat("####.######");
			
			bean.setField25(df1.format(Double.parseDouble(bean.getField3()) + Double.parseDouble(bean.getField19()))); //借+待
			if(Double.parseDouble(bean.getField21()) > 0){
				bean.setField26(df2.format(Double.parseDouble(bean.getField25()) / Double.parseDouble(bean.getField21()))); //负债比
			}
			if(Double.parseDouble(bean.getField20()) > 0){
				bean.setField27(df2.format(Double.parseDouble(bean.getField3()) / Double.parseDouble(bean.getField20()))); //单笔比
			}
			if(Double.parseDouble(bean.getField9()) > 0){
				bean.setField28(df2.format(Double.parseDouble(bean.getField14()) / Double.parseDouble(bean.getField9()))); //借款比
			}
			
			return bean;
		}catch(Exception ex){
			ex.printStackTrace();
			return null;
		}
	}
	
}
