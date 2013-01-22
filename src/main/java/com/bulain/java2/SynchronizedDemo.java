package com.bulain.java2;

import java.util.ArrayList;
import java.util.List;

public class SynchronizedDemo {
    private static volatile int count = 0;

    public static void main(String[] args) {
        List<Thread> listThread = new ArrayList<Thread>();
        for (int i = 0; i < 10; i++) {
            Incre incr = new Incre();
            listThread.add(incr);
            incr.start();

            Decre decr = new Decre();
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

        System.out.println("The count number: " + count);

    }

    private static synchronized void increment() {
        count++;
    }

    private static synchronized void decrease() {
        count--;
    }

    static class Incre extends Thread {
        public void run() {
            for (int i = 0; i < 1000; i++) {
                increment();
            }
        }
    }

    static class Decre extends Thread {
        public void run() {
            for (int i = 0; i < 1000; i++) {
                decrease();
            }
        }
    }

}
