package com.njmsita.exam.utils.format;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtil {
	/**
	 * 将元数据前补零，补后的总长度为指定的长度，以字符串的形式返回
	 * @param sourceDate 原数字
	 * @param formatLength 数字总长度
	 * @return 补零后的数字字符串
	 */
	public static String addZeroPrefix(int sourceDate, int formatLength) {
		/*
		 * 0 指前面补充零 formatLength 字符总长度为 formatLength d 代表为正数。
		 */
		String newString = String.format("%0" + formatLength + "d", sourceDate);
		return newString;
	}

	public static boolean isEmpty(String string)
	{
		return string==null||"".equals(string.trim());
	}

	public static boolean isNumber(String string){
		return string.matches("[0-9]+");
	}

	public static boolean isChar(String string){
		Pattern p = Pattern.compile("^[A-J]+$");
		Matcher m = p.matcher(string);
		return m.matches();
	}

	public static boolean isAnswer(String string,Integer size){
		if(string.length()<=10){
			for(int i =0;i<string.length()-1;i++){
				for(int j=i+1;j<string.length();j++){
					if(isChar(string)){
						if(string.charAt(i)==string.charAt(j)||(string.charAt(i)-'A')>=size||(string.charAt(j)-'A')>=size)
							return false;
					}else if(isNumber(string)){
						if(string.charAt(i)==string.charAt(j)||(string.charAt(i)-'0')>=size||(string.charAt(j)-'0')>=size)
							return false;
					}else {
						return false;
					}
				}
			}
			return true;
		}
		return false;
	}

	public static String sort(String string){
		char[] chars=string.toCharArray();
		for(int i =0;i<chars.length;i++){
			for(int j=i+1;j<string.length();j++){
				if(chars[i]>chars[j]){
					char temp=chars[i];
					chars[i]=chars[j];
					chars[j]=temp;
				}
			}
		}
		return new String(chars);
	}

	public static void main(String[] args){
		String num="98217";
		System.out.println(sort(num));
	}
}
