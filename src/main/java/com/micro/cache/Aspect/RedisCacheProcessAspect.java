package com.micro.cache.Aspect;

import com.micro.cache.loader.CacheLoader;
import com.micro.cache.loader.RedisLoader;

/**
 * Created by peichuan.mpc on 20/07/2018.
 */
public class RedisCacheProcessAspect extends CacheProccessorAspect {
    protected CacheLoader getCacheLoader() {
        return new RedisLoader();
    }
}
