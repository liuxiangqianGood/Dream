package com.hope.ServiceImp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;










import net.sf.json.JSONObject;

import org.springframework.stereotype.Service;

import com.hope.Bean.cart;
import com.hope.Dao.CartDao;
import com.hope.Service.CartService;
@Service("cartservice")
public class CartServiceImp implements CartService{
	@Resource(name="cartdao")
	private CartDao cartdao;
	@Override
	public String addcart(cart cart) {
		// TODO Auto-generated method stub
		Integer i=cartdao.addcart(cart);
		if(i>1){
			return "success";
		}else{
			return "error";
		}
	}
	@Override
	public JSONObject findcart(String total,String rows) {
		int totals=Integer.parseInt(total);
		int rowss=Integer.parseInt(rows);
		String sql="select * from cart";
		List<Object[]> listobject=(List<Object[]>) cartdao.findcart(sql);
		List<Map> list=new ArrayList<Map>();
		for (int i = 0; i < listobject.size(); i++) {
			Map map=new HashMap();
			//循环拿listobject中的数据，listobject.get(i)是循环语句从0开始的先拿第一条listobject.get(i)[0]是第一条数据里第一个值
			map.put("id", listobject.get(i)[0]);
			map.put("name", listobject.get(i)[1]);
			map.put("Flavor",listobject.get(i)[2]);
			map.put("cnumber", listobject.get(i)[3]);
			list.add(map);
		}
		Map map=new HashMap();
		map.put("total",  listobject.size());
		map.put("rows", list);
		JSONObject js=JSONObject.fromObject(map);
		
		return js;
	
	}
	@Override
	public void delete(cart cart) {
		// TODO Auto-generated method stub
		cartdao.deletecid(cart);
	}

}
