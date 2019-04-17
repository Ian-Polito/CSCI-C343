/*
 * C343 Summer 2018
 * Homework 3
 * Ian Polito
 * ipolito
 */

import java.util.*;

//Circular Double Linked List Implementation
public class CircularLList<E>  {
	
	private DLink<E> head;        // Pointer to list header
	private int size;		      // Size of list
	
	//Constructors
	CircularLList(int size) {
		//ignore size
		this();
	}
	
	CircularLList() {
		head = null;
		size = 0;
	}
	
	//Remove all elements from list
	public void clear() {
		head = null;
		size = 0;
	}
	
	//reverses the order of the list
	public void reverse() {
		Queue<E> reverser = new LinkedList<E>();
		DLink<E> n = head;
		if (head == null) {
			return;
		} else {
			reverser.add(n.element());
		}
		n = n.next();
		while (n != head) {
			reverser.add(n.element());
			n = n.next();
		}
		this.clear();
		while (!reverser.isEmpty()) {
			this.insert(reverser.remove());
		}
	}
	
	//returns pointer to head of list
	public DLink<E> getHead() {
		return head;
	}
	
	//inserts new node with value it at the beginning of the list
	public void insert(E it) {
		DLink<E> n = new DLink<E>(it, null, null);
		size++;
		if (head == null) {
			head = n;
			n.setNext(n);
			n.setPrev(n);
		} else {
			DLink<E> temp = head;
			head = n;
			n.setNext(temp);
			n.setPrev(temp.prev());
			temp.prev().setNext(n);
			temp.setPrev(n);
		}
	}
	
	//remove the link with it value
	public void remove(E it) {
		DLink<E> n = head;
		if (n.element().equals(it)) {
			if (length() == 1) {
				head = null;
				return;
			} else {
				n.next().setPrev(n.prev());
				n.prev().setNext(n.next());
				return;
			}
		}
		n = n.next();
		while (n != head) {
			if (n.element().equals(it)) {
				n.next().setPrev(n.prev());
				n.prev().setNext(n.next());
				return;
			}
		}
	}
	
	public int length() {
		return size;
	}
	
	//Overridden toString method
	@Override
	public String toString() {
		String result = "< ";
		DLink<E> n = head;
		if (head == null) {
			result += ">";
		} else {
			result += n.element() + " ";
			n = n.next();
			while (n != head) {
				result += n.element() + " ";
				n = n.next();
			}
			result += ">";
		}
		return result;
	}
}
