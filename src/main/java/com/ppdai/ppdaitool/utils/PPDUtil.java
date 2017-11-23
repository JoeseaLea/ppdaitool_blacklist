package com.ppdai.ppdaitool.utils;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;

import javax.imageio.ImageReader;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.NicelyResynchronizingAjaxController;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlImage;
import com.gargoylesoftware.htmlunit.html.HtmlInput;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.util.Cookie;
import com.ppdai.ppdaitool.utils.Dama2Util.DecodeResult;

public class PPDUtil {
	
	public static WebClient webClient = null;
	private static Set<Cookie> cookies = null;
	
	/*
	 * 请求间隔时间
	 */
	private static int minIntervalTime = Integer.valueOf(PropertiesUtil.getInstance().getProperty("min_interval_time", "5000"));
	private static int maxIntervalTime = Integer.valueOf(PropertiesUtil.getInstance().getProperty("max_interval_time", "5000"));
	private static int interval = maxIntervalTime - minIntervalTime;
	private static Random random = new Random();
	
	/*
	 * 打码兔配置信息
	 */
	private static int dama2AppID = Integer.valueOf(PropertiesUtil.getInstance().getProperty("appID", "51147"));
	private static String dama2SoftKey = PropertiesUtil.getInstance().getProperty("softKey", "0a7c1144d71e933c3356de4ba526f12c");
	private static String dama2Uname = PropertiesUtil.getInstance().getProperty("uname", "damasecurity");
	private static String dama2Upwd = PropertiesUtil.getInstance().getProperty("upwd", "joesea");
	
	/*
	 * 验证码保存路径配置信息
	 */
	private static String valiCodeImgSavePath = PropertiesUtil.getInstance().getProperty("valiCodeImgSavePath", "F:/\u62CD\u62CD\u8D37/valiCodeImg");
	
	public static WebClient getWebClient() {

		WebClient webClient = new WebClient(BrowserVersion.CHROME);
		webClient.getOptions().setJavaScriptEnabled(true); // 启用JS解释器，默认为true
		webClient.getOptions().setCssEnabled(false); // 禁用css支持
		// 设置Ajax异步处理控制器即启用Ajax支持
		webClient.setAjaxController(new NicelyResynchronizingAjaxController());
		// 当出现Http error时，程序不抛异常继续执行
		webClient.getOptions().setThrowExceptionOnFailingStatusCode(false);
		// 防止js语法错误抛出异常
		webClient.getOptions().setThrowExceptionOnScriptError(false); // js运行错误时，是否抛出异常
		webClient.getCookieManager().setCookiesEnabled(true);//开启cookie管理
		webClient.getOptions().setRedirectEnabled(true); 
		webClient.getOptions().setUseInsecureSSL(true);
		webClient.setAjaxController(new NicelyResynchronizingAjaxController());
		
//		webClient.getOptions().getProxyConfig().setProxyHost("61.54.56.222");
//		webClient.getOptions().getProxyConfig().setProxyPort(80);
		return webClient;
	}
	
	
	/**
	 * 登录拍拍贷
	 * @param username 用户名
	 * @param password 密码
	 * @return htmlPage 登录后跳转的页面数据
	 * @throws FailingHttpStatusCodeException
	 * @throws MalformedURLException
	 * @throws IOException
	 */
	public static HtmlPage login(String username, String password) throws FailingHttpStatusCodeException, MalformedURLException, IOException {
		webClient = getWebClient();
		// 拿到这个网页
        HtmlPage htmlPage = webClient.getPage("https://ac.ppdai.com/User/Login?message=&Redirect=");
        
        // 填入用户名和密码
        HtmlInput inputUsername = null;
        HtmlInput inputPassword = null;
        HtmlInput inputValidateCode = null;
        HtmlInput submit = null;
        
        inputUsername = (HtmlInput) htmlPage.getElementById("UserName");
        inputUsername.type(username);
        inputPassword = (HtmlInput) htmlPage.getElementById("Password");
        inputPassword.type(password);

        // 提交
        submit = (HtmlInput) htmlPage.getElementById("login_btn");
        htmlPage = submit.click();
        
        PPDUtil.cookies = webClient.getCookieManager().getCookies();
        
        String validateCode = null;
        
    	while (!PPDUtil.isLoginSuccess()) {
    		sleep ();
    		
    		if (null != htmlPage.getElementById("CodeImg")) {
    			htmlPage = htmlPage.getElementById("CodeImg").click();
    		}
    		
            inputValidateCode = (HtmlInput) htmlPage.getElementById("imgyzm");
            
            HtmlImage valiCodeImg = (HtmlImage) htmlPage.getElementById("CodeImg");
        	ImageReader imageReader = valiCodeImg.getImageReader();
        	BufferedImage bufferedImage = imageReader.read(0);

        	FileUtil.saveValiCodeImg(bufferedImage, valiCodeImgSavePath);
        	
        	Dama2Util dama = new Dama2Util(dama2AppID, dama2SoftKey, dama2Uname, dama2Upwd);
        	DecodeResult decodeResult = dama.d2File(4, 60, FileUtil.getFileHex(valiCodeImgSavePath + "/valiCodeImg.jpg"));
        	validateCode = decodeResult.result;
        	
        	inputValidateCode.setValueAttribute(validateCode);
            
            // 提交
            submit = (HtmlInput) htmlPage.getElementById("login_btn");
            htmlPage = submit.click();

            cookies = webClient.getCookieManager().getCookies();
    	}
        
        webClient.close();
        
        return htmlPage;
	}
	
	/**
	 * 判断登录是否成功
	 * @param cookies 登录后的cookies
	 * @return 登录成功返回true，不成功返回false
	 */
	public static boolean isLoginSuccess() {
		if (null != cookies) {
			String cookiesStr = cookies.toString();
			if (cookiesStr.contains("authid=") && cookiesStr.contains("ppd_uname=")) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * 获取指定url页面数据
	 * @param url url地址
	 * @return 页面数据
	 * @throws FailingHttpStatusCodeException
	 * @throws MalformedURLException
	 * @throws IOException
	 */
	public static HtmlPage getUrlPage(String url) throws FailingHttpStatusCodeException, MalformedURLException, IOException {
		sleep ();
		webClient = getWebClient();
		Iterator<Cookie> i = cookies.iterator();
		while (i.hasNext()) {
			webClient.getCookieManager().addCookie(i.next());
		}
		HtmlPage htmlPage = webClient.getPage(url);
		webClient.close();
		return htmlPage;
	}
	
	public static void sleep () {
        try {
			Thread.sleep(minIntervalTime + random.nextInt(interval));
		} catch (InterruptedException e) { }
	}
}
