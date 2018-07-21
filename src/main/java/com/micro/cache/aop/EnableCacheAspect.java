package com.micro.cache.aop;

import com.alibaba.fastjson.JSONObject;
import com.micro.cache.annotation.EnableCache;
import com.micro.cache.loader.CacheLoader;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.reflect.MethodSignature;

import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by peichuan.mpc on 20/07/2018.
 * 缓存切面类
 */
public abstract class EnableCacheAspect {

    Logger logger = Logger.getLogger("EnableCacheAspect");

    /**
     * 定义切面为处理com.micro.cache.annotation.EnableCache注解
     * @param proceedingJoinPoint
     * @return
     * @throws Throwable
     */
    @Around("@annotation(com.micro.cache.annotation.EnableCache)")
    public Object processCache(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        Object val = null;
        try {
            boolean isMethod = proceedingJoinPoint.getSignature() instanceof MethodSignature;
            if (!isMethod) {
                // 暂时不支持非方法级别
                return proceedingJoinPoint.proceed();
            }
            MethodSignature methodSignature = (MethodSignature) proceedingJoinPoint.getSignature();
            EnableCache enableCache = methodSignature.getMethod().getAnnotation(EnableCache.class);
            if (enableCache == null) {
                return proceedingJoinPoint.proceed();
            }

            CacheLoader cacheLoader = getCacheLoader();
            String cacheKey = getCacheKey(proceedingJoinPoint);

            formatLog("method -> %s get from cache", cacheKey);

            String cacheVal = cacheLoader.get(cacheKey);
            if (cacheVal == null) {
                formatLog("method -> %s not exits in cache ,put key", cacheKey);
                val = proceedingJoinPoint.proceed();
                boolean cacheSuccess = cacheLoader.set(cacheKey, JSONObject.toJSONString(val), enableCache.cacheTimes(), enableCache.timeUnit());
                if (!cacheSuccess) {
                    cacheFail(cacheKey, val, enableCache.cacheTimes(), enableCache.timeUnit());
                }
            } else {
                Class returnType = methodSignature.getReturnType();
                val = JSONObject.parseObject(cacheVal, returnType);
                formatLog("method -> %s  in cache val -> %s", cacheKey, cacheKey);
            }
        } catch (Throwable throwable) {
            processThrowable(throwable);
            throw throwable;
        }
        return val;
    }

    // 处理异常信息
    protected void processThrowable(Throwable throwable) {

    }


    // 缓存设置失败
    protected void cacheFail(String cacheKey, Object val, int times, TimeUnit timeUnit) {
        // sub class process
    }

    protected String getCacheKey(ProceedingJoinPoint proceedingJoinPoint) {
        MethodSignature signature = (MethodSignature) proceedingJoinPoint.getSignature();
        Method method = signature.getMethod();
        String beanName = proceedingJoinPoint.getTarget().getClass().getSimpleName();
        StringBuilder key = new StringBuilder();

        boolean isCacheKeyExits = false;
        if (!isCacheKeyExits) {
            for(Object param : proceedingJoinPoint.getArgs()) {
                key.append(param).append(getKeySplitor());
            }
        }
        return beanName + "." + method.getName() + key.subSequence(0, key.length() - 1).toString();
    }

    protected String getKeySplitor() {
        return "#";
    }


    protected abstract CacheLoader getCacheLoader();

    protected void formatLog(String content, String... params) {
        logger.log(Level.ALL, String.format(content, params));
    }
}
