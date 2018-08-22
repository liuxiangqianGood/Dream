package com.hope.DaoImp;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.hope.Bean.T_Role;
import com.hope.Dao.RoleDao;



@Repository(value="roleDao")
public class RoleDaoImp implements RoleDao{
	
	private static HibernateTemplate hibernateTemplate;
	@Autowired
	public RoleDaoImp(SessionFactory sessionFactory){
		hibernateTemplate=new HibernateTemplate(sessionFactory);
		
	}
	@Override
	public List<?> findall() {
		// TODO Auto-generated method stub
		return hibernateTemplate.find("from T_Role");
	}
	
	public List listpage(String sql) {
		// TODO Auto-generated method stub
		Session session=hibernateTemplate.getSessionFactory().openSession();
		Query q=session.createSQLQuery(sql).addEntity(T_Role.class);
		List list=q.list();
		session.close();
		return list;
		
	}
	@Override
	public boolean saveorupdate(T_Role role) {
		// TODO Auto-generated method stub
		try {
			hibernateTemplate.saveOrUpdate(role);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
		return true;
	}
	@Override
	public void deleteOrder(Object object) {
		// TODO Auto-generated method stub
		hibernateTemplate.delete(object);
	}

	public Object findOrderid(String rid){
		return hibernateTemplate.find("from T_Role where rid='"+rid+"'").get(0);
	}
}
