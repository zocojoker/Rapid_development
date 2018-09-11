
package com.foresee.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.foresee.exception.AdminException;
import lombok.extern.slf4j.Slf4j;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

/**
 * 
 * 控制器基类
 * 
 */
@Slf4j
public class BaseController extends MultiActionController {
    
	/**
	 * 日志
	 */
	protected final static Logger	LOG	= Logger.getLogger(BaseController.class);
	
	/**
	 * 将一个对象转换成json字符串
	 * 
	 * @param obj
	 * @return
	 */
	protected String toJsonString(Object obj) {
		String result = JSONObject.toJSONString(obj);
		LOG.debug("convert Java Object=" + obj + " to json string=" + result);
		return result;
	}
	
	/**
	 * 将一个对象转换成json数组
	 * 
	 * @author yubing
	 * @param obj
	 * @return
	 */
	protected String toJsonArrayString(Object obj) {
		String result = JSONArray.toJSONString(obj);
		LOG.debug("convert Java Object=" + obj + " to jsonArray string=" + result);
		return result;
	}
	
	/**
	 * 将一个json字符串装换成一个java对象
	 * 
	 * @param jsonString
	 * @param clazz
	 * @return
	 */
	protected <T> T parseJsonString(String jsonString, Class<T> clazz) {
		T t = JSONObject.parseObject(jsonString, clazz);
		LOG.debug("convert jsonString=" + jsonString + " to Java Object=" + t);
		return t;
	}
	
	/**
	 * 响应Hash错误码键
	 */
	public static final String	KEY_RESP_CODE					= "code";
	/**
	 * 响应原因Hash键
	 */
	public static final String	KEY_RESP_REASON					= "reason";
	
	/**
	 * 请求响应成功状态
	 */
	public final static String	RESPONE_MESSAGE_STATUS_SUCCESS	= "0";
	
	/**
	 * 请求响应失败状态
	 */
	public final static String	RESPONE_MESSAGE_STATUS_FAILURE	= "1";
	
	
	/**
	 * 获取操作失败的响应
	 * 
	 * @param errorMessage
	 * @return
	 */
	protected String buildErrorRespone(String errorMessage) {
		return buildRespone(errorMessage, RESPONE_MESSAGE_STATUS_FAILURE);
	}
	/**
	 * 获取操作失败的响应
	 * @param parms
	 * @param errorMessage
	 * @return
	 */
	protected String buildErrorRespone(Map<String, Object> parms,String errorMessage) {
		return buildRespone(parms,errorMessage, RESPONE_MESSAGE_STATUS_FAILURE);
	}
	
	/**
	 * 获取操作失败的缺省响应
	 * 
	 * @return
	 */
	protected String buildDefaultErrorRespone() { 
		return buildRespone("", RESPONE_MESSAGE_STATUS_FAILURE);
	}
	
	/**
	 * 
	 * 获取操作成功的缺省响应
	 * 
	 * @param errorMessage
	 * @return
	 */
	protected String buildDefaultSuccessRespone() {
		return buildRespone("成功!", RESPONE_MESSAGE_STATUS_SUCCESS);
	}
	
	/**
	 * 
	 * 获取操作成功的响应
	 * 
	 * @param errorMessage
	 * @return
	 */
	protected String buildSuccessRespone(String message) {
		return buildRespone(message, RESPONE_MESSAGE_STATUS_SUCCESS);
	}
	
	/**
	 * 
	 * 获取操作成功的响应
	 * 
	 * @param errorMessage
	 * @return
	 */
	protected String buildSuccessRespone(Map<String, Object> parms) {
		return buildRespone(parms, "操作成功!", RESPONE_MESSAGE_STATUS_SUCCESS);
	}
	
	/**
	 * 建造响应信息
	 * 
	 * @param messageCategory
	 * @param message
	 * @param code
	 * @return
	 */
	private String buildRespone(Map<String, Object> parms, String message, String code) {
		Map<String, Object> respone = parms;
		if (respone == null) {
			respone = new HashMap<String, Object>();
		}
		respone.put("message", message);
		respone.put("code", code);
		return JSONObject.toJSONString(respone);
	}
	
	/**
	 * 建造响应信息
	 * 
	 * @param messageCategory
	 * @param message
	 * @return
	 */
	private String buildRespone(String message, String code) {
		return buildRespone(null, message, code);
	}
	
	/**
	 * 获取参数值，并转换为int类型
	 * @param request
	 * @param para
	 * @return
	 */
	protected int getParameterToInt(HttpServletRequest request, String para) {
		int parameter = -1;
		
		Object object = request.getParameter(para);
		if (null != object) {
			parameter = new Integer(object.toString());
		}
		
		return parameter;
	}

	/**
	 * 获取参数值，并转换为int类型
	 * @param request
	 * @param para
	 * @return
	 */
	protected Integer getParameterToIntRetNull(HttpServletRequest request, String para) throws AdminException{
		Integer parameter = null;
		Object object = request.getParameter(para);
		if (null != object) {
			try{
				parameter = new Integer(object.toString());	
			}catch (AdminException e) {
				log.error("获取参数值，并转换为int类型异常，AdminException异常信息为{}", e.getMessage(), e);
				throw new AdminException("获取参数值，并转换为int类型异常", e);
			}
		}
		return parameter;
	}
	
	/**
	 * 获取参数值，并转换为double类型
	 * @param request
	 * @param para
	 * @return
	 */
	protected double getParameterToDouble(HttpServletRequest request, String para) {
		double parameter = 0.0;
		
		Object object = request.getParameter(para);
		if (null != object) {
			parameter = new Double(object.toString());
		}
		
		return parameter;
	}

	/**
	 * 获取参数值，并转换为double类型
	 * @param request
	 * @param para
	 * @return
	 */
	protected Double getParameterToDoubleRetNull(HttpServletRequest request, String para) throws AdminException{
		Double parameter = null;
		Object object = request.getParameter(para);
		if (null != object) {
			try{
				parameter = new Double(object.toString());
			}catch (AdminException e) {
				log.error("获取参数值，并转换为double类型异常，AdminException异常信息为{}", e.getMessage(), e);
				throw new AdminException("获取参数值，并转换为double类型异常", e);
			}
		}
		return parameter;
	}
	
	/**
	 * 增加获取字符串参数方法
	 * @param request
	 * @param para
	 * @return
	 */
	protected String getParamStr(HttpServletRequest request, String para) throws AdminException{
		try {
			request.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e) {
			log.error("增加获取字符串参数方法异常，AdminException异常信息为{}", e.getMessage(), e);
			throw new AdminException("增加获取字符串参数方法异常", e);
		}
		return StringUtils.hasText(request.getParameter(para)) ? request
				.getParameter(para) : "";
	}
	
	/**
	 * 增加获取字符串参数方法
	 * @param request
	 * @param para
	 * @return
	 */
	protected String getParamStrRetNull(HttpServletRequest request, String para){
		return StringUtils.hasText(request.getParameter(para)) ? request
				.getParameter(para) : null;
	}
	
	protected void renderJson(HttpServletResponse response,String jsonResponse) throws AdminException{
		PrintWriter printWriter = null;
		try {
			response.setContentType("text/html");//解决获取ajax请求返回xml结果
			printWriter = response.getWriter();
			printWriter.write(jsonResponse);
			printWriter.flush();
		} catch (IOException e) {
			log.error("增加获取字符串参数方法异常，AdminException异常信息为{}", e.getMessage(), e);
			throw new AdminException("增加获取字符串参数方法异常", e);
		} finally {
			if (printWriter != null) {
				IOUtils.closeQuietly(printWriter);
			}
		}
	}
	
}
