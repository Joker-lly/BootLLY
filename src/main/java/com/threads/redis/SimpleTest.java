package com.threads.redis;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class SimpleTest {
    private static int THREAD_NUMBERS = 1;
    private volatile CashMap<Integer, Integer> cashMap = new CashMap<>();
    public static void main(String[] args) throws InterruptedException {
        SimpleTest simpleTest = new SimpleTest();

        CountDownLatch countDownLatchPut = new CountDownLatch(THREAD_NUMBERS);

        for (int i = 0; i <THREAD_NUMBERS ; i++) {
            int finalI = i;
            new Thread(() ->{
                simpleTest.cashMap.put(finalI, finalI+10,3000);
                countDownLatchPut.countDown();
            }).start();
        }

        countDownLatchPut.await();
        TimeUnit.MILLISECONDS.sleep(2000);

        System.out.println("访问前 CashMap.size " + "时间为 "+ System.currentTimeMillis()  +simpleTest.cashMap.size());

        CountDownLatch countDownLatchGet = new CountDownLatch(THREAD_NUMBERS);
        for (int i = 0; i <THREAD_NUMBERS ; i++) {
            int finalI = i;
            new Thread(() ->{
                Integer result = simpleTest.cashMap.get(finalI);
                if (null != result){
                    System.out.println(Thread.currentThread().getName()+ "时间为 "+ System.currentTimeMillis()  + " the result :" + result);
                }
                countDownLatchGet.countDown();
            }).start();
        }
        countDownLatchGet.await();
        System.out.println("访问后 CashMap.size " + "时间为 "+ System.currentTimeMillis()  +simpleTest.cashMap.size());
    }
}
