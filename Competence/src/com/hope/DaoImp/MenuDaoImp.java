package com.hope.DaoImp;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;
import com.hope.Bean.T_Menu;
import com.hope.Dao.MenuDao;





@Repository("menuDao")
public class MenuDaoImp implements MenuDao{
	
	private static HibernateTemplate hibernateTemplate;
	@Autowired
	public MenuDaoImp(SessionFactory sessionFactory){
		hibernateTemplate=new HibernateTemplate(sessionFactory);
		
	}
	@Override
	public List findmenu(String sql) {
		// TODO Auto-generated method stub
		Session session=hibernateTemplate.getSessionFactory().openSession();
		Transaction ts=session.beginTransaction();
		List list=null;
				try{
					list=session.createSQLQuery(sql).list();
					ts.commit();
				}catch(Exception e){
					try{
						ts.rollback();
					}catch(Exception e1){
						e1.printStackTrace();
					}
				}finally{
					session.close();
				}
				System.out.println("list:"+list);
					
				return list;
	}
	
	public List listpage(String sql) {
		// TODO Auto-generated method stub
		Session session=hibernateTemplate.getSessionFactory().openSession();
		Query q=session.createSQLQuery(sql).addEntity(T_Menu.class);
		List list=q.list();
		session.close();
		return list;
		
	}
	@Override
	public boolean saveorupdate(T_Menu menu) {
		// TODO Auto-generated method stub
		try {
			hibernateTemplate.saveOrUpdate(menu);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
		return true;
	}
	@Override
	public void delete(T_Menu menu) {
		// TODO Auto-generated method stub
		hibernateTemplate.delete(menu);
	}
	@Override
	public void add(T_Menu menu) {
		// TODO Auto-generated method stub
		hibernateTemplate.save(menu);
	}


	
}
