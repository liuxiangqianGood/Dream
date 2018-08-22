package com.lxq.ServiceImp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;











import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;










import com.alibaba.fastjson.JSON;
import com.lxq.Dao.LoginDao;
import com.lxq.Entity.aop;
import com.lxq.Log.SystemServiceLog;
import com.lxq.Service.LoginService;
@Service("loginService")
public class LoginServiceImp implements LoginService{
	@Autowired
	private LoginDao loginDao;
	
	//ÿ�����������Ŀ���뿪��Redis�����¼����������д���Ŀ��Ȼ���ܵ�¼
	@Autowired
	private JedisPool jedis;
	//JedisPool�̰߳�ȫ���������ӳ�
	@SystemServiceLog(description="�õ���ѯ��¼��������")
	@Override
	public List findlogin(Map<String, Object> map) {
	
		// TODO Auto-generated method 

		//jedispool��ȡ����
		Jedis je = jedis.getResource();
		//�õ���ǰ̨��map��Ŵ���������
		String mapusername=(String) map.get("username");
		String mapuserpsw=(String) map.get("userpsw");
		//��ȡshiro����
		Subject sub=SecurityUtils.getSubject();
		//�õ���ǰsessionid
		String sessionid=(String) sub.getSession().getId();
		System.out.println("��ȡ��ǰsessionid:"+sessionid);
		
		//exists����
		String value=je.get(mapusername);//Redis���key���ж�
		je.close();
	//�ж��û��Ƿ��ѵ�¼����Ե�¼��ô�ڵ�¼����û����Ͳ����Ե�¼��
	 if(value!=null){
		 	//���ؿ����ݽ�realm�жϽ�ֹ��¼
			System.out.println("�û�:["+mapusername+"]�ѵ�¼����ֹǿ�Ƶ�¼");
			return null;

		}else{
			
		List list=loginDao.findlogin(map);

		JSONArray jsonArray=JSONArray.fromObject(list);
		JSONObject jsonObject=(JSONObject) jsonArray.get(0);
		Subject subject=SecurityUtils.getSubject();
		String username= jsonObject.getString("username");
		String userpsw=jsonObject.getString("userpsw");
		String userid=jsonObject.getString("userid");
		subject.getSession().setAttribute("username",username);
		subject.getSession().setAttribute("userpsw", userpsw);
		subject.getSession().setAttribute("userid", userid);
		
		//����Redis
		je.set(username,sessionid);
		je.set(userpsw,sessionid);
		//����key�Ĵ��ʱ��
		je.expire(username, 180);
		je.expire(userpsw, 180);
		//��Ҫ�رղ�Ȼ�ڴ�й©


		System.out.println("-----set Redis ok------");
		return list;
		}

		}
	
	
	@SystemServiceLog(description="��¼�û���ɫ")
	@Override
	public List findrname(Map<String, Object> map) {
		// TODO Auto-generated method stub
		
		List list=loginDao.findrname(map);
		return list;
	}
	//��־����
	@SystemServiceLog(description="������־")
	@Override
	public void addaop(aop aop) {
		// TODO Auto-generated method stub
		loginDao.addaop(aop);
	}

}
