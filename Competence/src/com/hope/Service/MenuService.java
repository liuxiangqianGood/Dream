package com.hope.Service;

import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import com.hope.Bean.T_Menu;
import com.hope.Bean.cart;

public interface MenuService {

	JSONObject findmenu(String total,String rows,String where);
		// TODO Auto-generated method stub

	List listpage(String page, String rows);
		
	boolean saveorupdate(T_Menu menu);
	
	void delete(T_Menu menu);
	
	void add(T_Menu menu);
	






}
