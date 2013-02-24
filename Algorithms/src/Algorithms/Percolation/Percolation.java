package Algorithms.Percolation;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;


public class Percolation 
{
	private int n;
	private boolean[] sites;
	private int top;
	private int bottom;
	private int numopen;
	private WeightedQuickUnionUF connections;
	
	public Percolation (int N) {
		if ( N <= 0) 
			throw new IndexOutOfBoundsException();
		n = N;
		sites = new boolean[n*n+2];
		connections = new WeightedQuickUnionUF(sites.length);
		for ( int i = 0; i < sites.length; i++)
			sites[i] = false;
		top = n*n;
		bottom = n*n+1;
		numopen = 0;
	}
	
	public void open ( int i, int j ) {
		int index = convert2to1(i,j);
		if ( index == -1)
			throw new IndexOutOfBoundsException();
		if (sites[index]) 
			return;
		sites[index] = true;	
		numopen++;
		
		int up = convert2to1(i-1, j);
		if (up == -1)
			connections.union(index, top);
		else if (sites[up])
				connections.union(index,up);
		
		int down = convert2to1(i+1, j);
		if (down == -1) 
			connections.union(index, bottom);
		else if (sites[down])
				connections.union(index,down);		
		
		int left = convert2to1(i, j-1);
		if (left != -1 && sites[left])
			connections.union(index,left);		
		
		int right = convert2to1(i, j+1);
		if (right != -1 && sites[right])
			connections.union(index,right);		
		
	}

	public boolean isOpen ( int i, int j) {
		int index = convert2to1(i,j);
		if ( index == -1)
			throw new IndexOutOfBoundsException();
		
		return sites[index];
	}
	
	public boolean isFull (int i, int j) {
		int index = convert2to1(i,j);
		if ( index == -1)
			throw new IndexOutOfBoundsException();
		
		return connections.connected(index, top);
	}
	
	public boolean percolates() {
		return connections.connected(bottom, top);
	}
	
	private int convert2to1( int i, int j) {
		int index = n*(i-1)+(j-1);
		if (index >= 0 && index <= n*n-1)
			return index;
		else return -1;
	}
	
	public double fraction() {
		return ((double) numopen) / ((double) n*n);
	}
	
}