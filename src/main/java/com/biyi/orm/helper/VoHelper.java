package com.biyi.orm.helper;

import java.io.StringWriter;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class VoHelper {
	
	private static Map<Class<?>, Class<?>> classTranslate = new HashMap<Class<?>, Class<?>>();

	static{
		classTranslate.put(java.sql.Timestamp.class, java.util.Date.class);
		classTranslate.put(java.sql.Date.class, java.util.Date.class);
	}
	
	public static Class<?> getTranslateClass(Class<?> c){
		return classTranslate.get(c);
	}
	
	public static String getAttributeNameFromColName(String tableName) {
		Pattern p = Pattern.compile("_+(\\w?)");
		Matcher m = p.matcher(tableName);
		StringBuffer sb = new StringBuffer();
		while(m.find()){
			if(m.start() != 0)
				m.appendReplacement(sb, m.group(1).toUpperCase());
			else
				m.appendReplacement(sb, m.group(1).toLowerCase());
		}
		m.appendTail(sb);
		return sb.toString();
	}
	
	public static String getClassNameFromTableName(String tableName) {
		Pattern p = Pattern.compile("(_+|^)(\\w?)");
		Matcher m = p.matcher(tableName);
		StringBuffer sb = new StringBuffer();
		while(m.find()){
			m.appendReplacement(sb, m.group(2).toUpperCase());
		}
		m.appendTail(sb);
		
		return sb.toString();
	}
	
	private static boolean isPublicNoStatic(int i){
		return ((i & Modifier.PUBLIC) == Modifier.PUBLIC) && ((i & Modifier.STATIC) != Modifier.STATIC);
	}
	
	public static String getSetMethod(Class<?> c, String prefix, String postfix){
		StringWriter sw = new StringWriter();
		if(prefix == null)
			prefix = "";
		if(postfix == null)
			postfix = "";
		Method[] ms = c.getDeclaredMethods();
		if(ms != null && ms.length > 0){
			for(Method m: ms){
				String name = m.getName();
				if(name.startsWith("set") &&  isPublicNoStatic(m.getModifiers())){
					sw.append(prefix).append(name).append("(").append(postfix).append(");\r\n");
				}
			}
		}
		return sw.toString();
	}

}
