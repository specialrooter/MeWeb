package com.mw.framework.resolver;

import java.util.Locale;
import java.util.Map;

import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;

/**
 *  多视图处理器
 * @Project SpringMVC
 * @Copyright © 2008-2013 SPRO Technology Consulting Limited. All rights reserved.
 * @fileName com.cf.core.resolver.MixedViewResolver
 * @Version 1.0.0
 * @author Allan Ai
 * @time 2013-6-2 上午11:51:18
 *
 */
public class MixedViewResolver implements ViewResolver{
	private Map<String,ViewResolver> resolvers;

	public void setResolvers(Map<String, ViewResolver> resolvers) {
		this.resolvers = resolvers;
	}
	
	public View resolveViewName(String viewName,Locale locale) throws Exception{
		int n=viewName.lastIndexOf(".");
		if(n!=-1){
			//取出扩展名
			String suffix=viewName.substring(n+1);
			//取出对应的ViewResolver
			ViewResolver resolver=resolvers.get(suffix);
			if(resolver==null){
				throw new RuntimeException("No ViewResolver for "+suffix);
			}
			return  resolver.resolveViewName(viewName, locale);
		}else{
			ViewResolver resolver=resolvers.get("jsp");
			return  resolver.resolveViewName(viewName, locale);
		}
	}
}
