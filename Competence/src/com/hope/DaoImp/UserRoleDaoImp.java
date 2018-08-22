package com.hope.DaoImp;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;




import com.hope.Bean.T_User_Role;
import com.hope.Dao.UserRoleDao;




@Repository(value="userroleDao")
public class UserRoleDaoImp implements UserRoleDao{
	
	private static HibernateTemplate hibernateTemplate;
	@Autowired
	public UserRoleDaoImp(SessionFactory sessionFactory){
		hibernateTemplate=new HibernateTemplate(sessionFactory);
		
	}
	@Override
	public List<?> findall() {
		// TODO Auto-generated method stub
		return hibernateTemplate.find("from T_User_Role");
	}
	
	public List listpage(String sql) {
		// TODO Auto-generated method stub
		Session session=hibernateTemplate.getSessionFactory().openSession();
		Query q=session.createSQLQuery(sql).addEntity(T_User_Role.class);
		List list=q.list();
		session.close();
		return list;
		
	}
	@Override
	public boolean saveorupdate(T_User_Role ur) {
		// TODO Auto-generated method stub
		try {
			hibernateTemplate.saveOrUpdate(ur);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
		return true;
	}
	@Override
	public void delete(T_User_Role ur) {
		// TODO Auto-generated method stub
		hibernateTemplate.delete(ur);
	}
	@Override
	public void add(T_User_Role ur) {
		// TODO Auto-generated method stub
		hibernateTemplate.save(ur);
	}

	
}
