package com.ppdai.ppdaitool;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import com.ppdai.ppdaitool.htmlpage.analysis.TenderPageFetcher;

public class TenderPageFetcherTest {
	public static void main(String[] args) {
		while(true){
			System.out.println("开始抓取...");
			List<String> pageUrlList = new ArrayList<String>();
			pageUrlList.add("http://www.ppdai.com/user/pdu4212037825");
			
			TenderPageFetcher fetcher = new TenderPageFetcher("d:\\pptools_page\\");
			fetcher.execute(pageUrlList);
			
			try {
				//每隔5分钟执行一次抓取
				TimeUnit.SECONDS.sleep(60 * 5);
			} catch (InterruptedException e) {
				e.printStackTrace();
			} 
		}
	}
	
}
