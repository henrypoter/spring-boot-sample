package com.ypp.tunte.sample.jasypt.alipay.vo;

/**
 * 功能说明
 *
 * @author yanpp
 * @createDateTime 2020/3/4
 **/

public class AlipayVo {

    private String out_trade_no;
    private String total_amount;
    private String subject;
    private String product_code;


    public void setOut_trade_no(String out_trade_no) {
        this.out_trade_no = out_trade_no;
    }

    public String getOut_trade_no() {
        return out_trade_no;
    }

    public void setTotal_amount(String total_amount) {
        this.total_amount = total_amount;
    }

    public String getTotal_amount() {
        return total_amount;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getSubject() {
        return subject;
    }

    public void setProduct_code(String product_code) {
        this.product_code = product_code;
    }

    public String getProduct_code() {
        return product_code;
    }
}
