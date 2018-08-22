package com.layui.Dao;

import java.util.List;
import java.util.Map;

import com.layui.Entity.tuser;

public interface UserDao {

	List findUser(tuser tuser);
	List findaddtuid(Map<String,Object> map);
	void addUser(Map<String,Object> map);
	Integer deletetuid(Map<String,Object> map);
	Integer updatername(Map<String,Object> map);
}
