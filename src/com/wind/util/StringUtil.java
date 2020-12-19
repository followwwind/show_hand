package com.wind.util;

/**
 * 字符串工具类
 * @author follow
 *
 */
public class StringUtil {

	
	private StringUtil(){
		
	}
	
	/**
	 * null或者空字符串
	 * @param str
	 * @return
	 */
	public static boolean isBlank(String str){
		return str == null || "".equals(str);
	}
}
