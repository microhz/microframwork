package com.micro.cache.aop;

import com.micro.cache.loader.CacheLoader;
import com.micro.cache.loader.MapCacheLoader;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * Created by peichuan.mpc on 21/07/2018.
 */
@Component
@Aspect
public class MapEnableCacheAspect extends EnableCacheAspect {
    protected CacheLoader getCacheLoader() {
        if (this.cacheLoader == null) {
            synchronized (this) {
                if (this.cacheLoader == null) {
                    this.cacheLoader = new MapCacheLoader();
                    return this.cacheLoader;
                }
                return this.cacheLoader;
            }
        } else {
            return this.cacheLoader;
        }
    }
}
