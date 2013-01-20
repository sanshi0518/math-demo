package com.bulain.java2;

public class NotifyDemo {
    public static void main(String[] args) {
        BoundedBuffer buffer = new BoundedBuffer();
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
        private BoundedBuffer buffer;
        public Incre(BoundedBuffer buffer) {
            this.buffer = buffer;
        }
        public void run() {
            for (int i = 0; i < 2; i++) {
                buffer.increment();
            }
        }
    }

    static class Decre extends Thread {
        private BoundedBuffer buffer;
        public Decre(BoundedBuffer buffer) {
            this.buffer = buffer;
        }
        public void run() {
            for (int i = 0; i < 2; i++) {
                buffer.decrease();
            }
        }
    }

    static class BoundedBuffer {
        private static final int MIN = 0;
        private static final int MAX = 1;

        private int count = 0;

        public synchronized void increment() {
            System.out.println(Thread.currentThread() + " Incre.enter()");
            while (count >= MAX) {
                try {
                    System.out.println(Thread.currentThread() + " Incre.wait()");
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println(Thread.currentThread() + " Incre.increment()");
            count++;
            System.out.println(Thread.currentThread() + " Incre.notifyAll()");
            this.notifyAll();
        }

        public synchronized void decrease() {
            System.out.println(Thread.currentThread() + " Decre.enter()");
            while (count <= MIN) {
                try {
                    System.out.println(Thread.currentThread() + " Decre.wait()");
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println(Thread.currentThread() + " Decre.decrease()");
            count--;
            System.out.println(Thread.currentThread() + " Decre.notifyAll()");
            this.notifyAll();
        }
    }

}
