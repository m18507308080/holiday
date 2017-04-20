/*
 * Copyright (C), 2014-2014, 佛祖保佑 , 永无BUG
 * FileName: AdministratorsDAOImpl.java
 * Author:   李牧牧
 * Date:     2014年11月14日 下午8:09:08
 * Description:  
 * History: 
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间                     版本号                  描述
 * 李牧牧       2013.11.14  1.0.0
 */
package com.nothin.holiday.admin.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;
import com.nothin.holiday.admin.dao.api.IAdministratorsDAO;
import com.nothin.holiday.admin.dao.domain.Administrators;

@Repository
public class AdministratorsDAOImpl extends GenericDAOBatisImpl<Administrators, AdministratorsDAOImpl> implements IAdministratorsDAO {
	
	@Override
	public List<Administrators> login(String account, String password) {
		Map<String, Object> params = new HashMap<>();
		params.put(VAR_TABLE_NAME, getTableName());
		params.put("account", account);
		params.put("password", password);
		return selectList("selectAdministratorsByAttributes", params);
	}
}
 