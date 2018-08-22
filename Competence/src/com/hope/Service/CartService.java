package com.hope.Service;

import java.util.List;

import net.sf.json.JSONObject;

import com.hope.Bean.cart;

public interface CartService {

	String addcart(cart cart);
	JSONObject findcart(String total,String rows);
	void delete(cart cart);
}
