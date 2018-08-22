package com.lxq.Redis;

public interface JedisClient {
	//Redis自己封装好的方法本次项目没用到
	String set(String key, String value); 
	String get(String key);
	Boolean exists(String key);
	Long expire(String key, int seconds);
	Long ttl(String key);
	Long incr(String key);
	Long hset(String key, String field, String value);
	String hget(String key, String field);
	Long hdel(String key, String... field);
}
