package com.threads.pool;

import java.util.concurrent.Callable;

public class MyCallTask implements Callable {
    private String name;
    public MyCallTask(String name) {
        this.name = name;
    }

    @Override
    public Object call() throws Exception {
        try {
            Thread.sleep(1000);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("task name "+ name + Thread.currentThread().getName()+" callable execute");
        return 1;
    }
}
