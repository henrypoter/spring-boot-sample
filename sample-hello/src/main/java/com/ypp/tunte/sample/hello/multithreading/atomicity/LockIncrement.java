package com.ypp.tunte.sample.hello.multithreading.atomicity;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * <p>原子性示例：通过Lock接口保证指定范围代码的原子性</p>
 *
 * @author yanpp
 * @createDateTime 2020/3/12
 **/
public class LockIncrement  {
    //定义个读写锁：锁内运行多线程读，单线程写
    private  final ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock(true);
    private int count = 1;
    public void increment() {
        //写锁 加锁
        readWriteLock.writeLock().lock();
        try{
            count++;
        }finally {
            //将解锁放在finally块中，保证必然执行，防止死锁
            readWriteLock.writeLock().unlock();
        }
    }

    public int getCount() {
        return count;
    }
}
