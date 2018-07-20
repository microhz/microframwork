package com.micro.cache.loader;

import java.util.concurrent.TimeUnit;

/**
 * 缓存接口定义, 可以实现自己的缓存，例如Redis, Tair等等
 */
public interface CacheLoader {

    Object get(String key);

    boolean set(String key, Object val, Integer cacheTime, TimeUnit timeUnit);
}
