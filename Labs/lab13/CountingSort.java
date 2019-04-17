/*
C343 Summer 2018
Lab 13
Ian Polito
ipolito

Partner
Levi Beyers
lebeyers
 */

import java.util.*;

public class CountingSort {

    //this method takes an array of integers and sorts it
    //using the CountingSort algorithm
    public int[] CountingSort(int[] array) {
        //create a result that will be the sorted array
        int result[] = new int[array.length];
        //first find the largest int in the array
        int largest = array[0];
        //loop through array to find largest int
        for (int i = 1; i < array.length; i++) {
            //check if largest is still largest
            if (largest < array[i]) {
                //this number is larger, replace largest
                largest = array[i];
            }
        }
        System.out.println("The largest value in the given array is: " + largest);
        //create an array b that is the size of the largest int in array
        int b[] = new int[largest+1];
        //process array and do the first step
        for (int i = 0; i < array.length; i++) {
            //increment the count at index array[i] by one
            b[array[i]] += 1;
        }
        //process b array and re-purpose it as lookup table
        for (int i = 1; i < b.length; i++) {
            //increment by previous index
            b[i] += b[i - 1];
        }
        //now go through given array again using the created lookup table
        //to sort the array
        for (int i = array.length-1; i >= 0; i--) {
            //take array[i] and find the number in that index of b
            //use the number stored there and pre-decrement it to find the index to place
            //the number at array[i]
            result[--b[array[i]]] = array[i];
        }
        //return the sorted array
        return result;
    }

    public static void main(String[] args) {
        //Testing Counting Sort

        //create instance of CountingSort
        CountingSort tester = new CountingSort();
        //create instance of Random
        Random rand = new Random();
        //create a new int array of random size
        int test[] = new int[rand.nextInt(64)];
        //fill array with random Integers
        for (int i = 0; i < test.length; i++) {
            //add random int to index i of test array
            test[i] = rand.nextInt(64);
        }
        //print initial unsorted array
        System.out.print("[");
        for (int i = 0; i < test.length; i++) {
            if (i == test.length - 1) {
                System.out.print(test[i] + "]\n");
            } else {
                System.out.print(test[i] + ", ");
            }
        }
        //use CountingSort object to call the sorting method to sort test array
        test = tester.CountingSort(test);
        //print sorted array
        System.out.print("[");
        for (int i = 0; i < test.length; i++) {
            if (i == test.length - 1) {
                System.out.print(test[i] + "]\n");
            } else {
                System.out.print(test[i] + ", ");
            }
        }
    }
}
