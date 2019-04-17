/*
C343 Summer 2018
Lab 07
Ian Polito
ipolito
 */

public class DynamicArray {

    private Integer[] array;

    //constructor for MyArray
    public DynamicArray(int size) {
        this.array = new Integer[size];
    }

    //removes the element at the given index by shifting all elements after it to the left
    //returns the removed element or zero if given index is out of bounds
    public Integer removeAt(int index) {
        //check if within bounds
        if (index < array.length && index > -1) {
            Integer result = array[index];
            Integer[] newArray = new Integer[array.length-1];
            int j = 0;
            for (int i = 0; i < array.length; i++) {
                if (i != index) {
                    newArray[j] = array[i];
                    j++;
                }
            }
            this.array = newArray;
            return result;
        } else {
            //not within bounds, return 0
            return 0;
        }
    }

    //adds an element to the array
    public void addElement(int val) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] == null) {
                array[i] = val;
                return;
            }
        }
        //couldn't find an empty (null) space
        Integer[] newArray = new Integer[array.length*2];
        for (int i = 0; i < array.length; i++) {
            newArray[i] = array[i];
        }
        newArray[array.length] = val;
        this.array = newArray;
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
