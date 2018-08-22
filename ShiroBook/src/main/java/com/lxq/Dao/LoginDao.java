package com.lxq.Dao;

import java.util.List;
import java.util.Map;

import com.lxq.Entity.aop;

public interface LoginDao {
	List findlogin(Map<String,Object> map);
	List findrname(Map<String,Object> map);
	//增加日志
	void addaop(aop aop);
}
