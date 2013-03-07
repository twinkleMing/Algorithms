
package Algorithms.PuzzleEight;

import java.util.Arrays;
import java.util.Iterator;

import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Stack;
public class Board {
	private int[][] board;
	private int N;
	private int i0;
	private int j0;
	public Board(int[][] blocks) {
		if (blocks.length != blocks[0].length)
			throw new ArrayIndexOutOfBoundsException();
		N = blocks.length;
		board = new int[N][N];
		for (int i = 0; i < N; i++)
			for (int j = 0; j < N; i++) {
				board[i][j] = blocks[i][j];
				if (board[i][j] == 0) {
					i0 = i;
					j0 = j;
				}
			}
			
	}
	
	public int dimensions() {
		return N;
	}
	
	public int hamming() {
		int sum = 0;
		for (int i = 0; i < N; i++)
			for (int j = 0; j < N; j++)
				if (board[i][j] != 0 && board[i][j] != N*i+j+1)
					sum++;
		return sum;
	}
	
	public int manhattan() {
		int sum = 0;
		for (int i = 0; i < N; i++)
			for (int j = 0; j < N; j++)
				if (board[i][j] != 0 && board[i][j] != N*i+j+1) {
					int ii = (board[i][j]-1)/N;
					int jj = board[i][j]-1-N*ii;
					sum+= Math.abs(i-ii);
					sum+= Math.abs(j-jj);
				}
					
		return sum;		
	}
	
	public boolean isGoal() {
		return hamming() == 0;
	}
	
	public boolean equals(Object y) {
		if (! (y instanceof Board))
			return false;
		for (int i = 0; i < N; i++)
			for (int j = 0; j < N; j++)
				if (board[i][j] != ((Board)y).board[i][j])
					return false;
		return true;
	}
	
	public Iterable<Board> neighbors() {
		Stack<Board> neighs = new Stack<Board>();
		int i, j;
		for (i = 0; i < N; i++)
			for (j = 0; j < N; j++)
				if (board[i][j] == 0)
					break;
		
	}
	

}