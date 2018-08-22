package com.lxq.realm;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

import com.lxq.Service.LoginService;

public class Myrealm extends AuthorizingRealm{
	@Autowired
	private LoginService loginService;
//角色处理区
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(
			PrincipalCollection principals) {
		// TODO Auto-generated method stub
		Subject subject=SecurityUtils.getSubject();
		String rname=(String) subject.getSession().getAttribute("rname");
		SimpleAuthorizationInfo info=new SimpleAuthorizationInfo();
		if(rname.equals("管理员")){	
			info.addRole("admin");
			info.addRole("qiantai");
			info.addRole("chushi");
			System.out.println("All已赋予");
		}else if (rname.equals("前台人员")) {
			info.addRole("qiantai");
			System.out.println("qiantai已赋予");
		}else if (rname.equals("厨师")) {
			info.addRole("chushi");
			System.out.println("chushi已赋予");
		}
		return info;
	}
//账户密码处理区
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken token) throws AuthenticationException {
		// TODO Auto-generated method stub
		UsernamePasswordToken token2=(UsernamePasswordToken) token;
		String username=token2.getUsername();
		char[] userpsw1=token2.getPassword();
		String userpsw=new String(userpsw1);

		Map<String,Object> map=new HashMap<String, Object>();
		map.put("username", username);
		map.put("userpsw", userpsw);
		List list=loginService.findlogin(map);
	
		
		if(list.size()==0){
		
			throw new UnknownAccountException();
	
		}else{
			JSONArray jsonArray=JSONArray.fromObject(list);
			JSONObject jsonObject=(JSONObject) jsonArray.get(0);
			SimpleAuthenticationInfo authenticationInfo=new SimpleAuthenticationInfo(
					jsonObject.getString("username"),
					jsonObject.getString("userpsw"),
					this.getName()
					);
			return authenticationInfo;
		}
	
	}

}
