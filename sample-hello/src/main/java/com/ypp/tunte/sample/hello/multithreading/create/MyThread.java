package com.ypp.tunte.sample.hello.multithreading.create;

import cn.hutool.core.util.RandomUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * 功能说明
 *
 * @author yanpp
 * @createDateTime 2020/3/11
 **/
@Slf4j
public class MyThread extends Thread {
    public MyThread(String name) {
        super(name);
    }

    @Override
    public void run() {
        long sleepMs = RandomUtil.randomLong(1000,9000);
        log.info("线程【{}】正在运行,预计运行{}...",this.getName(),sleepMs);
        try {
            sleep(sleepMs);
        } catch (InterruptedException e) {
           log.error("线程【{}】出错啦;错误:{}",this.getName(),e.getMessage());
        }finally {
            log.info("线程【{}】运行结束",this.getName());
        }
    }

    public static void main(String[] args) {
        for (int i=0;i<10;i++){
            new MyThread("MyThread-"+i).start();
        }
    }



}
