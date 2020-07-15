package com.ypp.tunte.sample.hello.multithreading.create;

import cn.hutool.core.util.RandomUtil;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

/**
 * <p>自定义线程03：实现Callable接口</p>
 *
 * @author yanpp
 * @createDateTime 2020/3/11
 **/

@Slf4j
public class MyCallableImpl implements Callable<Integer> {
    @Override
    public Integer call() throws Exception {
        int sleepMs = RandomUtil.randomInt(1000,9000);
        Thread.sleep(sleepMs);
        return sleepMs;
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //Future、Callable一般与Executor结合使用
        //Executor负责创建线程池服务
        //实现Callable接口形成的线程类，负责处理业务逻辑，并将处理结果返回
        //Future接口负责接收Callable接口返回的值
        ExecutorService executor = Executors.newFixedThreadPool(5);
        try {
            //定义一组返回值
            Future<Integer>[] futures = new Future[5];
            //向线程池提交任务
            for (int i = 0; i < 5; i++) {
                //注意Future的参数化类型要与Callable的参数化类型一致
                futures[i] = executor.submit(new MyCallableImpl());
            }
            //输出执行结果
            for (int i = 0; i < 5; i++) {
                log.info(String.valueOf(futures[i].get()));
            }
        } finally {//将关闭线程池放在finally中，最大限度保证线程安全
            //记得关闭这个线程池
            executor.shutdown();
        }

        log.info("end!");

    }

}
