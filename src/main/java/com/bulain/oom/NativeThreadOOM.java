package com.bulain.oom;

import java.io.IOException;

public class NativeThreadOOM extends Thread {
    public static void main(String[] args) {

        NativeThreadOOM nativeThreadOOM = new NativeThreadOOM();
        nativeThreadOOM.start();

        try {
            System.out.println("Click any key to exit...");
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
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