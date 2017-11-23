package com.ppdai.ppdaitool.utils;



public class StringUtil {
	public static String trimToEmpty(String text){
		if(text == null){
			return "";
		}else{
			return text.trim();
		}
	}
	
	public static boolean isEmpty(String text){
		return (text == null || text.length() == 0);
	}
	
	public static boolean isNotEmpty(String text){
		return (text != null && text.length() > 0);
	}
	
}
