package com.lxq.ServiceImp;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lxq.Dao.HtDao;
import com.lxq.Service.HtService;

@Service("htService")
public class HtServiceImp implements HtService{
	
	@Autowired
	private HtDao htDao;
	
	@Override
	public List findmenus(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return htDao.findmenus(map);
	}

}
