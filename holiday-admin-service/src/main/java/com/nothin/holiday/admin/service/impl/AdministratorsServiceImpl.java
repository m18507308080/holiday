/*
 * Copyright (C), 2014-2014, 佛祖保佑 , 永无BUG
 * FileName: AdministratorsServiceImpl.java
 * Author:   李牧牧
 * Date:     2014年11月14日 下午8:09:08
 * Description:  
 * History: 
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间                     版本号                  描述
 * 李牧牧       2013.11.14  1.0.0
 */
package com.nothin.holiday.admin.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.nothin.holiday.admin.dao.api.IAdministratorsDAO;
import com.nothin.holiday.admin.dao.api.IGenericDAO;
import com.nothin.holiday.admin.dao.domain.Administrators;
import com.nothin.holiday.admin.service.api.IAdministratorsService;
import com.nothin.holiday.admin.utils.Constants;
import com.nothin.holiday.admin.view.AdministratorsViewObject;
import com.nothin.holiday.admin.view.ResultViewObject;

@Service
public class AdministratorsServiceImpl extends GenericServiceImpl<AdministratorsViewObject, Administrators, AdministratorsServiceImpl>
		implements IAdministratorsService {
	
	@Autowired
	private IAdministratorsDAO iAdminDao;
	
	@Autowired
	public AdministratorsServiceImpl(IGenericDAO<Administrators> genericDAO) {
		super(genericDAO);
	}
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public ResultViewObject<Object> login(Map<String,Object> params) {
		ResultViewObject<Object> resultViewObject = new ResultViewObject<Object>() ;
		String account = (String) params.get("account");
		String password = (String) params.get("password");
		List<Administrators> administratorsList = iAdminDao.login(account, password) ;
		if (administratorsList != null && administratorsList.size() > 0) {
			resultViewObject.setResult(transToViewObject(administratorsList.get(0))) ;
			resultViewObject.setCode(Constants.RESULT_SUCCESS_CODE) ;
			resultViewObject.setMessage("登陆成功,并记录SEESION") ;
		} else {
			resultViewObject.setCode(Constants.RESULT_FAIL_CODE) ;
			resultViewObject.setMessage("登陆失败") ;
		}
		return resultViewObject ;
	}
	
}
