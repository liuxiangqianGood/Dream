package com.layui.Service;

import java.util.List;
import java.util.Map;

public interface LoginService {

	List login(Map<String,Object> map);
	String regist(Map<String,Object> map);

}
