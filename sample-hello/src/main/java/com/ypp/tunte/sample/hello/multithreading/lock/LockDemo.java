package com.ypp.tunte.sample.hello.multithreading.lock;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 功能说明
 *
 * @author yanpp
 * @createDateTime 2020/3/12
 **/
@Slf4j
public class LockDemo {
    //定义一个非公平的锁
    private static Lock lock = new ReentrantLock(false);
    public static void main(String[] args) throws InterruptedException {

        //线程0一直持有锁5000毫秒
        new Thread(()->{
            log.info("线程[{}]尝试获取锁",Thread.currentThread().getName());
            lock.lock();
            log.info("线程[{}]获取了锁...",Thread.currentThread().getName());
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                lock.unlock();
                log.info("线程[{}]释放了锁..",Thread.currentThread().getName());
            }
        }).start();
        Thread.sleep(10);
        //线程1通过lock.lock()持续去尝试获取锁
        new Thread(()->{
            log.info("线程[{}]通过lock.lock()持续去尝试获取锁",Thread.currentThread().getName());
            lock.lock();
            log.info("线程[{}]获取了锁...",Thread.currentThread().getName());
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                lock.unlock();
                log.info("线程[{}]释放了锁..",Thread.currentThread().getName());
            }

        }).start();

        //线程2通过lock.tryLock()尝试去获取一次锁
        new Thread(()->{
            log.info("线程[{}]通过lock.tryLock()尝试去获取一次锁",Thread.currentThread().getName());
           if(lock.tryLock()){
               log.info("线程[{}]获取了锁...",Thread.currentThread().getName());
               try {
                   Thread.sleep(10);
               } catch (InterruptedException e) {
                   e.printStackTrace();
               }finally {
                   lock.unlock();
                   log.info("线程[{}]释放了锁..",Thread.currentThread().getName());
               }
           } else {
               log.info("线程[{}]尝试获取锁失败，不再等待.",Thread.currentThread().getName());
           }

        }).start();
        //线程3通过lock.tryLock(long,TimeUnit)尝试在一定时间内去获取锁
        new Thread(()->{
            log.info("线程[{}]通过lock.tryLock(long,TimeUnit)尝试在一定时间内去获取锁",Thread.currentThread().getName());
            try {
                if (lock.tryLock(2, TimeUnit.SECONDS)){
                    log.info("线程[{}]获取了锁...",Thread.currentThread().getName());
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }finally {
                        lock.unlock();
                        log.info("线程[{}]释放了锁..",Thread.currentThread().getName());
                    }
                }else {
                    log.info("线程[{}]在指定时间内没有获取到锁，不再等待.",Thread.currentThread().getName());
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
                log.info("线程[{}]被thread.interrupt()中断，不在尝试去获取锁",Thread.currentThread().getName());
            }
        }).start();

        //线程4通过lock.lockInterruptibly()尝试可中断的去获取锁
        Thread thread4=new Thread(()->{

            try {
                log.info("线程[{}]通过lock.lockInterruptibly()尝试可中断的去获取锁",Thread.currentThread().getName());
                lock.lockInterruptibly();
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    lock.unlock();
                    log.info("线程[{}]释放了锁..",Thread.currentThread().getName());
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        thread4.start();
        Thread.sleep(3000);
        thread4.interrupt();

    }
}
