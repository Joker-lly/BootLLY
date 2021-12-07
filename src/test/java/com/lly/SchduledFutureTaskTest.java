package com.lly;

public class SchduledFutureTaskTest implements Runnable{
    @Override
    public void run() {
        System.out.println("执行定时任务");
    }
}
