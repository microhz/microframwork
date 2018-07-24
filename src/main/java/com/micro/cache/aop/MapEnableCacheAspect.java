package com.micro.cache.aop;

import com.micro.cache.loader.CacheLoader;
import com.micro.cache.loader.MapCacheLoader;

/**
 * Created by peichuan.mpc on 21/07/2018.
 */
public class MapEnableCacheAspect extends EnableCacheAspect {
    protected CacheLoader getCacheLoader() {
        return new MapCacheLoader();
    }
}
