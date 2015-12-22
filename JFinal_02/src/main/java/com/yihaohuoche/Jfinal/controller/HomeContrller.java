package com.yihaohuoche.Jfinal.controller;

import org.apache.log4j.Logger;

import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Record;
import com.yihaohuoche.Jfinal.services.HomeServices;

public class HomeContrller extends Controller {
	Logger logger = Logger.getLogger(HomeContrller.class);
	public void Home(){
		render("/index.jsp");
	}
	public void saveUser(){
		Record record = new Record();
		record.set("name", "BOB");
		record.set("age", 10);
		record.set("sex", "nan");
		HomeServices homeServices = new HomeServices();
		homeServices.save(record);
		render("/view/home/save.jsp");
	}
}
