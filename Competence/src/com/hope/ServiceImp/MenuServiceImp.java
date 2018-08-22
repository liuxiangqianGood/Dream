package com.hope.ServiceImp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.sf.json.JSONObject;



import org.springframework.stereotype.Service;

import com.hope.Bean.T_Menu;


import com.hope.Bean.cart;
import com.hope.Dao.MenuDao;
import com.hope.Service.MenuService;

@Service("menuService")
public class MenuServiceImp implements MenuService{
	@Resource(name="menuDao")
	private MenuDao menudao;
	@Override
	public JSONObject findmenu(String total,String rows,String where) {
		// TODO Auto-generated method stub

		String sql1="select * from t_menu";
		List<Object[]> listobject=menudao.findmenu(sql1);
		List<Map> list=new ArrayList<Map>();
		for(int i=0;i<listobject.size();i++){
			Map map=new HashMap();
			map.put("mid", listobject.get(i)[0]);
			map.put("mname", listobject.get(i)[1]);
			map.put("fid", listobject.get(i)[2]);
			map.put("urlaction", listobject.get(i)[3]);
			list.add(map);
		}
		Map map1=new HashMap();
		map1.put("total", listobject.get(0));
		map1.put("rows", list);
		JSONObject js=JSONObject.fromObject(map1);
		return js;

	}
	@Override
	public List listpage(String pageno,String pagesize) {

	String sql="SELECT * FROM ( SELECT ROWNUM AS LIMITNUM,menu.*  FROM T_Menu menu ) WHERE LIMITNUM BETWEEN ("+pageno+"-1)*"+pagesize+" AND "+pageno+"*"+pagesize;
	return menudao.listpage(sql);
}
	@Override
	public boolean saveorupdate(T_Menu menu) {
		// TODO Auto-generated method stub
		return menudao.saveorupdate(menu);
	}
	@Override
	public void delete(T_Menu menu) {
		// TODO Auto-generated method stub
		menudao.delete(menu);
	}
	@Override
	public void add(T_Menu menu) {
		// TODO Auto-generated method stub
		menudao.add(menu);
	}


}
