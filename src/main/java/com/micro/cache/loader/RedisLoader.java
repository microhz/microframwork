package com.micro.cache.loader;

import java.util.concurrent.TimeUnit;

/**
 * Created by peichuan.mpc on 20/07/2018.
 */
public class RedisLoader implements CacheLoader {
    public Object get(String key) {
        /**
         * test null return
         */
        return null;
    }

    public boolean set(String key, Object val, Integer cacheTime, TimeUnit timeUnit) {
        return false;
    }
}
