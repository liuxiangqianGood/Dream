package com.hope.Action;

import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.hope.util.VerifyCodeUtil;
import com.hope.Bean.T_User;
import com.hope.Service.LoginService;
import com.opensymphony.xwork2.ActionSupport;

@Controller
@Scope("prototype")
public class loginAction extends ActionSupport{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	HttpServletRequest request=ServletActionContext.getRequest();
	HttpServletResponse response=ServletActionContext.getResponse();
	
	@Autowired
	private LoginService loginService;
	private T_User user;
	
	
	public T_User getUser() {
		return user;
	}

	public void setUser(T_User user) {
		this.user = user;
	}

	public void login()throws Exception{
		String urlNameString="http://127.0.0.1:8088/Maven/login?username=user.username&password=user.password";
		urlNameString=urlNameString.replace("user.username",user.getUsername());
		urlNameString=urlNameString.replace("user.password", user.getPassword());
		String result="";
		try {
			//���ݵ�ַ��ȡ����	
			HttpGet request  = new HttpGet(urlNameString);
			//��ȡ��ǰ�ͻ��˶���
			HttpClient client=HttpClientBuilder.create().build();
			//���ݻ�õĿͻ��˶����͵�ַ����
			HttpResponse response=client.execute(request);
			//�ж������������Ƿ�����
			if(response.getStatusLine().getStatusCode()==HttpStatus.SC_OK){
				//���ܷ��صĲ���
				result=EntityUtils.toString(response.getEntity(),"UTF-8");
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		HttpSession session=request.getSession();
		session.setAttribute("name", user.getUsername());
		response.setCharacterEncoding("utf-8");
		response.getWriter().write(result);
	}
	
	public void findall() throws Exception{

		String page=request.getParameter("page")==null?"":request.getParameter("page");
		String rows=request.getParameter("rows")==null?"":request.getParameter("rows");

		JSONObject js=loginService.findall(page, rows);
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(js.toString());
	}
	
	public void add()throws Exception{
		//�ӿڵ�ַ
	String urlNameString="http://127.0.0.1:8088/Maven/regist?username=user.username&password=user.password";
	urlNameString=urlNameString.replace("user.username", user.getUsername());
	urlNameString=urlNameString.replace("user.password", user.getPassword());
	String result="";
	try {
		//���ݵ�ַ�������
		HttpGet request=new HttpGet(urlNameString);
		//��õ�ǰ�ͻ��˶���
		HttpClient client=HttpClientBuilder.create().build();
		//ͨ����õ�ǰ�ͻ��˶��������������Ӧ
		HttpResponse response=client.execute(request);
		if(response.getStatusLine().getStatusCode()==HttpStatus.SC_OK){
			result=EntityUtils.toString(response.getEntity(),"UTF-8");
		}
	} catch (Exception e) {
		// TODO: handle exception
		e.printStackTrace();
	}
	response.setCharacterEncoding("utf-8");
	response.getWriter().write(result);
	}
	public String regist()throws Exception{
		return "regist";
	}
	public void t_user()throws Exception{
		System.out.println("����T_user");
		String urlNameString="http://127.0.0.1:8088/Maven/findUser";
		String result="";
		try {
			HttpGet request=new HttpGet(urlNameString);
			HttpClient client=HttpClientBuilder.create().build();
			HttpResponse response=client.execute(request);
			if(response.getStatusLine().getStatusCode()==HttpStatus.SC_OK){
				result=EntityUtils.toString(response.getEntity(),"UTF-8");
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		JSONArray js=JSONArray.fromObject(result);
		Map map=new HashMap();
		map.put("total", js.size());
		map.put("rows", js);
		JSONObject jss=JSONObject.fromObject(map);
		response.setCharacterEncoding("utf-8");
		response.getWriter().write(jss.toString());
	}
	
	public String showuse(){
		return "onclickshowUser";
		
	}
	private String id;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String deleteuserid(){
		String id=request.getParameter("id") ;
		T_User user=new T_User();
		user.setId(id);

		loginService.deleteuser(user);
		return "ok";
	}
	public void update(){
		
		loginService.update(user);
	}
	
	public String updateid(){
		String id=request.getParameter("id");
		
		user=(T_User)loginService.findid(id);
		System.out.println("id:"+user.getId());
		return "updateid";
		
	}
	public String findone()throws Exception{
		return "onclickshowUser";
		
	}
	public String shoumain()throws Exception{

		return "success";
		
	}
	public void userRoleZtree()throws Exception{
		List<Map>listm=new ArrayList<Map>();
		HttpSession session=request.getSession();
		
	//11111111111111111111111111111111
		List<Object[]> list=loginService.querymenu();
		for(Object[] obj:list){
			Map map=new HashMap();
//			�˴���ȷ�������Ե���
			map.put("id", obj[0]);
			map.put("name", obj[1]);
			map.put("fid", obj[2]);
			map.put("urlaction", obj[3]);
			listm.add(map);
		}
	JSONArray jsonArray=JSONArray.fromObject(listm);
	response.setCharacterEncoding("UTF-8");
	response.getWriter().write(jsonArray.toString());
}
	//��֤��
	public void verify() throws Exception {
		//��ȡHttpSession ͨ��request��ȡ��

		HttpSession session =request.getSession();
		//����һ��6λ������ַ���
		String VerifyCode=VerifyCodeUtil.getNumber(6);
		OutputStream output=response.getOutputStream();
		//VerifyCodeUtil.create(����ַ�,��,��,��ʽ,�����);
		String verifystr= VerifyCodeUtil.create(VerifyCode, 80, 50, "JPG",output);
		output.close();
		//����֤�� ����session
		session.setAttribute("verify", verifystr);
		System.out.println("��֤��:"+verifystr);
	}
	public void userZtree()throws Exception{
		List<Map>listm=new ArrayList<Map>();
	//11111111111111111111111111111111
		List<Object[]> list=loginService.queryuser();

		for(Object[] obj:list){
			Map map=new HashMap();
//			�˴���ȷ�������Ե���
			map.put("id", obj[0]);
			map.put("name", obj[1]);
			map.put("urlaction", obj[3]);
			map.put("fid", obj[2]);
			listm.add(map);
		}
		JSONArray jsonArray=JSONArray.fromObject(listm);
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(jsonArray.toString());
	}
	//�˳���ǰ��¼AJAX
	public void Signout()throws Exception{
		HttpSession session=request.getSession();
		session.removeAttribute("name");
		response.getWriter().write("success");
		
	}
	//�����¼ҳ��
	public String showlogin(){
		
		return "showlogin";
	}
	
	
}
