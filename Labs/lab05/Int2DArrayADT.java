/*
C343 Summer 2018
Lab 05
Ian Polito
ipolito
 */

public interface Int2DArrayADT {

    //set a particular index in the matrix to a given integer
    public void set(int row, int col, int value);

    //make all integers in the matrix to 0
    public void zeroArray();

    //get an integer in a particular index in the matrix
    public int get(int row, int col);

    //return a particular row (array) in the matrix
    public int[] getRow(int row);

    //set a particular row (array) to a given integer
    public void setRow(int row, int value);

    //make all indices in a row (array) to 0
    public void deleteRow(int row);

    //return a particular column (array) in the matrix
    public int[] getColumn(int col);

    //set a particular column (array) in the matrix
    public void setColumn(int col, int value);

    //make all indices in a column (array) to 0
    public void deleteColumn(int col);

    //print the array
    public void printMatrix();
}
