package com.layui.Dao;

import java.util.List;
import java.util.Map;

public interface LoginDao {
//查询用户名密码登录
	List findlogin(Map<String,Object> map);
	Integer regist(Map<String,Object> map);

}
