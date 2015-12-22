package com.yihaohuoche.Jfinal.interceptor;

import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSON;
import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;

public class LoggerInterceptor implements Interceptor {
	Logger logger = Logger.getLogger(LoggerInterceptor.class);
	/**
	 * Description: setting the operator Log
	 * @param inv
	 * Created by fujianjian Dec 22, 2015
	 */
	@Override
	public void intercept(Invocation inv) {
		try {
			logger.info("Req URL:" +inv.getController().getRequest().getRequestURI());
			logger.info("Req Para:"+JSON.toJSONString(inv.getController().getRequest().getParameterMap()));
		} catch (Exception e) {
		}
		inv.invoke();
	}

}