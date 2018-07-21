package com.micro.cache.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.concurrent.TimeUnit;

/**
 * 支持缓存注解
 * @author micro
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface EnableCache {
    /**
     * 缓存的时间，默认60
     * @return
     */
    int cacheTimes() default 60;

    /**
     * 对应缓存时间单位 默认秒
     * @return
     */
    TimeUnit timeUnit() default TimeUnit.SECONDS;
}



