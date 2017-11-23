package com.ppdai.ppdaitool.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

public class PropertiesUtil {
    private static final String DEFAULT_PROPERTIES = "conf.properties";
	private final Properties properties;
	private static ResourceLoader resourceLoader = new DefaultResourceLoader();
	private static PropertiesUtil instance = new PropertiesUtil();
	
	public PropertiesUtil(){
		properties = initProperties(DEFAULT_PROPERTIES);
	}
	
	public PropertiesUtil(String propertiesFileName){
		properties = initProperties(propertiesFileName);
	}
	
	private Properties initProperties(String propertiesFileName){
    	InputStream inputStream = null;
		Properties props = new Properties();
		
		try {
	    	Resource resource = resourceLoader.getResource(propertiesFileName);
	    	inputStream = resource.getInputStream();
	    	props.load(inputStream);
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			try {
				inputStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		return props;
	}
	
	public static PropertiesUtil getInstance(){
	    return instance;
	}
	
	public static PropertiesUtil getInstance(String propertyFileName){
		return new PropertiesUtil(propertyFileName);
	}

	public String getProperty(String key){
		return getProperty(key, null);
	}
	
	public String getProperty(String key, String defaultValue){
		String value = properties.getProperty(key);
		return (value != null) ? value.trim() : defaultValue;
	}

    public static void main(String[] args) {
    	try{
	        System.out.println(PropertiesUtil.getInstance().getProperty("username"));
	        System.out.println(PropertiesUtil.getInstance().getProperty("password"));
    	}catch(Exception ex){
    		ex.printStackTrace();
    	}
    }
	
}
