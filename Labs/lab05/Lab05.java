/*
C343 Summer 2018
Lab 05
Ian Polito
ipolito
 */

public class Lab05 {

    public static void main(String[] args) {
        //Test Int2DArray implementation
        Int2DArray tester = new Int2DArray(7,7);
        tester.set(0,0,9);
        tester.setColumn(6,4);
        tester.setRow(5,7);
        System.out.println(tester.get(5,3)); //should be 7
        tester.printMatrix();
        tester.set(20,20,19); //should print an error
    }
}
