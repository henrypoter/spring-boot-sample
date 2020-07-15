package com.ypp.tunte.sample.redis.lock.core;

import com.ypp.tunte.sample.redis.lock.annotation.NoRepeatSubmitLock;
import com.ypp.tunte.sample.redis.lock.annotation.NoRepeatSubmitLockKey;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.context.expression.MethodBasedEvaluationContext;
import org.springframework.core.DefaultParameterNameDiscoverer;
import org.springframework.core.ParameterNameDiscoverer;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.List;

/**
 * 获取用户定义业务key
 *
 * @author yanpp
 * @createDateTime 2020/3/26
 **/
@Component
public class BusinessKeyProvider {
    private ParameterNameDiscoverer nameDiscoverer = new DefaultParameterNameDiscoverer();
    private ExpressionParser parser = new SpelExpressionParser();

    public String getKeyName(JoinPoint joinPoint, NoRepeatSubmitLock klock) {
        List<String> keyList = new ArrayList<>();
        Method method = getMethod(joinPoint);
        System.out.println(joinPoint.getTarget().getClass().getCanonicalName());
        keyList.add(joinPoint.getTarget().getClass().getCanonicalName());
        keyList.add(method.getName());
        List<String> definitionKeys = getSpelDefinitionKey(klock.keys(), method, joinPoint.getArgs());
        keyList.addAll(definitionKeys);
        List<String> parameterKeys = getParameterKey(method.getParameters(), joinPoint.getArgs());
        keyList.addAll(parameterKeys);
        return StringUtils.collectionToDelimitedString(keyList,"","lock-","");
    }

    private Method getMethod(JoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        if (method.getDeclaringClass().isInterface()) {
            try {
                method = joinPoint.getTarget().getClass().getDeclaredMethod(signature.getName(),
                        method.getParameterTypes());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return method;
    }


    private List<String> getSpelDefinitionKey(String[] definitionKeys, Method method, Object[] parameterValues) {
        List<String> definitionKeyList = new ArrayList<>();
        for (String definitionKey : definitionKeys) {
            if (definitionKey != null && !definitionKey.isEmpty()) {
                EvaluationContext context = new MethodBasedEvaluationContext(null, method, parameterValues, nameDiscoverer);
                String key = parser.parseExpression(definitionKey).getValue(context).toString();
                definitionKeyList.add(key);
            }
        }
        return definitionKeyList;
    }

    private List<String> getParameterKey(Parameter[] parameters, Object[] parameterValues) {
        List<String> parameterKey = new ArrayList<>();
        for (int i = 0; i < parameters.length; i++) {
            if (parameters[i].getAnnotation(NoRepeatSubmitLockKey.class) != null) {
                NoRepeatSubmitLockKey keyAnnotation = parameters[i].getAnnotation(NoRepeatSubmitLockKey.class);
                if (keyAnnotation.value().isEmpty()) {
                    parameterKey.add(parameterValues[i].toString());
                } else {
                    StandardEvaluationContext context = new StandardEvaluationContext(parameterValues[i]);
                    String key = parser.parseExpression(keyAnnotation.value()).getValue(context).toString();
                    parameterKey.add(key);
                }
            }
        }
        return parameterKey;
    }

}
