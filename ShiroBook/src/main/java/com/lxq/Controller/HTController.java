package com.lxq.Controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.lxq.Log.SystemControllerLog;
import com.lxq.Service.HtService;

@Controller
public class HTController {
	
	@Autowired
	private HtService htService;
	
	private List jsonArray;
	public List getJsonArray() {
		return jsonArray;
	}
	public void setJsonArray(List jsonArray) {
		this.jsonArray = jsonArray;
	}
	//��ʾҳ��
	@RequestMapping(value="/showHT")
	@ResponseBody
	@SystemControllerLog(description="��ת��̨")
	public String showHT(){
		
		return "��ת��....";	
	} 
	//��̨����
	@RequestMapping(value="/HTsee")
	@ResponseBody
	@SystemControllerLog(description="��ʾ��̨")
	public ModelAndView HTsee(){
		
		return new ModelAndView("HTBoot");
	}
	//�����û�ID��ѯ�˵���������Ϣ
	@RequestMapping(value="/findmenus")
	@ResponseBody
	@SystemControllerLog(description="��ѯ�˵�������")
	public void findmenus(HttpServletResponse response)throws Exception{
		Subject subject=SecurityUtils.getSubject();
		Session session=subject.getSession();
		String userid=(String) session.getAttribute("userid");
		Map<String,Object> map=new HashMap<String, Object>();
		map.put("userid", userid);
		List<?> list=htService.findmenus(map);
		
	}
}
