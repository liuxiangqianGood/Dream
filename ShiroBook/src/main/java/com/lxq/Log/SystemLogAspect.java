package com.lxq.Log;

import java.lang.reflect.Method;
import java.text.SimpleDateFormat;


import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;





import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;


import org.springframework.web.context.request.ServletRequestAttributes;





import com.alibaba.fastjson.JSONObject;
import com.lxq.Entity.aop;
import com.lxq.Service.LoginService;

@Aspect
@Component
public class SystemLogAspect {
	

		@Autowired
		private LoginService loginService;
		//�����쳣��־��¼����
		private static final Logger logger=LoggerFactory.getLogger(SystemLogAspect.class);

		//Serviceע�Ͳ�������һ��ע���е㷽��
		//@Pointcutȥ����д�õ�SystemServiceLog�ķ���
		@Pointcut("@annotation(com.lxq.Log.SystemServiceLog)")
		public void serviceAspect(){
			System.out.println("************serviceAspect**************");
		}
		//Controllerע�Ͳ�������һ��ע���е㷽��
		//@Pointcutȥ����д�õ�SystemControllerLog�ķ���
		@Pointcut("@annotation(com.lxq.Log.SystemControllerLog)")
		public void  controllerAspect(){
			System.out.println("************controllerAspect**************");
		}
		
		/**  
		 * ǰ��֪ͨ ��������Controller���¼�û��Ĳ���  
		 *  
		 * @param joinPoint �е�  
		 */    
		@Before("controllerAspect()")
		public void doBefore(JoinPoint joinPoint){
			
			HttpServletRequest request=((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();  
			HttpSession session=request.getSession();
			//�õ���¼���û���
			String username=(String) session.getAttribute("username");
			//�����IP
			String ip=request.getRemoteAddr();	
			//��ȡϵͳ��ǰʱ��
			SimpleDateFormat time= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			try {
				System.out.println("===ǰ��֪ͨ��ʼ===");
				System.out.println("���󷽷�:"+joinPoint.getTarget().getClass().getName()+"."+joinPoint.getSignature().getName()+"()");
				System.out.println("��������:"+ getControllerMethodDescription(joinPoint));
				System.out.println("����IP:"+ip);
				System.out.println("�����û�:"+username);
				System.out.println("��ȡϵͳʱ��:"+time.format(new Date()));
				//���ݿ���־aopΪһ��ʵ����˿��൱��һ������
				aop aop=new aop();
				aop.setId("");
				aop.setUsername(username);
				aop.setMethods((joinPoint.getTarget().getClass().getName() + "." + joinPoint.getSignature().getName() + "()"));
				aop.setTypess("1");
				aop.setIp(ip);
				aop.setParams(null);
				aop.setExceptioncode(null);
				aop.setDescription(getControllerMethodDescription(joinPoint));
				aop.setExceptiondetail("baiduExceptiondetail");
				aop.setCreateby(username);
				aop.setDotime(null);
				aop.setCreatedate(time.format(new Date()));
//		aop.setCreatedate(new Date());
//				��־������ݿ�
				loginService.addaop(aop);
				System.out.println("===ǰ��֪ͨ����===");
			} catch (Exception e) {
				// TODO: handle exception
				logger.error("===ǰ��֪ͨ�쳣===");
				logger.error("�쳣��Ϣ:{}",e.getMessage());
			}
		}
		//@AfterThrowing�쳣֪ͨ���ڷ����׳��쳣֮��ִ��
		@AfterThrowing(pointcut = "controllerAspect()",throwing = "e")
		public void doAfterThrowing(JoinPoint joinPoint, Throwable e) {    
			System.out.println("....��Ҫ����Aop�����ȡ�������Ե�.....");
			HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();    
			HttpSession session = request.getSession();    
			//��ȡsession�е��û�
			String username = (String) session.getAttribute("username");    
			//��ȡ����ip    
			String ip = request.getRemoteAddr();    
			//��ȡ�û����󷽷��Ĳ��������л�ΪJSON��ʽ�ַ���    
			JSONObject js = new JSONObject();
			String params = "";    
			if (joinPoint.getArgs() !=null   && joinPoint.getArgs().length > 0) {
				for ( int i = 0; i < joinPoint.getArgs().length; i++) { 
					System.out.println("----------------"+joinPoint.getArgs()[i] +";");
					params += js.toJSONString(joinPoint.getArgs()[i] +";");    
				}   
			}    
			try {    
				/*========����̨���=========*/ 
				System.out.println("=====�쳣֪ͨ��ʼ=====");    
				System.out.println("�쳣����:" + e.getClass().getName());    
				System.out.println("�쳣��Ϣ:" + e.getMessage());
				System.out.println("�쳣����:" + (joinPoint.getTarget().getClass().getName() + "." + joinPoint.getSignature().getName() + "()"));    
				System.out.println("��������:" + getServiceMthodDescription(joinPoint));  
				System.out.println("������:" + username);
				System.out.println("����IP:" + ip);
				System.out.println("�������:" + params);  
				/*==========���ݿ���־=========*/    
				System.out.println("------��־����ʼ------");
				

				
				aop aop = new aop();
				aop.setDescription(getServiceMthodDescription(joinPoint));    
				aop.setExceptioncode(e.getClass().getName());    
				aop.setTypess("1");    
				aop.setExceptiondetail(e.getMessage());    
				aop.setMethods((joinPoint.getTarget().getClass().getName() + "." + joinPoint.getSignature().getName() + "()"));    
				aop.setParams(params);    
				aop.setCreateby(username);    
				aop.setCreatedate(null);
				aop.setDotime(null);
				aop.setIp(ip);
				aop.setId("");
				Date now = new Date(); 
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");//���Է�����޸����ڸ�ʽ
				String hehe = dateFormat.format( now ); 
				System.out.println("doword"+hehe);
				aop.setCreatedate(null);
				aop.setDotime(hehe);
				aop.setIp(ip); 
				//�������ݿ�    
				loginService.addaop(aop); 
				System.out.println("=====�쳣֪ͨ����=====");    
			}  catch (Exception ex) {    
				//��¼�����쳣��־    
				logger.error("==�쳣֪ͨ�쳣==");    
				logger.error("�쳣��Ϣ:{}", ex.getMessage());    
			}    
			/*==========��¼�����쳣��־==========*/    
			logger.error("�쳣����:{}�쳣����:{}�쳣��Ϣ:{}����:{}", joinPoint.getTarget().getClass().getName() + joinPoint.getSignature().getName(), e.getClass().getName(), e.getMessage(), params);    

		}    
//		//@Around����֪ͨ��Χ���ŷ���ִ��
//		@Around("execution(* com.lxq.Controller..*(..))")  
//		public Object log(ProceedingJoinPoint p) throws Throwable {
//			Long starTime = System.currentTimeMillis();
//			Date now = new Date(); 
//			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");//���Է�����޸����ڸ�ʽ
//
//
//			String hehe = dateFormat.format( now ); 
//			System.out.println("doword"+hehe);
//			HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();    
//			String ip = request.getRemoteAddr();
//		
//			
//			System.out.println("����ִ�п�ʼʱ��:"+starTime); 
//			// Ŀ�����������
//			String className = p.getTarget().getClass().getName();
//			// ���õķ�����
//			String method = p.getSignature().getName();
//			// ��ǰϵͳʱ��
//			String date = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss") .format(new Date());
//			// ƴ��־��Ϣ
//			String msg ="IP:"+ip+ "-->�û���" + date + "��ִ����" + className + "." + method + "()";
//			// ��¼��־
//			System.out.println(msg);
//			// ִ��Ŀ������ķ���
//			Object obj = p.proceed();
//			// �ڵ���Ŀ�����ҵ�񷽷���Ҳ������һЩҵ����
//			System.out.println("-->����Ŀ�����ҵ�񷽷���...");
//			Long endTime = System.currentTimeMillis();
//			System.out.println("����ִ�н���ʱ��:"+endTime);
//			System.out.println("huanhuanshijian:"+new Date(endTime));
//			Short cc = (short) (endTime-starTime);
//			System.out.println("����ִ������ʱ��"+cc);
//			
//			aop aop = new aop();    
//			aop.setDescription(method);    
//			aop.setMethods(className);    
//			aop.setTypess("0");    
//			aop.setIp(ip);  
//			aop.setExceptioncode("");    
//			aop.setExceptiondetail("");    
//			aop.setParams("");    
//			aop.setCreateby("user");   
//		//	Long cc = bb-aa;
//			aop.setCreatedate(date);
//			aop.setDotime(hehe); 
//			//�������ݿ�    
//			loginService.addaop(aop);    
//			
//			return obj;
//		}
//
//
//		/**  
//		 * ��ӡ����ִ�к�ʱ����Ϣ���Ŵ�ӡ  
//		 * @param methodName  
//		 * @param startTime  
//		 * @param endTime  
//		 */  
//		private void printExecTime(String methodName, long startTime, long endTime) {  
//			long diffTime = endTime - startTime;  
//
//			logger.warn("-----" + methodName + " ����ִ�к�ʱ��" + diffTime + " ms");  
//
//		}  


		 /**  
	     * ��ȡע���жԷ�����������Ϣ ����service��ע��  
	     *  
	     * @param joinPoint �е�  
	     * @return ��������  
	     * @throws Exception  
	     */  
		public  static String getServiceMthodDescription(JoinPoint joinPoint)    
				throws Exception {    
			String targetName = joinPoint.getTarget().getClass().getName();    
			String methodName = joinPoint.getSignature().getName(); 
			Object[] arguments = joinPoint.getArgs();    
			Class targetClass = Class.forName(targetName);    
			Method[] methods = targetClass.getMethods();    
			String description = "";    
			for (Method method : methods) {    
				if (method.getName().equals(methodName)) {    
					Class[] clazzs = method.getParameterTypes();    
					if (clazzs.length == arguments.length) {    
						description = method.getAnnotation(SystemServiceLog. class).description();    
						break;    
					}    
				}    
			}    
			return description;    
		}    
		
		/**  
		 * ��ȡע���жԷ�����������Ϣ ����Controller��ע��  
		 *  
		 * @param joinPoint �е�  
		 * @return ��������  
		 * @throws Exception  
		 */    
		private static String getControllerMethodDescription(JoinPoint joinPoint) throws Exception {
			// TODO Auto-generated method stub
			//joinPoint���ӵ�getTarget�õ���Ŀ��getSignature�õ�ǩ��
			String targetName=joinPoint.getTarget().getClass().getName();
			String methodName=joinPoint.getSignature().getName();
			Object[] arguments=joinPoint.getArgs();
			Class targetClass=Class.forName(targetName);
			Method[] methods=targetClass.getMethods();
			String description="";
			for (Method method:methods) {
				if (method.getName().equals(methodName)) {
					Class[] classes=method.getParameterTypes();
					if (classes.length==arguments.length) {
						description=method.getAnnotation(SystemControllerLog. class).description();
						break;
					}
				}
			}
			return description;
		}
	
		
}
