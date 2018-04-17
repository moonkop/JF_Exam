package com.njmsita.exam.utils.idutil;

import java.util.UUID;

/**
 * 得到常用ID
 * @author aoyun
 *
 */
public class IdUtil {

	/**
	 * 得到一个UUID字符串
	 * @return UUID字符串
	 */
	public static String getUUID() {
		return UUID.randomUUID().toString().replace("-", "");
	}
}
