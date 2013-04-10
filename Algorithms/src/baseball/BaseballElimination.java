//package baseball;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Hashtable;
/*
import edu.princeton.cs.algs4.FlowEdge;
import edu.princeton.cs.algs4.FlowNetwork;
import edu.princeton.cs.algs4.FordFulkerson;
import edu.princeton.cs.introcs.In;
import edu.princeton.cs.introcs.StdOut;
*/
public class BaseballElimination {
	private int N;
	private String[] teams;
	private Hashtable<String, Integer> team_indexes;
	private int[] wins;
	private int[] losses;
	private int[] remainings;
	private int[][] games;
	private int[] isEliminated;
	private HashSet<String>[] subset;
	private int max_wins_index;
	
	public BaseballElimination(String filename) {
		In in = new In(filename);
		N = Integer.parseInt(in.readLine());
		String line = null;
		max_wins_index = 0;
		wins = new int[N];
		losses = new int[N];
		remainings = new int[N];
		games = new int[N][N];
		isEliminated = new int[N];
		subset = new HashSet[N];
		teams = new String[N];
		team_indexes = new Hashtable<String, Integer>();
		
		for (int i = 0; i < N; i++) {
			line = in.readLine();
			String[] data = line.split("\\s+");
			teams[i] = data[0];
			team_indexes.put(teams[i], i);
			isEliminated[i] = 0;
			wins[i] = Integer.parseInt(data[1]);
			if (wins[i] > wins[max_wins_index]) max_wins_index = i;
			losses[i] = Integer.parseInt(data[2]);
			remainings[i] = Integer.parseInt(data[3]);
			for (int j = 0; j < N; j++) {
				games[i][j] = Integer.parseInt(data[4+j]);
			}
		}
		in.close();
	}
	
	public int numberOfTeams() {
		return N;
	}
	
	public Iterable<String> teams() {
		return Arrays.asList(teams);
	}
	
	public int wins(String team) {
		if (!team_indexes.containsKey(team))
			throw new IllegalArgumentException();
		return wins[team_indexes.get(team)];
	}
	
	public int losses(String team) {
		if (!team_indexes.containsKey(team))
			throw new IllegalArgumentException();		
		return losses[team_indexes.get(team)];
	}
	
	public int remaining(String team) {
		if (!team_indexes.containsKey(team))
			throw new IllegalArgumentException();
		return remainings[team_indexes.get(team)];
	}
	
	public int against(String team1, String team2) {
		if (!team_indexes.containsKey(team1) || !team_indexes.containsKey(team2))
			throw new IllegalArgumentException();
		return games[team_indexes.get(team1)][team_indexes.get(team2)];
	}
	
	private void checkElimination(int k) {
		if (wins[k]+remainings[k] < wins[max_wins_index]) {
			isEliminated[k] = -1;
			subset[k] = new HashSet<String>();
			subset[k].add(teams[max_wins_index]);
			return;
		}
		int game_vertices = (N-1)*(N-2)/2;
		int team_vertices = N-1;
		FlowNetwork network = new FlowNetwork(2 + game_vertices + team_vertices);
		int index  = 1;
		for (int i = 0; i < N; i++) {
			if (i == k) continue;
			for (int j = i+1; j < N; j++) {
				if (j == k) continue;
				network.addEdge(new FlowEdge(0, index, games[i][j]));
				if (i < k) network.addEdge(new FlowEdge(index, game_vertices+1+i, Double.MAX_VALUE));
				else network.addEdge(new FlowEdge(index, game_vertices+i, Double.MAX_VALUE));
				if (j < k) network.addEdge(new FlowEdge(index, game_vertices+1+j, Double.MAX_VALUE));
				else network.addEdge(new FlowEdge(index, game_vertices+j, Double.MAX_VALUE));
				index++;
			}
		}
		for (int i = 0; i < N; i++) {
			if (i < k) network.addEdge(new FlowEdge(i+game_vertices+1, game_vertices+team_vertices+1, wins[k]+remainings[k]-wins[i]));
			if (i > k) network.addEdge(new FlowEdge(i+game_vertices, game_vertices+team_vertices+1, wins[k]+remainings[k]-wins[i]));
		}
		
		//StdOut.println(network);
		
		FordFulkerson solution = new FordFulkerson(network, 0, game_vertices+team_vertices+1);
		
        
		double EPSILON = 1E-11;
		for (FlowEdge e : network.adj(0)) {
			if (Math.abs(e.capacity() - e.flow()) > EPSILON) {
				isEliminated[k] = -1;
				subset[k] = new HashSet<String>();
				for (int i = 0; i < N; i++) {
					if (i < k) {
						if (solution.inCut(i+game_vertices+1)) 
							subset[k].add(teams[i]);
					}
					if (i > k) {
						if (solution.inCut(i+game_vertices)) 
							subset[k].add(teams[i]);
					}
						
				}
				return;
			}
				
		}
		isEliminated[k] = 1;	

	}
	public boolean isEliminated(String team) {
		if (!team_indexes.containsKey(team))
			throw new IllegalArgumentException();
		int k = team_indexes.get(team);
		if (isEliminated[k] == 0)
			checkElimination(k);
		return (isEliminated[k] < 0);
	}
	
	public Iterable<String> certificateOfElimination(String team) {
		if (!team_indexes.containsKey(team))
			throw new IllegalArgumentException();
		int k = team_indexes.get(team);
		if (isEliminated[k] == 0)
			checkElimination(k);
		return subset[k];
	}
	public static void main(String[] args) {
	    BaseballElimination division = new BaseballElimination(args[0]);
	    for (String team : division.teams()) {
	        if (division.isEliminated(team)) {
	            StdOut.print(team + " is eliminated by the subset R = { ");
	            for (String t : division.certificateOfElimination(team))
	                StdOut.print(t + " ");
	            StdOut.println("}");
	        }
	        else {
	            StdOut.println(team + " is not eliminated");
	        }
	    }
	}
}