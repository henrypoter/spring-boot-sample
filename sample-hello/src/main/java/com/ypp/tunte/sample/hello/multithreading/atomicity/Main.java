package com.ypp.tunte.sample.hello.multithreading.atomicity;

import lombok.extern.slf4j.Slf4j;

/**
 * 原子性测试
 *
 * @author yanpp
 * @createDateTime 2020/3/12
 **/
@Slf4j
public class Main {

    public static void main(String[] args) throws InterruptedException {
        //test1();
        //synchronizedTest();
        //lockTest();
        atomicTest();
    }

    /**
     * 原始测试存在原子性问题
     */
    public final static void test1(){
         int type = 0;//类型
         int num = 50000;//自增次数
         int sleepTime = 5000;//等待计算时间
         int begin;//开始的值
         Increment increment;

        //不进行原子性保护的大范围操作
        increment = new Increment();
        begin = increment.getCount();
        log.info("Java中普通的自增操作不是原子性操作。");
        log.info("当前运行类：" +increment.getClass().getSimpleName() +  "，count的初始值是：" + increment.getCount());
        for (int i = 0; i < num; i++) {
            new Thread(() -> {
                increment.increment();
            }).start();
        }
//等待足够长的时间，以便所有的线程都能够运行完
        try {
            Thread.sleep(sleepTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("进过" + num + "次自增，count应该 = " + (begin + num) + ",实际count = " + increment.getCount());

    }


    public final static void synchronizedTest() throws InterruptedException {
        int type = 0;//类型
        int num = 50000;//自增次数
        int sleepTime = 5000;//等待计算时间
        int begin;//开始的值
        Increment increment;
        //synchronized关键字能够保证原子性（代码块锁，多线程操作某一对象时，在某个代码块内只能单线程执行）
        increment = new SynchronizedIncrement();
        begin = increment.getCount();
        log.info("可以通过synchronized关键字保障代码的原子性");
        log.info("当前运行类：" +increment.getClass().getSimpleName() +  "，count的初始值是：" + increment.getCount());
        for (int i = 0; i < num; i++) {
            new Thread(() -> {
                increment.increment();
            }).start();
        }
//等待足够长的时间，以便所有的线程都能够运行完
        Thread.sleep(sleepTime);
        log.info("进过" + num + "次自增，count应该 = " + (begin + num) + ",实际count = " + increment.getCount());

    }

    public final static void lockTest() throws InterruptedException {
        int type = 0;//类型
        int num = 50000;//自增次数
        int sleepTime = 5000;//等待计算时间
        int begin;//开始的值
        LockIncrement increment;

        increment = new LockIncrement();
        begin = increment.getCount();
        log.info("可以通过Lock接口保证代码的原子性");
        log.info("当前运行类：" +increment.getClass().getSimpleName() +  "，count的初始值是：" + increment.getCount());
        for (int i = 0; i < num; i++) {
            new Thread(() -> {
                increment.increment();
            }).start();
        }
//等待足够长的时间，以便所有的线程都能够运行完
        Thread.sleep(sleepTime);
        log.info("进过" + num + "次自增，count应该 = " + (begin + num) + ",实际count = " + increment.getCount());

    }

    public final static void atomicTest() throws InterruptedException {
        int type = 0;//类型
        int num = 50000;//自增次数
        int sleepTime = 5000;//等待计算时间
        int begin;//开始的值
        AtomicIncrement increment1 = new AtomicIncrement();
        begin = increment1.getCount().get();
        log.info("可以通过Atomic类型保证变量的原子性");
        log.info("当前运行类：" +increment1.getClass().getSimpleName() +  "，count的初始值是：" + increment1.getCount());
        for (int i = 0; i < num; i++) {
            new Thread(() -> {
                increment1.increment();
            }).start();
        }
//等待足够长的时间，以便所有的线程都能够运行完
        Thread.sleep(sleepTime);
        log.info("进过" + num + "次自增，count应该 = " + (begin + num) + ",实际count = " + increment1.getCount());

    }

}
