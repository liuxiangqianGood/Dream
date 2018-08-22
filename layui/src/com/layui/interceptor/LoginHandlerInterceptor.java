package com.layui.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;


public class LoginHandlerInterceptor extends HandlerInterceptorAdapter{
	    private final Logger log = LoggerFactory.getLogger(LoginHandlerInterceptor.class);  
	    /**  
	     * ��ҵ��������������֮ǰ������  
	     * �������false  
	     *     �ӵ�ǰ������������ִ��������������afterCompletion(),���˳��������� 
	     * �������true  
	     *    ִ����һ��������,ֱ�����е���������ִ�����  
	     *    ��ִ�б����ص�Controller  
	     *    Ȼ�������������,  
	     *    �����һ������������ִ�����е�postHandle()  
	     *    �����ٴ����һ������������ִ�����е�afterCompletion()  
	     */    
	    
	    @Override    
	    public boolean preHandle(HttpServletRequest request,    
	            HttpServletResponse response, Object handler) throws Exception {    
	    	//�÷���ͬ��equals���Ǻ��Դ�Сд
	    	if ("POST".equalsIgnoreCase(request.getMethod())) {  System.out.println("����POST����"); }  
	        log.info("==============ִ��˳��: 1��preHandle================");    
	        String requestUri = request.getRequestURI();  
	        System.out.println(requestUri);
	        String contextPath = request.getContextPath();  
	        String url = requestUri.substring(contextPath.length());  
	        log.info("requestUri:"+requestUri);    
	        log.info("contextPath:"+contextPath);    
	        log.info("url:"+url);
	        String url1=request.getRequestURI();
	        if(url1.contains("/layui/regist")){
	        	return true;
	        };
	        if(url1.contains("/layui/enterregist")){
	        	return true;
	        };
	        if(url1.contains("/layui/cancel")){
	        	return true;
	        };
	        if(url1.contains("/layui/okcancel")){
	        	return true;
	        };
	        HttpSession session=request.getSession();
	        String username =(String) session.getAttribute("username");
	        log.info("username:"+username);
	        if(requestUri.equals("/layui/login")) {
	        	 return true;     
	        }else if(username == null){  
	            log.info("Interceptor:�û���Ϊ����תloginҳ�棡");  
	            request.getRequestDispatcher("/login").forward(request, response);
	            return false;  
	        }else  
	            return true;     
	    }    
	    
	    /** 
	     * ��ҵ��������������ִ����ɺ�,������ͼ֮ǰִ�еĶ���    
	     * ����modelAndView�м������ݣ����統ǰʱ�� 
	     */  
	    @Override    
	    public void postHandle(HttpServletRequest request,    
	            HttpServletResponse response, Object handler,    
	            ModelAndView modelAndView) throws Exception {     
	        log.info("==============ִ��˳��: 2��postHandle================");    
	        if(modelAndView != null){  //���뵱ǰʱ��    
	            modelAndView.addObject("var", "����postHandle");    
	        }    
	    }    
	    
	    /**  
	     * ��DispatcherServlet��ȫ����������󱻵���,������������Դ��   
	     *   
	     * �����������׳��쳣ʱ,��ӵ�ǰ����������ִ�����е���������afterCompletion()  
	     */    
	    @Override    
	    public void afterCompletion(HttpServletRequest request,    
	            HttpServletResponse response, Object handler, Exception ex)    
	            throws Exception {    
	        log.info("==============ִ��˳��: 3��afterCompletion================");    
	    }    
	  
	
}
