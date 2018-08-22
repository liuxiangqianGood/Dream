package com.layui.Service;

import java.util.List;
import java.util.Map;

import com.layui.Entity.tuser;

public interface UserService {
	List findUser(tuser tuser);
	List findaddtuid(Map<String,Object> map);
	void addUser(Map<String,Object> map);
	String deletetuid(Map<String,Object> map);
	String updatername(Map<String,Object> map);
}
