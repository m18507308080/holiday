/*
 * Copyright (C), 2014-2014, 佛祖保佑 , 永无BUG
 * FileName: AdministratorsVOCache.java
 * Author:   李牧牧
 * Date:     2014年11月14日 下午8:09:08
 * Description:  
 * History: 
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间                     版本号                  描述
 * 李牧牧       2013.11.14  1.0.0
 */
package com.nothin.holiday.admin.cache.dto;

import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.ObjectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.Cache;
import org.springframework.cache.support.SimpleValueWrapper;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.google.common.base.Optional;
import com.nothin.holiday.admin.view.AdministratorsViewObject;
import com.nothin.onolisa.framework.redis.client.IRedisClient;

/**
 * 基础信息缓存<br>
 * 
 * 
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public class AdministratorsVOCache implements Cache {
    private Logger logger=LoggerFactory.getLogger(getClass());
    /**
     * 缓存实体名称
     */
    private final String name;
    /**
     * redis二进制缓存
     */
    private final IRedisClient springRedisClient;
    /**
     * 过期时间
     */
    private final int expiration;
    
    /**
     * 默认过期时间
     */
    private final int defaultExpiration = 0;
    
    
    public AdministratorsVOCache(String name, IRedisClient springBinaryRedisClient) {
        this.name = name;
        this.springRedisClient = springBinaryRedisClient;
        this.expiration =defaultExpiration;
    }
  
    public AdministratorsVOCache(String name, IRedisClient springBinaryRedisClient, int expiration) {
        this.name = name;
        this.springRedisClient = springBinaryRedisClient;
        this.expiration =ObjectUtils.defaultIfNull(expiration, defaultExpiration);
    }
    
    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public Object getNativeCache() {
        return this.springRedisClient;
    }

    @Override
    public ValueWrapper get(Object key) {
        Optional<String> object = Optional.fromNullable(springRedisClient.get(String.valueOf(key)));
        ValueWrapper valueWrapper = (object.isPresent() ? new SimpleValueWrapper(JSON.parseObject(object.get(),new TypeReference<List<AdministratorsViewObject>>(){})) : null);
        return valueWrapper ;
    }

    @Override
    public void put(Object key, Object value) {
        String cacheValue=JSON.toJSONString(value);
        logger.debug("缓存{}:{}",key,cacheValue);
        if (expiration > 0) {
            springRedisClient.setex(String.valueOf(key), expiration, cacheValue);
        } else {
            springRedisClient.set(String.valueOf(key), cacheValue);
        }
    }
    
    

    @Override
    public void evict(Object key) {
    	logger.debug("按照{}删除缓存",key);
        //清除规则所属的缓存结果集
        Set<String> keys=springRedisClient.keys(String.valueOf(key));
        for (String cacheKey : keys) {
            springRedisClient.del(cacheKey);
        }
    }

    @Override
    public void clear() {
    }

}
