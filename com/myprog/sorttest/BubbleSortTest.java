package com.myprog.sorttest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;

public class BubbleSortTest {
    private static Random rand = new Random();
    private static final int MAX_INT_ARRAY_ELEM_VALUE = 100;

    static int getCnt() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Привет! Сколько элементов в коллекции: ");
        return sc.nextInt();
    }

    static ArrayList<Integer> getIntArray(int cnt) {
        ArrayList<Integer> randIntArray = new ArrayList<>();
        for(int i = 0; i < cnt; i++) {
            int randInt = rand.nextInt(MAX_INT_ARRAY_ELEM_VALUE);
            randIntArray.add(randInt);
        }
        //System.out.println("Случайная коллекция целых чисел: \n" + randIntArray);
        return randIntArray;
    }

    static void bubbleSort(ArrayList<Integer> arr) {
        if (arr == null || arr.size() == 0) {
            throw new IllegalArgumentException("Некорректный аргумент: коллекция пустая или не определена");
        }
        // Считаем, что есть хотя бы 1 элемент
        int len = arr.size();
        int tmp;
        boolean isSwapped;
        do {
            isSwapped = false;
            for(int i = 1; i < len; i++) {
                if (arr.get(i-1) > arr.get(i) ) {
                    tmp = arr.get(i-1);
                    arr.set(i-1, arr.get(i));
                    arr.set(i, tmp);
                    isSwapped = true;
                }
            }
            //System.out.println("Текущая итерация: " + arr);
            len--;
        }while(isSwapped);
    }

    public static void main(String[] args) {
        int cnt = getCnt();
        ArrayList<Integer> testIntArrayOne = getIntArray(cnt);
        ArrayList<Integer> testIntArrayTwo = getIntArray(cnt);

        long start = System.currentTimeMillis();
        bubbleSort(testIntArrayOne);
        long end = System.currentTimeMillis();
        System.out.println("Bubble Sort Time = " + (end - start) + " milliseconds");

        start = System.currentTimeMillis();
        Collections.sort(testIntArrayTwo);
        end = System.currentTimeMillis();
        System.out.println("Collections.sort Time = " + (end - start) + " milliseconds");

        //System.out.println("testIntArrayOne: " + testIntArrayOne);
        //System.out.println("testIntArrayTwo: " + testIntArrayTwo);
    }

}
