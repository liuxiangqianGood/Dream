package com.lxq.Shiro;

import java.util.concurrent.atomic.AtomicInteger;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheManager;
/**
 * 用户登录次数限制(5次)
 * 
 * 防止暴力破解
 * 
 * 
 **/
public class RetryLimitHashedCredentialsMatcher extends HashedCredentialsMatcher{
							
	//继承自HashedCredentialsMatcher     
		//HashedCredentialsMatcher配置的属性值要跟加密时的属性（hashAlgorithmName，hashIterations，storedCredentialsHexEncoded）一致，
		//storedCredentialsHexEnc表示是否存储散列后的密码为16进制，需要和生成密码时的一样。
		private Cache<String,AtomicInteger> passwordRetryCache;
		
		public RetryLimitHashedCredentialsMatcher(CacheManager cacheManager){
			//这里用到了ehcache小部分知识
			//其核心是CacheManager，一切Ehcache的应用都是从CacheManager开始的。
			//通过cacheManager拿到用户登陆的次数(缓存可以储存在内存中也可以是硬盘，这里用的硬盘。配置信息见ehcache.xml)
			passwordRetryCache=cacheManager.getCache("passwordRetryCache");
		}

		@Override
		public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
			//shiro的身份认证的流程，大致是这样的：
			//当我们调用subject.login(token)的时候，首先这次身份认证会委托给Security Manager，
			//而Security Manager又会委托给Authenticator，
			//接着Authenticator会把传过来的token再交给我们自己注入的Realm进行数据匹配从而完成整个认证。
			String username=(String)token.getPrincipal();
			AtomicInteger retryCount=passwordRetryCache.get(username);
			//如果retryCount==null证明用户还未登陆 缓存中拿不到
			//进行登录次数递增
			if(retryCount==null){
				retryCount=new AtomicInteger(0);
				passwordRetryCache.put(username, retryCount);
			}
			//如果不停尝试 登录次数大于了3  抛出异常
			if(retryCount.incrementAndGet()>3){
				//ExcessiveAttemptsException继承自AccountException 
				//异常信息:登录次数过多 一个用户多次登录异常:不允许多次登录,只能登录一次 。即不允许多处登录等等
				throw new ExcessiveAttemptsException();
			}
			//如果登录匹配 就移除passwordRetryCache重置次数便于下次登录
			boolean matches= super.doCredentialsMatch(token, info);
			if(matches){
				passwordRetryCache.remove(username);
			}
			return matches;
		}
		
	
	
	
}
