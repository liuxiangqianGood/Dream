package com.hope.DaoImp;

import java.util.List;




import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.hope.Bean.cart;
import com.hope.Dao.CartDao;
@Repository(value="cartdao")
public class CartDaoImp implements CartDao{

	private static HibernateTemplate hibernateTemplate;
	@Autowired
	public  CartDaoImp(SessionFactory sessionFactory){
		hibernateTemplate=new HibernateTemplate(sessionFactory);
	}
	
	@Override
	public Integer addcart(cart  cart) {
		
		return (Integer) hibernateTemplate.save(cart);
	}

	@Override
	public List<?> findcart(String sql) {
		Session session=hibernateTemplate.getSessionFactory().openSession();
		List list=session.createSQLQuery(sql).list();

		session.close();
		return list;
	}

	@Override
	public void deletecid(Object object) {
		// TODO Auto-generated method stub
		 hibernateTemplate.delete(object);
	}

}
