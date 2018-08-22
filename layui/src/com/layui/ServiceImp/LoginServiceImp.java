package com.layui.ServiceImp;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.layui.Dao.LoginDao;
import com.layui.Service.LoginService;
@Service("loginService")
public class LoginServiceImp implements LoginService{
		@Autowired
		private LoginDao loginDao;

	@Override
	public List login(Map<String,Object> map) {
		// TODO Auto-generated method stub
		
			List list=loginDao.findlogin(map);

			return list;
	}

	@Override
	public String regist(Map<String, Object> map) {
		// TODO Auto-generated method stub
		Integer i=loginDao.regist(map);
		if(i>0){
			return "success";
		}else{
			return "error";
		}
	}


}
