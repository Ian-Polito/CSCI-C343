/*
C343 Summer 2018
Lab 07
Ian Polito
ipolito
 */

public class MyArray {

    private Integer[] array;

    //constructor for MyArray
    public MyArray(int size) {
        this.array = new Integer[size];
    }

    //removes the element at the given index by making that index null as in the first method
    //returns the removed element or zero if given index is out of bounds
    public Integer removeAt(int index) {
        //check if within bounds
        if (index < array.length && index > -1) {
            Integer result = array[index];
            array[index] = null;
            return result;
        } else {
            //not within bounds, return 0;
            return 0;
        }
    }

    //adds an element to the array, returns 1 if successful, 0 otherwise
    public int addElement(int val) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] == null) {
                array[i] = val;
                return 1;
            }
        }
        //couldn't find an empty (null) space
        return 0;
    }

    //overridden toString method; prints out its array of integers
    @Override
    public String toString() {
        String result = "[";
        for (int i = 0; i < array.length; i++) {
            if (i == (array.length - 1)) {
                result += array[i] + "]";
            } else {
                result += array[i] + ", ";
            }
        }
        return result;
    }
}
