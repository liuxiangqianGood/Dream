package com.layui.DaoImp;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.layui.Dao.LoginDao;
@Repository("loginDao")
public class LoginDaoImp implements LoginDao{
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;

	@Override
	public List findlogin(Map<String,Object> map) {
		// TODO Auto-generated method stub
		return sqlSessionTemplate.selectList("map.login",map);
	}

	@Override
	public Integer regist(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return sqlSessionTemplate.insert("map.regist",map);
	}


}
