package com.ypp.tunte.sample.hello.spring.conditional.impl;

import com.ypp.tunte.sample.hello.spring.conditional.PrinterService;
import com.ypp.tunte.sample.hello.spring.conditional.PrinterServiceConditional;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Conditional;
import org.springframework.stereotype.Service;

/**
 * 功能说明
 *
 * @author yanpp
 * @createDateTime 2020/3/18
 **/
@Service
//@Conditional(PrinterServiceConditional.LaserCondition.class)
@ConditionalOnProperty(value = "my.printer", havingValue = "laser",matchIfMissing = true)
public class LaserPrinterServiceImpl implements PrinterService {
    @Override
    public String print(String content) {
        return "激光打印机打印：" + content;
    }
}
