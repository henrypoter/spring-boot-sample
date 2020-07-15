package com.ypp.tunte.sample.hello.multithreading.visibility;

/**
 * 功能说明
 *
 * @author yanpp
 * @createDateTime 2020/3/12
 **/
public class Main {
    private static volatile boolean stop;
    public static void main(String[] args) throws InterruptedException {
        common();
    }

    public static void common() throws InterruptedException {

        new Thread(() -> {
            System.out.println("Ordinary A is running...");
            while (!stop) {
                ;
            }
            System.out.println("Ordinary A is terminated.");
        }).start();
        Thread.sleep(10);
        new Thread(() -> {
            System.out.println("Ordinary B is running...");
            stop = true;
            System.out.println("Ordinary B is terminated.");
        }).start();

    }
}
