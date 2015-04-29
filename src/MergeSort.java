<<<<<<< HEAD

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
=======
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

>>>>>>> origin/master
/**
 *
 * @author s130973
 */
public class MergeSort {

    private int[][] numbers;
    private int[][] helper;

    private int number;

 

    public void sort(int[][] values) {

        this.numbers = values;
        number = values.length;
        this.helper = new int[number][2];
        mergesort(0, number - 1);

    }

    private void mergesort(int low, int high) {
        // check if low is smaller then high, if not then the array is sorted
        if (low < high) {
            // Get the index of the element which is in the middle
            int middle = low + (high - low) / 2;
            // Sort the left side of the array
            mergesort(low, middle);
            // Sort the right side of the array
            mergesort(middle + 1, high);
            // Combine them both
            merge(low, middle, high);

        }
    }

    private void merge(int low, int middle, int high) {

        // Copy both parts into the helper array
        for (int i = low; i <= high; i ++) {
            helper[i][0] = numbers[i][0];
        }

        int i = low;
        int j = middle + 1;
        int k = low;
        // Copy the smallest values from either the left or the right side back
        // to the original array
        while (i <= middle && j <= high) {
            if (helper[i][0] <= helper[j][0]) {
                numbers[k][0] = helper[i][0];
                i ++;
            } else {
                numbers[k][0] = helper[j][0];
                j ++;
            }
            k ++;
        }
        // Copy the rest of the left side of the array into the target array
        while (i <= middle) {
            numbers[k][0] = helper[i][0];
            k ++;
            i ++;
        }

    }
<<<<<<< HEAD
=======

    
>>>>>>> origin/master
}
