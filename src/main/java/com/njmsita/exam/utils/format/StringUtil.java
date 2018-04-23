package com.njmsita.exam.utils.format;

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
		return string==null||string.isEmpty();
	}

}
