package com.threads.pool;

import java.util.Queue;
import java.util.concurrent.*;

/**
 * 线程池测试
 */
public class Test {
    public static void main(String[] args) {
        ThreadPoolExecutor threadPoolExecutor =
                new ThreadPoolExecutor(2, 5, 10000L, TimeUnit.MILLISECONDS, new ArrayBlockingQueue<>(10));
        threadPoolExecutor.submit(new MyCallTask("num1"));
        threadPoolExecutor.submit(new MyCallTask("num2"));
        threadPoolExecutor.submit(new MyCallTask("num3"));

        Future submit = threadPoolExecutor.submit(new MyCallTask("num4"));
        try {
            Object o = submit.get();
            System.out.println((Integer)o);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        threadPoolExecutor.shutdown();

    }
}
