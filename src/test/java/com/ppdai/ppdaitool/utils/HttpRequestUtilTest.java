package com.ppdai.ppdaitool.utils;

import org.junit.Test;

public class HttpRequestUtilTest {

	@Test
	public void testSendGet() {
		String s = HttpRequestUtil.sendGet("http://api.dama2.com:7766/app/d2Url?appID=51147&user=damasecurity&pwd=3bf5feb014d2b0bfc93387af3c521004&type=4&timeout=60&url=https%3A%2F%2Fac.ppdai.com%2F%2FValidateCode%2FImage%3Ftmp%3D0.5917451097722585&cookie=aliyungf_tc%3DAQAAACit+UWIVwMA6+mMPaOfQJSq0pxr%3B+uniqueid%3D5dd07b3a-0c2b-4822-8a33-1edb96f5cd65%3B+regSourceId%3D0%3B+referID%3D0%3B+fromUrl%3D%3B+referDate%3D2017-10-9+22%3A39%3A39%3B+currentUrl%3Dhttps%3A%2F%2Fac.ppdai.com%2Fuser%2Flogin%3Fmessage%3D%26redirect%3D%3B+aliyungf_tc%3DAQAAAGudfFjO8wkA6+mMPYghviRV65MR%3B+HMACCOUNT%3D4B1A145BB8ACA5D0%3B+Hm_lvt_f87746aec9be6bea7b822885a351b00f%3D1507559981%3B+Hm_lpvt_f87746aec9be6bea7b822885a351b00f%3D1507559981%3B+qlui_loginuname%3D319011485%40qq.com&sign=3b3aeece", "");
		System.out.println(s);
	}

}
