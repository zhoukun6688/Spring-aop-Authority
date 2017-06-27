package com.spring.mybatis.componet.impl;

import org.springframework.beans.factory.annotation.Autowired;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import com.spring.mybatis.componet.JedisClient;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JedisClientSingle implements JedisClient {

	@Autowired
	private JedisPool jedisPool;

	@Override
	public String hmset(String key, Map<String, String> map) {
		Jedis jedis = jedisPool.getResource();
		String result=jedis.hmset(key,map);
		/*if (jedis!=null){
			jedisPool.returnResource(jedis);
		}*/
		jedis.close();
		return result;
	}

	@Override
	public List<String> hmget(String key, String filed) {
		Jedis jedis = jedisPool.getResource();
		List<String> result=jedis.hmget(filed);
		/*if (jedis!=null){
			jedisPool.returnResource(jedis);
		}*/
		jedis.close();
		return result;
	}

	@Override
	public Map<String, String> hgetAll(String key) {
		Jedis jedis = jedisPool.getResource();
		Map<String, String> value;
		value = jedis.hgetAll(key );
		/*if (jedis!=null){
			jedisPool.returnResource(jedis);
		}*/

		jedis.close();
        return  value;
	}

	@Override
	public String set(String key, String value) {
		Jedis jedis = jedisPool.getResource();
		String result = jedis.set(key, value);
		if (jedis!=null){
			jedisPool.returnResource(jedis);
		}
	//	jedis.close();
		return result;
	}

	@Override
	public String get(String key) {
		Jedis jedis = jedisPool.getResource();
		String result = jedis.get(key);
		if (jedis!=null){
			jedisPool.returnResource(jedis);
		}
		//jedis.close();
		return result;
	}

	@Override
	public Long hset(String key, String item, String value) {
		Jedis jedis = jedisPool.getResource();
		Long result = jedis.hset(key, item, value);
		if (jedis!=null){
			jedisPool.returnResource(jedis);
		}
		//jedis.close();
		return result;
	}

	@Override
	public String hget(String key, String item) {
		Jedis jedis = jedisPool.getResource();
		String result = jedis.hget(key, item);
		if (jedis!=null){
			jedisPool.returnResource(jedis);
		}
		//jedis.close();
		return result;
	}

	@Override
	public Long incr(String key) {
		Jedis jedis = jedisPool.getResource();
		Long result = jedis.incr(key);
		jedis.close();
		return result;
	}

	@Override
	public Long decr(String key) {
		Jedis jedis = jedisPool.getResource();
		Long result = jedis.decr(key);
		jedis.close();
		return result;
	}

	@Override
	public Long expire(String key, int second) {
		Jedis jedis = jedisPool.getResource();
		Long result = jedis.expire(key, second);
		jedis.close();
		return result;
	}

	@Override
	public Long ttl(String key) {
		Jedis jedis = jedisPool.getResource();
		Long result = jedis.ttl(key);
		jedis.close();
		return result;
	}

	@Override
	public Long hdel(String key, String item) {
		Jedis jedis = jedisPool.getResource();
		Long result = jedis.hdel(key, item);
		jedis.close();
		return result;
	}


}
