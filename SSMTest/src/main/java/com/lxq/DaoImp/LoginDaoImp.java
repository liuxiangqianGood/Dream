package com.lxq.DaoImp;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.lxq.Dao.LoginDao;

@Repository("loginDao")
public class LoginDaoImp implements LoginDao{
	
		@Autowired
		private SqlSessionTemplate sqlSessionTemplate;
		
	//²éÑ¯µÇÂ¼
	@Override
	public List findlogin(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return sqlSessionTemplate.selectList("map.findlogin",map);
	}

	@Override
	public List finduser() {
		// TODO Auto-generated method stub
		return sqlSessionTemplate.selectList("map.finduser");
	}

	@Override
	public Integer deleteid(Map<String, Object> map) {
		
		return sqlSessionTemplate.delete("map.deleteid",map);
	}

	@Override
	public List findid(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return sqlSessionTemplate.selectList("map.findid",map);
	}

	@Override
	public Integer updateid(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return sqlSessionTemplate.update("map.updateid",map);
	}

	@Override
	public Integer adduser(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return sqlSessionTemplate.insert("map.adduser",map);
	}

}
