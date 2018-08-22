package com.layui.DaoImp;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.layui.Dao.LayuiDao;
import com.layui.Entity.im;
import com.layui.Entity.menu;
@Repository("layuiDao")
public class LayuiDaoImp implements LayuiDao{
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;

	@Override
	public List findmenu(Map<String,Object> map) {
		// TODO Auto-generated method stub
		return sqlSessionTemplate.selectList("map.findmenu",map);
	}

	@Override
	public List findim(im im) {
		// TODO Auto-generated method stub
		return sqlSessionTemplate.selectList("map.findim",im);
	}

	@Override
	public Integer delIM(Map map) {
		// TODO Auto-generated method stub
		return sqlSessionTemplate.delete("map.delIM",map);
	}

	@Override
	public List findid(Map map) {
		// TODO Auto-generated method stub
		return sqlSessionTemplate.selectList("map.findid",map);
	}

	@Override
	public Integer updateid(Map map) {
		// TODO Auto-generated method stub
		return sqlSessionTemplate.update("map.updateid",map);
	}



}
