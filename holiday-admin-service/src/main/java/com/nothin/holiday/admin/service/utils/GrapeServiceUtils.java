/*
 * Copyright (C), 2014-2014, 佛祖保佑 , 永无BUG
 * FileName: GrapeServiceUtils.java
 * Author:   李牧牧
 * Date:     2014年11月14日 下午8:09:08
 * Description:  
 * History: 
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间                     版本号                  描述
 * 李牧牧       2013.11.14  1.0.0
 */
package com.nothin.holiday.admin.service.utils;

import com.nothin.holiday.admin.dao.utils.GrapeDAOUtils;

/**
 * Grape工具类
 * 
 * @author 李牧牧
 * @version 1.0
 *
 */
public final class GrapeServiceUtils {

    // hide from initiating
    private GrapeServiceUtils() {}
    
    
    public static void copyProperties( Object dest, Object orig ) {
        
        try {
            
            GrapeDAOUtils.copyProperties( dest, orig );
            
        } catch ( Exception ex ) {
            throw new IllegalArgumentException( ex );
        }
    }
    
}
