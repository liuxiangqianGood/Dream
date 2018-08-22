package com.hope.Dao;

import java.util.List;

import com.hope.Bean.cart;

public interface CartDao {
	//购物车方法
	Integer addcart(cart cart);
	List<?> findcart(String sql);
	void deletecid(Object object);
}
