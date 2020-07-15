package com.ypp.tunte.sample.hello.spring.conditional;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

import java.util.Arrays;

/**
 * 判断要初始化哪个具体你的实现类（写在一个类里，用内部类，比较清爽）
 *
 * @author yanpp
 * @createDateTime 2020/3/18
 **/
public class PrinterServiceConditional {
    public static class InkCondition implements Condition {

        @Override
        public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
            return isInkPrinter(context, metadata);
        }
    }

    public static class LaserCondition implements Condition {

        @Override
        public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
            return isLaserPrinter(context, metadata);
        }
    }

    private static String getPrinter(ConditionContext context, AnnotatedTypeMetadata metadata) {
        String myPrinter = context.getEnvironment().getProperty("my.printer");
        if (!Arrays.asList("ink", "laser").contains(myPrinter)) {
            throw new RuntimeException("Invalid config");
        }
        return myPrinter;
    }

    /**
     * 判断是否喷墨打印机
     * @param context
     * @param metadata
     * @return
     */
    public static boolean isInkPrinter(ConditionContext context, AnnotatedTypeMetadata metadata) {
        return "ink".equals(getPrinter(context, metadata));
    }

    /**
     * 判断是否激光打印机
     * @param context
     * @param metadata
     * @return
     */
    public static boolean isLaserPrinter(ConditionContext context, AnnotatedTypeMetadata metadata) {
        return "laser".equals(getPrinter(context, metadata));
    }

}
