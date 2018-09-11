/**
 * Copyright(c) Foresee Science & Technology Ltd. 
 */
package com.foresee.dao;

import java.util.Date;

import com.foresee.exception.MyBaseException;

/**
 * 
 * <pre>
 * 返回服务器当前时间。
 * </pre>
 * @author foresee
 * @version 1.00.00
 * <pre>
 * 修改记录
 *    修改后版本:     修改人：  修改日期:     修改内容: 
 * </pre>
 */
public interface IServerTime {

	/**
	 * Spring受管Bean ID。
	 */
	public static final String BEAN_ID = "base_serverTime";
	
	/**
	 * 返回服务器当前时间。
	 * 
	 * @return Date 返回服务器当前时间
	 * 
	 * @throws MyBaseException FBRP异常
	 */
	public Date getServerDateTime() throws MyBaseException;

}