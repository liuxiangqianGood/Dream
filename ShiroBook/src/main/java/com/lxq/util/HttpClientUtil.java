package com.lxq.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

import net.sf.json.JSONObject;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
//两台服务器接口调用添加数据POST提交方式
public class HttpClientUtil {
	
	public static String getMyIngerface(String url,String arg0){
		CloseableHttpClient httpClient = null;  
		HttpPost httpPost = null;  
		String result = null;  
		try{  
			httpClient = HttpClients.createDefault(); 
			httpPost = new HttpPost(url);  
			JSONObject js=JSONObject.fromObject(arg0);
			List<NameValuePair> list = new ArrayList<NameValuePair>();  
			Iterator<?> iterator = js.entrySet().iterator();
			while(iterator.hasNext()) {
				@SuppressWarnings("unchecked")
				Entry<String,String> o=(Entry<String, String>) iterator.next();
				list.add(new BasicNameValuePair(o.getKey(),o.getValue())); 
			}
			if(list.size() > 0){  
				UrlEncodedFormEntity entity = new UrlEncodedFormEntity(list,"utf-8");  
				httpPost.setEntity(entity);  
			}  
			HttpResponse response = httpClient.execute(httpPost);  
			if(response != null){  
				HttpEntity resEntity = response.getEntity();  
				if(resEntity != null){  
					result = EntityUtils.toString(resEntity,"utf-8");  
				}  
			}  
		}catch(Exception ex){  
			ex.printStackTrace();  
		}  
		return result;  
	}  

}


