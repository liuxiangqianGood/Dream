package com.hope.Dao;

import java.util.List;

import com.hope.Bean.T_Role;



public interface RoleDao {

	List<?> findall();
	
	List listpage(String sql);
	
	boolean saveorupdate(T_Role order);
	
	void deleteOrder(Object object);
	
	Object findOrderid(String orderid);
	
	
}
