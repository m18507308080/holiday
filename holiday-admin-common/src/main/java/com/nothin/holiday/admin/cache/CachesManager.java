/*
 * Copyright (C), 2014-2014, 佛祖保佑 , 永无BUG
 * FileName: CacheManager.java
 * Author:   李牧牧
 * Date:     2014年11月14日 下午8:09:08
 * Description:  
 * History: 
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间                     版本号                  描述
 * 李牧牧       2013.11.14  1.0.0
 */
package com.nothin.holiday.admin.cache;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;

import com.nothin.holiday.admin.cache.exception.CacheNullException;

/**
 * 缓存管理<br>
 * 
 * @author 李牧牧
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public class CachesManager implements CacheManager {

    private final Map<String, Cache> caches = new ConcurrentHashMap<String, Cache>();

    public void setCaches(Collection<Cache> caches) {
        if (CollectionUtils.isNotEmpty(caches)) {
            for (Cache cache : caches) {
                this.caches.put(cache.getName(), cache);
            }
        }
    }

    @Override
    public Cache getCache(String name) {
        Cache cache = caches.get(name);
        if (cache == null) {
            throw new CacheNullException(name);
        }
        return cache;
    }

    @Override
    public Collection<String> getCacheNames() {
        return Collections.unmodifiableSet(caches.keySet());
    }

}
