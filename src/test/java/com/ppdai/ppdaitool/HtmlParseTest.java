package com.ppdai.ppdaitool;
import com.ppdai.ppdaitool.export.HtmlParseResultExporter;


public class HtmlParseTest {
	public static void main(String[] args) {
		HtmlParseResultExporter.execute("D:\\git_proj\\study\\HtmlParse\\doc\\pages", "d:\\test.xls");
		
//		String filename = "D:\\git_proj\\study\\HtmlParse\\doc\\pages\\169.html";
//		HtmlParseVo bean = HtmlParser.parse(filename);
//		System.out.println(bean.toString());
		
		System.out.println("finish");
	}
	
}
