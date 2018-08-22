package com.lxq.DaoImp;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.lxq.Dao.HtDao;

@Repository("htDao")
public class HtDaoImp implements HtDao{
	
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	@Override
	public List findmenus(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return sqlSessionTemplate.selectList("map.findmenus",map);
	}

}
