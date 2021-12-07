package com.threads.redis;

import java.util.*;
import java.util.concurrent.*;
import java.util.function.BiConsumer;

/**
 * @author guojunchao 郭大师
 * @since 2020-08-31
 */
public class CashMap<K, V> {

    private volatile Map<K, V> map = new ConcurrentHashMap<>();

    private volatile Map<K, Long> timeMap = new HashMap<>();

    private TimerTask task;

    private ScheduledExecutorService scheduler;

    public V put(K key,V value) {
        return map.put(key, value);
    }

    public V get(Object key) {
        Long time = timeMap.get(key);
        if (null == time || System.currentTimeMillis() < time) {
        //    System.out.println("size after get"+  map.size());
            return map.get(key);
        }
        System.out.println("size after get"+  map.size());
        return null;
    }

    public synchronized V put(K key, V value, long time) {

        //ThreadPoolExecutor//
        timeMap.put(key, System.currentTimeMillis() + time);
        if (null == scheduler) {
            initTask();
        }
        return map.put(key, value);
    }

    public void forEach(BiConsumer<? super K, ? super V> action) {
        map.forEach(action);
    }

    public void initTask() {

        Runnable runnable = () -> {
            Set<K> set = new HashSet();

            timeMap.forEach(
                    (key,value)->{
                        if (System.currentTimeMillis() > value) {
                            map.remove(key);
                            set.add(key);
                        }
                    }
            );
            if (set.size() > 0) {
                for (K k : set) {
                    timeMap.remove(k);
                }
            }
        };
        scheduler = new ScheduledThreadPoolExecutor(1);
        scheduler.scheduleAtFixedRate(runnable, 0, 1, TimeUnit.SECONDS);
    }

    public int size(){
        return map.size();
    }

}
