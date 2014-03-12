/**
 *
 */
package com.mw.framework.commons;

import java.sql.Timestamp;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.http.HttpRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import net.sf.json.JsonConfig;
import net.sf.json.util.CycleDetectionStrategy;

import com.mw.framework.bean.Constants;
import com.mw.framework.bean.impl.UUIDEntity;
import com.mw.framework.util.JsonDateValueProcessor;

/**
 * @Project MeWeb
 * @Copyright © 2008-2014 SPRO Technology Consulting Limited. All rights
 *            reserved.
 * @fileName com.mw.framework.commons.BaseController.java
 * @Version 1.0.0
 * @author Allan Ai
 * @time 2014-3-10
 * 
 */
public class BaseController {

	public HttpSession getSession() {
		return getRequest().getSession();
	}

	public HttpServletRequest getRequest() {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
				.getRequestAttributes()).getRequest();
		return request;
	}

	/**
	 * 设置系统默认数据
	 * 
	 * @param entity
	 */
	public void setEntity(UUIDEntity entity) {
		if (entity.getId() != null && entity.getId().length() > 0) {
			entity.setUpdateUser((String) getSession().getAttribute(
					Constants.CURR_USER_ID));
			entity.setUpdateTime(new Date());
		} else {
			entity.setCreateUser((String) getSession().getAttribute(
					Constants.CURR_USER_ID));
			entity.setCreateTime(new Date());
		}
	}

	/**
	 * 
	 * @param strings
	 * @return
	 */
	protected JsonConfig getJsonConfig(String... strings) {
		return getJsonConfig("yyyy-MM-dd HH:mm:ss", strings);
	}

	/**
	 * jsonconfig
	 * 
	 * @param format
	 * @param strings
	 * @return
	 */
	protected JsonConfig getJsonConfig(String format, String... strings) {
		JsonConfig config = new JsonConfig();

		config.setIgnoreDefaultExcludes(false);
		config.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);
		// 只要设置这个数组，指定过滤哪些字段
		config.setExcludes(/* new String[]{"children","parent"} */strings);
		config.registerJsonValueProcessor(Date.class,
				new JsonDateValueProcessor(format));
		config.registerJsonValueProcessor(Timestamp.class,
				new JsonDateValueProcessor(format));

		return config;
	}
}
