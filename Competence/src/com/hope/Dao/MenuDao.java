package com.hope.Dao;

import java.util.List;

import com.hope.Bean.T_Menu;
import com.hope.Bean.cart;


public interface MenuDao {

	List findmenu(String sql);
	
	List listpage(String sql);
	
	boolean saveorupdate(T_Menu menu);
	
	void delete(T_Menu menu);
	
	void add(T_Menu menu);
	

	
	
}
