package com.bp.aopProject.service.impl;

import com.bp.annotation.CacheKey;
import com.bp.annotation.Cacheable;
import com.bp.aopProject.service.Dosomething;

/**
 * 
 * @author current_bp
 * @createTime 20161102
 *
 */

public class DosomethhingImpl implements Dosomething {
	private static int count = 1;

	public String sayName(String name) {
		System.out.println("===>sayName:" + name);
		return "hello " + name;
	}

	@Cacheable(expire = 3600)
	public String get(@CacheKey String id) {
		System.out.println("===>get: id:"+id);
		return getValue(id);
	}
	
	public String getValue(String key){
		String result = key+"_"+count;
		++count;
		return result;
	}

}
