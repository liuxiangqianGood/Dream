package com.hope.ServiceImp;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;









import com.hope.Bean.T_Role;
import com.hope.Dao.RoleDao;
import com.hope.Service.RoleService;
@Service("roleService")
public class RoleServiceImp implements RoleService{
	@Resource(name="roleDao")
	private RoleDao roledao;
	@Override
	public List<?> findall() {
		// TODO Auto-generated method stub
		
		return roledao.findall();
	}
	@Override
	public List listpage(String pageno,String pagesize) {
//		此处用实体还是数据库表格的名字
	String sql="SELECT * FROM ( SELECT ROWNUM AS LIMITNUM,r.*  FROM T_Role r ) WHERE LIMITNUM BETWEEN ("+pageno+"-1)*"+pagesize+" AND "+pageno+"*"+pagesize;
	return roledao.listpage(sql);
}
	@Override
	public boolean saveorupdate(T_Role role) {
		// TODO Auto-generated method stub
		return roledao.saveorupdate(role);
	}
	@Override
	public void delete(Object object) {
		// TODO Auto-generated method stub
		roledao.deleteOrder(object);
	}
	@Override
	public Object findOrderid(String orderid) {
		
		// TODO Auto-generated method stub
		return roledao.findOrderid(orderid);
	}
}
