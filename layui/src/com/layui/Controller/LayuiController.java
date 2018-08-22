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
	//查询layui表显示layui后台布局左侧
	@RequestMapping(value="/findmenu")
	@ResponseBody
	public void findmenu(HttpServletResponse response,HttpServletRequest request)throws Exception{
		HttpSession session=request.getSession();
		String tuid=(String) session.getAttribute("tuid");
		Map<String,Object> map=new HashMap<String, Object>();
		map.put("tuid", tuid);
		List<?> list=layuiService.findmenu(map);
		System.out.println("list:"+list);
		//储存父ID数据池
		List<Map<String,Object>> Flistmap=new ArrayList<Map<String,Object>>();
		
		//储存子ID数据池
		List<Map<String,Object>> sonListMap=new ArrayList<Map<String,Object>>();
		
		//循环拿传来的全部数据分别储存
		for (int i = 0; i < list.size(); i++) {
			Map<String,Object> maptwo=(Map<String,Object>) list.get(i);
			//map拿到的数据判断字段fid如果为空就把map拿到的这条数据放到父储存池中
			if(maptwo.get("FID")==null){
				Flistmap.add(maptwo);
			}else{
				sonListMap.add(maptwo);
			};
		}
		//循环父ID储存池
		for (int i = 0; i < Flistmap.size(); i++) {
			Map<String,Object> Fmap=Flistmap.get(i);
			String Mid=(String)Fmap.get("MID");
			//一次性用来储存即将加到该主菜单的子菜单！此处不太懂后面的操作
			List<Map<String,Object>> sonmap=new ArrayList<Map<String,Object>>();
			//循环子ID储存池菜单
			for (int j = 0; j < sonListMap.size(); j++) {
				Map<String,Object> mapson=sonListMap.get(j);
				//判断拿到的fid对应主ID否，一旦对应将数据放到sonlistmap，放到上面定义的子菜单存储池中
				if(mapson.get("FID").equals(Mid)){
					sonmap.add(mapson);
				}
			}
			//判断上面子存储有没有数据，有数据了将这个储存池的数据放到//循环父ID储存池定义的map中
			if(sonmap!=null){
				Fmap.put("list", sonmap);
			}
		}
		jsonArray=JSONArray.fromObject(Flistmap);
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(jsonArray.toString());
		}

	//显示im最新活动
		@RequestMapping("/findnewIM")
		@ResponseBody
		public ModelAndView findnewIM(im im,HttpServletRequest request)throws Exception{
			
			List list=layuiService.findim(im);
			request.setAttribute("return_list", list);
			return new ModelAndView("newIM","return_list",list);
		}
		

	//删除通告信息
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
	//通过ID拿到ID下的信息
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
	//修改IM信息
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
