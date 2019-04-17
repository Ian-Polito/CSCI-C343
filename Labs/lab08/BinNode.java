/*
C343 Summer 2018
Lab 08
Ian Polito
ipolito
 */

// a simple class for binary node (C343)
public class BinNode <K extends Comparable<?super K>>{

    private K key; //only key, no value
    private BinNode<K> left; //left child
    private BinNode<K> right; //right child
    private int size; //the size (#nodes) of the subtree rooted at this node
    private int children;

    public BinNode(K k) {
        key = k;
        left = right = null;
        size = 1;
    }

    public void setLeft(BinNode<K> node) {
        left = node;
        children++;
    }

    public void setRight(BinNode<K> node) {
        right = node;
        children++;
    }

    public boolean isLeaf() {
        if (left == null && right == null) {
            return true;
        } else {
            return false;
        }
    }

    public BinNode<K> getLeft() {
        return left;
    }

    public BinNode<K> getRight() {
        return right;
    }

    public K getKey() {
        return key;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int newsize) {
        size = newsize;
    }

    //checks if the tree/subtree is balanced
    //if the height of the left node and right node differs by more than
    //1, the tree/subtree is not balanced. Every node must be checked
    public boolean checkBalance() {
        if (right == null && left == null) {
            return true;
        } else if (right != null && left == null) {
            if (right.getHeight() > 1) {
                return false;
            } else {
                return right.checkBalance();
            }
        } else if (right == null && left != null) {
            if (left.getHeight() > 1) {
                return false;
            } else {
                return left.checkBalance();
            }
        } else {
            if (Math.abs(right.getHeight() - left.getHeight()) > 1) {
                return false;
            } else {
                return (right.checkBalance() && left.checkBalance());
            }
        }
    }

    //returns the height of the tree/subtree
    public int getHeight() {
        if (right == null && left == null) {
            return 1;
        } else if (right == null && left != null) {
            return 1 + left.getHeight();
        } else if (right != null && left == null) {
            return 1 + right.getHeight();
        } else {
            return 1 + max(right.getHeight(), left.getHeight());
        }
    }

    public int max(int a, int b) {
        if (a > b) {
            return a;
        } else if (a < b) {
            return b;
        } else {
            return a;
        }
    }
}
