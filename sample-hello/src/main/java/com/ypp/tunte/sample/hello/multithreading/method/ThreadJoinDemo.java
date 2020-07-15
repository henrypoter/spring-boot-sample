package com.ypp.tunte.sample.hello.multithreading.method;

import lombok.extern.slf4j.Slf4j;

/**
 * java线程基本方法：join
 *
 * @author yanpp
 * @createDateTime 2020/3/11
 **/
@Slf4j
public class ThreadJoinDemo {

    //线程间的共享资源
    private static String config = "配置未被初始化.";

    public static void main(String[] args) throws InterruptedException {
        //unJoin();
        addJoin();
    }

    public static void unJoin(){

        log.info("==========不使用t.join()方法==========");
        new Thread(()->{
            log.info("[线程1]开始运行...");
            Thread otherThread =new Thread(()->{
                log.info("[线程2]开始运行...");
                try {
                    Thread.sleep(200);
                    config = "配置已被[线程2]初始化.";
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                log.info("[线程2]运行结束");
            });

            //启动线程2
            otherThread.start();

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            log.info("[线程1]获取的配置为：{}",config);
            log.info("[线程1]运行结束");
        }).start();

    }

    public static void addJoin() throws InterruptedException {
        Thread.sleep(1000);
        log.info("==========使用t.join()方法==========");
        new Thread(()->{
            log.info("[线程11]开始运行...");
            Thread otherThread=new Thread(()->{
                log.info("[线程22]开始运行...");
                try {
                    Thread.sleep(500);
                    config = "配置已被[线程22]初始化.";
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                log.info("[线程22]运行结束");
            });
            //启动线程22
            otherThread.start();
            try {
                //在线程11中，加入线程22-->需要等到线程22结束之后才能结束线程11
                otherThread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            log.info("[线程11]等待[线程22]结束");

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            log.info("[线程11]获取的配置为：" + config);
            log.info("[线程11]运行结束");

        }).start();
    }

}
