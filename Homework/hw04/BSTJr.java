/*
C343 Summer 2018
Homework 4
Ian Polito
ipolito
 */

// a simple BST tree:
//  it's so simple we call it BSTJr...
//
// for C343
public class BSTJr <K extends Comparable<?super K>> {

    BinNode<K> root;
    private int size;

    public BSTJr() {
        root = null;
        size = 0;
    }

    public void build(K[] ks) {
        for (int i = 0; i < ks.length; i++) {
            insert(ks[i]);
        }
    }

    public void insert(K k) {
        BinNode<K> t = new BinNode<K>(k);
        size++;
        if (root == null) {
            root = t;
        } else {
            root.insert(t);
        }
    }

    public BinNode<K> search(BinNode<K> entry, K k) {
        if (entry == null) {
            return null;
        } else {
            entry.setSize(entry.getSize() + 1); //update the size of the subtree by one
            if (entry.isLeaf()) {
                return entry;
            }
            if (k.compareTo(root.getKey()) < 0) {
                if (entry.getLeft() != null) {
                    return search(entry.getLeft(), k);
                } else {
                    return entry;
                }
            } else {
                if (entry.getRight() != null) {
                    return search(entry.getRight(), k);
                } else {
                    return entry;
                }
            }
        }
    }

    public void display() {
        if(root == null) {
            return;
        }
        System.out.println("Preorder enumeration: key(size-of-the-subtree)");
        preorder(root);
        System.out.println();
        System.out.println("Inorder enumeration: key(size-of-the-subtree)");
        inorder(root);
        System.out.println();
        System.out.println("Postorder enumeration: key(size-of-the-subtree)");
        postorder(root);
        System.out.println();
    }

    public void inorder(BinNode<K> entry) {
        if (entry.getLeft() != null) {
            inorder(entry.getLeft());
        }
        System.out.print(entry.getKey() + "(" + entry.getSize() + ") ");
        if (entry.getRight() != null) {
            inorder(entry.getRight());
        }
    }

    public void postorder(BinNode<K> entry) {
        if (entry.getLeft() != null) {
            postorder(entry.getLeft());
        }
        if (entry.getRight() != null) {
            postorder(entry.getRight());
        }
        System.out.print(entry.getKey() + "(" + entry.getSize() + ") ");
    }

    //given to us, correct implementation
    public void preorder(BinNode<K> entry) {
        System.out.print(entry.getKey() + "(" + entry.getSize() + ") ");
        if (entry.getLeft() != null) {
            preorder(entry.getLeft());
        }
        if (entry.getRight() != null) {
            preorder(entry.getRight());
        }
    }

    //checks if the tree is balanced
    public boolean checkBalance() {
        return root.checkBalance();
    }
    
    public BinNode<K> findKthSmallest(int k) throws Exception {
    	if (this.size < k || k < 1) {
    		throw new Exception("Error: invalid argument");
    	} else {
    		if ((root.getLChildren() == k - 1) || (root.getLChildren() == k && k == 2)) {
    			return root;
    		} else if (root.getLChildren() >= k) {
    			return root.getLeft().findKthSmallest(k);
    		} else {
    			return root.getRight().findKthSmallest(k - 1 - root.getLChildren());
    		}
    	}
    }

    public static void main(String[] argv) {
        BSTJr<Integer> tree = new BSTJr<Integer>();
        Integer[] ks = {37, 24, 42, 7, 2, 40, 120};
        tree.build(ks);
        tree.display();

        //Test checkBalance()
        System.out.println("Tree is balanced: " + tree.checkBalance());
        //the tree is not balanced, so this should return false
        //make a balanced tree to test
        BSTJr<Integer> tree2 = new BSTJr<Integer>();
        Integer[] ks2 = {50, 25, 75, 15, 30};
        tree2.build(ks2);
        tree2.display();
        System.out.println("Tree2 is balanced: " + tree2.checkBalance());
        //the tree is balanced, so this should return true
        
        BSTJr<Integer> tree3 = new BSTJr<Integer>();
        Integer[] ks3 = {20, 10, 40, 5, 12, 25, 50, 11, 14, 45};
        tree3.build(ks3);
        try {
        	//TESTING findKthSmallest method
        	
        	//should print 5, 10, 11, 12, 14, 20, 25, 40, 45, 50
        	System.out.println("k = 1: " + tree3.findKthSmallest(1).getKey());
        	System.out.println("k = 2: " + tree3.findKthSmallest(2).getKey());
        	System.out.println("k = 3: " + tree3.findKthSmallest(3).getKey());
        	System.out.println("k = 4: " + tree3.findKthSmallest(4).getKey());
        	System.out.println("k = 5: " + tree3.findKthSmallest(5).getKey());
        	System.out.println("k = 6: " + tree3.findKthSmallest(6).getKey());
        	System.out.println("k = 7: " + tree3.findKthSmallest(7).getKey());
        	System.out.println("k = 8: " + tree3.findKthSmallest(8).getKey());
        	System.out.println("k = 9: " + tree3.findKthSmallest(9).getKey());
        	System.out.println("k = 10: " + tree3.findKthSmallest(10).getKey());
        	
        	//instance where the Kth element will not be found
        	//i.e when k is larger than the number of nodes or smaller than 1
        	System.out.println("k = 0: " + tree3.findKthSmallest(0).getKey());
        	System.out.println("k = 20: " + tree3.findKthSmallest(20).getKey());
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
}