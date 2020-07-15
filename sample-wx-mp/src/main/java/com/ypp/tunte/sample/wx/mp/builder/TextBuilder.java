package com.ypp.tunte.sample.wx.mp.builder;

import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutTextMessage;

/**
 * 功能说明
 *
 * @author yanpp
 * @createDateTime 2020/3/12
 **/
public class TextBuilder extends AbstractBuilder {
    @Override
    public WxMpXmlOutMessage build(String content, WxMpXmlMessage wxMessage,
                                   WxMpService service) {
        WxMpXmlOutTextMessage m = WxMpXmlOutMessage.TEXT().content(content)
                .fromUser(wxMessage.getToUser()).toUser(wxMessage.getFromUser())
                .build();
        return m;
    }
}
