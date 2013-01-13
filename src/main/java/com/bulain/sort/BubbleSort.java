package com.bulain.sort;

public class BubbleSort implements Sort {
    public void sort(int[] params) {
        int temp;
        for (int i = params.length - 1; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                if (params[j] > params[j + 1]) {
                    temp = params[j];
                    params[j] = params[j + 1];
                    params[j + 1] = temp;
                }
            }
        }

    }
}
