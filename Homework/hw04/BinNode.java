/*
C343 Summer 2018
Homework 4
Ian Polito
ipolito
 */

// a simple class for binary node (C343)
public class BinNode <K extends Comparable<?super K>>{

    private K key; //only key, no value
    private BinNode<K> left; //left child
    private BinNode<K> right; //right child
    private int size;
    private int rchildren;
    private int lchildren;

    public BinNode(K k) {
        key = k;
        left = right = null;
        size = 1;
        rchildren = 0;
        lchildren = 0;
    }
    
    public BinNode<K> findKthSmallest(int k) throws Exception {
    	if ((lchildren == k - 1) || (lchildren == k && k == 2)) {
    		return this;
    	} else if (lchildren >= k) {
    		return left.findKthSmallest(k);
    	} else {
    		return right.findKthSmallest(k - 1 - lchildren);
    	}
    }

    public void insert (BinNode<K> node) {
        if (node.getKey().compareTo(this.getKey()) < 0) {
            if (left == null) {
                left = node;
            } else {
                left.insert(node);
            }
            lchildren++;
        } else {
            if (right == null) {
                right = node;
            } else {
                right.insert(node);
            }
            rchildren++;
        }
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
    
    public int getLChildren() {
    	return lchildren;
    }
    
    public int getRChildren() {
    	return rchildren;
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