/*
 * Copyright (C), 2014-2014, 佛祖保佑 , 永无BUG
 * FileName: StringDateConverter.java
 * Author:   李牧牧
 * Date:     2014年11月14日 下午8:09:08
 * Description:  
 * History: 
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间                     版本号                  描述
 * 李牧牧       2013.11.14  1.0.0
 */
package com.nothin.holiday.admin.converter;

import java.text.ParseException;
import java.util.Date;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.convert.converter.Converter;

/**
 * 
 * Project Name: holiday
 * File Name: StringDateConverter
 * Package Name: com.nothin.holiday.admin.converter
 * @author 李牧牧
 * Date: 2014-07-07
 * Copyright (c) 2014
 * 
 */
public class StringDateConverter extends DateConverterBase implements Converter<String, Date> {
    /**
     * 日志
     */
    private static Logger logger = LoggerFactory.getLogger(StringDateConverter.class);
    @Override
    public Date convert(String source) {
        if (source == null) {
            return null;
        }

        String trim = source.trim();
        if (trim.length() == 0) {
            return null;
        }

        try {
            return source.contains(":") ? getDateTimeFormat().parse(trim) : getDateFormat().parse(trim);
        } catch (ParseException e) {

        }
		return null;
    }

}
