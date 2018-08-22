package com.hope.ServiceImp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;


import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
























import com.hope.Bean.T_User;
import com.hope.Dao.LoginDao;
import com.hope.Service.LoginService;
import com.opensymphony.xwork2.ActionContext;

@Service("loginService")
public class LoginServiceImpl implements LoginService{
	
	
	
	@Resource(name="loginDao")
	private LoginDao loginDao;
	

	@Override
	public String Login(T_User user) {
		// TODO Auto-generated method stub
		if(loginDao.login(user)>0){
			return "success";
		}
		return "error";
	}



	@Override
	public JSONObject findall(String total,String rows) {
		// TODO Auto-generated method stub
		int totals=Integer.parseInt(total);
		int rowss=Integer.parseInt(rows);
		String sql1="select t_user.*,t_role.rname from t_user "
				+ "join t_user_role on t_user.id=t_user_role.id "
				+ "join t_role on t_role.rid=t_user_role.rid";
		List<Object[]> listobject=(List<Object[]>) loginDao.findall(sql1);
		List<Map> list=new ArrayList<Map>();
		for(int i=0;i<listobject.size();i++){
			Map map=new HashMap();
			map.put("id", listobject.get(i)[0]);
			map.put("username", listobject.get(i)[1]);
			map.put("password", listobject.get(i)[2]);
			map.put("rname", listobject.get(i)[3]);
			list.add(map);
		}
		Map map2=new HashMap();
		map2.put("total", listobject.size());
		map2.put("rows", list);
		JSONObject js=JSONObject.fromObject(map2);
		return js;
	}

	@Override
	public List listpage(String pageno,String pagesize) {

		String sql="SELECT * FROM ( SELECT ROWNUM AS LIMITNUM,t.*  FROM T_User t ) WHERE LIMITNUM BETWEEN ("+pageno+"-1)*"+pagesize+" AND "+pageno+"*"+pagesize;
		return loginDao.listpage(sql);
	}



	@Override
	public void add(T_User user) {
		// TODO Auto-generated method stub
		loginDao.add(user);
	}



	@Override
	public void deleteuser(T_User user) {
		// TODO Auto-generated method stub
		loginDao.deleteuser(user);
	}



	@Override
	public void update(T_User user) {
		// TODO Auto-generated method stub
		loginDao.update(user);
	}



	@Override
	public T_User findid(String id) {
		// TODO Auto-generated method stub
		
		return loginDao.findid(id);
		
	}

	@Override
	public List querymenu() {
		// TODO Auto-generated method stub
	
		String sql="select * from t_menu";
		System.out.println(sql);
		return loginDao.createSql(sql);
	}
	@Override
	public List queryuser() {
		// TODO Auto-generated method stub
	
		String sql="select * from t_user";
		System.out.println(sql);
		return loginDao.createSql(sql);
	}

//	public boolean fistrue(int fid){
//		
//		String sql="select count(*) from t_menu where mid='"+fid+"'";
//		String count=loginDao.createSql(sql).get(0).toString();
//		if(count.equals("0")){
//			return false;
//		}else{
//			return true;
//		}	
	//}

}
