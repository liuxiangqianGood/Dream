package com.hope.DaoImp;

import java.util.List;

import javax.transaction.Transaction;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;







import com.hope.Bean.T_User;
import com.hope.Dao.LoginDao;
@Repository(value="loginDao")
public class LoginDaoImpl  implements LoginDao{

	private static HibernateTemplate hibernateTemplate;
	@Autowired
	public  LoginDaoImpl(SessionFactory sessionFactory){
		hibernateTemplate=new HibernateTemplate(sessionFactory);
	}


	@Override
	public int login(T_User user) {
		// TODO Auto-generated method stub
		
		return hibernateTemplate.findByExample(user).size();
		
	}


	@Override
	public List<?> findall(String sql) {
		// TODO Auto-generated method stub
		Session session=hibernateTemplate.getSessionFactory().openSession();
		List list=session.createSQLQuery(sql).list();
		session.close();
		return list;
	}


	//此处分页还有用？
	public List listpage(String sql) {
		// TODO Auto-generated method stub
		Session session=hibernateTemplate.getSessionFactory().openSession();
		Query q=session.createSQLQuery(sql).addEntity(T_User.class);
		List list=q.list();
		session.close();
		return list;
		
	}


	@Override
	public void add(T_User user) {
		// TODO Auto-generated method stub
		hibernateTemplate.save(user);
	}


	@Override
	public void deleteuser(T_User user) {
		// TODO Auto-generated method stub
		hibernateTemplate.delete(user);
	}


	@Override
	public void update(T_User user) {
		// TODO Auto-generated method stub
		hibernateTemplate.update(user);
	}

	@Override
	public T_User findid(String id) {
		// TODO Auto-generated method stub
		return (T_User) hibernateTemplate.get(T_User.class, id);
	}
	
	

	@Override
	public List createSql(String sql) {
		Session session=hibernateTemplate.getSessionFactory().openSession();
		Query q=session.createSQLQuery(sql);
		//.addEntity("t_menu.class")?
		List list=q.list();
		session.close();
		return list;
	}



	

}
