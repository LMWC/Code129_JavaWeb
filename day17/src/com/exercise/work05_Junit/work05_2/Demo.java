package com.exercise.work05_Junit.work05_2;

import org.junit.Test;

public class Demo {
    /**
     * 获得数组中的最大值
     */
    public int getMax(int[] arr){
        int max = arr[0];
        for (int i = 1; i < arr.length; i++) {
            max = arr[i] > max ? arr[i] : max;
        }
        return max;
    }

    @Test
    public void testGetMax(){
        int[] arr = {4, 6, 8, 3, 4, 9, 1, 2};
        int max = getMax(arr);
        System.out.println("max: " + max);
    }
}
