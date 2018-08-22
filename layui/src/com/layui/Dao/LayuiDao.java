package com.layui.Dao;

import java.util.List;
import java.util.Map;

import com.layui.Entity.im;
import com.layui.Entity.menu;

public interface LayuiDao {

	List findmenu(Map<String,Object> map);
	List findim(im im);
	Integer delIM(Map map);
	List findid(Map map);
	Integer updateid(Map map);
}
