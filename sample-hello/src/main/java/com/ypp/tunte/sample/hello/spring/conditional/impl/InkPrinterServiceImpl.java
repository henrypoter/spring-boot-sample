package com.ypp.tunte.sample.hello.spring.conditional.impl;

import com.ypp.tunte.sample.hello.spring.conditional.PrinterService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

/**
 * 功能说明
 *
 * @author yanpp
 * @createDateTime 2020/3/18
 **/
@Service
//@Conditional(PrinterServiceConditional.InkCondition.class)
@ConditionalOnProperty(value = "my.printer", havingValue = "ink")
public class InkPrinterServiceImpl implements PrinterService {
    @Override
    public String print(String content) {
        return "喷墨打印机打印：" + content;
    }
}
