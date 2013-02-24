package Algorithms.Percolation;
import edu.princeton.cs.introcs.StdRandom;
public class PercolationStats 
{
	double[] results;
	
	public PercolationStats ( int N, int T ) {
		results = new double[T];
		for (int i = 0; i < T; i++) {
			results[i] = simperco(N);
		}
	}
	public double mean()
	public double stddev()
	public double confidenceLo()
	public double confidenceHi()
	public static void main(String[] args)
	
	private double simperco(int N) {
		Percolation p = new Percolation(N);
		
		while (!p.percolates()) {
			int i = StdRandom.uniform(N+1);
			int j = StdRandom.uniform(N+1);
			p.open(i, j);			
		}
		return p.fraction();
	}
	
}