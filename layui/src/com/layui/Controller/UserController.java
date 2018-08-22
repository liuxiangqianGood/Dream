package com.layui.Controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
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

import com.layui.Entity.tuser;
import com.layui.Service.UserService;

@Controller
public class UserController {

	@Autowired
	private UserService userService;
	
	private List jsonArray;
	
	public List getJsonArray() {
		return jsonArray;
	}
	public void setJsonArray(List jsonArray) {
		this.jsonArray = jsonArray;
	}

	
	//拿到新注册的用户的ID
		@RequestMapping(value="/findaddtuserid")
		@ResponseBody
		public void findaddtuserid(HttpServletRequest request,HttpServletResponse response)throws Exception{
			HttpSession session=request.getSession();
			String username=(String) session.getAttribute("username");
			Map<String, Object> map=new HashMap<String, Object>();
			map.put("username", username);
			List list=userService.findaddtuid(map);
			 jsonArray=JSONArray.fromObject(list);
			 JSONObject jsonObject=(JSONObject) jsonArray.get(0);
			String tuid=jsonObject.getString("tuid");
			session.setAttribute("tuid", tuid);
			response.sendRedirect("addUser");
		}
		//赋予新注册的用户普通角色
	@RequestMapping(value="/addUser")
	@ResponseBody
	public ModelAndView addUser(HttpServletRequest request){
		HttpSession session=request.getSession();
		String tuid=(String) session.getAttribute("tuid");
		Map<String,Object> map=new HashMap<String, Object>();
		map.put("tuid", tuid);
		map.put("rid", 2);
		userService.addUser(map);
		session.removeAttribute("tuid");
		session.removeAttribute("username");
		return new ModelAndView("login");
		
	}
	//拿到数据库url进入此方法后返回到RYjsp页面
	@RequestMapping(value="/ShowTuser")
	@ResponseBody
	public ModelAndView ShowTuser(){
		
	return new ModelAndView("RY");
		
	}
	
	@RequestMapping(value="/findUser")
	@ResponseBody
	public String findUser(tuser tuser,HttpServletResponse response){
		
		List list=userService.findUser(tuser);
		Map<String,Object> map=new HashMap<String, Object>();
		map.put("code", 0);
		map.put("msg", null);
		map.put("count", 1000);
		map.put("data", list);
		JSONObject jsonObject=JSONObject.fromObject(map);
		System.out.println("user:"+jsonObject);
		return 	jsonObject.toString();
		//直接返回的已经转化字符类型jsonArray，是因为方法上加了@ResponseBody
		//用流输出的是未加@ResponseBody
		
	}
	@RequestMapping(value="/deletetuid")
	@ResponseBody
	public void deletetuid(HttpServletRequest request,HttpServletResponse response)throws Exception{
		
		String tuid=request.getParameter("tuid");
		Map<String,Object> map=new HashMap<String, Object>();
		map.put("tuid", tuid);
		if(userService.deletetuid(map).equals("success")){
			response.getWriter().write("success");
		}else{
			response.getWriter().write("error");
		}
	}
	//通过ID修改角色
	@RequestMapping(value="/updatername")
	@ResponseBody
	public void updatername(HttpServletRequest request,HttpServletResponse response)throws Exception{
		String tuid=request.getParameter("tuid");
		String rid=request.getParameter("rid");
		Map<String,Object> map=new HashMap<String, Object>();
		map.put("tuid", tuid);
		map.put("rid", rid);
		if(userService.updatername(map).equals("success")){
			response.getWriter().write("success");
		}else{
			response.getWriter().write("error");
		}
		

		
		
	}
}
