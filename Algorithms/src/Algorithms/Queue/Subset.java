package Algorithms.Queue;

import java.util.Iterator;
import java.util.Scanner;

import edu.princeton.cs.introcs.StdIn;
import edu.princeton.cs.introcs.StdOut;
import edu.princeton.cs.introcs.StdRandom;

public class Subset{
	
	public static void main(String[] args) {
		/*		
		
		String input = "A B C D E F G H I";
		Deque<String> q = new Deque<String>();
		Scanner s = new Scanner(input).useDelimiter(" ");		
		while ( s.hasNext()) {
			String str =  s.next();
			int flip = StdRandom.uniform(2);
			if (flip == 0)
				q.addLast(str);		
			else 
				q.addFirst(str);
		}	
		Iterator<String> it = q.iterator();
		while (it.hasNext())
				StdOut.println(it.next());	
		
	
		String input = "A B C D E F G H I";
		int k = 3;
		RandomizedQueue<String> q = new RandomizedQueue<String>();
		
		Scanner s = new Scanner(input).useDelimiter(" ");
		while ( s.hasNext()) {
			String str =  s.next();
			q.enqueue(str);			
		}
		Iterator<String> it = q.iterator();
		for(int i = 0; i < k; i ++) {
			if (it.hasNext())
				StdOut.println(it.next());
		}		
			
*/
		if (args.length != 1) {
			StdOut.println("The number of arguments should be one!");
			return;
		}
		int k = Integer.parseInt(args[0]);
		
		RandomizedQueue<String> q = new RandomizedQueue<String>();
		String[] strs =  StdIn.readStrings();
		for ( int i = 0; i < strs.length; i++)			
			q.enqueue(strs[i]);
		
		Iterator<String> it = q.iterator();
		for(int i = 0; i < k; i ++) {
			if (it.hasNext())
				StdOut.println(it.next());
		}
		

	}
}