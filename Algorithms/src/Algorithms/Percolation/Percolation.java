
package Algorithms.Percolation;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;


public class Percolation 
{
	private int n;
	private boolean[] sites;
	private int top;
	private int bottom;
	private WeightedQuickUnionUF connections;
	
	public Percolation(int N) {
		if (N <= 0) 
			throw new IndexOutOfBoundsException();
		n = N;
		sites = new boolean[n*n+2];
		connections = new WeightedQuickUnionUF(sites.length);
		for (int i = 0; i < n*n; i++)
			sites[i] = false;
		sites[n*n] = true;
		sites[n*n+1] = false;
		top = n*n;
		bottom = n*n+1;
	}
	
	public void open(int i, int j) {
		int index = convert2to1(i, j);
		if (sites[index]) 
			return;
		sites[index] = true;	
		
		if (i == 1)
			connections.union(index, top);
		else {
			int up = convert2to1(i-1, j);
			if (sites[up])
				connections.union(index, up);
		}
			
		if (i == n)
			connections.union(index, bottom);
		else {
			int down = convert2to1(i+1, j);
			if (sites[down])
				connections.union(index, down);	
		}
		
		if (j != 1) {
			int left = convert2to1(i, j-1);
			if (sites[left])
				connections.union(index, left);	
		}		

		if (j != n) {
			int right = convert2to1(i, j+1);
			if (sites[right])
				connections.union(index, right);	
		}

	}

	public boolean isOpen(int i, int j) {
		int index = convert2to1(i, j);		
		return sites[index];
	}
	
	public boolean isFull(int i, int j) {
		int index = convert2to1(i, j);		
		return isOpen(i, j) && connections.connected(index, top);
	}
	
	public boolean percolates() {
		return connections.connected(bottom, top);
	}
	
	private int convert2to1(int i, int j) {
		if (i < 1 || i > n || j < 1 || j > n)
			throw new IndexOutOfBoundsException();
		int index = n*(i-1)+(j-1);
		return index;
	}
	
}