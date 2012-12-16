package com.biyi.util;

import java.io.File;

public final class StringUtil {

	/**
	 * 判断字符串是否是空的
	 */
	public static boolean isEmpty(String str){
		return (str == null) || ("".equals(str = str.trim()));
	}
	
	public static boolean isNull(String str){
		return (str == null) || ("".equals(str = str.trim())) || "null".equals(str);
	}
	
	public static void main(String[] args) {
		System.out.println(File.separator);
	}

	public static String RSlash(String path) {
		
		if(path.endsWith(File.separator)){
			return path;
		}
		return path + File.separator;
	}
	
	public static String URLRSlash(String path) {
		
		if(path.endsWith("/")){
			return path;
		}
		return path + "/";
	}
	
	public static String defaultValue(String value, String defaultValue){
		if(isEmpty(value)){
			return defaultValue;
		}
		return value;
	}
	
	public static String noNull(String str){
		if(isNull(str)){
			return "";
		}
		return str;
	}
	
	public static String select(Integer dest, Integer source){
		if(dest.equals(source)){
			return "selected='selected'";
		}
		return "";
	}
}
