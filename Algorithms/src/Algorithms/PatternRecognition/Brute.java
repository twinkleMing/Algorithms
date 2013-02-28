package Algorithms.PatternRecognition;

import java.util.Arrays;

import edu.princeton.cs.introcs.*;



public class Brute {
	private void sortPoints(Point[] points) {
		
	}
	private static String arrayToString(Point[] points) {
		if (points.length != 4)
			throw new ArrayIndexOutOfBoundsException();
		String str = points[0].toString();
		for (int i = 1; i < points.length; i++) {
			str = str + " -> " + points[i].toString();
		}
		return str;
	}
	private static void arrayDrawTo(Point[] points) {
		if (points.length != 4)
			throw new ArrayIndexOutOfBoundsException();
		
		for (int i = 1; i < points.length; i++) {
			points[0].drawTo(points[i]);
		}	
	}
	public static void main (String[] args) {
		if (args.length != 1) {
			StdOut.println("Number of parameters is not correct!");
			return;
		}
		String filename = args[0];
		int[] xy = In.readInts(filename);
		int n = xy[0];
		if (xy.length != n*2+1)
			throw new ArrayIndexOutOfBoundsException();
		
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        StdDraw.show(0);
        
		Point[] points = new Point[n];
		for (int i = 0; i < n; i++) {	
			points[i] = new Point(xy[2*i+1], xy[2*i+2]);
			points[i].draw();
		}
		Arrays.sort(points);
		
		Point[] arrayPoints = new Point[4];
		for (int i = 0; i < n; i++) {
			arrayPoints[0] = points[i];
			for (int j = i+1; j < n; j++) {
				arrayPoints[1] = points[j];
				double pq = arrayPoints[0].slopeTo(arrayPoints[1]);
				for (int k = j+1; k < n; k++) {
					arrayPoints[2] = points[k];
					double pr = arrayPoints[0].slopeTo(arrayPoints[2]);
					if ( pq != pr) 
						continue;
					for (int l = k+1; l < n; l++) {
						arrayPoints[3] = points[l];
						double ps = arrayPoints[0].slopeTo(arrayPoints[3]);
						if (pq == ps) {
							StdOut.println(arrayToString(arrayPoints));
							arrayPoints[0].drawTo(arrayPoints[3]);
						}
					}
				}
			}
		}
		 StdDraw.show(0);
	}
}