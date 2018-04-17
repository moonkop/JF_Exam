package com.njmsita.exam.utils.format;

import java.io.File;
import java.io.IOException;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FileUtils;

public class Base64Util {

	/**
	 * 将传入的字符串进行Base64编码后返回
	 * @param str 未编码字符串
	 * @return Base64编码字符串
	 */
	public static String encode(String str) {
		return new String(Base64.encodeBase64(str.getBytes()));
	}
	
	/**
	 * 将传入的Base64编码字符串解码后返回
	 * @param str Base64编码字符串
	 * @return 解码后字符串
	 */
	public static String decode(String str) {
		return new String(Base64.decodeBase64(str.getBytes()));
	}
	
	/**
	 * 将传入的文件转为Base64编码字符串
	 * @param file 要编码的文件
	 * @return Base64编码字符串
	 */
	public static String fileToStr(File file) {
		String str = null;
		try {
			byte[] fileData = FileUtils.readFileToByteArray(file);
			return new String(Base64.encodeBase64(fileData));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return str;
	}
	
	/**
	 * 将Base64编码字符串写到传入的文件中
	 * @param file 文件对象
	 * @param str Base64字符串
	 */
	public static void strToFile(File file, String str) {
		byte[]	base64Byte = Base64.decodeBase64(str.getBytes());
		try {
			FileUtils.writeByteArrayToFile(file, base64Byte);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
