import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class Kosaraju extends Graph {
	Graph g;
	Deque<Integer> finishTime;
	boolean[] visited;
	int[] leader;
	int s;
	int n;
	
	public Kosaraju(Graph graph, int size) {
		finishTime = new ArrayDeque<Integer>();;
		visited = new boolean[size+1];
		leader = new int[size+1];
		s = 0;
		n = size;
		g = graph;
	}
	
	public void kosaraju() {
		Graph gRev = g.transpose();
		dfsLoop(gRev);
		System.out.println("1st Pass Complete.");
		dfsLoopInOrder(g);
		System.out.println("2nd Pass Complete.");
		System.out.println("5 Largest SCC Sizes: " + Arrays.toString(sccSize()));
	}
	
	// DFS loop for 1st pass (on reverse graph)
	public void dfsLoop(Graph graph) {
		visited = new boolean[n+1];
		// assume nodes labeled 1 to n
		for (int i = n; i > 0; i--) {
			if (!visited[i]) {
				s = i;
				dfs(graph, i);
				
			}
		}
	}
	
	// DFS loop for second pass of the graph given order of finishing time
	public void dfsLoopInOrder(Graph graph) {
		visited = new boolean[n+1];
		leader = new int[n+1];
		while(!finishTime.isEmpty()) {
			int currNode = finishTime.poll();
			if (!visited[currNode]) {
				s = currNode;
				dfsInOrder(graph, currNode);
			}
		}
	}

	// DFS method that records finish time order
	public void dfs(Graph graph, int startNode) {
		visited[startNode] = true;
		
		for(int j : graph.get(startNode)) {
			if (!visited[j]) {
				dfs(graph,j);
			}
		}
		finishTime.push(startNode);
	}
	
	// DFS method that records node leaders/parents of the SCC but does NOT mess with finishing time order
	public void dfsInOrder(Graph graph, int startNode) {
		leader[startNode] = s;
		visited[startNode] = true;
		
		for(int j : graph.get(startNode)) {
			if (!visited[j]) {
				dfsInOrder(graph,j);
			}
		}
	}
	
	// Find the five largest SCCs in the graph
	public int[] sccSize() {
		int[] topFive = new int[5];
		int[] counter = new int[n+1];
		
		// tally the leader (parent) nodes in the array
		for(int item : leader) {
			if (item != 0) {
				counter[item] += 1;
			}
		}
		
		// account for graphs with less than 5 SCCs
		int lim;
		if (counter.length <= 5) {
			lim = counter.length-1; 
		} else {
			lim = 5;
		}
		
		// find top 5
		Arrays.sort(counter);
		for (int i = 0; i<lim; i++) {
			topFive[i] = counter[n-i];
		}
		return topFive; // note, this variable will only give top five sizes and is not representative of the leader node (represented by the indices) because it has been sorted
	}
	
	public static void main(String args[]) {
		int n = 875714;
		Graph graph = new Graph(n);
		try {
			graph.importList("SCC.txt"); // should get [434821, 968, 459, 313, 211]
		} catch (Exception e){
			e.printStackTrace();
		}
		
		Kosaraju ko = new Kosaraju(graph, n);
		ko.kosaraju();
	}

}
