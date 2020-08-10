import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class Graph {
	ArrayList<LinkedList<Integer>> graph;
	int numNodes;
	
	public Graph() {
		graph = new ArrayList<>();
	}
	
	public Graph(int n) {
		numNodes = n+1; 	// zero is ignored for ease of indexing
		graph = new ArrayList<>();
		for (int i = 0; i < numNodes; i++) {
			graph.add(i, new LinkedList<Integer>());
		}
	}
	
	public void add(int tail, int head) {
		graph.get(tail).add(head);
	}
	public boolean hasEdge(int index) {
		return (graph.get(index) == null);
	}
	public LinkedList<Integer> get(int index) {
		return graph.get(index);
	}
	
	// import .txt file of graph 
	public void importList(String filename) throws FileNotFoundException{
		File f = new File(filename);
		Scanner sc = new Scanner(f);
		
		// this parser loop assumes there is ONE edge per line in the file
		while (sc.hasNext()) {
			String[] line = sc.nextLine().split("\\s+");
			this.add(Integer.parseInt(line[0]), Integer.parseInt(line[1]));
		}
		sc.close();
	}
	
	// reverse directed edges of graph
	public Graph transpose() {
		Graph revG = new Graph(numNodes);
		for (int i = 1; i < numNodes; i++) {
			for(int val : graph.get(i)) {
				revG.add(val,i);
			}
		}
		return revG;
	}
}
