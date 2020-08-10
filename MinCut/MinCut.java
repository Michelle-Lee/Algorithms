/**
 * MinCut implements Karger's Minimum Cut algorithm using Random Selection
 * to find the smallest subset of edges in a Graph
 */

import java.util.*;
import java.util.Scanner;
import java.util.Random;
import java.io.File;
import java.io.FileNotFoundException;

public class MinCut2 {
    int vertices;
    MyGraph graph;
    // Create graph using input .txt file (tab delimited)
    public MinCut2(int v) throws FileNotFoundException {
        this.vertices = v;
        graph = new MyGraph(vertices+1);
        
        File f = new File("kargerMinCut.txt");
        Scanner scn = new Scanner(f);
        for (int i = 1; i <= vertices; i++){
            String[] vertInLine = scn.nextLine().split("\t");
            List<Integer> ls = new ArrayList<Integer>();
            // iterate through each line
            for(int idx=1; idx<vertInLine.length;idx++){
                ls.add(Integer.parseInt(vertInLine[idx]));
            }
            // iterate through each element in the line
            for(int j : ls){
                graph.addEdge(i, j);
            }
        }
        scn.close();
    }
    
    // Minimum Cut 
    public int findMinCut2(int numV){
        int numVertices = numV;
        int mc = 0;
        while(numVertices > 2){
           Random rand = new Random();
           
           // randomly choose a vertex/row in adjacency list
           int v1 = rand.nextInt(numV);
            
           // randomly choose an element in chosen row
           int len = graph.get(v1).size();
           if(len == 0){
               continue;
           }
           int index = rand.nextInt(len);
           int v2 = graph.get(v1).get(index);
           
           // merge/contract the two random vertices
           graph.contract(v1,v2);
           numVertices--;
        }
        for (int iter=0; iter<200; iter++){
            
            if(graph.get(iter).size() > 0){
                mc = graph.get(iter).size();
            }
        }
        return mc;
    }
    
    public static void main(String args[])throws FileNotFoundException {
        int v = 200;
        int trials = 100;
        int minimum = 9999;
        for (int t = 0; t < trials; t++){
            MinCut2 g = new MinCut2(v);
            int m = g.findMinCut2(v);
            if (m < minimum){
                minimum = m;
            }
        }
        System.out.println("Absolute Minimum Cut: " + minimum);
    }
}
