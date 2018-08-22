package com.lxq.Controller;


import java.awt.Window;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import com.lxq.Log.SystemControllerLog;
import com.lxq.Service.LoginService;

@Controller

public class LoginController {
	@Autowired
	private JedisPool jedis;
	@Autowired
	private LoginService loginService;
	@RequestMapping(value="/login")
	@ResponseBody
	@SystemControllerLog(description="查询登录")
	public ModelAndView login(HttpServletRequest request,HttpServletResponse response)throws Exception{
	
		String username=request.getParameter("username");
		String userpsw=request.getParameter("userpsw");
		
		UsernamePasswordToken token=new UsernamePasswordToken(username,userpsw);
		token.setRememberMe(true);
		Subject subject=SecurityUtils.getSubject();
		try {
			System.out.println("......准备进入realm验证.....");
			subject.login(token);
			System.out.println("user:["+username+"]验证成功");

			//获取登录的用户角色
			Map<String,Object> map=new HashMap<String, Object>();
			map.put("username", username);
			List list=loginService.findrname(map);
			JSONArray jsonArray=JSONArray.fromObject(list);
			JSONObject jsonObject=(JSONObject) jsonArray.get(0);
			String rname=jsonObject.getString("RNAME");
			subject.getSession().setAttribute("rname", rname);
			subject.hasRole(rname);
			System.out.println("user:["+rname+"]角色认证成功");

		} catch (UnknownAccountException un) {
			// TODO: handle exception
			System.out.println("未知账户异常");
			
		}catch (IncorrectCredentialsException in) {
			// TODO: handle exception
			System.out.println("不正确的凭据异常");
		
		}catch (LockedAccountException lo) {
			// TODO: handle exception
			System.out.println("锁定账户异常");
		
		}catch (ExcessiveAttemptsException Ex) {
			// TODO: handle exception
			System.out.println("频繁尝试异常");
		
		}catch (AuthenticationException au) {
			// TODO: handle exception
			System.out.println("user:["+username+"]未知异常请联系管理员");
		
		}
		//判断是否认证成功进入相应页面
		if(subject.isAuthenticated()){
			return new ModelAndView("/center");
		}else{
			token.clear();
			return new ModelAndView("/index");
		}	
		
	}
	//注销页面清空token,session
	@RequestMapping(value="/logout")
	@ResponseBody
	@SystemControllerLog(description="注销登录")
	public ModelAndView logout(){
		//获得Shiro工具
		Subject subject=SecurityUtils.getSubject();
		//退出登录清理token
		UsernamePasswordToken token=new UsernamePasswordToken();
		token.clear();
		String username=(String) subject.getSession().getAttribute("username");
		String userpsw=(String) subject.getSession().getAttribute("userpsw");
		Jedis je = jedis.getResource();
		//删除Redis里所有数据
		je.flushDB();
		je.close();
		SecurityUtils.getSubject().logout();
		return new ModelAndView("/index");
	}
}
