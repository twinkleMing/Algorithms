package KDTrees;

import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.SET;
import edu.princeton.cs.introcs.StdDraw;



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
			draw(n.left, new RectHV(rect.xmin(),rect.ymin(),n.point.x(), rect.ymax()));
			draw(n.right, new RectHV(n.point.x(),rect.ymin(),rect.xmax(), rect.ymax()));
		}
		else {
			StdDraw.setPenColor(StdDraw.BLUE);
			StdDraw.line(rect.xmin(), n.point.y(), rect.xmax(), n.point.y());	
			draw(n.left, new RectHV(rect.xmin(),rect.ymin(), rect.xmax(), n.point.y()));
			draw(n.right, new RectHV(rect.xmin(), n.point.y(), rect.xmax(), rect.ymax()));
		}
		
		
	}
	
	public void draw() {
		draw (root, new RectHV(1.0, 1.0, 1.0, 1.0));
	}
	
	private void range(Node n, SET<Point2D> set, RectHV rect) {
		if (rect.contains(n.point))
			set.add(n.point);
		
		if (n.isVertical) {
			RectHV subrect = new RectHV(rect.xmin(),rect.ymin(),n.point.x(), rect.ymax());
			if (rect.intersects(subrect)) {
				//range(n.left, set, rect.)
			}
			draw(n.left, ));
			draw(n.right, new RectHV(n.point.x(),rect.ymin(),rect.xmax(), rect.ymax()));
		}
		else {
			StdDraw.setPenColor(StdDraw.BLUE);
			StdDraw.line(rect.xmin(), n.point.y(), rect.xmax(), n.point.y());	
			draw(n.left, new RectHV(rect.xmin(),rect.ymin(), rect.xmax(), n.point.y()));
			draw(n.right, new RectHV(rect.xmin(), n.point.y(), rect.xmax(), rect.ymax()));
		}		
	}
	public Iterable<Point2D> range(RectHV rect) {
		
	}
}