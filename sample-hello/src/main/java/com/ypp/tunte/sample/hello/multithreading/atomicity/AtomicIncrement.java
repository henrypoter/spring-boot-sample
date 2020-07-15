package com.ypp.tunte.sample.hello.multithreading.atomicity;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 功能说明
 *
 * @author yanpp
 * @createDateTime 2020/3/12
 **/
public class AtomicIncrement {
    private AtomicInteger count = new AtomicInteger(1);

    /**
     * <p>无需其他处理，直接自增即可</p>
     *
     **/
    public void increment() {
        count.getAndIncrement();
    }

    public AtomicInteger getCount() {
        return count;
    }

}
