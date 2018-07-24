package com.cache.test;

import com.micro.cache.annotation.EnableCache;

import java.util.concurrent.TimeUnit;

/**
 * Created by peichuan.mpc on 21/07/2018.
 */
public class CacheAspectTest {

    @EnableCache(cacheTimes = 1, timeUnit = TimeUnit.HOURS)
    public void cacheMethod() {

    }
}
