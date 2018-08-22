package com.lxq.ServiceImp;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lxq.Dao.LoginDao;
import com.lxq.Service.LoginService;

@Service("loginService")
public class LoginServiceImp implements LoginService{
	@Autowired
	private LoginDao loginDao;
	
	//²éÑ¯µÇÂ¼
	@Override
	public String findlogin(Map<String, Object> map) {
		// TODO Auto-generated method stub
		List list=loginDao.findlogin(map);
		if(list.size()>0){
			return "success";
		}else{
			return "error";
		}
	}

	@Override
	public List finduser() {
		// TODO Auto-generated method stub
		return loginDao.finduser();
	}

	@Override
	public String deleteid(Map<String, Object> map) {
		// TODO Auto-generated method stub
		Integer i=loginDao.deleteid(map);
		if(i==1){
			return "success";
		}else{
			return "error";
		}
	}

	@Override
	public List findid(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return loginDao.findid(map);
	}

	@Override
	public String updateid(Map<String, Object> map) {
		// TODO Auto-generated method stub
		Integer i=loginDao.updateid(map);
		if(i==1){
			return "success";
		}else{
			return "error";
		}

	}

	@Override
	public String adduser(Map<String, Object> map) {
		// TODO Auto-generated method stub
		Integer i=loginDao.adduser(map);
		if(i==1){
			return "success";
		}else{
			return "error";
		}
	}

}
