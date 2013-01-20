package com.bulain.java5;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConditionDemo {
    public static void main(String args[]) {
        final BoundedBuffer buffer = new BoundedBuffer();

        ExecutorService putService = Executors.newCachedThreadPool();
        for (int i = 0; i < 5; i++) {
            final int index = i;
            putService.submit(new Thread() {
                public void run() {
                    try {
                        for (int j = 0; j < 5; j++) {
                            int put = 10 * index + j;
                            Thread.sleep((int) (Math.random() * 1000));
                            buffer.put(Integer.toString(put));
                            System.out.println(Thread.currentThread() + " PUT[" + index + "] Put: " + put);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        }
        putService.shutdown();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ExecutorService taekService = Executors.newCachedThreadPool();
        for (int i = 0; i < 5; i++) {
            final int index = i;
            taekService.submit(new Thread() {
                public void run() {
                    try {
                        for (int j = 0; j < 5; j++) {
                            Thread.sleep((int) (Math.random() * 1000));
                            Object take = buffer.take();
                            System.out.println(Thread.currentThread() + " TAKE[" + index + "] Take: " + take);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        }
        taekService.shutdown();
    }

    static class BoundedBuffer {
        final Lock lock = new ReentrantLock();//锁对象  
        final Condition notFull = lock.newCondition();//写线程条件   
        final Condition notEmpty = lock.newCondition();//读线程条件   

        final Object[] items = new Object[3];//缓存队列  
        int putptr/*写索引*/, takeptr/*读索引*/, count/*队列中存在的数据个数*/;

        public void put(Object x) throws InterruptedException {
            lock.lock();
            try {
                while (count == items.length)
                    //如果队列满了   
                    notFull.await();//阻塞写线程  
                items[putptr] = x;//赋值   
                if (++putptr == items.length)
                    putptr = 0;//如果写索引写到队列的最后一个位置了，那么置为0  
                ++count;//个数++  
                notEmpty.signalAll();//唤醒读线程  
            } finally {
                lock.unlock();
            }
        }

        public Object take() throws InterruptedException {
            lock.lock();
            try {
                while (count == 0)
                    //如果队列为空  
                    notEmpty.await();//阻塞读线程  
                Object x = items[takeptr];//取值   
                if (++takeptr == items.length)
                    takeptr = 0;//如果读索引读到队列的最后一个位置了，那么置为0  
                --count;//个数--  
                notFull.signalAll();//唤醒写线程  
                return x;
            } finally {
                lock.unlock();
            }
        }
    }

}
