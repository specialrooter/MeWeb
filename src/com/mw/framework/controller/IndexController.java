/**
 *
 */
package com.mw.framework.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mw.framework.bean.Message;
import com.mw.framework.dao.SysOrganDao;
import com.mw.framework.dao.SysUserDao;
import com.mw.framework.domain.SysOrgan;
import com.mw.framework.domain.SysUser;

/**
 * @Project MeWeb
 * @Copyright © 2008-2014 SPRO Technology Consulting Limited. All rights reserved.
 * @fileName com.mw.framework.controller.IndexController.java
 * @Version 1.0.0
 * @author Allan Ai
 * @time 2014-2-28
 *
 */
@Controller
@RequestMapping("/*")
public class IndexController {

	@Autowired
	private SysUserDao sysUserDao;
	
	/**
	 * 跳转到主界面
	 * @return
	 */
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String index(){
		return null;
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	@ResponseBody
	public Message login(String username,String password){
		Message msg = null;
		SysUser sysUser = sysUserDao.findByUserNameAndPassword(username, password);
		if(sysUser!=null){
			msg = new Message("");
		}else{
			msg = new Message("LO-O-500", "用户名获密码错误");
		}
		return msg;
	}
	
	public String navigation(){
		return null;
	}
}
