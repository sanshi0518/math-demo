package com.bulain.sort;

import org.junit.Assert;
import org.junit.Test;

public class BubbleSortTest {
    private BubbleSort sort = new BubbleSort();

    @Test
    public void test() {
        int[] result = new int[]{1, 2, 3, 4, 5, 6, 7, 8};

        int[] params = new int[]{2, 3, 5, 6, 8, 4, 1, 7};
        sort.sort(params);
        Assert.assertArrayEquals(result, params);

        params = new int[]{1, 2, 3, 4, 5, 6, 7, 8};
        sort.sort(params);
        Assert.assertArrayEquals(result, params);

        params = new int[]{8, 7, 6, 5, 4, 3, 2, 1};
        sort.sort(params);
        Assert.assertArrayEquals(result, params);
    }

}
