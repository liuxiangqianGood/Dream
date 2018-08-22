package com.layui.Service;

import java.util.List;
import java.util.Map;

import com.layui.Entity.im;
import com.layui.Entity.menu;

public interface LayuiService {

	List findmenu(Map<String,Object> map);
	List findim(im im);
	String delIM(Map map);
	List findid(Map map);
	String updateid(Map map);
}
