package com.hope.Service;

import java.util.List;

import net.sf.json.JSONObject;

import com.hope.Bean.T_User;



public interface LoginService{
	
	public String Login(T_User user);

	void add(T_User user);
//	∑÷“≥≤È—Ø
	List listpage(String pageone,String pagesize);
	
	void deleteuser(T_User user);
	
	void update(T_User user);


	List querymenu();

	List queryuser();

	T_User findid(String id);

	JSONObject findall(String total, String rows);

      //public List querymenu(int id);
//	
//	boolean Fistrue(int id);
}
