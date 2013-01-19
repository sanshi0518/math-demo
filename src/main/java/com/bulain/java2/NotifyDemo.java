package com.bulain.java2;

public class NotifyDemo {
    private static volatile int count = 0;
    private static int MIN = 0;
    private static int MAX = 10;

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
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
        if (count >= MAX) {
            try {
                NotifyDemo.class.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        count++;
        NotifyDemo.class.notifyAll();
    }

    private static synchronized void decrease() {
        if (count <= MIN) {
            try {
                NotifyDemo.class.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        count--;
        NotifyDemo.class.notifyAll();
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
