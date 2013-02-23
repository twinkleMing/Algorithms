package Algorithms.Percolation;
import WeightedQuickUnionUF;


public class Percolation 
{
	private int n;
	boolean[] sites;
	WeightedQuickUnionUF connections;
	
	public Percolation (int N) {
		n = N;
		sites = new boolean[n*n+2];
		for ( int i = 0; i < sites.length; i++)
			sites[i] = false;
		for ( int i = 0; i < n; i++)
			sites[i]
	}
	
	public void open ( int i, int j ) {
		int index = convert2to1(i,j);
		if (!sites[index]) {
			sites[index] = true;
			
		}
	}

	public boolean isOpen ( int i, int j) {
		
	}
	
	public boolean isFull (int i, int j) {
		
	}
	
	public boolean percolates() {
		
	}
	
	private int convert2to1( int i, int j) {
		return n*i+j;
	}
	
}