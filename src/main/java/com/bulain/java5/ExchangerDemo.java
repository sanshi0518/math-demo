package com.bulain.java5;

import java.util.concurrent.Exchanger;

public class ExchangerDemo {

    public static void main(String args[]) {
        Exchanger<String> exgr = new Exchanger<String>();
        new TakeTask(exgr).start();
        new PutTask(exgr).start();
    }

    static class PutTask extends Thread {
        Exchanger<String> ex;
        String str;

        PutTask(Exchanger<String> c) {
            ex = c;
            str = new String();
        }

        public void run() {
            char ch = 'A';
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 4; j++) {
                    str += (char) ch++;
                }

                try {
                    str = ex.exchange(str);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    static class TakeTask extends Thread {
        Exchanger<String> ex;
        String str;

        TakeTask(Exchanger<String> c) {
            ex = c;
        }

        public void run() {
            for (int i = 0; i < 3; i++) {
                try {
                    str = ex.exchange(new String());
                    System.out.println("Take: " + str);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
