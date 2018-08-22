package com.hope.ServiceImp;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.hope.Bean.T_Role;
import com.hope.Bean.T_User_Role;
import com.hope.Dao.RoleDao;
import com.hope.Dao.UserRoleDao;
import com.hope.Service.RoleService;
import com.hope.Service.UserRoleService;
@Service("userroleService")
public class UserRoleServiceImp implements UserRoleService{
	@Resource(name="userroleDao")
	private UserRoleDao userroledao;
	@Override
	public List<?> findall() {
		// TODO Auto-generated method stub
		
		return userroledao.findall();
	}
	@Override
	public List listpage(String pageno,String pagesize) {
//		此处用实体还是数据库表格的名字
	String sql="SELECT * FROM ( SELECT ROWNUM AS LIMITNUM,ur.*  FROM T_User_Role ur ) WHERE LIMITNUM BETWEEN ("+pageno+"-1)*"+pagesize+" AND "+pageno+"*"+pagesize;
	return userroledao.listpage(sql);
}
	@Override
	public boolean saveorupdate(T_User_Role ur) {
		// TODO Auto-generated method stub
		return userroledao.saveorupdate(ur);
	}
	@Override
	public void delete(T_User_Role ur) {
		// TODO Auto-generated method stub
		userroledao.delete(ur);
	}
	@Override
	public void add(T_User_Role ur) {
		// TODO Auto-generated method stub
		userroledao.add(ur);
	}

}
