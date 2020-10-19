package top.sunslikes.test.practice.sort;

import java.io.File;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Comparator;

public class HeapSort {
    private static ArrayList<Integer> integers = new ArrayList<>();
    public static void heapSort(int k) {
        if (k > 1000) {
            return;
        }
        integers.add(k);
        heapSort(2 * k + 1);
        heapSort(2 * k + 2);
    }

    public static void main(String[] args) {
        heapSort(0);
        integers.sort((a, b) -> {
            return a > b? 1: -1;
        });
        try {
            System.out.println(new File("/QuickSort.java").toURI().toURL());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        System.out.println(integers);
    }
}
