package com.layui.ServiceImp;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.layui.Dao.LayuiDao;
import com.layui.Entity.im;
import com.layui.Entity.menu;
import com.layui.Service.LayuiService;
@Service("layuiService")
public class LayuiServiceImp implements LayuiService{
	@Autowired
	private LayuiDao layuiDao;
	@Override
	public List findmenu(Map<String,Object> map) {
		// TODO Auto-generated method stub
		return layuiDao.findmenu(map);
	}
	@Override
	public List findim(im im) {
		// TODO Auto-generated method stub
		return layuiDao.findim(im);
	}
	@Override
	public String delIM(Map map) {
		// TODO Auto-generated method stub
		Integer i=layuiDao.delIM(map);
		if(i>0){
			return "success";
		}else{
			return "error";
		}
	}
	@Override
	public List findid(Map map) {
		// TODO Auto-generated method stub
		return layuiDao.findid(map);
	}
	@Override
	public String updateid(Map map) {
		// TODO Auto-generated method stub
		Integer i=layuiDao.updateid(map);
		if(i>0){
			return "success";
		}else{
			return "error";
		}
		
	}

}
