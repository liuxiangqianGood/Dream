package com.lxq.Controller;

import java.io.IOException;
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

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lxq.Service.LoginService;

@Controller
public class LoginController {
	@Autowired
	private LoginService loginService;

	//��¼
	@RequestMapping(value="/login")
	@ResponseBody
	public void login(HttpServletRequest request,HttpServletResponse response)throws Exception{
		
		String username=request.getParameter("username");
		String password=request.getParameter("password");
		Map<String,Object> map=new HashMap<String, Object>();
		map.put("username", username);
		map.put("password", password);
		if(loginService.findlogin(map).equals("success")){
			HttpSession session=request.getSession();
			session.setAttribute("username", username);
			response.getWriter().write("success");
		}else{
			response.getWriter().write("error");
		}
	}
	
	//��ҳ���ע�����ע��ҳ��
	@RequestMapping(value="/regist")
	public ModelAndView regist(){
		
		return new ModelAndView("regist");
		
	}
	//ע��һ���û�
	@RequestMapping(value="/adduser")
	public void adduser(HttpServletRequest request,HttpServletResponse response)throws Exception{
		String username=request.getParameter("username");
		String password=request.getParameter("password");
		Map<String,Object> map=new HashMap<String, Object>();
		map.put("username", username);
		map.put("password", password);
		if(loginService.adduser(map).equals("success")){
			response.getWriter().write("success");
		}else{
			response.getWriter().write("error");
		}
	}
	//���ص�¼ҳ��
	@RequestMapping(value="/showlogin")
	public ModelAndView showlogin(){
		return new ModelAndView("index");
		
	}
	
	//��ʾһ�ű����Ϣ
	@RequestMapping(value="/showuser")
	@ResponseBody
	public ModelAndView showuser(HttpServletRequest request){
		List list=loginService.finduser();
		request.setAttribute("showuser", list);
		return new ModelAndView("showuser","showuser",list);
	}
	//ͨ��idɾ��һ������
	@RequestMapping(value="/deleteid")
	@ResponseBody
	public void deleteid(HttpServletRequest request,HttpServletResponse response)throws Exception{
		String id=request.getParameter("id");
		int uid=Integer.parseInt(id);
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("uid", uid);
		if(loginService.deleteid(map).equals("success")){
			response.getWriter().write("success");
		}else{
			response.getWriter().write("error");
		}
	}
	@RequestMapping(value="/findid")
	@ResponseBody
	public ModelAndView findid(HttpServletRequest request){
		String id=request.getParameter("id");
		int uid=Integer.parseInt(id);
		Map<String,Object> map=new HashMap<String, Object>();
		map.put("uid", uid);
		List list=loginService.findid(map);
		request.setAttribute("showid", list);
		return new ModelAndView("showid","showid",list);	
	}
	@RequestMapping(value="/updateid")
	public void updateid(HttpServletRequest request,HttpServletResponse response)throws Exception{
		String id=request.getParameter("uid");
		int uid=Integer.parseInt(id);
		String username=request.getParameter("username");
		String password=request.getParameter("password");
		Map<String,Object> map=new HashMap<String, Object>();
		map.put("uid", uid);
		map.put("username", username);
		map.put("password", password);
		if(loginService.updateid(map).equals("success")){
			response.getWriter().write("success");
		}else{
			response.getWriter().write("error");
		}
		
	}
	//pagehelper��ҳ
/*	@RequestMapping(value = "/pageHelper",produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String pageHelper(HttpServletRequest request,Integer limit,Integer offset,String search) throws IOException {
		
		if(null == limit) {
			limit = 5;
		}
		if(null == offset) {
			offset = 1;
		}
		if(null == search) {
			search = "";
		}
		System.out.println("Springboot:" + "limit:" + limit + ",offset:" + offset + ",search:" + search);
		PageHelper.startPage(offset,limit);
		List<?> list = us.pageHelper(search);
		PageInfo<?> pageList = new PageInfo<>(list);
		Map<String,Object> resultMap = new HashMap<String,Object>();
		//��ҳ
		resultMap.put("startRow", "");
		//βҳ
		resultMap.put("endRow", "");
		//��ҳ��DatagridResult 
		resultMap.put("total", pageList.getTotal());
		resultMap.put("rows", pageList.getList());

		String jsobStr = JSONObject.toJSONString(resultMap);
		System.out.println(jsobStr);
		return jsobStr;
	}*/

}
