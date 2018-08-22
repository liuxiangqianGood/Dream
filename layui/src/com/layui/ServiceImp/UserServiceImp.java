package com.layui.ServiceImp;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.layui.Dao.UserDao;
import com.layui.Entity.tuser;
import com.layui.Service.UserService;
@Service("userService")
public class UserServiceImp implements UserService{
	@Autowired
	private UserDao userDao;
	@Override
	public List findUser(tuser tuser) {
		// TODO Auto-generated method stub
		return userDao.findUser(tuser);
	}
	@Override
	public List findaddtuid(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return userDao.findaddtuid(map);
	}
	@Override
	public void addUser(Map<String, Object> map) {
		// TODO Auto-generated method stub
		userDao.addUser(map);
	}
	@Override
	public String deletetuid(Map<String, Object> map) {
		// TODO Auto-generated method stub
		Integer i=userDao.deletetuid(map);
		if(i>0){
			return "success";
		}else{
			return "error";
		}
		
	}
	@Override
	public String updatername(Map<String, Object> map) {
		// TODO Auto-generated method stub
		Integer i=userDao.updatername(map);
		if(i>0){
			return "success";
		}else{
			return "error";
		}
	
	}

}
