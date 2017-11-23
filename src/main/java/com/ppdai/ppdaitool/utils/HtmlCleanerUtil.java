package com.ppdai.ppdaitool.utils;

import org.htmlcleaner.TagNode;

/**
 * HtmlCleaner工具类
 */
public class HtmlCleanerUtil {
	public static TagNode getSingleNode(TagNode parentNode, String xpath){
		try{
			Object[] nodes = parentNode.evaluateXPath(xpath);
			if(nodes != null && nodes.length > 0){
				TagNode tagNode = (TagNode)nodes[0];
				return tagNode;
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return null;
	}
	
	public static TagNode[] getMultiNode(TagNode parentNode, String xpath){
		try{
			Object[] nodes = parentNode.evaluateXPath(xpath);
			if(nodes != null && nodes.length > 0){
				TagNode[] nodeArr = new TagNode[nodes.length];
				for(int i=0; i<nodes.length; i++){
					nodeArr[i] = (TagNode)nodes[i];
				}
				return nodeArr;
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return null;
	}
	
	public static TagNode getSingleElementByName(TagNode parentNode, String name){
		TagNode[] nodeArr = parentNode.getElementsByName(name, true);
		if(nodeArr != null && nodeArr.length > 0){
			return nodeArr[0];
		}
		return null;
	}
	
	public static TagNode[] getMultiElementByName(TagNode parentNode, String name){
		TagNode[] nodeArr = parentNode.getElementsByName(name, true);
		if(nodeArr != null && nodeArr.length > 0){
			return nodeArr;
		}
		return null;
	}
	
	public static String getSingleNodeText(TagNode parentNode, String xpath){
		try{
			Object[] nodes = parentNode.evaluateXPath(xpath);
			if(nodes != null && nodes.length > 0){
				return nodes[0].toString().trim();
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return "";
	}
	
}
