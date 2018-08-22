package com.lxq.Shiro;

import java.util.concurrent.atomic.AtomicInteger;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheManager;
/**
 * �û���¼��������(5��)
 * 
 * ��ֹ�����ƽ�
 * 
 * 
 **/
public class RetryLimitHashedCredentialsMatcher extends HashedCredentialsMatcher{
							
	//�̳���HashedCredentialsMatcher     
		//HashedCredentialsMatcher���õ�����ֵҪ������ʱ�����ԣ�hashAlgorithmName��hashIterations��storedCredentialsHexEncoded��һ�£�
		//storedCredentialsHexEnc��ʾ�Ƿ�洢ɢ�к������Ϊ16���ƣ���Ҫ����������ʱ��һ����
		private Cache<String,AtomicInteger> passwordRetryCache;
		
		public RetryLimitHashedCredentialsMatcher(CacheManager cacheManager){
			//�����õ���ehcacheС����֪ʶ
			//�������CacheManager��һ��Ehcache��Ӧ�ö��Ǵ�CacheManager��ʼ�ġ�
			//ͨ��cacheManager�õ��û���½�Ĵ���(������Դ������ڴ���Ҳ������Ӳ�̣������õ�Ӳ�̡�������Ϣ��ehcache.xml)
			passwordRetryCache=cacheManager.getCache("passwordRetryCache");
		}

		@Override
		public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
			//shiro�������֤�����̣������������ģ�
			//�����ǵ���subject.login(token)��ʱ��������������֤��ί�и�Security Manager��
			//��Security Manager�ֻ�ί�и�Authenticator��
			//����Authenticator��Ѵ�������token�ٽ��������Լ�ע���Realm��������ƥ��Ӷ����������֤��
			String username=(String)token.getPrincipal();
			AtomicInteger retryCount=passwordRetryCache.get(username);
			//���retryCount==null֤���û���δ��½ �������ò���
			//���е�¼��������
			if(retryCount==null){
				retryCount=new AtomicInteger(0);
				passwordRetryCache.put(username, retryCount);
			}
			//�����ͣ���� ��¼����������3  �׳��쳣
			if(retryCount.incrementAndGet()>3){
				//ExcessiveAttemptsException�̳���AccountException 
				//�쳣��Ϣ:��¼�������� һ���û���ε�¼�쳣:�������ε�¼,ֻ�ܵ�¼һ�� ����������ദ��¼�ȵ�
				throw new ExcessiveAttemptsException();
			}
			//�����¼ƥ�� ���Ƴ�passwordRetryCache���ô��������´ε�¼
			boolean matches= super.doCredentialsMatch(token, info);
			if(matches){
				passwordRetryCache.remove(username);
			}
			return matches;
		}
		
	
	
	
}
