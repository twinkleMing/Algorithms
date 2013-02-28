import java.util.Arrays;

import edu.princeton.cs.introcs.In;
import edu.princeton.cs.introcs.StdDraw;
import edu.princeton.cs.introcs.StdOut;

public class Fast {
	
	
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
			Point[] arraypoints = points.clone();
			Arrays.sort(arraypoints, i+1, n, points[i].SLOPE_ORDER);
			int k = 1;
			double slope = points[i].slopeTo(points[i+1]);
			for (int j = i+2; j < n; j++) {
				double tmp = points[i].slopeTo(points[j]);
				if (tmp == slope) {
					k++;
				}
				else {
					if (k >= 3) 
				}
			}
			
		}
	}
}