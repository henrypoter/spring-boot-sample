package com.ypp.tunte.sample.hello.controller;

import com.ypp.tunte.sample.hello.spring.conditional.PrinterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 功能说明
 *
 * @author yanpp
 * @createDateTime 2020/3/18
 **/
@RestController
public class PrinterController {
    /**
     * 这里红色提示找不到bean，原因是用了@Conditional，导致 IDEA 无法识别到有注入
     */
    @Autowired
    private PrinterService printerService;

    @GetMapping("/print")
    public String test() {
        return printerService.print("hello world");
    }
}
