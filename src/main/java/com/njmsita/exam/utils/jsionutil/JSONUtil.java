package com.njmsita.exam.utils.jsionutil;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

public class JSONUtil {

	public static final String toJSONString(Object obj) {
		return JSON.toJSONString(obj, SerializerFeature.WriteMapNullValue);
	}
	
}
