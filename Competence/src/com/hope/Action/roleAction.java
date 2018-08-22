package com.hope.Action;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.json.JSONObject;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import com.hope.Bean.T_Role;
import com.hope.Service.RoleService;
import com.opensymphony.xwork2.ActionSupport;

@Controller
@Scope("prototype")
public class roleAction extends ActionSupport{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2L;
	private HttpServletRequest request=ServletActionContext.getRequest();
	private HttpServletResponse response=ServletActionContext.getResponse();
@Autowired
private RoleService roleservice;

public void findall() throws IOException{
	int total=roleservice.findall().size();
	String page=request.getParameter("page")==null?"1":request.getParameter("page");
	String rows=request.getParameter("rows")==null?"1":request.getParameter("rows");
	List<T_Role> list=roleservice.listpage(page,rows);
	Map map=new HashMap();
	map.put("total", total);
	map.put("rows", list);
	response.setContentType("text/html;charset=UTF-8");
	JSONObject jsonArray= JSONObject.fromObject(map);
	System.out.println("JSONARRAY:"+jsonArray.toString());	
	//输出流返回的数据
	response.getWriter().write(jsonArray.toString());
}

	private T_Role role;

	public T_Role getOrder() {
		return role;
	}

	public void setOrder(T_Role role) {
		this.role = role;
	}
	
	public void saveorupdate() throws IOException{

			String results="error";
			
			if(role!=null){
				System.out.println("进来没");
//				HttpSession session=request.getSession();
//				order.setUser((User)session.getAttribute("user"));
				if(roleservice.saveorupdate(role)){
					results="success";
				}
			}
			response.getWriter().write(results);
	}

	public void deleteorder(){
		System.out.println("跳转到删除？");
		String str[]=request.getParameter("idss").split(",");
		System.out.println("str.length"+str.length);
		for(int i=0;i<str.length;i++){
			
			T_Role or=new T_Role();
			or.setRid(i);
			roleservice.delete(or);
		}
	}
//	弹窗增加或修改
	public String addorup(){
		System.out.println("asdasdsad");
		String status=request.getParameter("status")==null?"":request.getParameter("status").toString();
		if(status.equals("add")){
			return "addorup";
		}else{
			String rid=request.getParameter("rid ")==null?"":request.getParameter("rid").toString();
			if(rid.equals("")){
				return "addorup";
			}else{
				role=(T_Role)roleservice.findOrderid(rid);
				return "addorup";
			}
		}
	}
	
	public String onclickshowrole(){
		return "onclickshowrole";
		
	}
	
}
