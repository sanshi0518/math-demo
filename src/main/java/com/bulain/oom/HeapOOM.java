package com.bulain.oom;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

public class HeapOOM extends Thread {
    public static void main(String[] args) {

        HeapOOM heapOOM = new HeapOOM();
        heapOOM.start();

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
            ByteBuffer allocateDirect = ByteBuffer.allocate(1024 * 1024);
            bf.add(allocateDirect);
            System.out.printf("[%d]Allocate: %s\n", i++, "1M");
        }
    }
}
