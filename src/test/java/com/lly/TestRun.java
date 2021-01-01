package com.lly;

/**
 * 描述 ：测试线程中的run方法 和 start 方法的区别
 *
 * @author Joker-lly
 * @since 2021-01-01
 */
public class TestRun {
    public static void main(String[] args) {

        /**
         * run() 和 start() 区别：
         * run：会直接在当前线程执行run方法中的代码
         * start：会调用 native 方法 start0 然后创建一个线程 通过 JNI 回调 Thread 中的 run方法
         */

        System.out.println("main 方法开始执行，此时线程名称为："+Thread.currentThread().getName());
        new Thread(() ->{
            try {
                System.out.println("执行线程 lucy Thread Run 方法 此时线程名称为："+Thread.currentThread().getName());
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"lucy").run();


        new Thread(() ->{
            try {
                System.out.println("执行线程 Lisa Thread Run 方法 此时线程名称为："+Thread.currentThread().getName());
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"Lisa").start();
    }
}
