package com.spring.utils;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtils {

	private static final ObjectMapper MAPPER = new ObjectMapper();

	public static String objectToJson(Object object) {
		if (object != null) {
			try {
				String jsonData = MAPPER.writeValueAsString(object);
				return jsonData;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
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
				// TODO Auto-generated catch block
				e.printStackTrace();
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
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}

}
