//package bwDataCompression;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
/*
import edu.princeton.cs.algs4.HexDump;
import  edu.princeton.cs.introcs.*;

*/
public class MoveToFront {
	
	private static int R = 256;
	private static class Node {
		int value;
		Node next;
		public Node(int v, Node n) {
			value = v;
			next = n;
		}
	}
	private static Node start;
	
	public static void encode() {
		start = new Node (-1, null);
		Node p = start;
		for (int i = 0; i < R; i++) {
			p.next = new Node(i,null);
			p = p.next;
		}
		while (!BinaryStdIn.isEmpty()) {
			char c = BinaryStdIn.readChar();
			int i = 0;
			p = start;
			while (p.next.value != c) {
				i++;
				p = p.next;
			}
			BinaryStdOut.write(i,8);
			BinaryStdOut.flush();
			if (i != 0) {
				Node q = p.next;
				p.next = p.next.next;
				q.next = start.next;
				start.next = q;				
			}

			
		}

		
		
	}
	
	public static void decode() {
		start = new Node (-1, null);
		Node p = start;
		for (int i = 0; i < R; i++) {
			p.next = new Node(i,null);
			p = p.next;
		}
		while (!BinaryStdIn.isEmpty()) {
			char c = BinaryStdIn.readChar();
			//StdOut.printf("%02x", c & 0xff);
			//BinaryStdOut.flush();		
			p = start;
			for (int i = 0; i < c; i++) {
				p = p.next;
			}
			BinaryStdOut.write(p.next.value);
			BinaryStdOut.flush();

			if (c != 0) {
				Node q = p.next;
				p.next = p.next.next;
				q.next = start.next;
				start.next = q;				
			}

		}		
	}
	
	
	public static void main(String[] args) throws FileNotFoundException {
		if (args.length != 1)
			throw new IllegalArgumentException();
		//System.setIn(new FileInputStream("src/bwDataCompression/abra_result.txt"));
		//System.setIn(new FileInputStream("src/bwDataCompression/abra.txt"));
		char a = args[0].charAt(0);
		if (a == '-') encode();
		else if (a == '+') decode();
		else throw new IllegalArgumentException();
	}
}