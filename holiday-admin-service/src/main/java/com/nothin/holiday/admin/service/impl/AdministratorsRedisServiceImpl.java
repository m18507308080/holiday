/*
 * Copyright (C), 2014-2014, 佛祖保佑 , 永无BUG
 * FileName: AdministratorsRedisServiceImpl.java
 * Author:   李牧牧
 * Date:     2014年11月14日 下午8:09:08
 * Description:  
 * History: 
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间                     版本号                  描述
 * 李牧牧       2013.11.14  1.0.0
 */
package com.nothin.holiday.admin.service.impl;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import com.nothin.holiday.admin.service.api.IAdministratorsRedisService;

@Service
public class AdministratorsRedisServiceImpl implements IAdministratorsRedisService {
	
	@Cacheable(value = "administratorsVOCache", key = "T(com.nothin.holiday.admin.cache.utils.KeyUtil).key(#b)")
	public String demo (String b) {
		return "123";
	}
}
