package com;

import com.lly.business.entity.UserEntity;
import com.lly.util.page.R;
import com.threads.pool.MyCallTask;
import sun.nio.ch.ThreadPool;

import javax.swing.text.html.parser.Entity;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.locks.ReentrantLock;

public class Test {
    private volatile UserEntity en2;

    private void  so () {
        UserEntity entity = en2;
        if (1==1) {
            en2 = new UserEntity();
        }

    }
    static volatile boolean flag =false;
    public static void main(String[] args) throws InterruptedException {
        ArrayBlockingQueue blockingQueue = new ArrayBlockingQueue<>(10);
//        ThreadPoolExecutor executor =
//                new ThreadPoolExecutor(5, 2, 1000, TimeUnit.MILLISECONDS);
//
//        executor.submit(new MyCallTask("ss"));
        //Executors.newFixedThreadPool()
        ReentrantLock lock = new ReentrantLock(true);
//        lock.lock();
//        System.out.println("------");
//        lock.unlock();
//
//        UserEntity entity ;

        List<Thread> list = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
           Thread t = new Thread(()->{
               if (Thread.currentThread().isInterrupted()){
                   System.out.println(Thread.currentThread().getName()+"线程被中断1");
                 //  throw new NullPointerException();
               }
                lock.lock();
                System.out.println(Thread.currentThread().getName() + " get lock");
                    while (!flag) {
                        if (flag) {
                            if (Thread.currentThread().isInterrupted()){
                                System.out.println(Thread.currentThread().getName()+"线程已经中断，没有完成任务");
                                //  throw new NullPointerException();
                            }
                            break;
                        }
                    }
               if (Thread.currentThread().isInterrupted()){
                   System.out.println(Thread.currentThread().getName()+"线程已经中断，没有完成任务");
                   //  throw new NullPointerException();
               }
               System.out.println(Thread.currentThread().getName()+ "开始执行任务");
             /*  try {
                   if (Thread.currentThread().isInterrupted()){
                       System.out.println(Thread.currentThread().getName()+"线程已经中断，没有完成任务");
                       //  throw new NullPointerException();
                   }
                   Thread.sleep(2000);
                   System.out.println(Thread.currentThread().getName()+ "开始执行任务");
               } catch (InterruptedException e) {
                   e.printStackTrace();
               }*/
               lock.unlock();

            });
           t.start();
            list.add(t);
        }

        list.get(3).interrupt();
        Thread.sleep(4000);
        list.forEach(thread -> {
            System.out.println(thread.getName()+thread.getState());
        });
        flag=true;
        System.out.println("++++++++++++");
        list.forEach(thread -> {
            System.out.println(thread.getName()+thread.getState());
        });

    }
}
