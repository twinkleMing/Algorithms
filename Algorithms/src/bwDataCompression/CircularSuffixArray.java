//package bwDataCompression;

import java.util.Arrays;
import java.util.Comparator;

public class CircularSuffixArray {
	private int N;
	private Integer[] index;
	private String S;
	
	private class SuffixComparator implements Comparator<Integer> {

		public int compare(Integer a, Integer b) {
			while (S.charAt(a) == S.charAt(b)) {
				a++;
				b++;
			}
			return S.charAt(a) - S.charAt(b);
		}


		
	}
	public CircularSuffixArray(String s) {
		S = s;
		N = s.length();
		index = new Integer[N];
		for (int i = 0; i < N; i++) {
			index[i] = i;
		}
		Arrays.sort(index, new SuffixComparator());
		
	}
	
	public int length() {
		return N;
	}
	
	public int index(int i) {
		if (i < 0 || i >= N)
			return -1;
		return index[i];
	}
	
}