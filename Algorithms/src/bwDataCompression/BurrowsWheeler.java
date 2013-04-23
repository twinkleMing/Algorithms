package bwDataCompression;

import edu.princeton.cs.introcs.BinaryStdIn;
import edu.princeton.cs.introcs.BinaryStdOut;



public class BurrowsWheeler {

	
	public static void encode() {
		String s = BinaryStdIn.readString();
		int N = s.length();
		int start = -1;
		CircularSuffixArray csa = new CircularSuffixArray(s);
		char[] tail = new char[N];
		for (int i = 0; i < N; i++) {
			tail[i] = s.charAt((csa.index(i)-1)%N);
			if (csa.index(i) == 0)
				start = i;
		}
		BinaryStdOut.write(start, 32);
		for (int i = 0; i < N; i++)
			BinaryStdOut.write(tail[i], 8);
	}
	
	public static void decode() {
		int start = BinaryStdIn.readInt(32);
		char
	}
	
}