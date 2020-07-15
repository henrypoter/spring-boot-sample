package com.ypp.tunte.sample.hello.multithreading.atomicity;

/**
 * <p>原子性示例：不是原子性</p>
 *
 * @author yanpp
 * @createDateTime 2020/3/12
 **/
public class Increment {
    public int count = 1;
    public void increment(){
        count++;
    }
    public int getCount(){
        return this.count;
    }
}
