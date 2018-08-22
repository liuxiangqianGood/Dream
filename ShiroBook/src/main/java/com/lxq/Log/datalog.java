package com.lxq.Log;

import java.sql.Connection;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.lang.reflect.Field;

import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.session.defaults.DefaultSqlSession.StrictMap;

@Intercepts({@Signature(type = StatementHandler.class, method = "prepare", args = { Connection.class, Integer.class}) })
public class datalog implements Interceptor {

	@Override
	public Object intercept(Invocation invocation) throws Throwable {
		
		Object target = invocation.getTarget();
		long startTime = System.currentTimeMillis();
		StatementHandler statementHandler = (StatementHandler)target;
		try {
			return invocation.proceed();
		} finally {
			long endTime = System.currentTimeMillis();
			long sqlCost = endTime - startTime;
			BoundSql boundSql = statementHandler.getBoundSql();
			String sql = boundSql.getSql();
			Object parameterObject = boundSql.getParameterObject();
			List<ParameterMapping> parameterMappingList = boundSql.getParameterMappings();

			// ��ʽ��Sql��䣬ȥ�����з����滻����
			sql = formatSql(sql, parameterObject, parameterMappingList);
			System.out.println(parameterMappingList);
			System.err.println("SQL��[" + sql + "]ִ�к�ʱ[" + sqlCost + "ms]");
		}

	}

	@Override
	public Object plugin(Object target) {
		// TODO Auto-generated method stub
		System.out.println("plugin");
		return Plugin.wrap(target, this);
	}

	@Override
	public void setProperties(Properties properties) {
		// TODO Auto-generated method stub
		System.out.println("setProperties");
	}

	private String formatSql(String sql, Object parameterObject, List<ParameterMapping> parameterMappingList) {
		// ����sql�ַ������ж�
		if (sql == null || sql.length() == 0) {
			return "";
		}

		// ����sql
		sql = beautifySql(sql);

		// ���������ĳ�����ֱ�Ӱ�Sql����һ�·��س�ȥ
		if (parameterObject == null || parameterMappingList == null || parameterMappingList.size() == 0) {
			return sql;
		}

		// ����һ��û���滻��ռλ����sql�����ڳ��쳣ʱ����
		String sqlWithoutReplacePlaceholder = sql;

		try {
			if (parameterMappingList != null) {
				Class<?> parameterObjectClass = parameterObject.getClass();

				// ���������StrictMap��Value����ΪCollection����ȡkey="list"�����ԣ�������Ҫ��Ϊ�˴���<foreach>ѭ��ʱ����List���ֲ�����ռλ���滻
				// ����select * from xxx where id in <foreach collection="list">...</foreach>
				if (isStrictMap(parameterObjectClass)) {
					StrictMap<Collection<?>> strictMap = (StrictMap<Collection<?>>)parameterObject;

					if (isList(strictMap.get("list").getClass())) {
						sql = handleListParameter(sql, strictMap.get("list"));
					}
				} else if (isMap(parameterObjectClass)) {
					// ���������Map��ֱ��ǿת��ͨ��map.get(key)������ȡ����������ֵ
					// ������Ҫ��Ϊ�˴���<insert>��<delete>��<update>��<select>ʱ����parameterTypeΪmap�ĳ���
					Map<?, ?> paramMap = (Map<?, ?>) parameterObject;
					sql = handleMapParameter(sql, paramMap, parameterMappingList);
				} else {
					// ͨ�ó��������紫����һ���Զ���Ķ�����߰��ֻ�����������֮һ����String
					sql = handleCommonParameter(sql, parameterMappingList, parameterObjectClass, parameterObject);
				}
			}
		} catch (Exception e) {
			// ռλ���滻�����г����쳣���򷵻�û���滻��ռλ�����Ǹ�ʽ��������sql���������ٱ�֤sql����BoundSql�е�sql���ÿ�
			return sqlWithoutReplacePlaceholder;
		}

		return sql;
	}

	/**
	 * ����Sql
	 */
	private String beautifySql(String sql) {
		// sql = sql.replace("\n", "").replace("\t", "").replace("  ", " ").replace("( ", "(").replace(" )", ")").replace(" ,", ",");
		sql = sql.replaceAll("[\\s\n ]+"," ");
		return sql;
	}

	/**
	 * �������ΪList�ĳ���
	 */
	private String handleListParameter(String sql, Collection<?> col) {
		if (col != null && col.size() != 0) {
			for (Object obj : col) {
				String value = null;
				Class<?> objClass = obj.getClass();

				// ֻ��������������͡������������͵İ�װ�ࡢString������
				// ����Ǹ�������Ҳ�ǿ��Եģ��������ӵ������ֳ������٣�д�����ʱ��Ҫ�ж�һ��Ҫ�õ����Ǹ��������е��ĸ�����
				if (isPrimitiveOrPrimitiveWrapper(objClass)) {
					value = obj.toString();
				} else if (objClass.isAssignableFrom(String.class)) {
					value = "\"" + obj.toString() + "\""; 
				}

				sql = sql.replaceFirst("\\?", value);
			}
		}

		return sql;
	}

	/**
	 * �������ΪMap�ĳ���
	 */
	private String handleMapParameter(String sql, Map<?, ?> paramMap, List<ParameterMapping> parameterMappingList) {
		for (ParameterMapping parameterMapping : parameterMappingList) {
			Object propertyName = parameterMapping.getProperty();
			Object propertyValue = paramMap.get(propertyName);
			if (propertyValue != null) {
				if (propertyValue.getClass().isAssignableFrom(String.class)) {
					propertyValue = "\"" + propertyValue + "\"";
				}

				sql = sql.replaceFirst("\\?", propertyValue.toString());
			}
		}

		return sql;
	}

	/**
	 * ����ͨ�õĳ���
	 */
	private String handleCommonParameter(String sql, List<ParameterMapping> parameterMappingList, Class<?> parameterObjectClass, 
			Object parameterObject) throws Exception {
		for (ParameterMapping parameterMapping : parameterMappingList) {
			String propertyValue = null;
			// �����������ͻ��߻����������͵İ�װ�ֱ࣬��toString���ɻ�ȡ�������Ĳ���ֵ������ֱ��ȡparamterMapping�е�property���Լ���
			if (isPrimitiveOrPrimitiveWrapper(parameterObjectClass)) {
				propertyValue = parameterObject.toString();
			} else {
				String propertyName = parameterMapping.getProperty();

				Field field = parameterObjectClass.getDeclaredField(propertyName);
				// Ҫ��ȡField�е�����ֵ��������뽫˽�����Ե�accessible����Ϊtrue
				field.setAccessible(true);
				propertyValue = String.valueOf(field.get(parameterObject));
				if (parameterMapping.getJavaType().isAssignableFrom(String.class)) {
					propertyValue = "\"" + propertyValue + "\"";
				}
			}

			sql = sql.replaceFirst("\\?", propertyValue);
		}

		return sql;
	}

	/**
	 * �Ƿ�����������ͻ��߻����������͵İ�װ��
	 */
	private boolean isPrimitiveOrPrimitiveWrapper(Class<?> parameterObjectClass) {
		return parameterObjectClass.isPrimitive() || 
				(parameterObjectClass.isAssignableFrom(Byte.class) || parameterObjectClass.isAssignableFrom(Short.class) ||
						parameterObjectClass.isAssignableFrom(Integer.class) || parameterObjectClass.isAssignableFrom(Long.class) ||
						parameterObjectClass.isAssignableFrom(Double.class) || parameterObjectClass.isAssignableFrom(Float.class) ||
						parameterObjectClass.isAssignableFrom(Character.class) || parameterObjectClass.isAssignableFrom(Boolean.class));
	}

	/**
	 * �Ƿ�DefaultSqlSession���ڲ���StrictMap
	 */
	private boolean isStrictMap(Class<?> parameterObjectClass) {
		return parameterObjectClass.isAssignableFrom(StrictMap.class);
	}

	/**
	 * �Ƿ�List��ʵ����
	 */
	private boolean isList(Class<?> clazz) {
		Class<?>[] interfaceClasses = clazz.getInterfaces();
		for (Class<?> interfaceClass : interfaceClasses) {
			if (interfaceClass.isAssignableFrom(List.class)) {
				return true;
			}
		}

		return false;
	}

	/**
	 * �Ƿ�Map��ʵ����
	 */
	private boolean isMap(Class<?> parameterObjectClass) {
		Class<?>[] interfaceClasses = parameterObjectClass.getInterfaces();
		for (Class<?> interfaceClass : interfaceClasses) {
			if (interfaceClass.isAssignableFrom(Map.class)) {
				return true;
			}
		}

		return false;
	}








}
