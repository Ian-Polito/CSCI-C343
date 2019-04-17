/*
C343 Summer 2018
Lab 08
Ian Polito
ipolito
 */

//a simple class for binary tree (mini-assignment)
public class BinNodeJr <E extends Comparable<?super E>> {

    private E value;
    private BinNodeJr<E> left;
    private BinNodeJr<E> right;

    public BinNodeJr(E e) {
        value = e;
        left = right = null;
    }

    public void setLeft(BinNodeJr<E> node) {
        left = node;
    }

    public void setRight(BinNodeJr<E> node) {
        right = node;
    }

    public boolean find(E q) {
        if (q.compareTo(value) == 0) {
            return true;
        } else {
            if (left != null && right != null) {
                return (left.find(q) || right.find(q));
            } else if (left == null && right != null) {
                return (right.find(q));
            } else if (left != null && right == null) {
                return (left.find(q));
            } else {
                return false;
            }
        }
    }

    public static void main(String[] argv) {
        BinNodeJr<Integer> root = new BinNodeJr<Integer>(10);
        BinNodeJr<Integer> node1 = new BinNodeJr<Integer>(30);
        BinNodeJr<Integer> node2 = new BinNodeJr<Integer>(40);
        root.setLeft(node1);
        root.setRight(node2);
        //find() is to be implemented
        System.out.println("40 is found in the tree: " + root.find(40));
        //find(40) shall return true
        System.out.println("100 is found in the tree: " + root.find(100));
        //find(100) shall return false

        //Additional test cases
        BinNodeJr<Integer> node3 = new BinNodeJr<Integer>(9);
        node1.setLeft(node3);
        BinNodeJr<Integer> node4 = new BinNodeJr<Integer>(25);
        node2.setRight(node4);
        System.out.println("9 is found in the tree: " + root.find(9));
        //find(9) should return true
        System.out.println("27 is found in the tree: " + root.find(27));
        //find(27) should return false
    }
}
