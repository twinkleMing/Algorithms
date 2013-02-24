
//package Algorithms.Percolation;
//import edu.princeton.cs.introcs.StdOut;
//import edu.princeton.cs.introcs.StdRandom;
//import edu.princeton.cs.introcs.StdStats;

public class PercolationStats 
{
	private double[] results;
	private double mean;
	private double std;
	
	public PercolationStats(int N, int T) {

		if (N <= 0 || T <= 0)
			throw new IllegalArgumentException();		

		mean = Double.NaN;
		std = Double.NaN;
		results = new double[T];
		for (int i = 0; i < T; i++) {
			results[i] = simPerco(N);
		}
	}
	
	private double simPerco(int N) {
		Percolation p = new Percolation(N);
		int numopen = 0;
		while (!p.percolates()) {
			int i = StdRandom.uniform(N);
			int j = StdRandom.uniform(N);
			p.open(i+1, j+1);
			numopen++;
		}
		//StdOut.println(p.fraction());
		return ((double) numopen) / ((double) N*N);
	}
	
	public double mean() {
		if (results == null || results.length == 0)
			return Double.NaN;
	
		if (!Double.isNaN(mean))
			return mean;

		mean = StdStats.mean(results);	
		return mean;
	}
	
	public double stddev() {
		if (results == null || results.length == 0 || results.length == 1)
			return Double.NaN;
		
		if (!Double.isNaN(std))
			return std;
		
		std = StdStats.stddev(results);
		return std;
	}
	
	public double confidenceLo() {
		if (results == null || results.length == 0)
			return Double.NaN;
		
		if (Double.isNaN(mean))
			mean();
		if (Double.isNaN(std))
			stddev();
		return (mean - 1.96*std/Math.sqrt((double) results.length));
	}
	
	public double confidenceHi() {
		if (results == null || results.length == 0)
			return Double.NaN;
		
		if (Double.isNaN(mean))
			mean();
		if (Double.isNaN(std))
			stddev();
		return (mean + 1.96*std/Math.sqrt((double) results.length));
	}
	
	
	public static void main(String[] args) {
		if (args.length != 2) {
			StdOut.println("Number of arguments should be two!");
			return;
		}
		int N = Integer.parseInt(args[0]);
		int T = Integer.parseInt(args[0]);
		PercolationStats expPerco = new PercolationStats(N, T);
		StdOut.println("mean                    = "+Double.toString(expPerco.mean()));
		StdOut.println("stddev                  = "+Double.toString(expPerco.stddev()));
		StdOut.println("95% confidence interval = "+Double.toString(expPerco.confidenceLo())+", "+Double.toString(expPerco.confidenceHi()));
	}
	

	
}