package com.bulain.java5;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Notify5Demo {
    public static void main(String[] args) {
        BoundedNunmber buffer = new BoundedNunmber();
        for (int i = 0; i < 2; i++) {
            Incre incr = new Incre(buffer);
            incr.start();

            Decre decr = new Decre(buffer);
            decr.start();
        }

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("The count number: " + buffer.count);

    }

    static class Incre extends Thread {
        private BoundedNunmber buffer;
        public Incre(BoundedNunmber buffer) {
            this.buffer = buffer;
        }
        public void run() {
            for (int i = 0; i < 2; i++) {
                buffer.increment();
            }
        }
    }

    static class Decre extends Thread {
        private BoundedNunmber buffer;
        public Decre(BoundedNunmber buffer) {
            this.buffer = buffer;
        }
        public void run() {
            for (int i = 0; i < 2; i++) {
                buffer.decrease();
            }
        }
    }

    static class BoundedNunmber {
        private static final int MIN = 0;
        private static final int MAX = 1;

        private int count = 0;
        private Lock lock = new ReentrantLock();
        private Condition notFull = lock.newCondition();
        private Condition notEmpty = lock.newCondition();

        public void increment() {
            lock.lock();
            try {
                System.out.println(Thread.currentThread() + " Incre.enter()");
                while (count >= MAX) {
                    try {
                        System.out.println(Thread.currentThread() + " Incre.wait()");
                        notFull.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println(Thread.currentThread() + " Incre.increment()");
                count++;
                System.out.println(Thread.currentThread() + " Incre.notifyAll()");
                notEmpty.signalAll();
            } finally {
                lock.unlock();
            }
        }

        public void decrease() {
            lock.lock();
            try {
                System.out.println(Thread.currentThread() + " Decre.enter()");
                while (count <= MIN) {
                    try {
                        System.out.println(Thread.currentThread() + " Decre.wait()");
                        notEmpty.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println(Thread.currentThread() + " Decre.decrease()");
                count--;
                System.out.println(Thread.currentThread() + " Decre.notifyAll()");
                notFull.signalAll();
            } finally {
                lock.unlock();
            }
        }
    }

}
