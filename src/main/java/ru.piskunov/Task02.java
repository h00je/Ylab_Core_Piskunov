package ru.piskunov;

import java.util.Arrays;

public class Task02 {
    public static void main(String[] args) {

        int[] arr = new int[]{5, 6, 3, 2, 5, 1, 4, 9};

        customQuickSort(arr);

        System.out.println(Arrays.toString(arr));
    }

    public static void customQuickSort(int[] arr) {
        sorting(arr, 0, arr.length - 1);
    }

    public static void sorting(int[] arr, int firsIndex, int lastIndex) {
        if (firsIndex < lastIndex) {

            int divideIndex = parcelling(arr, firsIndex, lastIndex);

            sorting(arr, firsIndex, divideIndex);
            sorting(arr, divideIndex + 1, lastIndex);
        }
    }

    public static int parcelling(int[] arr, int firstIndex, int lastIndex) {
        int supportElement = arr[lastIndex];

        while (firstIndex <= lastIndex) {
            while (arr[firstIndex] < supportElement) {
                firstIndex++;
            }
            while (arr[lastIndex] > supportElement) {
                lastIndex--;
            }
            if (firstIndex <= lastIndex) {
                swap(arr, firstIndex, lastIndex);
                firstIndex++;
                lastIndex--;
            }
        }
        return lastIndex;
    }

    public static void swap(int[] arr, int index1, int index2) {
        int temp = arr[index2];
        arr[index2] = arr[index1];
        arr[index1] = temp;
    }
}
