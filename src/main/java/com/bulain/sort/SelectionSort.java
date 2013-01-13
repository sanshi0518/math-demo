package com.bulain.sort;

public class SelectionSort implements Sort {
    public void sort(int[] params) {
        for (int i = params.length - 1; i > 0; i--) {
            int k = -1;
            int max = Integer.MIN_VALUE;
            for (int j = 0; j <= i; j++) {
                if (max < params[j]) {
                    max = params[j];
                    k = j;
                }
            }
            params[k] = params[i];
            params[i] = max;
        }

    }
}
