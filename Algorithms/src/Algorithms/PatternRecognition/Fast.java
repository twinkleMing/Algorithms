package Algorithms.PatternRecognition;

import java.util.Arrays;
import edu.princeton.cs.introcs.In;
import edu.princeton.cs.introcs.StdDraw;
import edu.princeton.cs.introcs.StdOut;

public class Fast {
	
	private static String arrayToString(Point[] points, int start, int end) {
		if ((end-start) < 2)
			throw new ArrayIndexOutOfBoundsException();
		String str = points[0].toString();
		for (int i = start; i <= end; i++) {
			str = str + " -> " + points[i].toString();
		}
		return str;
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
		
		
		for (int i = 0; i < points.length-3; i++) {
			Point[] arraypoints = Arrays.copyOf(points, points.length);
			Arrays.sort(arraypoints, 0, n, points[i].SLOPE_ORDER);
			boolean flagDrawed = false;
			double slope = Double.NEGATIVE_INFINITY;
			int k = 0;
			int j;
			for (j = 1; j < n; j++) {
				double nextslope = arraypoints[0].slopeTo(arraypoints[j]);

				if (nextslope == slope) {
					k++;
					if (!flagDrawed)
						flagDrawed = (arraypoints[j].compareTo(arraypoints[0]) < 0);
				}

				else {
					if (k >= 3 && !flagDrawed) {
						StdOut.println(arrayToString(arraypoints, j-k, j-1));
						arraypoints[j-1].drawTo(arraypoints[0]);
					}
					k = 1;
					slope = nextslope;
					flagDrawed = (arraypoints[j].compareTo(arraypoints[0]) < 0);
				}				
				
			}
			if (k >= 3 && !flagDrawed) {
				StdOut.println(arrayToString(arraypoints, j-k, j-1));
				arraypoints[j-1].drawTo(arraypoints[0]);
			}	
			
			
		}
		StdDraw.show(0);	
	}
		
}