package com.layui.Intercept;

import java.sql.Connection;
import java.util.Properties;

import org.apache.ibatis.executor.statement.BaseStatementHandler;
import org.apache.ibatis.executor.statement.RoutingStatementHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;

import com.layui.util.ReflectHelper;


@Intercepts({@Signature(type=StatementHandler.class,method="prepare",args={Connection.class})})
public class MybatisIntercept implements Interceptor{
	@Override 
	public Object intercept(Invocation lxq) throws Throwable {
		// TODO Auto-generated method stub
	RoutingStatementHandler rsh=(RoutingStatementHandler)lxq.getTarget();
		System.out.println("执行的方法："+rsh.getBoundSql().getSql());
		BaseStatementHandler delegate = (BaseStatementHandler) ReflectHelper.getValueByFieldName(rsh, "delegate");
		MappedStatement mappedStatement = (MappedStatement) ReflectHelper.getValueByFieldName(delegate, "mappedStatement");	
		String id=mappedStatement.getId().toString();
		System.out.println("这是我获取的xml执行的id："+id);
		BoundSql boundSql = delegate.getBoundSql();
		Object parameterObject = boundSql.getParameterObject();
		//反应帮手.设定值的字段名
//		ReflectHelper.setValueByFieldName(boundSql, "sql", "select * from t_user"); 
		return lxq.proceed();
	}

	@Override
	public Object plugin(Object arg0) {
		// TODO Auto-generated method stub
		return Plugin.wrap(arg0, this);
	}

	@Override
	public void setProperties(Properties arg0) {
		// TODO Auto-generated method stub
		String dialect;
		dialect=arg0.get("dialect").toString();
		System.out.println("dialect:"+dialect);
	
	}

}
