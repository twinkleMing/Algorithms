package KDTrees;

import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.SET;
public class PointSET {
	SET<Point2D> set;
	
	public PointSET() {
		set = new SET<Point2D>();
	}
	
	public boolean isEmpty() {
		return set.isEmpty();
	}
	
	public int size() {
		return set.size();
	}
	
	public void insert(Point2D p) {
		set.add(p);
	}
	
	public boolean contains(Point2D p) {
		return set.contains(p);
	}
	
	public void draw() {
		for (Point2D p : set) 
			p.draw();
	}
	
	public Iterable<Point2D> range(RectHV rect) {
		SET<Point2D> ranged = new SET<Point2D>();
		for (Point2D p : set) 
			if (rect.contains(p))
				ranged.add(p);
		return ranged;		
	}
	
	public Point2D nearest(Point2D p) {
		Point2D neighbor = null;
		double d = Double.MAX_VALUE;
		for (Point2D pp : set) {
			if (pp.distanceTo(p) < d) {
				d = pp.distanceTo(p);
				neighbor = pp;
			}
		}
		return neighbor;
	}
}