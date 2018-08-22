package com.lxq.Redis;

public interface JedisClient {
	//Redis�Լ���װ�õķ���������Ŀû�õ�
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
