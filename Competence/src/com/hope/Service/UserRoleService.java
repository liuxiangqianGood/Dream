package com.hope.Service;

import java.util.List;

import com.hope.Bean.T_User_Role;





public interface UserRoleService {

	List<?> findall();
		// TODO Auto-generated method stub

	List listpage(String page, String rows);
		
	boolean saveorupdate(T_User_Role ur);
	
	void delete(T_User_Role ur);
	
	void add(T_User_Role ur);




}
