/*
 * Copyright (C), 2014-2014, 佛祖保佑 , 永无BUG
 * FileName: Constants.java
 * Author:   李牧牧
 * Date:     2014年11月14日 下午8:09:08
 * Description:  
 * History: 
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间                     版本号                  描述
 * 李牧牧       2013.11.14  1.0.0
 */
package com.nothin.holiday.admin.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * 常量定义类
 * 
 * @author 李牧牧
 * @version 1.0
 *
 */
public final class Constants {

    private Constants() {}
    
    public static final String DATETIME_PATTERN = "yyyy-MM-dd HH:mm:ss";
    
    public static final String CODE_WILDCARD = "*"; // 编号通配符
    
    public static final int DAYS_MIN = 0;       // 最小天数
    public static final int DAYS_MAX = 100000;  // 最大天数, ~270 years
    
    public static final byte RESULT_SUCCESS_CODE = 0x0000 ;
    public static final byte RESULT_FAIL_CODE = 0x0001 ;
    
    public static final String FIT_TO_ALL = CODE_WILDCARD; // 适用所有范围
    
    // boolean true 字符串表示
    public static final String BOOLEAN_TRUE_IN_STR = "1"; 
    
    // boolean false 字符串表示
    public static final String BOOLEAN_FALSE_IN_STR = "0";
    
    private static final Map<Class<? extends Enum<?>>, Enum<?>[]> 
               mapEnumTypes = new HashMap<>();
    
    static {
        mapEnumTypes.put( Gear.class, 
                Gear.class.getEnumConstants() );
    }
            
    
    // 车挡类型
    public static enum Gear {
        AUTO( "A" ),                // 自动
        MANUAL( "M" ),              // 手动
        MANUAL_PLUS_AUTO( "B" ),    // 手自一体
        NA( "-" );                  // N/A 未知
        
        private String type;
        
        private Gear( String type ) {
            this.type = type;
        }
        
        @Override
        public String toString() {
            return this.type;
        }
    }
    
    public static Enum<?> getEnumByValue( Class<? extends Enum<?>> enumClass, 
            String value ) {
        
        if( enumClass == null || ! enumClass.isEnum() ) {
            throw new IllegalArgumentException( 
                    "Argument enumClass is null!!" );
        }
        
        if( value == null || "".equals( value ) ) {
            return null;
        }
        
        for( Class<? extends Enum<?>> eclass : mapEnumTypes.keySet() ) {
            if( eclass.equals( enumClass ) ) {
                for( Enum<?> e : mapEnumTypes.get( eclass ) ) {
                    if( e.toString().equals( value ) ) {
                        return e;
                    }
                }
            }
        }
        
        throw new IllegalArgumentException( 
                "Enum class \"" + enumClass.getName() + "\""
                  + " with value \"" + value + "\""
                  + " is not supported!!" );
    }
    
}








