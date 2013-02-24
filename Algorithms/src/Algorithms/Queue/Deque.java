package Algorithms.Queue;

import java.util.Iterator;
import java.util.NoSuchElementException;



public class Deque<Item> implements Iterable<Item> {
	
	private class Node {
		private Item item;
		private Node next;
		private Node prev;
		
		private Node(Item i, Node n, Node p) {
			item = i;
			next = n;
			prev = p;
		}
	}
	
	private Node first;
	private Node last;
	private int N;
	
	
	public Deque() {
		first = null;
		last = null;
		N = 0;
	}
	
	public boolean isEmpty() {
		return N == 0;
	}
	
	public int size() {
		return N;
	}
	public void addFirst(Item item) {
		if (item == null)
			throw new NullPointerException();
		
		if (first == null) {
			first = new Node(item, null, null);
			last = first;
		}
		else {
			Node oldfirst = first;
			Node first = new Node(item, null, oldfirst);
			oldfirst.prev = first;
		}

		N++;
	}
	
	public void addLast(Item item) {
		if (item == null)
			throw new NullPointerException();
		
		if (last == null) {
			last = new Node(item, null, null);
			first = last;
		}
		else {
			Node oldlast = last;
			last = new Node(item, oldlast, null);
			oldlast.next = last;
		}
		N++;
	}
	
	public Item removeFirst() {
		if (first == null)
			throw new NoSuchElementException();
		
		Item t = first.item;
		first = first.next;
		if (first == null)
			last = null;
		else
			first.prev = null;
		N--;
		return t;
	}
	public Item removeLast() {
		if (last == null)
			throw new NoSuchElementException();
		
		Item t = last.item;
		last = last.prev;
		if (last == null)
			first = null;
		else
			last.next = null;
		N--;
		return t;		
	}
	
	public Iterator<Item> iterator() {
		return new ListIterator();
	}
	
	private class ListIterator implements Iterator<Item> {
		private Node current = first;
		
		public boolean hasNext() {
			return current == null;
		}

		public Item next() {
			if (current == null)
				throw new NoSuchElementException();
			
			Item t = current.item;
			current = current.next;
			return t;
		}

		public void remove() {
			throw new UnsupportedOperationException();
		}
		
	}
}