/*
 * Copyright (C), 2014-2014, 佛祖保佑 , 永无BUG
 * FileName: DateStringConverter.java
 * Author:   李牧牧
 * Date:     2014年11月14日 下午8:09:08
 * Description:  
 * History: 
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间                     版本号                  描述
 * 李牧牧       2013.11.14  1.0.0
 */
package com.nothin.holiday.admin.converter;

import java.util.Date;

import org.springframework.core.convert.converter.Converter;

/**
 * 
 * Project Name: holiday
 * File Name: DateStringConverter
 * Package Name: com.nothin.holiday.admin.converter
 * @author 李牧牧
 * Date: 2014-07-07
 * Copyright (c) 2014
 * 
 */
public class DateStringConverter extends DateConverterBase implements Converter<Date, String> {

    @Override
    public String convert(Date source) {
        if (source == null) {
            return "";
        }

        return getDateFormat().format(source);
    }

}
