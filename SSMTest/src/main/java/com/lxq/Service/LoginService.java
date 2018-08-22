package com.lxq.Service;

import java.util.List;
import java.util.Map;

public interface LoginService {

	String findlogin(Map<String,Object> map);
	List finduser();
	String deleteid(Map<String,Object> map);
	List findid(Map<String,Object> map);
	String updateid(Map<String,Object> map);
	String adduser(Map<String,Object> map);
}
