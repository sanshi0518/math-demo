package com.bulain.oom;

public class NativeThreadOOM extends Thread {
    public static void main(String[] args) {

        NativeThreadOOM nativeThreadOOM = new NativeThreadOOM();
        nativeThreadOOM.start();

    }

    public void run() {
        while (true) {
            WorkThread thread = new WorkThread();
            try {
                thread.start();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    static class WorkThread extends Thread {
        public void run() {
            System.out.println("Starting " + this);
            while (true) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                }
            }
        }
    }
}