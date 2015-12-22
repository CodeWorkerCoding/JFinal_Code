package com.yihaohuoche.Jfinal.common;

import com.jfinal.config.Constants;
import com.jfinal.config.Handlers;
import com.jfinal.config.Interceptors;
import com.jfinal.config.JFinalConfig;
import com.jfinal.config.Plugins;
import com.jfinal.config.Routes;
import com.jfinal.ext.plugin.quartz.QuartzPlugin;
import com.jfinal.kit.PropKit;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.activerecord.dialect.MysqlDialect;
import com.jfinal.plugin.c3p0.C3p0Plugin;
import com.jfinal.plugin.redis.RedisPlugin;
import com.jfinal.render.ViewType;
import com.yihaohuoche.Jfinal.controller.HomeContrller;
import com.yihaohuoche.Jfinal.interceptor.LoggerInterceptor;

/**
 * ClassName: Config
 * Description: JFinal Framework Setting
 * Author: fujianjian
 * Date: Dec 22, 2015
 */
public class Config extends JFinalConfig{
//	protected QuartzPlugin quartzPlugin = null;
	
	/**
	 * Description: config Constants varible.
	 * @param me
	 * Created by fujianjian Dec 22, 2015
	 */
	@Override
	public void configConstant(Constants me) {
		PropKit.use("config.properties"); //load the config setting file,
		me.setDevMode(PropKit.getBoolean("devMode", false));
		me.setViewType(ViewType.JSP);
		me.setError404View("/Error/jsp/404.jsp");
		me.setError401View("/Error/jsp/401.jsp");
		me.setError500View("/Error/jsp/500.jsp");
	}

	@Override
	public void configHandler(Handlers arg0) {
		// TODO Auto-generated method stub
		
	}
	
	/**
	 * Description: config global Intercepter
	 * @param arg0
	 * Created by fujianjian Dec 22, 2015
	 */
	@Override
	public void configInterceptor(Interceptors interceptors) {
		interceptors.add(new LoggerInterceptor());
	}

	@Override
	public void configPlugin(Plugins plugins) {
		PropKit.use("config.properties");
		C3p0Plugin c3p0Plugin = new C3p0Plugin(PropKit.get("jdbc.url"), PropKit.get("jdbc.user"), PropKit.get("jdbc.password"),PropKit.get("jdbc.deriver"));
		plugins.add(c3p0Plugin);
		ActiveRecordPlugin arp = new ActiveRecordPlugin("main", c3p0Plugin);
		arp.setShowSql(true);
		arp.setDialect(new MysqlDialect());
		plugins.add(arp);	
		
		RedisPlugin redisPlugin = new RedisPlugin(PropKit.get("redis.name"), PropKit.get("redis.host"));
		plugins.add(redisPlugin);
		
//		quartzPlugin = new QuartzPlugin();
//		plugins.add(quartzPlugin);
	}
	/**
	 * Description: config route
	 * @param arg0
	 * Created by fujianjian Dec 22, 2015
	 */
	@Override
	public void configRoute(Routes rts) {
		rts.add("/", HomeContrller.class);
	}
	
}
