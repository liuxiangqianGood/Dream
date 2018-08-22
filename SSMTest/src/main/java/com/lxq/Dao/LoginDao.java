package com.lxq.Dao;

import java.util.List;
import java.util.Map;

public interface LoginDao {
		List findlogin(Map<String,Object> map);
		List finduser();
		Integer deleteid(Map<String,Object> map);
		List findid(Map<String,Object> map);
		Integer updateid(Map<String,Object> map);
		Integer adduser(Map<String,Object> map);
}
