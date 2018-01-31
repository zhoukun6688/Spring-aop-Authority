package com.spring.utils;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JsonUtils {

	private JsonUtils(){}

	private static final ObjectMapper MAPPER = new ObjectMapper();

	private static final Logger logger= LoggerFactory.getLogger(JsonUtils.class);

	public static String objectToJson(Object object) {
		if (object != null) {
			try {
				String jsonData = MAPPER.writeValueAsString(object);
				return jsonData;
			} catch (Exception e) {

				logger.info("转换出错",e);
			}
		}
		return null;
	}

	public static <T> T jsonToObject(String jsonData, Class<T> clazz) {
		if (StringUtils.isNotBlank(jsonData)) {
			try {
				T t = MAPPER.readValue(jsonData, clazz);
				return t;
			} catch (Exception e) {
				logger.info("转换出错",e);
			}
		}

		return null;
	}

	public static <T> List<T> jsonToList(String jsonData, Class<T> clazz) {
		if (StringUtils.isNotBlank(jsonData)) {
			try {
				List<T> list = MAPPER.readValue(jsonData,
						MAPPER.getTypeFactory().constructCollectionType(List.class, clazz));
				return list;
			} catch (Exception e) {
				logger.info("转换出错",e);
			}
		}
		return null;
	}

}
