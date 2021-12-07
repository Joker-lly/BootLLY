package com.lly;

import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Test {

    public static void main(String[] args) throws InterruptedException {
        AtomicInteger atomicInteger = new AtomicInteger();

        ReentrantLock lock = new ReentrantLock();
        

        Condition condition1 = lock.newCondition();

        Condition condition2 = lock.newCondition();

        new Thread(() ->{
            lock.lock();

            System.out.println( Thread.currentThread().getName() + "即将进入休息状态");
            try {
                condition1.await();

                System.out.println(Thread.currentThread().getName() + "休息结束");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            lock.unlock();
        },"lisa").start();

        new Thread(() ->{
            System.out.println( Thread.currentThread().getName() + "即将进入休息状态");
            lock.lock();

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            condition1.signal();


            try {
                condition2.await();

                System.out.println(Thread.currentThread().getName() + "休息结束");
            } catch (InterruptedException e) {
                System.out.println(Thread.currentThread().getName() + "发生异常");
                e.printStackTrace();
            }

            lock.unlock();
        },"baby").start();

        Thread.sleep(10000);



        System.out.println("============");

        condition2.signal();



    }
}
