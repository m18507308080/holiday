/*
 * Copyright (C), 2014-2014, 佛祖保佑 , 永无BUG
 * FileName: DateConverterBase.java
 * Author:   李牧牧
 * Date:     2014年11月14日 下午8:09:08
 * Description:  
 * History: 
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间                     版本号                  描述
 * 李牧牧       2013.11.14  1.0.0
 */
package com.nothin.holiday.admin.converter;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * 
 * Project Name: holiday
 * File Name: DateConverterBase
 * Package Name: com.nothin.holiday.admin.converter
 * @author 李牧牧
 * Date: 2014-07-07
 * Copyright (c) 2014
 * 
 */
public class DateConverterBase {
    /** datePattern */
    private String datePattern = "yyyy-MM-dd";
    /** timePattern */
    private String timePattern = "HH:mm:ss";
    /** dateFormat */
    private DateFormat dateFormat = new SimpleDateFormat(datePattern);
    /** dateTimeFormat */
    private DateFormat dateTimeFormat = new SimpleDateFormat(datePattern + " " + timePattern);

    /**
     * 功能描述: <br>
     * 〈功能详细描述〉
     *
     * @return 日期
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public DateFormat getDateFormat() {
        return dateFormat;
    }

    /**
     * 功能描述: <br>
     * 〈功能详细描述〉
     *
     * @param dateFormat 格式
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public void setDateFormat(DateFormat dateFormat) {
        this.dateFormat = dateFormat;
    }

    /**
     * @return the dateTimeFormat
     */
    public DateFormat getDateTimeFormat() {
        return dateTimeFormat;
    }

    /**
     * @param dateTimeFormat the dateTimeFormat to set
     */
    public void setDateTimeFormat(DateFormat dateTimeFormat) {
        this.dateTimeFormat = dateTimeFormat;
    }

}
