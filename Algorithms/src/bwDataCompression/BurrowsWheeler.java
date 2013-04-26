//package bwDataCompression;

import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Comparator;
/*
import edu.princeton.cs.introcs.BinaryStdIn;
import edu.princeton.cs.introcs.BinaryStdOut;
import edu.princeton.cs.introcs.StdOut;
*/


public class BurrowsWheeler {
	private static char[] t;

	public static void encode() {
		String s = BinaryStdIn.readString();
		int N = s.length();
		int start = -1;
		CircularSuffixArray csa = new CircularSuffixArray(s);
		char[] tail = new char[N];
		for (int i = 0; i < N; i++) {
			tail[i] = s.charAt(((csa.index(i)-1+N)%N));
			if (csa.index(i) == 0)
				start = i;
		}
		BinaryStdOut.write(start, 32);
		for (int i = 0; i < N; i++)
			BinaryStdOut.write(tail[i], 8);
		BinaryStdOut.flush();
	}
	
	private class indexComparator implements Comparator<Integer> {

		public int compare(Integer a, Integer b) {
			
			return Character.compare(t[a], t[b]);
		}
		
	}
	public static void decode() {
		int first = BinaryStdIn.readInt(32);
		String s = BinaryStdIn.readString();
		//StdOut.println(s);
		t = s.toCharArray();
		Integer[] next = new Integer[t.length];
		for (int i = 0; i < next.length; i++)
			next[i] = i;

		Arrays.sort(next, new Comparator<Integer>() {
		    public int compare(final Integer o1, final Integer o2) {
		        return Character.compare(t[o1], t[o2]);
		    }});
		for (int i = 0; i < next.length; i++ ) {
			first = next[first];
			BinaryStdOut.write(t[first]);
			BinaryStdOut.flush();

		}
		
	}
	public static void main(String[] args) throws FileNotFoundException {
		if (args.length != 1)
			throw new IllegalArgumentException();
		//System.setIn(new FileInputStream("src/bwDataCompression/abra_result.txt"));
		//System.setIn(new FileInputStream("src/bwDataCompression/abra.txt"));
		char a = args[0].charAt(0);
		if (a == '-') encode();
		else if (a == '+') decode();
		else throw new IllegalArgumentException();
	}
}