package com.ypp.tunte.sample.redis.lock.annotation;

import java.lang.annotation.*;

/**
 * 不能重复提交锁
 *
 * @author yanpp
 * @createDateTime 2020/3/26
 **/
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface NoRepeatSubmitLock {
    /**
     * 锁的名称
     * @return
     */
    String name() default "";

    /**
     * @author fly
     */
    String key() default "";

    /**
     *上锁以后xxx秒过期时间
     * @return expireTime
     */
    long expireTime() default 5000L;

    /**
     * 自定义业务key
     * @return keys
     */
    String [] keys() default {};

}
