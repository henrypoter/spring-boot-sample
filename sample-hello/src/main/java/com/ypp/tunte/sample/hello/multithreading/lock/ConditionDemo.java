package com.ypp.tunte.sample.hello.multithreading.lock;

import lombok.extern.slf4j.Slf4j;

import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * <p>Lock接口-Condition学习-可中断锁、可定时锁</p>
 *
 * @author yanpp
 * @createDateTime 2020/3/12
 **/
@Slf4j
public class ConditionDemo {
    //定义一个非公平的锁
    private static Lock lock = new ReentrantLock(false);

    public static void main(String[] args) throws InterruptedException {
        Condition condition = lock.newCondition();
        //线程0：通过await()进行等待，直到被中断或者被唤醒
        Thread thread0 = new Thread(()->{
            log.info("线程[await()-{}]尝试获取锁...",Thread.currentThread().getName());
            lock.lock();
            log.info("线程[await()-{}]获取了锁.",Thread.currentThread().getName());
            try {
                //通过await()进行等待，直到被中断或者被唤醒
                condition.await();
                log.info("线程[await()-{}]被唤醒...",Thread.currentThread().getName());
            } catch (InterruptedException e) {
                e.printStackTrace();
                log.info("线程[await()-{}]被中断...",Thread.currentThread().getName());
            }finally {
                lock.unlock();
                log.info("线程[await()-{}]释放了锁.",Thread.currentThread().getName());
            }
        });

        //线程1：通过awaitNanos(long)进行等待，直到被中断、被唤醒、或时间超时
        Thread thread1 = new Thread(()->{
            log.info("线程[awaitNanos(long)-{}]尝试获取锁...",Thread.currentThread().getName());
            lock.lock();
            log.info("线程[awaitNanos(long)-{}]获取了锁.",Thread.currentThread().getName());
            //通过awaitNanos(long)进行等待，直到被中断、被唤醒或时间用尽
            //剩余等待时间
            try {
                long remainTime  = condition.awaitNanos(1000000000);
                //如果剩余时间大于0，则表明此线程是被唤醒的
                if (remainTime>0){
                    log.info("线程[awaitNanos(long)-{}]被唤醒...",Thread.currentThread().getName());
                }else {
                    //如果剩余时间小于等于0，则表明此线程是因为等待时间耗尽而停止等待的
                    log.info("线程[awaitNanos(long)-{}]等待时间用尽，停止等待...",Thread.currentThread().getName());
                }
            } catch (InterruptedException e) {
                log.info("线程[awaitNanos(long)-{}]被中断...",Thread.currentThread().getName());
            }finally {
                lock.unlock();
                log.info("线程[awaitNanos(long)-{}]释放了锁.",Thread.currentThread().getName());
            }
        });

        //线程2：通过await(long,TimeUnit)进行等待，直到被中断、被唤醒、或时间超时
        Thread thread2 = new Thread(()->{
            log.info("线程[await(long,TimeUnit)-{}]尝试获取锁...",Thread.currentThread().getName());
            lock.lock();
            log.info("线程[await(long,TimeUnit)-{}]获取了锁.",Thread.currentThread().getName());
            //通过awaitNanos(long)进行等待，直到被中断、被唤醒或时间用尽
            //返回true则表明是被唤醒的，false表明是时间用尽
            try {
                boolean result =condition.await(2, TimeUnit.SECONDS);
                if (result){
                    log.info("线程[await(long,TimeUnit)-{}]被唤醒....",Thread.currentThread().getName());
                }else {
                    log.info("线程[await(long,TimeUnit)-{}]等待时间用尽，停止等待...",Thread.currentThread().getName());
                }
            } catch (InterruptedException e) {
                log.info("线程[await(long,TimeUnit)-{}]被中断",Thread.currentThread().getName());
            }finally {
                lock.unlock();
                log.info("线程[await(long,TimeUnit)-{}]释放了锁.",Thread.currentThread().getName());
            }
        });

        //线程3：通过awaitUntil(deadline)进行等待，直到被中断、被唤醒、或时间到期
        Thread thread3 = new Thread(()->{
            log.info("线程[awaitUntil(deadline)-{}]尝试获取锁...",Thread.currentThread().getName());
            lock.lock();
            log.info("线程[awaitUntil(deadline)-{}]获取了锁.",Thread.currentThread().getName());

            try {
                Date deadline = new Date(System.currentTimeMillis() + 5000);//5秒之后
                //通过awaitUntil(deadline)进行等待，直到被中断、被唤醒或到达截止时间
                //返回true则表明是被唤醒的，false表明是时间用尽
                boolean result =  condition.awaitUntil(deadline);
                if (result){
                    log.info("线程[awaitUntil(deadline)-{}]被唤醒...",Thread.currentThread().getName());
                }else {
                    log.info("线程[awaitUntil(deadline)-{}]到达截止时间，停止等待...",Thread.currentThread().getName());
                }


            } catch (InterruptedException e) {
                //e.printStackTrace();
                log.info("线程[awaitUntil(deadline)-{}]被中断...",Thread.currentThread().getName());
            }finally {
                lock.unlock();
                log.info("线程[awaitUntil(deadline)-{}]释放了锁.",Thread.currentThread().getName());
            }

        });

        //线程4：通过awaitUninterruptibly()进行等待，直到被唤醒
        Thread thread4 = new Thread(()->{
            log.info("线程[awaitUninterruptibly()-{}]尝试获取锁...",Thread.currentThread().getName());
            lock.lock();
            log.info("线程[awaitUninterruptibly()-{}]获取了锁...",Thread.currentThread().getName());
            try {
                //通过awaitUninterruptibly()进行等待，直到被唤醒
                condition.awaitUninterruptibly();
                log.info("线程[awaitUninterruptibly()-{}]被唤醒...",Thread.currentThread().getName());
            }finally {
                lock.unlock();
                log.info("线程[awaitUninterruptibly()-{}]释放了锁.",Thread.currentThread().getName());
            }

        });

        thread0.start();
        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();

        /**
         * 0 等待线程自己结束
         * 1 通过中断结束线程
         * 2 通过condition.signalAll()唤醒所有线程
         */
        int type = 2;
        switch (type){
            case 0:
                //让能自己结束的自己结束
                Thread.sleep(100);
                log.info("======================等待线程自己结束");
                break;
            case 1:
                //尝试中断线程
                Thread.sleep(100);
                log.info("======================尝试中断线程");
                thread0.interrupt();
                thread1.interrupt();
                thread2.interrupt();
                thread3.interrupt();
                thread4.interrupt();
                break;
            case 2:
                Thread.sleep(100);
                log.info("======================开始唤醒所有还在等待的线程");
                //在main线程中，通过condition.signalAll()唤醒所有
                log.info("线程[condition.signalAll()-{}]尝试获取锁...",Thread.currentThread().getName());
                lock.lock();
                log.info("线程[condition.signalAll()-{}]获取了锁,并唤醒所有等待的线程...",Thread.currentThread().getName());
                try {
                    condition.signalAll();
                }finally {
                    lock.unlock();
                    log.info("线程[condition.signalAll()-{}]释放了锁.",Thread.currentThread().getName());
                }
                break;
                default:
                    break;
        }

    }
}
