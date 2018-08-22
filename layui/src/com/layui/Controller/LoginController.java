package com.layui.Controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.layui.Service.LoginService;

@Controller
public class LoginController {

	@Autowired
	private LoginService loginService;
	//��¼�˴������жϽ�ɫ���õĵ�¼�û���ID
	@RequestMapping(value="/login")
	@ResponseBody
	public void login(HttpServletRequest request,HttpServletResponse response)throws Exception{
		String username=request.getParameter("username");
		String password=request.getParameter("password");
		Map<String,Object> map=new HashMap<String, Object>();
		map.put("username", username);
		map.put("password", password);
		List list=loginService.login(map);
		if(list.size()>0){
			JSONArray jsonArray=JSONArray.fromObject(list);
			JSONObject jsonObject=(JSONObject) jsonArray.get(0);
			String tuid=jsonObject.getString("tuid");
			HttpSession session=request.getSession();
			session.setAttribute("tuid", tuid);
			session.setAttribute("username", username);
			response.getWriter().write("success");
		}else{
	
			response.getWriter().write("error");
		}
	}
	//�����̨ҳ��
	@RequestMapping(value="/showHTlayui")
	@ResponseBody
	public ModelAndView showHTlayui()throws Exception{
		
		return new ModelAndView("HTlayui");
	}
	//ע��ҳ����ȡ��ע�᷵��success
	@RequestMapping(value="/cancel")
	@ResponseBody
	public void cancel(HttpServletResponse response)throws Exception{
		
		response.getWriter().write("success");
	}
	
	//ע���û���ת�ص�¼ҳ��
	@RequestMapping(value="/okcancel")
	@ResponseBody
	public ModelAndView okcancel(HttpServletRequest request)throws Exception{
		HttpSession session=request.getSession();
		session.removeAttribute("username");
		return new ModelAndView("login");
	}
	//��¼ҳ����ע�����ע��ҳ��
	@RequestMapping(value="/regist")
	@ResponseBody
	public ModelAndView regist(){
		return new ModelAndView("regist");	
	}
	//ȷ��ע��
	@RequestMapping(value="/enterregist")
	@ResponseBody
	public void enterregist(HttpServletRequest request,HttpServletResponse response)throws Exception{
		String username=request.getParameter("username");
		String password=request.getParameter("password");
		Map<String,Object> map=new HashMap<String, Object>();
		map.put("username", username);
		map.put("password", password);
		if(loginService.regist(map).equals("success")){
			HttpSession session=request.getSession();
			session.setAttribute("username", username);
			response.getWriter().write("success");
		}else{
			response.getWriter().write("error");
		}
	}

}
