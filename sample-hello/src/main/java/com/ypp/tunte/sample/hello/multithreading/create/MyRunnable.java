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
public class MyRunnable implements Runnable {



    private String name;
    public MyRunnable(String name){
        this.name = name;
    }

    @Override
    public void run() {
        long sleepMs = RandomUtil.randomLong(1000,9000);
        System.out.println(String.format("%s进在运行...",this.name));
        try {
            Thread.sleep(sleepMs);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            log.info("{}线程运行结束!",this.name);
        }
    }

    public static void main(String[] args) {
        for (int i=0 ; i<100;i++){
            new Thread(new MyRunnable("thread-"+i)).start();
        }
    }
}
