package com.ypp.tunte.sample.jasypt.alipay.controller;

import com.alibaba.fastjson.JSON;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.ypp.tunte.sample.jasypt.alipay.vo.AlipayVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.UUID;

/**
 * 功能说明
 *
 * @author yanpp
 * @createDateTime 2020/3/4
 **/
@RestController
@RequestMapping("/alipay")
@Slf4j
public class PayController {

    @Value("${ALIPAY.SERVER}")
    private String gatewayUrL;

    @Value("${ALIPAY.APPID}")
    private String app_id;

    @Value("${ALIPAY.PRIVATEKEY}")
    private String merchant_private_key;

    @Value("${ALIPAY.PUBLIC}")
    private String alipay_public_key;

    @Value("${ALIPAY.SIGN}")
    private String sign_type;

    @Value("${ALIPAY.RETURNA_URL}")
    private String return_url;

    @Value("${ALIPAY.NOTIFY_URL}")
    private String notify_url;

    private String charset = "utf-8";

    @GetMapping("pay")
    private String alipayPay() throws AlipayApiException {

        AlipayVo vo = new AlipayVo();
        //这里模拟了一个订单的id
        vo.setOut_trade_no(UUID.randomUUID().toString().replace("-", ""));
        vo.setTotal_amount("0.01");
        vo.setSubject("测试付款商品A");
        //这个是固定的，沙箱默认就用这个参数
        vo.setProduct_code("FAST_INSTANT_TRADE_PAY");
        String json = JSON.toJSONString(vo);
        log.info("发起支付传参："+json);
        AlipayClient alipayClient = new DefaultAlipayClient(gatewayUrL, app_id,
                merchant_private_key, "json", charset, alipay_public_key, sign_type);
        // 设置请求参数
        AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
        alipayRequest.setReturnUrl(return_url);
        alipayRequest.setNotifyUrl(notify_url);
        alipayRequest.setBizContent(json);
        String result = null;
        try {
            result = alipayClient.pageExecute(alipayRequest).getBody();
        } catch (Exception e) {
            log.info("payerror" + result);
        }

        return result;
    }

    /**
     * @Description: 支付宝回调接口
	 * @param out_trade_no 商户订单号
	 * @param trade_no     支付宝交易凭证号
	 * @param trade_status 交易状态
	 * @return String
	 * @throws AlipayApiException
	 */
    @PostMapping("notify")
    private String alipayNotify(HttpServletRequest request, String out_trade_no, String trade_no, String trade_status)
            throws AlipayApiException {
        Map<String, String> map = new HashMap<String, String>();
        Map<String, String[]> requestParams = request.getParameterMap();
        for (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext(); ) {
            String name = iter.next();
            String[] values = requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i] : valueStr + values[i] + ",";
            }
            map.put(name, valueStr);
        }
        boolean signVerified = false;
        try {
            signVerified = AlipaySignature.rsaCheckV1(map, alipay_public_key, charset, sign_type);
        } catch (com.alipay.api.AlipayApiException e) {
            log.info("notify 验证失败",e);
            // 验签发生异常,则直接返回失败
            return ("--failed--");
        }
        if (signVerified) {
            //处理你的业务逻辑，更细订单状态等
            return ("--success--");
        } else {
            log.info("验证失败,不去更新状态");
            return ("--failed--");
        }
    }
    /**
     * @Description: 支付宝回调接口
     * @param out_trade_no 商户订单号
     * @param trade_no     支付宝交易凭证号
     * @return String
     * @throws AlipayApiException
     */
    @GetMapping("return")
    private String alipayReturn(Map<String, String> params, HttpServletRequest request, String out_trade_no, String trade_no, String total_amount)
            throws AlipayApiException {
        Map<String, String> map = new HashMap<String, String>();
        Map<String, String[]> requestParams = request.getParameterMap();
        for (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext(); ) {
            String name = iter.next();
            String[] values = requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i] : valueStr + values[i] + ",";
                System.out.println(valueStr);
            }
            map.put(name, valueStr);
        }
        boolean signVerified = false;
        try {
            signVerified = AlipaySignature.rsaCheckV1(map, alipay_public_key, charset, sign_type);
        } catch (AlipayApiException e) {
            log.info("支付宝回调异常", e);
            // 验签发生异常,则直接返回失败
            return ("--fail--");
        }
        if (signVerified) {
            return ("--success--");
        } else {
            return ("fail");
        }
    }


}
