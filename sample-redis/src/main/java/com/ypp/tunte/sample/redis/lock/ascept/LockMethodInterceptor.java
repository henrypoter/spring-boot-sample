package com.ypp.tunte.sample.redis.lock.ascept;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.ypp.tunte.sample.redis.lock.annotation.NoRepeatSubmitLock;
import com.ypp.tunte.sample.redis.lock.core.BusinessKeyProvider;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.StringRedisTemplate;

import javax.annotation.Resource;
import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

/**
 * 功能说明
 *
 * @author yanpp
 * @createDateTime 2020/3/26
 **/
@Aspect
@Configuration
public class LockMethodInterceptor {
    private static final Cache<String, Object> CACHES = CacheBuilder.newBuilder()
            // 最大缓存 100 个
            .maximumSize(1000)
            // 设置写缓存后 5 秒钟过期
            .expireAfterWrite(5, TimeUnit.SECONDS)
            .build();

    @Resource
    private BusinessKeyProvider businessKeyProvider;

    @Autowired
    private StringRedisTemplate redisTemplate;


    @Around("execution(public * *(..)) && @annotation(com.ypp.tunte.sample.redis.lock.annotation.NoRepeatSubmitLock)")
    public Object interceptor(ProceedingJoinPoint pjp) {
        MethodSignature signature = (MethodSignature) pjp.getSignature();
        Method method = signature.getMethod();
        NoRepeatSubmitLock localLock = method.getAnnotation(NoRepeatSubmitLock.class);
        String key = businessKeyProvider.getKeyName(pjp,localLock);

        //System.out.println(businessKeyProvider.getKeyName(pjp,localLock));


     /*   if (!StringUtils.isEmpty(key)) {
            if (CACHES.getIfPresent(key) != null) {
                throw new RuntimeException("请勿重复请求");
            }
            // 如果是第一次请求,就将 key 当前对象压入缓存中
            CACHES.put(key, key);
        }
        try {
            return pjp.proceed();
        } catch (Throwable throwable) {
            throw new RuntimeException("服务器异常");
        } finally {
            // TODO 为了演示效果,这里就不调用 CACHES.invalidate(key); 代码了
        }*/

        //如果缓存中存在此key 视为重复提交
        if(redisTemplate.opsForValue().get(key) == null){
            //不存在 放入redis 设置超时时间为2s
            redisTemplate.opsForValue().set(key,key,localLock.expireTime(), TimeUnit.MILLISECONDS);

        }else{


            throw new RuntimeException("重复提交");
        }
        try {
            return pjp.proceed();
        } catch (Throwable throwable) {
            throw new RuntimeException("服务器异常");
        }
    }



    /**
     * key 的生成策略,如果想灵活可以写成接口与实现类的方式（TODO 后续讲解）
     *
     * @param keyExpress 表达式
     * @param args       参数
     * @return 生成的key
     */
    private String getKey(String keyExpress, Object[] args) {
        for (int i = 0; i < args.length; i++) {
            keyExpress = keyExpress.replace("arg[" + i + "]", args[i].toString());
        }
        return keyExpress;
    }
}
