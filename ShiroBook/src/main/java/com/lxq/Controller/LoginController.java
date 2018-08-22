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
	@SystemControllerLog(description="��ѯ��¼")
	public ModelAndView login(HttpServletRequest request,HttpServletResponse response)throws Exception{
	
		String username=request.getParameter("username");
		String userpsw=request.getParameter("userpsw");
		
		UsernamePasswordToken token=new UsernamePasswordToken(username,userpsw);
		token.setRememberMe(true);
		Subject subject=SecurityUtils.getSubject();
		try {
			System.out.println("......׼������realm��֤.....");
			subject.login(token);
			System.out.println("user:["+username+"]��֤�ɹ�");

			//��ȡ��¼���û���ɫ
			Map<String,Object> map=new HashMap<String, Object>();
			map.put("username", username);
			List list=loginService.findrname(map);
			JSONArray jsonArray=JSONArray.fromObject(list);
			JSONObject jsonObject=(JSONObject) jsonArray.get(0);
			String rname=jsonObject.getString("RNAME");
			subject.getSession().setAttribute("rname", rname);
			subject.hasRole(rname);
			System.out.println("user:["+rname+"]��ɫ��֤�ɹ�");

		} catch (UnknownAccountException un) {
			// TODO: handle exception
			System.out.println("δ֪�˻��쳣");
			
		}catch (IncorrectCredentialsException in) {
			// TODO: handle exception
			System.out.println("����ȷ��ƾ���쳣");
		
		}catch (LockedAccountException lo) {
			// TODO: handle exception
			System.out.println("�����˻��쳣");
		
		}catch (ExcessiveAttemptsException Ex) {
			// TODO: handle exception
			System.out.println("Ƶ�������쳣");
		
		}catch (AuthenticationException au) {
			// TODO: handle exception
			System.out.println("user:["+username+"]δ֪�쳣����ϵ����Ա");
		
		}
		//�ж��Ƿ���֤�ɹ�������Ӧҳ��
		if(subject.isAuthenticated()){
			return new ModelAndView("/center");
		}else{
			token.clear();
			return new ModelAndView("/index");
		}	
		
	}
	//ע��ҳ�����token,session
	@RequestMapping(value="/logout")
	@ResponseBody
	@SystemControllerLog(description="ע����¼")
	public ModelAndView logout(){
		//���Shiro����
		Subject subject=SecurityUtils.getSubject();
		//�˳���¼����token
		UsernamePasswordToken token=new UsernamePasswordToken();
		token.clear();
		String username=(String) subject.getSession().getAttribute("username");
		String userpsw=(String) subject.getSession().getAttribute("userpsw");
		Jedis je = jedis.getResource();
		//ɾ��Redis����������
		je.flushDB();
		je.close();
		SecurityUtils.getSubject().logout();
		return new ModelAndView("/index");
	}
}
