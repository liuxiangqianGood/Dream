package com.hope.Action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.hope.Bean.cart;
import com.hope.Service.CartService;
import com.opensymphony.xwork2.ActionSupport;
@Controller
@Scope("prototype")
public class cartAction extends ActionSupport{
	
	private static final long serialVersionUID = 5L;
	
	HttpServletRequest request=ServletActionContext.getRequest();
	HttpServletResponse response=ServletActionContext.getResponse();
	@Autowired
	private CartService cartservice;
	//购物车添加
	public void addCart(cart cart)throws Exception{
		System.out.println("..........进入购物车方法.......");
		String name=request.getParameter("name");
		String Flavor=request.getParameter("Flavor");
//		Map<String,Object> map=new HashMap<String, Object>();
//		map.put("name", name);
//		map.put("Flavor", Flavor);
		if(cartservice.addcart(cart).equals("success")){
			response.getWriter().write("success");
		}else{
			response.getWriter().write("error");
		}
	}
	
	public void findcart()throws Exception{
		//rows排  total全部的  page页
		System.out.println("..........findcart方法..........");
	
		String page=request.getParameter("page")==null?"":request.getParameter("page");
		String rows=request.getParameter("rows")==null?"":request.getParameter("rows");
		JSONObject js=cartservice.findcart(page,rows);
		response.setContentType("text/html;charset=UTF-8");
		response.getWriter().write(js.toString());

	}
	public void deletecid()throws Exception{
		String id=request.getParameter("id");
		cart cart=new cart();
		cart.setId(id);
		cartservice.delete(cart);

	}

}
