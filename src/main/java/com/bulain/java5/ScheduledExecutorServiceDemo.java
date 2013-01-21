package com.bulain.java5;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class ScheduledExecutorServiceDemo {
    public static void main(String args[]) {
        ScheduledExecutorServiceDemo demo = new ScheduledExecutorServiceDemo();
        demo.testScheduledExecutorService();
    }

    private void testScheduledExecutorService() {
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(2);

        List<ScheduledFuture<Task>> listScheduledFuture = new ArrayList<ScheduledFuture<Task>>();
        for (int i = 0; i < 10; i++) {
            ScheduledFuture<Task> futrue = scheduledExecutorService.schedule(new Task(i), 1000, TimeUnit.MILLISECONDS);
            listScheduledFuture.add(futrue);
        }

        for (ScheduledFuture<Task> futrue : listScheduledFuture) {
            try {
                Task task = futrue.get();
                System.out.println("Task[" + task.index + "] finished, sleep " + task.timeout + "ms, Start at "
                        + task.dataStart + ", End at " + task.dateEnd);;
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }

        scheduledExecutorService.shutdown();

    }

    static class Task implements Callable<Task> {
        int index;
        int timeout;
        long dataStart;
        long dateEnd;

        public Task(int index) {
            this.index = index;
            timeout = (int) (Math.random() * 1000);
            dataStart = new Date().getTime();
        }
        public Task call() throws Exception {
            Thread.sleep(timeout);
            dateEnd = new Date().getTime();
            return this;
        }
    }

}
