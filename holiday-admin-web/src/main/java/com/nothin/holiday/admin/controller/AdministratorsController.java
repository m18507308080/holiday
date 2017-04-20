/*
 * Copyright (C), 2014-2014, 佛祖保佑 , 永无BUG
 * FileName: AdministratorsController.java
 * Author:   李牧牧
 * Date:     2014年11月14日 下午8:09:08
 * Description:  
 * History: 
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间                     版本号                  描述
 * 李牧牧       2013.11.14  1.0.0
 */
package com.nothin.holiday.admin.controller;

import java.util.Map;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.nothin.holiday.admin.service.api.IAdministratorsService;
import com.nothin.holiday.admin.utils.Constants;
import com.nothin.holiday.admin.view.ResultViewObject;

/**
 * 
 * Project Name: holiday File Name: AdminController Package Name:
 * com.nothin.holiday.admin.controller
 * 
 * @author 李牧牧 Date: 2014-07-07 Copyright (c) 2014
 * 
 */
@Controller
@RequestMapping("/")
public class AdministratorsController {

	@Autowired
	private IAdministratorsService iAdminService;

	@RequestMapping("/index")
	public ModelAndView welcome() {
		ModelAndView modelAndView = new ModelAndView("login.htm");
		return modelAndView;
	}
	
	/**
	 * 
	 * index:(AJAX验证登陆账户是否合法并跳转到管理页面). <br/>
	 * TODO(访问管理界面需要请求该方法).<br/>
	 * 
	 * @author 李牧牧
	 * @return AJAX验证登陆账户是否合法
	 * @since JDK 1.7
	 */
	@RequestMapping(value = "/admin/login", method = RequestMethod.POST)
	public @ResponseBody ResultViewObject<Object> login(@RequestParam Map<String,Object> params , HttpSession session) {
		ResultViewObject<Object> resultViewObject = iAdminService.login(params) ;
		if (Constants.RESULT_SUCCESS_CODE == resultViewObject.getCode())
			session.setAttribute("user", resultViewObject.getResult()) ;
		return resultViewObject ;
	}
	
	/**
	 * 
	 * index:(AJAX注销用户信息并返回登陆页). <br/>
	 * TODO(访问管理界面需要请求该方法).<br/>
	 * 
	 * @author 李牧牧
	 * @return AJAX验证登陆账户是否合法
	 * @since JDK 1.7
	 */
	@RequestMapping("/loginout")
	public ModelAndView loginout(HttpSession session) {
		session.removeAttribute("user") ;
		session.invalidate() ;
		ModelAndView modelAndView = new ModelAndView("redirect:/index.html");
		return modelAndView;
	}

	/**
	 * 
	 * index:(初始化参数并跳转到管理页面). <br/>
	 * TODO(访问管理界面需要请求该方法).<br/>
	 * 
	 * @author 李牧牧
	 * @return 跳转到管理页面
	 * @since JDK 1.7
	 */
	@RequestMapping("/admin/index")
	public ModelAndView index() {
		ModelAndView modelAndView = new ModelAndView("common/index.htm");
		return modelAndView;
	}
}
