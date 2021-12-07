package com.threads;

import com.sun.org.apache.xpath.internal.operations.String;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;

public class Test3 {
    public static void main(String[] args) {
        ReentrantLock lock = new ReentrantLock();

        Condition condition = lock.newCondition();
        Condition condition1 = lock.newCondition();

       // Test2 test2 = new Test2();

    }
}
