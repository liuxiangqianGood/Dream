package com.layui.Dao;

import java.util.List;
import java.util.Map;

public interface LoginDao {
//��ѯ�û��������¼
	List findlogin(Map<String,Object> map);
	Integer regist(Map<String,Object> map);

}
