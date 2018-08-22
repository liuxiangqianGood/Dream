package com.hope.Service;

import java.util.List;

import com.hope.Bean.T_Role;



public interface RoleService {

	List<?> findall();
		// TODO Auto-generated method stub

	List listpage(String page, String rows);
		
	boolean saveorupdate(T_Role role);
	
	void delete(Object object);
	
	Object findOrderid(String rid);




}
