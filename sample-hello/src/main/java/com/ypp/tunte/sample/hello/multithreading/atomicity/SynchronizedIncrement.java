package com.ypp.tunte.sample.hello.multithreading.atomicity;

/**
 * <p>原子性示例：通过synchronized保证代码块的原子性</p>
 *
 * @author yanpp
 * @createDateTime 2020/3/12
 **/
public class SynchronizedIncrement extends Increment {

    /**
     * <p>添加关键字synchronized，使之成为同步方法</p>
     */
    @Override
    public synchronized  void increment() {
        count++;
    }
}
