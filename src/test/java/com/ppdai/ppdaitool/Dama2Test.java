package com.ppdai.ppdaitool;

import org.junit.Test;

import com.ppdai.ppdaitool.utils.MD5Util;

public class Dama2Test {

	@Test
	public void test() {
		
		String appID = "51147";
		String user = "damasecurity";
		String pwd = "joesea";
		String key = "0a7c1144d71e933c3356de4ba526f12c";
		int type = 4;
		String url = "https://ac.ppdai.com/ValidateCode/Image";
//		String cookie = "";
//		String sign = "";
//		、user、pwd、type、len(可选)、timeout（可选）、url、cookie（可选）、referer（可选）、sign
		
		StringBuilder damaURL = new StringBuilder("http://api.dama2.com:7766/app/d2Url");
		damaURL.append("?appID=" + appID);
		damaURL.append("&user=" + user);
		
		String md5User = MD5Util.getMD5(user);
		String md5Password = MD5Util.getMD5(pwd);
		String md5UserPwd = MD5Util.getMD5(md5User + md5Password);
		String md5Pwd = MD5Util.getMD5(key + md5UserPwd);
		
		damaURL.append("&pwd=" + md5Pwd);
		damaURL.append("&type=" + type);
		damaURL.append("&url=" + url);
		
//		连接软件KEY、用户名和其他需要签名的字段参数，得到A。如软件KEY为“c94984758dd79a3dfbe19e6ef46552a6”，用户名为“name”,对于查询余额，结果为“c94984758dd79a3dfbe19e6ef46552a6name”。
//		对上一步结果A进行MD5，并扩展为16进制形式得到B。对“c94984758dd79a3dfbe19e6ef46552a6name”进行MD5并扩展，结果为“7e1c15798951f6a49d4f33239a3c38cd”。
//		在上一步结果B的前面截取8个字符，就得到sign。如上一步截取结果为“7e1c1579”
//		软件KEY、user、url、cookie
		String md5Sign = MD5Util.getMD5(key + user + url);

		damaURL.append("&sign=" + md5Sign.substring(0, 8));
		
		System.out.println(damaURL);
		
	}

}
