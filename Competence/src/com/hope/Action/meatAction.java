package com.hope.Action;


import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;


import com.hope.Bean.meat;
@Controller
@Scope("prototype")
public class meatAction {

	HttpServletResponse response=ServletActionContext.getResponse();
	HttpServletRequest request=ServletActionContext.getRequest();
	private meat meat;

	public meat getMeat() {
		return meat;
	}
	public void setMeat(meat meat) {
		this.meat = meat;
	}

	private List jsonArray;
	public List getJsonArray() {
		return jsonArray;
	}
	public void setJsonArray(List jsonArray) {
		this.jsonArray = jsonArray;
	}
	//调用接口查询菜单肉类表
	public String findmeat()throws Exception{
		System.out.println("..........进入findmeat方法？？........");
		String	urlNameString="http://127.0.0.1:8088/Maven/findmeat";
		String result="";
		try {
			HttpGet get=new HttpGet(urlNameString);
			HttpClient client=HttpClientBuilder.create().build();
			HttpResponse response=client.execute(get);
			if(response.getStatusLine().getStatusCode()==HttpStatus.SC_OK){
				result=EntityUtils.toString(response.getEntity(),"UTF-8");
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		jsonArray=JSONArray.fromObject(result);
		System.out.println(result);
		response.setContentType("text/html;charset=UTF-8");

		return "showmeat";
	}


	//删除菜单菜品信息
	public void deletemeat()throws Exception{
		System.out.println("...........");
		String id=request.getParameter("id");
		String urlNameString="http://127.0.0.1:8088/Maven/deletemeat?id=meat.id";
		urlNameString=urlNameString.replace("meat.id",id);
		String result="";
		try {
			HttpGet get=new HttpGet(urlNameString);
			HttpClient client=HttpClientBuilder.create().build();
			HttpResponse response=client.execute(get);
			if(response.getStatusLine().getStatusCode()==HttpStatus.SC_OK){
				result=EntityUtils.toString(response.getEntity(),"UTF-8");
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		response.getWriter().write(result);

	}
	//查询到一个ID显示其数据
	public String findid()throws Exception{
		System.out.println(".....进入findid方法......");
		String id=request.getParameter("id");
		String urlNameString="http://127.0.0.1:8088/Maven/findmid?id=meat.id";
		urlNameString=urlNameString.replace("meat.id",id);
		String result="";
		try {
			HttpGet get=new HttpGet(urlNameString);
			HttpClient client=HttpClientBuilder.create().build();
			HttpResponse response=client.execute(get);
			if(response.getStatusLine().getStatusCode()==HttpStatus.SC_OK){
				result=EntityUtils.toString(response.getEntity(),"UTF-8");
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		jsonArray=JSONArray.fromObject(result);
		return "showid";
	}
	//菜单表添加到购物车
	public void addmeat()throws Exception{
		String id=request.getParameter("id");
		System.out.println("id:"+id);
		String name=request.getParameter("name");
		String flavor=request.getParameter("flavor");
		String cnumber=request.getParameter("cnumber");
		String urlNameString="http://127.0.0.1:8088/Maven/addcart?id=meat.id&name=meat.name&flavor=meat.flavor&cnumber=meat.cnumber";
		urlNameString=urlNameString.replace("meat.id", id);
		urlNameString=urlNameString.replace("meat.name", name);
		urlNameString=urlNameString.replace("meat.flavor", flavor);
		urlNameString=urlNameString.replace("meat.cnumber", cnumber);
		String result="";
		try {
			HttpGet get=new HttpGet(urlNameString);
			HttpClient client=HttpClientBuilder.create().build();
			HttpResponse response=client.execute(get);
			if(response.getStatusLine().getStatusCode()==HttpStatus.SC_OK){
				result=EntityUtils.toString(response.getEntity(),"UTF-8");
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		response.getWriter().write(result);
	}
	//增加菜品
	public String savemeat(){
		return "savemeat";
	}
	//菜单表增加
	public void enter()throws Exception{
		String id=request.getParameter("id");
		String name=request.getParameter("name");
		String flavor=request.getParameter("flavor");
		String urlNameString="http://127.0.0.1:8088/Maven/addmeat?id=meat.id&name=meat.name&flavor=meat.flavor";
		urlNameString=urlNameString.replace("meat.id", id);
		urlNameString=urlNameString.replace("meat.name", name);
		urlNameString=urlNameString.replace("meat.flavor", flavor);
		String result="";

		try {
			HttpGet get=new HttpGet(urlNameString);
			HttpClient client=HttpClientBuilder.create().build();
			HttpResponse response=client.execute(get);
			if(response.getStatusLine().getStatusCode()==HttpStatus.SC_OK){
				result=EntityUtils.toString(response.getEntity(),"UTF-8");
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		response.getWriter().write(result);	
	}
	//模糊查询
	public String selectname()throws Exception{

		String name=request.getParameter("name");
		String urlNameString="http://127.0.0.1:8088/Maven/selectname?name="+name;
		String result="";
		try {
			HttpGet get=new HttpGet(urlNameString);
			HttpClient client=HttpClientBuilder.create().build();
			HttpResponse response=client.execute(get);
			if(response.getStatusLine().getStatusCode()==HttpStatus.SC_OK){
				result=EntityUtils.toString(response.getEntity(),"UTF-8");
				//System.out.println(result);

				//System.out.println(jsonArray.toString());
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		jsonArray=JSONArray.fromObject(result);
		response.setContentType("text/html;charset=UTF-8");
		return "showmeat";

	}


}
