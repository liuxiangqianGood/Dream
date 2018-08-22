package com.hope.Dao;

import java.util.List;

import com.hope.Bean.T_User_Role;





public interface UserRoleDao {

	List<?> findall();
	
	List listpage(String sql);
	
	boolean saveorupdate(T_User_Role ur);
	
	void delete(T_User_Role ur);
	
	void add(T_User_Role ur);
	
	
}
