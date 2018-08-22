package com.hope.Action;

import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.json.JSONObject;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import com.hope.Bean.T_Menu;
import com.hope.Service.MenuService;
import com.opensymphony.xwork2.ActionSupport;
@Controller
@Scope("prototype")
public class menuAction extends ActionSupport{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4L;
	private HttpServletRequest request=ServletActionContext.getRequest();
	private HttpServletResponse response=ServletActionContext.getResponse();
@Autowired
private MenuService menuservice;

public String findall() throws IOException{
	
	return "onclickshowT_Menu";
}
public String findone() throws IOException{
	
	return "success";
}
public void findmenu() throws IOException{
	//不理解并没有调用分页的方法怎么会可以给他分了？写了方法后自动运用？
	
	String page=request.getParameter("page")==null?"":request.getParameter("page");
	String rows=request.getParameter("rows")==null?"":request.getParameter("rows");
	String where=request.getParameter("where")==null?"":request.getParameter("where");
	JSONObject js=menuservice.findmenu(page, rows,where);
	response.setContentType("text/html;charset=UTF-8");
	response.getWriter().write(js.toString());
}

	private T_Menu menu;
	public T_Menu getMenu() {
		return menu;
	}

	public void setMenu(T_Menu menu) {
		this.menu = menu;
	}

	public void saveorupdate() throws IOException{

			String results="error";
			
			if(menu!=null){

				if(menuservice.saveorupdate(menu)){
					results="success";
				}
			}
			response.getWriter().write(results);
	}

	public void deleteorder(){
		System.out.println("跳转到删除？");
		String str[]=request.getParameter("ids").split(",");
		System.out.println("str.length"+str.length);
		for(int i=0;i<str.length;i++){
			
			T_Menu or=new T_Menu();
			or.setMid(i);
			menuservice.delete(or);
		}
	}
//	弹窗增加或修改
	public String addorup(){
	
		String status=request.getParameter("status")==null?"":request.getParameter("status").toString();
		if(status.equals("add")){
			return "addorup";
		}else{
			String rid=request.getParameter("mid")==null?"":request.getParameter("mid").toString();
			if(rid.equals("")){
				return "addorup";
			}else{
				
				return "addorup";
			}
		}
	}
	public String xixi(){
		return "success";
		
	}
	
	public String onclickshowT_Menu(){
		return "onclickshowT_Menu";
		
	}
	
	public void addmid(){
		menuservice.add(menu);
	}
	public void deletemid(){
		
		int mid=Integer.parseInt(request.getParameter("mid"));
		T_Menu menu=new T_Menu();
		menu.setMid(mid);
		menuservice.delete(menu);
	}
	//菜单区
	public String findmeat()throws Exception{
		return "showmeat";
	}
	public String findvege()throws Exception{
		return "showvege";
	}
	public String soup()throws Exception{
		return "showsoup";
	}

	
}
