/*
 * Copyright (C), 2014-2014, 佛祖保佑 , 永无BUG
 * FileName: TimestampStringConverter.java
 * Author:   李牧牧
 * Date:     2014年11月14日 下午8:09:08
 * Description:  
 * History: 
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间                     版本号                  描述
 * 李牧牧       2013.11.14  1.0.0
 */
package com.nothin.holiday.admin.converter;

import java.sql.Timestamp;

import org.springframework.core.convert.converter.Converter;

/**
 * 
 * Project Name: holiday
 * File Name: TimestampStringConverter
 * Package Name: com.nothin.holiday.admin.converter
 * @author 李牧牧
 * Date: 2014-07-07
 * Copyright (c) 2014
 * 
 */
public class TimestampStringConverter extends DateConverterBase implements Converter<Timestamp, String> {

    @Override
    public String convert(Timestamp source) {
        if (source == null) {
            return "";
        }
        return getDateFormat().format(source);
    }

}
