package com.layui.DaoImp;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.layui.Dao.UserDao;
import com.layui.Entity.tuser;
@Repository("userDao")
public class UserDaoImp implements UserDao{
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	@Override
	public List findUser(tuser tuser) {
		// TODO Auto-generated method stub
		return sqlSessionTemplate.selectList("map.findUser",tuser);
	}
	@Override
	public List findaddtuid(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return sqlSessionTemplate.selectList("map.findaddtuid",map);
	}
	@Override
	public void addUser(Map<String, Object> map) {
		// TODO Auto-generated method stub
		sqlSessionTemplate.insert("map.addUser",map);
	}
	@Override
	public Integer deletetuid(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return sqlSessionTemplate.delete("map.deletetuid",map);
	}
	@Override
	public Integer updatername(Map<String,Object> map) {
		// TODO Auto-generated method stub
		return sqlSessionTemplate.update("map.updatername",map);
	}
	
}
