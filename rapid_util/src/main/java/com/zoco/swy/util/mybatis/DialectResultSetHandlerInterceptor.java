/**
 * Copyright(c) Foresee Science & Technology Ltd. 
 */
package com.zoco.swy.util.mybatis;

import com.zoco.swy.util.ReflectUtil;
import org.apache.ibatis.executor.resultset.FastResultSetHandler;
import org.apache.ibatis.executor.resultset.ResultSetHandler;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.session.RowBounds;

import java.sql.Statement;
import java.util.Properties;

/**
 * 
 * <pre>
 * Interceptor实现类。
 * </pre>
 * @author luoshifei  luoshifei@zoco.cn
 * @version 1.00.00
 * <pre>
 * 修改记录
 *    修改后版本:     修改人：  修改日期:     修改内容: 
 * </pre>
 */
@Intercepts({ @Signature(type = ResultSetHandler.class, method = "handleResultSets", args = { Statement.class }) })
public class DialectResultSetHandlerInterceptor implements Interceptor {

	/* (non-Javadoc)
	 * @see org.apache.ibatis.plugin.Interceptor#intercept(org.apache.ibatis.plugin.Invocation)
	 */
	@Override
	public Object intercept(Invocation invocation) throws Throwable {
		FastResultSetHandler resultSet = (FastResultSetHandler) invocation
				.getTarget();

		RowBounds rowBounds = (RowBounds) ReflectUtil.getFieldValue(resultSet,
				"rowBounds");

		if (rowBounds.getLimit() > 0
				&& rowBounds.getLimit() < RowBounds.NO_ROW_LIMIT) {
			ReflectUtil.setFieldValue(resultSet, "rowBounds", new RowBounds());
		}
		return invocation.proceed();
	}

	/* (non-Javadoc)
	 * @see org.apache.ibatis.plugin.Interceptor#plugin(java.lang.Object)
	 */
	@Override
	public Object plugin(Object target) {
		return Plugin.wrap(target, this);
	}

	/* (non-Javadoc)
	 * @see org.apache.ibatis.plugin.Interceptor#setProperties(java.utils.Properties)
	 */
	@Override
	public void setProperties(Properties properties) {
	}
	
}