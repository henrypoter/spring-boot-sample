package com.ypp.tunte.sample.redis.lock.annotation;

import java.lang.annotation.*;

/**
 * 不能重复提交锁
 *
 * @author yanpp
 * @createDateTime 2020/3/26
 **/
@Target(value = {ElementType.PARAMETER, ElementType.TYPE})
@Retention(value = RetentionPolicy.RUNTIME)
public @interface NoRepeatSubmitLockKey {

    String value() default "";

}
