package com.bulain.java5;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

public class BlockingQueueDemo implements Runnable {

    public static BlockingQueue<String> queue = new LinkedBlockingQueue<String>(3);
    private int index;

    public BlockingQueueDemo(int i) {
        this.index = i;
    }

    public void run() {
        try {
            queue.put(String.valueOf(this.index));
            System.out.println("[" + this.index + "]进入列队");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String args[]) {
        ExecutorService service = Executors.newCachedThreadPool();
        for (int i = 0; i < 10; i++) {
            service.submit(new BlockingQueueDemo(i));
        }
        Runnable thread = new Runnable() {
            public void run() {
                try {
                    while (true) {
                        Thread.sleep((int) (Math.random() * 1000));
                        if (BlockingQueueDemo.queue.isEmpty()) {
                            break;
                        }
                        String str = BlockingQueueDemo.queue.take();
                        System.out.println("[" + str + "]出列队");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        service.submit(thread);
        service.shutdown();
    }
}