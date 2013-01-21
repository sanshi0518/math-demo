package com.bulain.java5;

import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class CompletionServiceDemo {
    public static void main(String args[]) {
        CompletionServiceDemo demo = new CompletionServiceDemo();
        demo.testCompletionService();
    }

    private void testCompletionService() {
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        CompletionService<Task> completionService = new ExecutorCompletionService<Task>(executorService);

        for (int i = 0; i < 10; i++) {
            completionService.submit(new Task(i));
        }

        for (int i = 0; i < 10; i++) {
            try {
                Future<Task> future = completionService.take();
                Task task = future.get();
                System.out.println("Task[" + task.index + "] finished, sleep " + task.timeout + "ms");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }

        executorService.shutdown();

    }

    static class Task implements Callable<Task> {
        int index;
        int timeout;

        public Task(int index) {
            this.index = index;
            timeout = (int) (Math.random() * 1000);
        }
        public Task call() throws Exception {
            Thread.sleep(timeout);
            return this;
        }
    }

}
