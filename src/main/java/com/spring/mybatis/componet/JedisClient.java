package com.spring.mybatis.componet;

import java.util.List;
import java.util.Map;

public interface JedisClient {
	public String set(String key, String value);
	public String get(String key);
	public Long hset(String key, String item, String value);
	public String hget(String key, String item);
	public Long incr(String key);
	public Long decr(String key);
	public Long expire(String key, int second);
	public Long ttl(String key);
	public Long hdel(String key, String item);
	public String hmset(String key, Map<String,String> map);
	public List<String> hmget(String key,String filed);
	public Map<String, String> hgetAll(String key);
}
