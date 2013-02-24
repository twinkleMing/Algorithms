package Algorithms.Queue;

import java.util.Iterator;
import java.util.NoSuchElementException;
import edu.princeton.cs.introcs.StdRandom;


public class RandomizedQueue<Item> implements Iterable<Item> {
	private int N;
	private Item[] queue;
	
	private void resize(int n) {
		Item[] newq = (Item[]) new Object[n];
		for ( int i = 0; i < N; i++)
			newq[i] = queue[i];
		queue = newq;		
	}
	public RandomizedQueue() {
		queue = null;
		N = 0;
	}
	
	public boolean isEmpty() {
		return N == 0;
	}
	
	public int size() {
		return N;
	}
	
	public void enqueue(Item item) {
		if (item == null)
			throw new NullPointerException();
		
		if (queue == null)
			queue = (Item[]) new Object[2];
		
		if (N == queue.length)
			resize(N*2);

		queue[N] = item;
		N++;
	}
	
	public Item dequeue() {
		if (N == 0)
			throw new NoSuchElementException();
		
		int r = StdRandom.uniform(N);
		Item t = queue[r];
		queue[r] = queue[N-1];
		queue[N-1] = null;
		N--;
		if (N > 0 && N <= queue.length/4)
			resize(N/2);
		return t;
	}
	
	public Item sample() {
		if (N == 0)
			throw new NoSuchElementException();
		
		int r = StdRandom.uniform(N);
		Item t = queue[r];
		return t;
	}
	
	public Iterator<Item> iterator() {
		return new ListIterator();
	}
	
	private class ListIterator implements Iterator<Item> {
		
		private int [] order;
		private int current;
		public ListIterator() {
			order = new int[N];
			for ( int i = 0; i < N; i++) 
				order[i] = i;
			StdRandom.shuffle(order);
			current = 0;
		}
		
		public boolean hasNext() {
			return current < N;
		}

		public Item next() {
			if (!hasNext()) 
				throw new NoSuchElementException();

			Item t = queue[order[current]];
			current++;
			return t;
		}

		public void remove() {
			throw new UnsupportedOperationException();		
		}
		
	}
}