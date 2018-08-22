package com.lxq.DaoImp;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import redis.clients.jedis.Jedis;

import com.lxq.Dao.LoginDao;
import com.lxq.Entity.aop;
@Repository("loginDao")
public class LoginDaoImp implements LoginDao{
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	@Override
	public List findlogin(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return sqlSessionTemplate.selectList("map.findlogin",map);
	}

	@Override
	public List findrname(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return sqlSessionTemplate.selectList("map.findrname",map);
	}

	@Override
	public void addaop(aop aop) {
		// TODO Auto-generated method stub
		sqlSessionTemplate.insert("map.addaop",aop);
	}
	
	/*public static void main(String[] args) {
		Jedis jedis = new Jedis("127.0.0.1",6379);jedis.sadd("a", "hello world");
	}
*/
}
