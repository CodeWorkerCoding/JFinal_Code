package com.yihaohuoche.Jfinal.services;

import java.util.List;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;

public class BaseServices {
	public List getAll(){
		List list = Db.find("select * from users");
		return list;
	}
	public Record get(int _id) {
		Record record = (Record) Db.find("select * from users where _id=?",_id);
		return record;
	}
	
	public void save(Record record){
		Db.save("users", record);
	}
}
