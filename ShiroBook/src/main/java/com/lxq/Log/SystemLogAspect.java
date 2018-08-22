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
		//本地异常日志记录对象
		private static final Logger logger=LoggerFactory.getLogger(SystemLogAspect.class);

		//Service注释层声明的一个注解切点方法
		//@Pointcut去引用写好的SystemServiceLog的方法
		@Pointcut("@annotation(com.lxq.Log.SystemServiceLog)")
		public void serviceAspect(){
			System.out.println("************serviceAspect**************");
		}
		//Controller注释层声明的一个注解切点方法
		//@Pointcut去引用写好的SystemControllerLog的方法
		@Pointcut("@annotation(com.lxq.Log.SystemControllerLog)")
		public void  controllerAspect(){
			System.out.println("************controllerAspect**************");
		}
		
		/**  
		 * 前置通知 用于拦截Controller层记录用户的操作  
		 *  
		 * @param joinPoint 切点  
		 */    
		@Before("controllerAspect()")
		public void doBefore(JoinPoint joinPoint){
			
			HttpServletRequest request=((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();  
			HttpSession session=request.getSession();
			//拿到登录的用户名
			String username=(String) session.getAttribute("username");
			//请求的IP
			String ip=request.getRemoteAddr();	
			//获取系统当前时间
			SimpleDateFormat time= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			try {
				System.out.println("===前置通知开始===");
				System.out.println("请求方法:"+joinPoint.getTarget().getClass().getName()+"."+joinPoint.getSignature().getName()+"()");
				System.out.println("方法描述:"+ getControllerMethodDescription(joinPoint));
				System.out.println("请求IP:"+ip);
				System.out.println("请求用户:"+username);
				System.out.println("获取系统时间:"+time.format(new Date()));
				//数据库日志aop为一个实体类此刻相当于一个容器
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
//				日志添加数据库
				loginService.addaop(aop);
				System.out.println("===前置通知结束===");
			} catch (Exception e) {
				// TODO: handle exception
				logger.error("===前置通知异常===");
				logger.error("异常信息:{}",e.getMessage());
			}
		}
		//@AfterThrowing异常通知，在方法抛出异常之后执行
		@AfterThrowing(pointcut = "controllerAspect()",throwing = "e")
		public void doAfterThrowing(JoinPoint joinPoint, Throwable e) {    
			System.out.println("....将要进行Aop切面获取数据请稍等.....");
			HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();    
			HttpSession session = request.getSession();    
			//读取session中的用户
			String username = (String) session.getAttribute("username");    
			//获取请求ip    
			String ip = request.getRemoteAddr();    
			//获取用户请求方法的参数并序列化为JSON格式字符串    
			JSONObject js = new JSONObject();
			String params = "";    
			if (joinPoint.getArgs() !=null   && joinPoint.getArgs().length > 0) {
				for ( int i = 0; i < joinPoint.getArgs().length; i++) { 
					System.out.println("----------------"+joinPoint.getArgs()[i] +";");
					params += js.toJSONString(joinPoint.getArgs()[i] +";");    
				}   
			}    
			try {    
				/*========控制台输出=========*/ 
				System.out.println("=====异常通知开始=====");    
				System.out.println("异常代码:" + e.getClass().getName());    
				System.out.println("异常信息:" + e.getMessage());
				System.out.println("异常方法:" + (joinPoint.getTarget().getClass().getName() + "." + joinPoint.getSignature().getName() + "()"));    
				System.out.println("方法描述:" + getServiceMthodDescription(joinPoint));  
				System.out.println("请求人:" + username);
				System.out.println("请求IP:" + ip);
				System.out.println("请求参数:" + params);  
				/*==========数据库日志=========*/    
				System.out.println("------日志处理开始------");
				

				
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
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");//可以方便地修改日期格式
				String hehe = dateFormat.format( now ); 
				System.out.println("doword"+hehe);
				aop.setCreatedate(null);
				aop.setDotime(hehe);
				aop.setIp(ip); 
				//保存数据库    
				loginService.addaop(aop); 
				System.out.println("=====异常通知结束=====");    
			}  catch (Exception ex) {    
				//记录本地异常日志    
				logger.error("==异常通知异常==");    
				logger.error("异常信息:{}", ex.getMessage());    
			}    
			/*==========记录本地异常日志==========*/    
			logger.error("异常方法:{}异常代码:{}异常信息:{}参数:{}", joinPoint.getTarget().getClass().getName() + joinPoint.getSignature().getName(), e.getClass().getName(), e.getMessage(), params);    

		}    
//		//@Around环绕通知，围绕着方法执行
//		@Around("execution(* com.lxq.Controller..*(..))")  
//		public Object log(ProceedingJoinPoint p) throws Throwable {
//			Long starTime = System.currentTimeMillis();
//			Date now = new Date(); 
//			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");//可以方便地修改日期格式
//
//
//			String hehe = dateFormat.format( now ); 
//			System.out.println("doword"+hehe);
//			HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();    
//			String ip = request.getRemoteAddr();
//		
//			
//			System.out.println("方法执行开始时间:"+starTime); 
//			// 目标组件的类名
//			String className = p.getTarget().getClass().getName();
//			// 调用的方法名
//			String method = p.getSignature().getName();
//			// 当前系统时间
//			String date = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss") .format(new Date());
//			// 拼日志信息
//			String msg ="IP:"+ip+ "-->用户在" + date + "，执行了" + className + "." + method + "()";
//			// 记录日志
//			System.out.println(msg);
//			// 执行目标组件的方法
//			Object obj = p.proceed();
//			// 在调用目标组件业务方法后也可以做一些业务处理
//			System.out.println("-->调用目标组件业务方法后...");
//			Long endTime = System.currentTimeMillis();
//			System.out.println("方法执行结束时间:"+endTime);
//			System.out.println("huanhuanshijian:"+new Date(endTime));
//			Short cc = (short) (endTime-starTime);
//			System.out.println("方法执行消耗时间"+cc);
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
//			//保存数据库    
//			loginService.addaop(aop);    
//			
//			return obj;
//		}
//
//
//		/**  
//		 * 打印方法执行耗时的信息，才打印  
//		 * @param methodName  
//		 * @param startTime  
//		 * @param endTime  
//		 */  
//		private void printExecTime(String methodName, long startTime, long endTime) {  
//			long diffTime = endTime - startTime;  
//
//			logger.warn("-----" + methodName + " 方法执行耗时：" + diffTime + " ms");  
//
//		}  


		 /**  
	     * 获取注解中对方法的描述信息 用于service层注解  
	     *  
	     * @param joinPoint 切点  
	     * @return 方法描述  
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
		 * 获取注解中对方法的描述信息 用于Controller层注解  
		 *  
		 * @param joinPoint 切点  
		 * @return 方法描述  
		 * @throws Exception  
		 */    
		private static String getControllerMethodDescription(JoinPoint joinPoint) throws Exception {
			// TODO Auto-generated method stub
			//joinPoint连接点getTarget得到的目标getSignature得到签名
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
