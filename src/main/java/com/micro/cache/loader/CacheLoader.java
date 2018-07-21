package com.micro.cache.loader;

import java.util.concurrent.TimeUnit;

/**
 * 缓存接口定义, 可以实现自己的缓存，例如Redis, Tair等等
 */
public interface CacheLoader {

    /**
     * 获取缓存
     * @param key
     * @return
     */
    String get(String key);

    /**
     * 设置缓存与超时时间
     * @param key
     * @param val
     * @param cacheTime
     * @param timeUnit
     * @return
     */
    boolean set(String key, Object val, Integer cacheTime, TimeUnit timeUnit);
}
