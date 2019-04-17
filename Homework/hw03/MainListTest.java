/*
 * C343 Summer 2018
 * Homework 3
 * Ian Polito
 * ipolito
 */

public class MainListTest<E> {
	
	static final int testsize = 100000; // try larger testsize values
	static long time1, time2;
	
	//takes two LList objects that have their elements in sorted order
	//and returns another LList that merges the two and is in sorted order
	public LList<E> mergeTwoLists(Object l1, Object l2) {
		LList<E> list1 = (LList<E>)l1;
		LList<E> list2 = (LList<E>)l2;
		LList<E> result = new LList<E>();
		DLink<E> head1 = list1.getHead();
		DLink<E> head2 = list2.getHead();
		while (true) {
			if (head1 == null && head2 == null) {
				break;
			} else if (head1 == null) {
				if (head2.element() != null) {
					result.insert(head2.element());
				}
				head2 = head2.next();
			} else if (head2 == null) {
				if (head1.element() != null) {
					result.insert(head1.element());
				}
				head1 = head1.next();
			} else {
				if (head1.element() != null && head2.element() != null) {
					Comparable elem1 = (Comparable)head1.element();
					Comparable elem2 = (Comparable)head2.element();
					if (elem1.compareTo(elem2) > 0) {
						//head1.element() > head2.element()
						result.insert(head2.element());
						head2 = head2.next();
					} else if (elem1.compareTo(elem2) < 0) {
						//head1.element() < head2.element()
						result.insert(head1.element());
						head1 = head1.next();
					} else {
						//head1.element() == head1.element()
						result.insert(head1.element());
						head1 = head1.next();
						result.insert(head2.element());
						head2 = head2.next();
					}
				} else {
					if (head1.element() == null) {
						head1 = head1.next();
					}
					if (head2.element() == null) {
						head2 = head2.next();
					}
				}
			}
		}
		result.reverse();
		return result;
	}
	
	public static void main(String[] args) {
		//Testing LList Functionality
		LList<Integer> LL = new LList<Integer>();
		System.out.println("Start");
		time1 = System.currentTimeMillis();
		for (int i = 0; i < testsize; i++) {
			LL.insert(2);
			LL.insert(3);
			LL.insert(4);
			LL.insert(5);
			LL.clear();
		}
		time2 = System.currentTimeMillis();
		long totaltime = (time2-time1);
		System.out.println("For " + testsize + " iterations it took " + totaltime + " milliseconds.");
		
		//Testing reverse() method
		System.out.println("Start");
		time1 = System.currentTimeMillis();
		LL.insert(2);
		LL.reverse();
		time2 = System.currentTimeMillis();
		LL.clear();
		totaltime = (time2-time1);
		System.out.println("For size " + 1 + " it took " + totaltime + " milliseconds to reverse.");
		
		time1 = System.currentTimeMillis();
		LL.insert(2);
		LL.insert(3);
		LL.reverse();
		time2 = System.currentTimeMillis();
		LL.clear();
		totaltime = (time2-time1);
		System.out.println("For size " + 2 + " it took " + totaltime + " milliseconds to reverse.");
		
		time1 = System.currentTimeMillis();
		LL.insert(2);
		LL.insert(3);
		LL.insert(4);
		LL.reverse();
		time2 = System.currentTimeMillis();
		LL.clear();
		totaltime = (time2-time1);
		System.out.println("For size " + 3 + " it took " + totaltime + " milliseconds to reverse.");
		
		time1 = System.currentTimeMillis();
		LL.insert(2);
		LL.insert(3);
		LL.insert(4);
		LL.insert(5);
		LL.reverse();
		time2 = System.currentTimeMillis();
		LL.clear();
		totaltime = (time2-time1);
		System.out.println("For size " + 4 + " it took " + totaltime + " milliseconds to reverse.");
		
		time1 = System.currentTimeMillis();
		LL.insert(2);
		LL.insert(3);
		LL.insert(4);
		LL.insert(5);
		LL.insert(99);
		LL.reverse();
		time2 = System.currentTimeMillis();
		LL.clear();
		totaltime = (time2-time1);
		System.out.println("For size " + 5 + " it took " + totaltime + " milliseconds to reverse.");
		
		//Testing mergeTwoLists() method
		LL.insert(22);
		LL.insert(18);
		LL.insert(9);
		System.out.println(LL);
		LList<Integer> LL2 = new LList<Integer>();
		LL2.insert(14);
		LL2.insert(6);
		LL2.insert(1);
		System.out.println(LL2);
		MainListTest test = new MainListTest();
		LList<Integer> LL3 = (LList<Integer>)test.mergeTwoLists(LL,LL2);
		System.out.println("Merged List: "+ LL3);
		
		//Testing CircularLList
		CircularLList<Integer> CLL = new CircularLList<Integer>();
		CLL.insert(5);
		CLL.insert(9);
		CLL.insert(20);
		System.out.println(CLL);
		CLL.reverse();
		System.out.println(CLL);
		CLL.remove(9);
		System.out.println(CLL);
	}
}
