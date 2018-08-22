package com.lxq.Service;

import java.util.List;
import java.util.Map;

import com.lxq.Entity.aop;

public interface LoginService {

	List findlogin(Map<String,Object> map);
	List findrname(Map<String,Object> map);
	void addaop(aop aop);
}
