/**
 * This is a Graph classed used in MinCut
 */

import java.util.LinkedList;
import java.util.ArrayList;
import java.util.List;

public class MyGraph {
    int vertex;
    LinkedList<LinkedList<Integer>> list;
    public MyGraph(int vertex){
        this.vertex = vertex;
        list = new LinkedList<>();
        for (int i = 0; i < vertex; i++){
            list.add(i, new LinkedList<Integer>());
        }
    }
    public void addEdge(int source, int destination){
        list.get(source).add(destination);
    }
    public LinkedList<Integer> get(int v){
        return list.get(v);
    }
    public void removeVertex(int row, Object element){
        list.get(row).remove(element);
    }
    public void contract(int x, int y){
        /** 
         * Append elements in vertex y LinkedList to vertex x LinkedList
         * Do not add elements equal to x's value (x+1) to avoid self-loops
         * Create new list, not just a reference to list (to avoid concurrent modification)
         */
        
        List<Integer> yVertices = new ArrayList<Integer>(this.get(y));
        for (int item : yVertices){
            if(item == x){
                this.removeVertex(x,y);         // for ever y item equal to x, x has an element equal to y; must be removed (self-loop)
                this.removeVertex(y,item);
                continue;
            }
            if(item != x & item != y){
                this.get(x).addLast(item);      // add elements of y to x
            }
            
            // re-navigate y's connections to be connected with x instead
            int itemSize = this.get(item).size();
            for (int j = 0; j < itemSize; j++){
                if (this.get(item).get(j) == y){
                    this.get(item).set(j, x);
                }
            }
            
            this.removeVertex(y,item);
        }
    }
}

