
/*************************************************************************
 * Name:
 * Email:
 *
 * Compilation:  javac Point.java
 * Execution:
 * Dependencies: StdDraw.java
 *
 * Description: An immutable data type for points in the plane.
 *
 *************************************************************************/

package Algorithms.PatternRecognition;

import edu.princeton.cs.introcs.*;
import java.util.Comparator;

public class Point implements Comparable<Point> {

    // compare points by slope
    public final Comparator<Point> SLOPE_ORDER = new slopeOrder();       // YOUR DEFINITION HERE

    private final int x;                              // x coordinate
    private final int y;                              // y coordinate

    // create the point (x, y)
    public Point(int x, int y) {
        /* DO NOT MODIFY */
        this.x = x;
        this.y = y;
    }

    // plot this point to standard drawing
    public void draw() {
        /* DO NOT MODIFY */
        StdDraw.point(x, y);
    }

    // draw line between this point and that point to standard drawing
    public void drawTo(Point that) {
        /* DO NOT MODIFY */
        StdDraw.line(this.x, this.y, that.x, that.y);
    }

    // slope between this point and that point
    public double slopeTo(Point that) {
        /* YOUR CODE HERE */
    	int dx = that.x - this.x;
    	int dy = that.y - this.y;
    	
    	if (dx == 0 && dy == 0)
    		return Double.NEGATIVE_INFINITY;
    	if (dx == 0)
    		return Double.POSITIVE_INFINITY;
    	if (dy == 0)
    		return 0.0;
    	return ((double) dy) / ((double) dx);
    }

    // is this point lexicographically smaller than that one?
    // comparing y-coordinates and breaking ties by x-coordinates
    public int compareTo(Point that) {
        /* YOUR CODE HERE */
    	if (this.x < that.x)
    		return -1;
    	if (this.x > that.x)
    		return 1;
    	if (this.y < that.y)
    		return -1;
    	if (this.y > that.y)
    		return 1;
    	return 0;
    }

    // return string representation of this point
    public String toString() {
        /* DO NOT MODIFY */
        return "(" + x + ", " + y + ")";
    }

    private class slopeOrder implements Comparator<Point> {

		public int compare(Point o1, Point o2) {
			double slope1 = slopeTo(o1);
			double slope2 = slopeTo(o2);
			return Double.compare(slope1, slope2);
		}
    	
    }
    
    
    // unit test
    public static void main(String[] args) {
        /* YOUR CODE HERE */
    }
    

}
