/*
 * Copyright (C), 2014-2014, 佛祖保佑 , 永无BUG
 * FileName: KeyUtil.java
 * Author:   李牧牧
 * Date:     2014年11月14日 下午8:09:08
 * Description:  
 * History: 
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间                     版本号                  描述
 * 李牧牧       2013.11.14  1.0.0
 */
package com.nothin.holiday.admin.cache.utils;

import java.text.MessageFormat;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.ObjectUtils;
import com.google.common.collect.Lists;

/**
 * 生成redis缓存Key
 *
 * @author 李牧牧
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public abstract class KeyUtil {
	/**
	 * 车享宝 Redis 缓存key
	 */
	public static final String BABYINCAR_KEY="{0}_{1}_{2}";
    
    
    /**
     * 根据参数生成key
     * @param unionOneKey
     * @return
     * @throws Exception 
     */
    public static String key(String unionOneKey) throws Exception{
        return MessageFormat.format(BABYINCAR_KEY ,new Object[]{unionOneKey,"ALL","ALL"});
    }
    
    
    /**
     * 根据参数生成key
     * @param modelKey
     * @param unionOneKey
     * @return
     * @throws Exception 
     */
    public static String key(String modelKey , String unionOneKey) throws Exception{
        return MessageFormat.format(BABYINCAR_KEY ,new Object[]{modelKey,unionOneKey,"ALL"});
    }
    
    /**
     * 根据参数生成key
     * @param modelKey
     * @param unionOneKey
     * @param unionTwoKey
     * @return
     * @throws Exception 
     */
    public static String key(String modelKey , String unionOneKey , String unionTwoKey) throws Exception{
        return MessageFormat.format(BABYINCAR_KEY ,new Object[]{modelKey,unionOneKey,unionTwoKey});
    }
    
    
    /**
     * 功能描述: <br>
     * 将Map的数据按照key的升序排列组合成String,最后通过MD5的方式返回校验数据
     *
     * @param paramMap 参数
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public static String map2MD5(Map<String,Object> paramMap){
       if( MapUtils.isEmpty(paramMap)){
           return "*";
       }
       List<String> mapKeyList=Lists.newArrayList(paramMap.keySet());
       Collections.sort(mapKeyList);
       
       StringBuffer sb=new StringBuffer();
       for(String key:mapKeyList){
           sb.append(key).append(ObjectUtils.toString(paramMap.get(key)));
       }
       return DigestUtils.md5Hex(sb.toString());
    }
}
