/*
package KDTrees;


import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.SET;
import edu.princeton.cs.introcs.StdDraw;

*/
import java.util.HashSet;
import java.util.Set;

public class KdTree {
	private Node root;
	
	private class Node {
		private Point2D point;
		private Node left, right;
		private int N;
		private boolean isVertical;
		//private RectHV rect;
		
		private Node(Point2D p, boolean isV) {
			point = p;
			left = null;
			right = null;
			N = 1;
			isVertical = isV;
		}	
		

	}
	
	private int size(Node x) {
		if (x == null) return 0;
		return x.N;
	}
	public KdTree() {
		root = null;
	}
	
	public boolean isEmpty() {
		return root == null;
	}
	
	public int size() {
		return size(root);
	}
	
	private Node insert(Node n, Point2D p, boolean isV) {
		if (n == null) 
			return new Node(p, isV);
		if (p.equals(n.point))
			return n;
		if (n.isVertical)
			if (p.x() < n.point.x())
				n.left = insert(n.left,p, !n.isVertical);
			else n.right = insert(n.right, p, !n.isVertical);
		else
			if (p.y() < n.point.y())
				n.left = insert(n.left,p, !n.isVertical);
			else n.right = insert(n.right, p, !n.isVertical);
		
		n.N = size(n.left) + size(n.right) + 1;
		return n;
	}
	
	public void insert(Point2D p) {
		root = insert(root, p, true);
	}
	
	private boolean contains(Node n, Point2D p) {
		if (n == null)
			return false;
		if (n.point.equals(p))
			return true;
		if (n.isVertical)
			if (p.x() < n.point.x())
				return contains(n.left, p);
			else return contains(n.right, p);
		else
			if (p.y() < n.point.y())
				return contains(n.left, p);
			else return contains(n.right, p);
	}
	
	public boolean contains(Point2D p) {
		return contains(root, p);
	}
	
	private void draw(Node n, RectHV rect) {
		StdDraw.setPenColor(StdDraw.BLACK);
		StdDraw.setPenRadius(.01);
		n.point.draw();
		StdDraw.setPenRadius();
		
		if (n.isVertical) {
			StdDraw.setPenColor(StdDraw.RED);
			StdDraw.line(n.point.x(), rect.ymin(), n.point.x(), rect.ymax());	
			if (n.left != null)
				draw(n.left, new RectHV(rect.xmin(),rect.ymin(),n.point.x(), rect.ymax()));
			if (n.right != null)
				draw(n.right, new RectHV(n.point.x(),rect.ymin(),rect.xmax(), rect.ymax()));
		}
		else {
			StdDraw.setPenColor(StdDraw.BLUE);
			StdDraw.line(rect.xmin(), n.point.y(), rect.xmax(), n.point.y());
			if (n.left != null)
				draw(n.left, new RectHV(rect.xmin(),rect.ymin(), rect.xmax(), n.point.y()));
			if (n.right != null)
				draw(n.right, new RectHV(rect.xmin(), n.point.y(), rect.xmax(), rect.ymax()));
		}
		
		
	}
	
	public void draw() {
		draw (root, new RectHV(0.0, 0.0, 1.0, 1.0));
	}
	
	private void range(Node n, Set<Point2D> set, RectHV rect) {
		if ( n == null) 
			return;
		
		if (rect.contains(n.point))
			set.add(n.point);
		
		if (n.isVertical) {
			if (rect.xmin() <= n.point.x()) 
				range(n.left, set, rect);
			if (rect.xmax() >= n.point.x())
				range(n.right, set, rect);
		}
		else {
			if (rect.ymin() <= n.point.y())
				range(n.left, set, rect);
			if (rect.ymax() >= n.point.y())	
				range(n.right, set, rect);
		}	
	}
	public Iterable<Point2D> range(RectHV rect) {
		Set<Point2D> set = new HashSet<Point2D>();
		range(root, set, rect);
		return set;
		
	}
	
	private Point2D nearest(Point2D p, Node n, Point2D nearpoint, double neardist) {
		if (n == null)
			return null;
		
		Point2D currnearpoint = nearpoint;
		double currneardist = neardist;
		if (nearpoint == null || n.point.distanceSquaredTo(p) < neardist) {
			currnearpoint = n.point;
			currneardist = n.point.distanceSquaredTo(p);
		}
		Point2D leftNear, rightNear;
		
		if ((n.isVertical && p.x() <= n.point.x()) || (!n.isVertical && p.y() <= n.point.y())) {
			leftNear = nearest(p, n.left, currnearpoint, currneardist);
			if (leftNear != null && leftNear.distanceSquaredTo(p) < currneardist) {
				currnearpoint = leftNear;
				currneardist = leftNear.distanceSquaredTo(p);
			}
			double distToLine = 0.0;
			if (n.isVertical)
				distToLine = Math.pow(Math.abs(p.x() - n.point.x()), 2);
			else
				distToLine = Math.pow(Math.abs(p.y() - n.point.y()), 2);
			if (distToLine < currneardist){
				rightNear = nearest(p, n.right, currnearpoint, currneardist);
				if (rightNear != null && rightNear.distanceSquaredTo(p) < currneardist) {
					currnearpoint = rightNear;
					currneardist = rightNear.distanceSquaredTo(p);
				}
			}
		}
				
		if ((n.isVertical && p.x() > n.point.x()) || (!n.isVertical && p.y() > n.point.y())) {
			rightNear = nearest(p, n.right, currnearpoint, currneardist);
			if (rightNear != null && rightNear.distanceSquaredTo(p) < currneardist) {
				currnearpoint = rightNear;
				currneardist = rightNear.distanceSquaredTo(p);
			}
			double distToLine = 0.0;
			if (n.isVertical)
				distToLine = Math.pow(Math.abs(p.x() - n.point.x()), 2);
			else
				distToLine = Math.pow(Math.abs(p.y() - n.point.y()), 2);
			if (distToLine < currneardist){
				leftNear = nearest(p, n.left, currnearpoint, currneardist);
				if (leftNear != null && leftNear.distanceSquaredTo(p) < currneardist) {
					currnearpoint = leftNear;
					currneardist = leftNear.distanceSquaredTo(p);
				}
			}
		}				
		
		return currnearpoint;
				
	}
	public Point2D nearest(Point2D p) {
		return nearest(p, root, null, 0.0);
		
	}
}