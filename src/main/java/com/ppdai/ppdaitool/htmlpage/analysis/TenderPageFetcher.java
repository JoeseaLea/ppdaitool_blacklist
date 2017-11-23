package com.ppdai.ppdaitool.htmlpage.analysis;

import java.io.File;
import java.util.List;

import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.ppdai.ppdaitool.utils.FileUtil;
import com.ppdai.ppdaitool.utils.PPDUtil;
import com.ppdai.ppdaitool.utils.PropertiesUtil;

/**
 * 投标记录页面抓取
 * 	如： http://www.ppdai.com/user/pdu4212037825
 */
public class TenderPageFetcher {
	private static final String BASE_URL = "http://www.ppdai.com";
	private String saveBasePath;
	
	public TenderPageFetcher(String saveBasePath){
		this.saveBasePath = saveBasePath;
	}
	
	public void execute(List<String> pageUrlList){
		try{
			if(pageUrlList != null && pageUrlList.size() > 0){
				login();
				
				for(String pageUrl : pageUrlList){
					fetchPage(pageUrl);
				}
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	/**
	 * 登录
	 */
	private void login()throws Exception{
        String username = PropertiesUtil.getInstance().getProperty("username");
        String password = PropertiesUtil.getInstance().getProperty("password");
        PPDUtil.login(username, password);
	}
	
	/**
	 * 页面抓取并解析
	 */
	private void fetchPage(String pageUrl){
		try{
			String uid = pageUrl.substring(pageUrl.lastIndexOf("/") + 1);
			System.out.println("uid=" + uid);
			
			//页面所在文件夹
			String dirPath = getSaveBasePath() + uid;
			File dirFile = new File(dirPath);
			if(!dirFile.exists()){
				dirFile.mkdirs();
			}
			
			//页面抓取
			HtmlPage htmlPage = PPDUtil.getUrlPage(pageUrl);
	        
	        //投标记录列表数据
	        @SuppressWarnings("rawtypes")
			List aList = htmlPage.getByXPath("//div[@id='memberinfocontent']/div[@id='div2']//tr//a");
	        if(aList != null && aList.size() > 0){
	        	for(int i=0; i<aList.size(); i++){
	        		try{
	        			String href = ((HtmlAnchor)aList.get(i)).getAttribute("href");
		        		if(href != null && href.length() > 0){
		        			//明细页面URL地址
		        			String pageFullPath = BASE_URL + href;
		        			String id = pageFullPath.substring(pageFullPath.lastIndexOf("/") + 1);
		        			System.out.println(id + " --> " + pageFullPath);
		        			
		        			//保存页面内容到文件
		        			String pageFilePath = dirPath + File.separator + id + ".html";
		        			System.out.println("pageFilePath=" + pageFilePath);
		        			File pageFile = new File(pageFilePath);
		        			if(!pageFile.exists()){
			        			//页面内容
			        			HtmlPage detailPage = PPDUtil.getUrlPage(pageFullPath);
			        			String pageContent = detailPage.asXml();
			        			
		        				FileUtil.writeFile(pageFilePath, pageContent);
		        			}
		        		}
	        		}catch(Exception ex){
	        			//ex.printStackTrace();
	        		}
	        	}
	        }
	        
	        System.out.println("page fetch finish: " + pageUrl);
	        
		}catch(Exception ex){
			//ex.printStackTrace();
		}
	}
	
	public String getSaveBasePath() {
		if(!saveBasePath.endsWith("/") && !saveBasePath.endsWith("\\")){
			saveBasePath += File.separator;
		}
		return saveBasePath;
	}
	
}
