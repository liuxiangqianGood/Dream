package com.lxq.ServiceImp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;











import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;










import com.alibaba.fastjson.JSON;
import com.lxq.Dao.LoginDao;
import com.lxq.Entity.aop;
import com.lxq.Log.SystemServiceLog;
import com.lxq.Service.LoginService;
@Service("loginService")
public class LoginServiceImp implements LoginService{
	@Autowired
	private LoginDao loginDao;
	
	//每次启动这个项目必须开启Redis服务登录密码才能运行此项目不然不能登录
	@Autowired
	private JedisPool jedis;
	//JedisPool线程安全的网络连接池
	@SystemServiceLog(description="拿到查询登录返回数据")
	@Override
	public List findlogin(Map<String, Object> map) {
	
		// TODO Auto-generated method 

		//jedispool获取方法
		Jedis je = jedis.getResource();
		//拿到从前台在map里放传来的数据
		String mapusername=(String) map.get("username");
		String mapuserpsw=(String) map.get("userpsw");
		//获取shiro工具
		Subject sub=SecurityUtils.getSubject();
		//拿到当前sessionid
		String sessionid=(String) sub.getSession().getId();
		System.out.println("获取当前sessionid:"+sessionid);
		
		//exists存在
		String value=je.get(mapusername);//Redis里的key做判断
		je.close();
	//判断用户是否已登录如果以登录那么在登录这个用户名就不可以登录了
	 if(value!=null){
		 	//返回空数据进realm判断禁止登录
			System.out.println("用户:["+mapusername+"]已登录！禁止强制登录");
			return null;

		}else{
			
		List list=loginDao.findlogin(map);

		JSONArray jsonArray=JSONArray.fromObject(list);
		JSONObject jsonObject=(JSONObject) jsonArray.get(0);
		Subject subject=SecurityUtils.getSubject();
		String username= jsonObject.getString("username");
		String userpsw=jsonObject.getString("userpsw");
		String userid=jsonObject.getString("userid");
		subject.getSession().setAttribute("username",username);
		subject.getSession().setAttribute("userpsw", userpsw);
		subject.getSession().setAttribute("userid", userid);
		
		//放入Redis
		je.set(username,sessionid);
		je.set(userpsw,sessionid);
		//设置key的存活时间
		je.expire(username, 180);
		je.expire(userpsw, 180);
		//需要关闭不然内存泄漏


		System.out.println("-----set Redis ok------");
		return list;
		}

		}
	
	
	@SystemServiceLog(description="登录用户角色")
	@Override
	public List findrname(Map<String, Object> map) {
		// TODO Auto-generated method stub
		
		List list=loginDao.findrname(map);
		return list;
	}
	//日志增加
	@SystemServiceLog(description="增加日志")
	@Override
	public void addaop(aop aop) {
		// TODO Auto-generated method stub
		loginDao.addaop(aop);
	}

}
