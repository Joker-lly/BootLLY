package com.threads;

import java.util.Date;
import java.util.concurrent.Semaphore;

public class Test2 {
public void main(String[] args) {
    Semaphore semaphore = new Semaphore(3);

    for (int i = 0; i < 20; i++) {
       Thread thread= new Thread(() -> {
            try {
                semaphore.acquire();
                System.out.println(Thread.currentThread().getName() + "开始执行 时间"+ System.currentTimeMillis());
                Thread.sleep(1000);
                System.out.println(Thread.currentThread().getName() + "执行结束");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                semaphore.release();
            }
        });
    }
}
}


