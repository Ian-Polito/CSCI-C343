/*
C343 Summer 2018
Lab 05
Ian Polito
ipolito
 */

public class Int2DArray implements Int2DArrayADT {

    private int[][] matrix;
    private int rows;
    private int cols;

    //constructor for Int2DArray
    public Int2DArray(int rows, int cols) {
        this.matrix = new int[rows][cols];
        this.rows = rows--;
        this.cols = cols--;
    }

    //set a particular index in the matrix to a given integer
    public void set(int row, int col, int value) {
        //check if we are within bounds
        if (row <= rows && col <= cols) {
            matrix[row][col] = value;
        } else {
            System.out.println("Error: given index is out of bounds.");
        }
    }

    //make all integers in the matrix to 0
    public void zeroArray() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                matrix[i][j] = 0;
            }
        }
    }

    //get an integer in a particular index in the matrix
    public int get(int row, int col) {
        //check if we are within bounds
        if (row <= rows && col <= cols) {
            return matrix[row][col];
        } else {
            System.out.println("Error: given index is out of bounds.");
            //return 0 in this case
            return 0;
        }
    }

    //return a particular row (array) in the matrix
    public int[] getRow(int row) {
        //check if we are within bounds
        if (row <= rows) {
            return matrix[row];
        } else {
            System.out.println("Error: given index is out of bounds.");
            //return empty array in this case
            return new int[0];
        }
    }

    //set a particular row (array) to a given integer
    public void setRow(int row, int value) {
        //check if we are within bounds
        if (row <= rows) {
            for (int i = 0; i < rows; i++) {
                matrix[row][i] = value;
            }
        } else {
            System.out.println("Error: given index is out of bounds.");
        }
    }

    //make all indices in a row (array) to 0
    public void deleteRow(int row) {
        //check if we are within bounds
        if (row <= rows) {
            for (int i = 0; i < rows; i++) {
                matrix[row][i] = 0;
            }
        } else {
            System.out.println("Error: given index is out of bounds.");
        }
    }

    //return a particular column (array) in the matrix
    public int[] getColumn(int col) {
        //check if we are within bounds
        if (col <= cols) {
            int[] result = new int[rows];
            for (int i = 0; i < rows; i++) {
                result[i] = matrix[i][col];
            }
            return result;
        } else {
            System.out.println("Error: given index is out of bounds.");
            //return empty array in this case
            return new int[0];
        }
    }

    //set a particular column (array) in the matrix
    public void setColumn(int col, int value) {
        //check if we are within bounds
        if (col <= cols) {
            for (int i = 0; i < rows; i++) {
                matrix[i][col] = value;
            }
        } else {
            System.out.println("Error: given index is out of bounds.");
        }
    }

    //make all indices in a column (array) to 0
    public void deleteColumn(int col) {
        //check if we are within bounds
        if (col <= cols) {
            for (int i = 0; i < rows; i++) {
                matrix[i][col] = 0;
            }
        } else {
            System.out.println("Error: given index is out of bounds.");
        }
    }

    //print the matrix
    public void printMatrix() {
        System.out.print("[");
        for (int i = 0; i < rows; i++) {
            System.out.print("(");
            for (int j = 0; j < cols; j++) {
                if ((j + 1) == cols) {
                    System.out.print(matrix[i][j]);
                } else {
                    System.out.print(matrix[i][j] + ", ");
                }
            }
            if ((i + 1) == rows) {
                System.out.print(")]\n");
            } else {
                System.out.print(")\n");
            }
        }
    }
}
