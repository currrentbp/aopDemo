package com.bp.aopProject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bp.aopProject.service.Dosomething;

/**
 * 
 * @author current_bp
 * @createTime 20161102
 *
 */
@Controller("helloController")
@RequestMapping("helloController")
public class HelloController {
	
	@Autowired
	Dosomething dosomething;
	
	@RequestMapping(value = "helloWorld", method = RequestMethod.GET)
	@ResponseBody
	public String helloWorld(){
		
		return dosomething.sayName("baopan");
	}
	
	@RequestMapping(value = "getCache", method = RequestMethod.GET)
	@ResponseBody
	public String getCache(String id){
		
		return dosomething.get(id);
	}

}
