package com.ppdai.ppdaitool.htmlpage.analysis;

import static org.junit.Assert.fail;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

import org.junit.Test;

import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.ppdai.ppdaitool.utils.PPDUtil;
import com.ppdai.ppdaitool.vo.BlacklistVo;

public class BlackListTest {

	@Test
	public void testGetBlackListInfo() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetBlackListInfoNew() {
		WebClient webClient = PPDUtil.getWebClient();
		try {
			HtmlPage htmlPage = webClient.getPage("file:///F:/拍拍贷/blacklistnew.html");
			BlackList bk = new BlackList();
			List<BlacklistVo> list = bk.getBlackListInfoNew(htmlPage);
			System.out.println(list.size());
		} catch (FailingHttpStatusCodeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Test
	public void testGetRepaymentDetailsHtmlPage() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetRepaymentDetails() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetBlacklistdetailURL() {
		fail("Not yet implemented");
	}

	@Test
	public void testIsLastPage() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetNextHtmlPageURL() {
		fail("Not yet implemented");
	}

}
