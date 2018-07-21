package com.micro.cache.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 缓存入参字段的key值
 * 支持固定的参数缓存，默认全部参数缓存
 * @Reference com.micro.cache.annotation.EnabkeCache
 *
 * @author micro
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface CacheKey {
}