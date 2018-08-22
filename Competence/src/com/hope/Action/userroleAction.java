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
import com.hope.Bean.T_User_Role;
import com.hope.Service.UserRoleService;
import com.opensymphony.xwork2.ActionSupport;
@Controller
@Scope("prototype")
public class userroleAction extends ActionSupport{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3L;
	private HttpServletRequest request=ServletActionContext.getRequest();
	private HttpServletResponse response=ServletActionContext.getResponse();
	
@Autowired
private UserRoleService userroleservice;


public void findall() throws IOException{
	int total=userroleservice.findall().size();
	String page=request.getParameter("page")==null?"1":request.getParameter("page");
	String rows=request.getParameter("rows")==null?"1":request.getParameter("rows");
	List<T_User_Role> list=userroleservice.listpage(page,rows);
	Map map=new HashMap();
	map.put("total", total);
	map.put("rows", list);
	response.setContentType("text/html;charset=UTF-8");
	JSONObject jsonArray= JSONObject.fromObject(map);
	System.out.println("JSONARRAY:"+jsonArray.toString());	
	//输出流返回的数据
	response.getWriter().write(jsonArray.toString());
}

	private T_User_Role ur;



	public T_User_Role getUr() {
		return ur;
	}

	public void setUr(T_User_Role ur) {
		this.ur = ur;
	}

	public void saveorupdate() throws IOException{

			String results="error";
			
			if(ur!=null){
				System.out.println("进来没");
//				HttpSession session=request.getSession();
//				order.setUser((User)session.getAttribute("user"));
				if(userroleservice.saveorupdate(ur)){
					results="success";
				}
			}
			response.getWriter().write(results);
	}
private int urid;

	public int getUrid() {
	return urid;
}

public void setUrid(int urid) {
	this.urid = urid;
}

	public void deleteur(){
		int urid=Integer.parseInt(request.getParameter("urid"));
		T_User_Role ur=new T_User_Role();
		ur.setUrid(urid);
		userroleservice.delete(ur);
		
	}
//	弹窗增加或修改
	public String addorup(){
		System.out.println("asdasdsad");
		String status=request.getParameter("status")==null?"":request.getParameter("status").toString();
		if(status.equals("add")){
			return "addurid";
		}else{
			String rid=request.getParameter("urid ")==null?"":request.getParameter("urid").toString();
			if(rid.equals("")){
				return "add";
			}else{
		
				return "addorup";
			}
		}
	}
	
	public String onclickshowT_User_Role(){
		return "onclickshowT_User_Role";
		
	}
	private Object object;
	
	public Object getObject() {
		return object;
	}

	public void setObject(Object object) {
		this.object = object;
	}

	public String add(){
		userroleservice.add(ur);
		return "success";
	}
	
}
