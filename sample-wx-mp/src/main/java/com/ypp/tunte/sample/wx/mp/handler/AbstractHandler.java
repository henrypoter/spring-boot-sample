package com.ypp.tunte.sample.wx.mp.handler;

import me.chanjar.weixin.mp.api.WxMpMessageHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 功能说明
 *
 * @author yanpp
 * @createDateTime 2020/3/12
 **/
public abstract class AbstractHandler implements WxMpMessageHandler {
    protected Logger logger = LoggerFactory.getLogger(getClass());
}
