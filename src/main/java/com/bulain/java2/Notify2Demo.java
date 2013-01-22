package com.bulain.java2;

import java.util.ArrayList;
import java.util.List;

public class Notify2Demo {
    public static void main(String[] args) {
        List<Thread> listThread = new ArrayList<Thread>();

        BoundedNunmber buffer = new BoundedNunmber();
        for (int i = 0; i < 2; i++) {
            Incre incr = new Incre(buffer);
            listThread.add(incr);
            incr.start();

            Decre decr = new Decre(buffer);
            listThread.add(decr);
            decr.start();
        }

        for (Thread thread : listThread) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
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
        private char[] mutex = new char[0];

        public void increment() {
            synchronized (mutex) {
                System.out.println(Thread.currentThread() + " Incre.enter()");
                while (count >= MAX) {
                    try {
                        System.out.println(Thread.currentThread() + " Incre.wait()");
                        mutex.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println(Thread.currentThread() + " Incre.increment()");
                count++;
                System.out.println(Thread.currentThread() + " Incre.notifyAll()");
                mutex.notifyAll();
            }
        }

        public void decrease() {
            synchronized (mutex) {
                System.out.println(Thread.currentThread() + " Decre.enter()");
                while (count <= MIN) {
                    try {
                        System.out.println(Thread.currentThread() + " Decre.wait()");
                        mutex.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println(Thread.currentThread() + " Decre.decrease()");
                count--;
                System.out.println(Thread.currentThread() + " Decre.notifyAll()");
                mutex.notifyAll();
            }
        }
    }

}
