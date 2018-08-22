package com.layui.Controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.layui.Entity.im;
import com.layui.Entity.menu;
import com.layui.Service.LayuiService;

@Controller
public class LayuiController {
	@Autowired
	private LayuiService layuiService;
	private List jsonArray;
	public List getJsonArray() {
		return jsonArray;
	}
	public void setJsonArray(List jsonArray) {
		this.jsonArray = jsonArray;
	}
	//��ѯlayui����ʾlayui��̨�������
	@RequestMapping(value="/findmenu")
	@ResponseBody
	public void findmenu(HttpServletResponse response,HttpServletRequest request)throws Exception{
		HttpSession session=request.getSession();
		String tuid=(String) session.getAttribute("tuid");
		Map<String,Object> map=new HashMap<String, Object>();
		map.put("tuid", tuid);
		List<?> list=layuiService.findmenu(map);
		System.out.println("list:"+list);
		//���游ID���ݳ�
		List<Map<String,Object>> Flistmap=new ArrayList<Map<String,Object>>();
		
		//������ID���ݳ�
		List<Map<String,Object>> sonListMap=new ArrayList<Map<String,Object>>();
		
		//ѭ���ô�����ȫ�����ݷֱ𴢴�
		for (int i = 0; i < list.size(); i++) {
			Map<String,Object> maptwo=(Map<String,Object>) list.get(i);
			//map�õ��������ж��ֶ�fid���Ϊ�վͰ�map�õ����������ݷŵ����������
			if(maptwo.get("FID")==null){
				Flistmap.add(maptwo);
			}else{
				sonListMap.add(maptwo);
			};
		}
		//ѭ����ID�����
		for (int i = 0; i < Flistmap.size(); i++) {
			Map<String,Object> Fmap=Flistmap.get(i);
			String Mid=(String)Fmap.get("MID");
			//һ�����������漴���ӵ������˵����Ӳ˵����˴���̫������Ĳ���
			List<Map<String,Object>> sonmap=new ArrayList<Map<String,Object>>();
			//ѭ����ID����ز˵�
			for (int j = 0; j < sonListMap.size(); j++) {
				Map<String,Object> mapson=sonListMap.get(j);
				//�ж��õ���fid��Ӧ��ID��һ����Ӧ�����ݷŵ�sonlistmap���ŵ����涨����Ӳ˵��洢����
				if(mapson.get("FID").equals(Mid)){
					sonmap.add(mapson);
				}
			}
			//�ж������Ӵ洢��û�����ݣ��������˽��������ص����ݷŵ�//ѭ����ID����ض����map��
			if(sonmap!=null){
				Fmap.put("list", sonmap);
			}
		}
		jsonArray=JSONArray.fromObject(Flistmap);
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(jsonArray.toString());
		}

	//��ʾim���»
		@RequestMapping("/findnewIM")
		@ResponseBody
		public ModelAndView findnewIM(im im,HttpServletRequest request)throws Exception{
			
			List list=layuiService.findim(im);
			request.setAttribute("return_list", list);
			return new ModelAndView("newIM","return_list",list);
		}
		

	//ɾ��ͨ����Ϣ
	@RequestMapping(value="/delIM")
	@ResponseBody
	public void delIM(HttpServletRequest request,HttpServletResponse response)throws Exception{
		String id=request.getParameter("id");
		Map<String,Object> map=new HashMap<String, Object>();
		map.put("id", id);
		if(layuiService.delIM(map).equals("success")){
			response.getWriter().write("success");
		}else{
			response.getWriter().write("error");
		}
	}
	private im im;	
	public im getIm() {
		return im;
	}
	public void setIm(im im) {
		this.im = im;
	}
	//ͨ��ID�õ�ID�µ���Ϣ
	@RequestMapping(value="/findid")
	@ResponseBody
	public ModelAndView findid(HttpServletRequest request)throws Exception{
		String id=request.getParameter("id");
		Map<String,Object> map=new HashMap<String, Object>();
		map.put("id", id);
		List list=layuiService.findid(map);
		
		request.setAttribute("showlist", list);
		return new ModelAndView("updateid","showlist",list);
		
	}
	//�޸�IM��Ϣ
	@RequestMapping(value="/updateid")
	@ResponseBody
	public void updateid(HttpServletRequest request,HttpServletResponse response)throws Exception{
		String id=request.getParameter("id");
		String name=request.getParameter("name");
		String body=request.getParameter("body");
		Map<String,Object> map=new HashMap<String, Object>();
		map.put("id", id);
		map.put("name", name);
		map.put("body", body);
		if(layuiService.updateid(map).equals("success")){
			response.getWriter().write("success");
		}else{
			response.getWriter().write("error");
		}
	}
	
	
}
