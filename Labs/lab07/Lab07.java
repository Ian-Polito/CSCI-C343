/*
C343 Summer 2018
Lab 07
Ian Polito
ipolito
 */

public class Lab07 {

    public static void main(String[] args) {
        //Test MyArray functionality
        DynamicArray tester = new DynamicArray(3);
        System.out.println(tester);
        tester.addElement(5);
        tester.addElement(9);
        tester.addElement(20);
        System.out.println(tester);
        tester.removeAt(1);
        System.out.println(tester);
        tester.addElement(34);
        System.out.println(tester);

        System.out.println("");

        MyArray tester2 = new MyArray(10);
        tester2.addElement(6);
        tester2.addElement(19);
        tester2.addElement(0);
        tester2.addElement(3);
        tester2.addElement(90);
        System.out.println(tester2);
        tester2.removeAt(3);
        System.out.println(tester2);
    }
}
