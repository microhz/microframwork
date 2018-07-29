package com.cache.test;

import com.micro.cache.annotation.EnableCache;
import com.micro.cache.aop.EnableCacheAspect;
import com.micro.cache.aop.MapEnableCacheAspect;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.aop.aspectj.annotation.AspectJProxyFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.*;
import org.springframework.lang.Nullable;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.TimeUnit;

/**
 * Created by peichuan.mpc on 21/07/2018.
 */
@RunWith(SpringRunner.class)
@Import(MyConfig.class)
public class CacheAspectTest {

    @Autowired
    private AspectJProxyFactory aspectJProxyFactory;


    @Test
    public void testCache() {
        Cache cache = aspectJProxyFactory.getProxy();
        for (int i = 0;i < 10;i ++) {
            cache.doSomething("hello");
        }
    }


}


interface Cache {
    @EnableCache(cacheTimes = 1, timeUnit = TimeUnit.HOURS)
    Object doSomething(String params);
}

class BeanPrintPostBeanProccessor implements BeanPostProcessor {
    @Nullable
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("------------initializing bean name : " + beanName + "--------------");
        return null;
    }
}

@Configuration
class MyConfig {

    @Bean
    public EnableCacheAspect mapEnableCacheAspect() {
        return new MapEnableCacheAspect();
    }

    @Bean
    public Cache cache() {
        return new Cache() {

            // 方法注解不继承
            @EnableCache(cacheTimes = 1, timeUnit = TimeUnit.HOURS)
            public Object doSomething(String params) {
                System.out.println("执行cacheMethod");
                return "dbInfo + " + params;
            }
        };
    }

    @Bean
    public BeanPostProcessor BeanPrintPostBeanProccessor() {
        return new BeanPrintPostBeanProccessor();
    }

    @Bean
    @Autowired
    public AspectJProxyFactory aspectJProxyFactory(Cache cache) {
        AspectJProxyFactory aspectJProxyFactory = new AspectJProxyFactory(cache);
        aspectJProxyFactory.addAspect(new MapEnableCacheAspect());
        return aspectJProxyFactory;
    }

}
