package ru.piskunov;

import java.util.concurrent.ThreadLocalRandom;

public class Task01 {
    public static void main(String[] args) {
        int min = 0;
        int max = 0;
        int sum = 0;
        int[][] arrays = arrayWrite(new int[2][10]);

        for (int i = 0; i < arrays.length; i++) {
            for (int j = 0; j < arrays[i].length - 1; j++) {
                if (arrays[i][j] > max) {
                    max = arrays[i][j];
                } else if (arrays[i][j] < min) {
                    min = arrays[i][j];
                }
                sum += arrays[i][j];
            }
        }
        int average = sum / (arrays.length * arrays[0].length);
    }

    public static int[][] arrayWrite(int[][] array) {
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                array[i][j] = ThreadLocalRandom.current().nextInt(0, 100);
            }
        }
        return array;
    }
}
