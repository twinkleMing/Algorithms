package Algorithms.Queue;

import java.util.Iterator;

import edu.princeton.cs.introcs.StdIn;
import edu.princeton.cs.introcs.StdOut;

public class Subset{
	
	public static void main(String[] args) {
		if (args.length != 1) {
			StdOut.println("The number of arguments should be one!");
			return;
		}
		int k = Integer.parseInt(args[0]);
		
		RandomizedQueue<String> q = new RandomizedQueue<String>();
		while(StdIn.hasNextLine()) {
			String s =  StdIn.readString();
			q.enqueue(s);
		}
		Iterator<String> it = q.iterator();
		for(int i = 0; i < k; i ++) {
			if (it.hasNext())
				StdOut.println(it.next());
		}
	}
}