package com.micro.cache.loader;

import com.google.common.cache.CacheBuilder;
import com.google.common.collect.Maps;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Created by peichuan.mpc on 21/07/2018.
 * 默认的缓存实现, 永久不超时
 */
@Deprecated
public class MapCacheLoader implements CacheLoader {

    private Map<String, Object> cacheMap = Maps.newHashMap();

    public Object get(String key) {
        return cacheMap.get(key);
    }

    public boolean set(String key, Object val, Integer cacheTime, TimeUnit timeUnit) {
        this.cacheMap.put(key, val);
        return Boolean.TRUE;
    }
}
