package com.lxq.Controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;


import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lxq.util.HttpClientUtil;

@Controller
public class TestController {

	//�����ύ��ʽ��ο���
	
		//get��ʽ��ȡ�õ�����
		@RequestMapping(value="/getAllfood")
		@ResponseBody
		public void getAllfood(HttpServletResponse response) throws Exception{
			String urlNameString="http://19r1x09355.51mypc.cn/XBMMavenSSM/Food/getAllFood";
			String result="";
			try {
				HttpGet get=new HttpGet(urlNameString);
				HttpClient client=HttpClientBuilder.create().build();
				HttpResponse response2=client.execute(get);
				if(response2.getStatusLine().getStatusCode()==HttpStatus.SC_OK){
					//���ܷ��صĲ���
					result=EntityUtils.toString(response2.getEntity(),"UTF-8");
				}
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			System.out.println("��������:"+result);
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(result);
		}

		
		
		//ǰ̨�õ������ύ����̨����POST�ύ��ʽ
		@RequestMapping(value="/addoracle")
		@ResponseBody
		public void addoracle(HttpServletRequest request,HttpServletResponse response)throws Exception{
			String name=request.getParameter("name");
			String tableid=request.getParameter("tableid");
			String allmoney=request.getParameter("allmoney");
			String urlNameString="http://19r1x09355.51mypc.cn/XBMMavenSSM/Order/addOneOrder";
			String result="";

			Map<String,String> map=new HashMap<String,String>();
			map.put("name", name);
			map.put("tableid", tableid);
			map.put("allmoney",allmoney);
			JSONObject jsob=JSONObject.fromObject(map);
			result=HttpClientUtil.getMyIngerface(urlNameString, jsob.toString());
			System.out.println("���ص�result:"+result);
			response.getWriter().write(result);
		}

}
