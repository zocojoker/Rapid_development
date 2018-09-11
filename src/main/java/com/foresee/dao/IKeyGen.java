/**
 * Copyright(c) Foresee Science & Technology Ltd. 
 */
package com.foresee.dao;

import com.foresee.exception.MyBaseException;

/**
 * 
 * <pre>
 * 生成各类UUID的接口。
 * </pre>
 * @author luoshifei luoshifei@foresee.cn
 * @version 1.00.00
 * <pre>
 * 修改记录
 *    修改后版本:     修改人：  修改日期:     修改内容: 
 * </pre>
 */
public interface IKeyGen {

	/**
	 * Spring受管Bean ID。
	 */
	public static final String BEAN_ID = "fbrp_infrastructure_keyGen";

	/**
	 * 基于IP地址返回唯一UUID。
	 * 
	 * @return Long 基于IP地址返回唯一UUID
	 * 
	 * @throws MyBaseException FBRP异常。
	 */
	public Long getLongKey() throws MyBaseException;

	/**
	 * 基于当前时间生成32位UUID。
	 * 
	 * @return String 基于当前时间生成32位UUID
	 * 
	 * @throws MyBaseException FBRP异常。
	 */
	public String getUUIDKey() throws MyBaseException;

	/**
	 * 基于对象HashCode生成32位UUID。
	 * 
	 * @param obj Object
	 * 
	 * @return String 基于对象HashCode生成32位UUID
	 */
	public String getUUIDKey(Object obj);

}