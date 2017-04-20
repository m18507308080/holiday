/*
 * Copyright (C), 2014-2014, 佛祖保佑 , 永无BUG
 * FileName: CacheNullException.java
 * Author:   李牧牧
 * Date:     2014年11月14日 下午8:09:08
 * Description:  
 * History: 
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间                     版本号                  描述
 * 李牧牧       2013.11.14  1.0.0
 */
package com.nothin.holiday.admin.cache.exception;

import java.text.MessageFormat;

/**
 * 〈一句话功能简述〉<br>
 * 没有找到相应的cache异常
 * 
 * @author 李牧牧
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public class CacheNullException extends RuntimeException {

    private static final long serialVersionUID = 5780149869755681715L;
    private static final String MSG = "{0}没有相应的对象";

    public CacheNullException(String msg) {
        super(MessageFormat.format(MSG, msg));
    }

    public CacheNullException(Throwable thr) {
        super(thr);
    }

    public CacheNullException(String msg, Throwable thr) {
        super(msg, thr);
    }

}
