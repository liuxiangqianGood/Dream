package com.hope.Dao;

import java.util.List;

import com.hope.Bean.T_User;

public interface LoginDao {
//	��¼
	public int login(T_User user);
//	��ѯ����
	List<?> findall(String sql);
//	��ҳ
	void add(T_User user);
	
	public List listpage(String sql);
	
	void deleteuser(T_User user);
	
	void update(T_User user);

	List createSql(String sql);
	T_User findid(String id);
}
