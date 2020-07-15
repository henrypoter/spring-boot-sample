package com.ypp.tunte.sample.jasypt.ijpay.interceptor;

import com.ijpay.wxpay.WxPayApiConfigKit;
import com.ypp.tunte.sample.jasypt.ijpay.controller.wxpay.AbstractWxPayApiController;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 功能说明
 *
 * @author yanpp
 * @createDateTime 2020/3/4
 **/
public class WxPayInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object handler) {
        if (HandlerMethod.class.equals(handler.getClass())) {
            HandlerMethod method = (HandlerMethod) handler;
            Object controller = method.getBean();
            if (!(controller instanceof AbstractWxPayApiController)) {
                throw new RuntimeException("控制器需要继承 AliPayApiController");
            }
            WxPayApiConfigKit.setThreadLocalWxPayApiConfig(((AbstractWxPayApiController) controller).getApiConfig());
            return true;
        }
        return false;
    }
}