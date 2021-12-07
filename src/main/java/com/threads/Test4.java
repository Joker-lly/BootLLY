package com.threads;

import com.lly.util.page.R;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

public class Test4 {

    private static int num = 0;
    static ReentrantLock lock = new ReentrantLock(true);
    public static void main(String[] args) {

        for (int i = 0; i < 3; i++) {
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + " 准备拿锁");
                lock.lock();

                for (int j = 0; j < 10; j++) {
                    num ++;
                }
                System.out.println(Thread.currentThread().getName()+"===");

                lock.unlock();

            }).start();
        }
    }
}
