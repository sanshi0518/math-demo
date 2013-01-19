package com.bulain.oom;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

public class NativeOOM extends Thread {
    public static void main(String[] args) {
        NativeOOM nativeOOM = new NativeOOM();
        nativeOOM.start();

        try {
            System.out.println("Click any key to exit...");
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private List<ByteBuffer> bf = new ArrayList<ByteBuffer>();
    public void run() {
        int i = 0;
        while (true) {
            ByteBuffer allocateDirect = ByteBuffer.allocateDirect(1024 * 1024);
            bf.add(allocateDirect);
            System.out.printf("[%d]AllocateDirect: %s\n", i++, "1M");
        }
    }
}