package com.bulain.java2;

public class NotifyDemo {
    private static volatile int count = 0;
    private static final int MIN = 0;
    private static final int MAX = 1;

    public static void main(String[] args) {
        for (int i = 0; i < 2; i++) {
            Incre incr = new Incre();
            incr.start();

            Decre decr = new Decre();
            decr.start();
        }

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("The count number: " + count);

    }

    private static synchronized void increment() {
        System.out.println(Thread.currentThread() + " Incre.enter()");
        while (count >= MAX) {
            try {
                System.out.println(Thread.currentThread() + " Incre.wait()");
                NotifyDemo.class.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(Thread.currentThread() + " Incre.increment()");
        count++;
        System.out.println(Thread.currentThread() + " Incre.notifyAll()");
        NotifyDemo.class.notifyAll();
    }

    private static synchronized void decrease() {
        System.out.println(Thread.currentThread() + " Decre.enter()");
        while (count <= MIN) {
            try {
                System.out.println(Thread.currentThread() + " Decre.wait()");
                NotifyDemo.class.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(Thread.currentThread() + " Decre.decrease()");
        count--;
        System.out.println(Thread.currentThread() + " Decre.notifyAll()");
        NotifyDemo.class.notifyAll();
    }

    static class Incre extends Thread {
        public void run() {
            for (int i = 0; i < 2; i++) {
                increment();
            }
        }
    }

    static class Decre extends Thread {
        public void run() {
            for (int i = 0; i < 2; i++) {
                decrease();
            }
        }
    }

}
