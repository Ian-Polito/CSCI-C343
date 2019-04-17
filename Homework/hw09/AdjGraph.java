/*
 * C343 Summer 2018
 * Homework 9
 * Ian Polito
 * ipolito
 */

// a simple implementation for graphs with adjacency lists
// lab 17 solution
// C343 Summer 2018
// we use a few java.util classes:
import java.util.*;

public class AdjGraph implements Graph {

    // is it a directed graph (true or false) :
    private boolean digraph;

    private int totalNodes;
     // all the nodes in the graph:
    private Vector<String> nodeList;

    private int totalEdges;    
     // all the adjacency lists, one for each node in the graph:
    private Vector<LinkedList<Integer>>  adjList;

    // all the weights of the edges, one for each node in the graph:
    private Vector<LinkedList<Integer>> adjWeight;
    
    // every visited node:
    private Vector<Boolean> visited;
    // list of nodes pre-visit:
    private Vector<Integer> nodeEnum;

    // array of to-be-distances:
    private int[] d;


    public AdjGraph() {
        init();
    }

    public AdjGraph(boolean ifdigraph) {
        init();
        digraph = ifdigraph;
    }

    public void init() {
        nodeList = new Vector<String>(); 
        adjList = new Vector<LinkedList<Integer>>(); 
        adjWeight = new Vector<LinkedList<Integer>>();
        visited = new Vector<Boolean>();
        nodeEnum = new Vector<Integer>();
        totalNodes = totalEdges = 0;
        d = new int[totalNodes];
        digraph = false;
    }

    // set vertices
    public void setVertices(String[] nodes) {
        for (int i = 0; i < nodes.length; i ++) {
            nodeList.add(nodes[i]);
            adjList.add(new LinkedList<Integer>());
            adjWeight.add(new LinkedList<Integer>());
            visited.add(false);
            totalNodes ++;
        }
    }
    
    // add a vertex
    public void addVertex(String label) {
        nodeList.add(label);
        visited.add(false);
        adjList.add(new LinkedList<Integer>());
        adjWeight.add(new LinkedList<Integer>());
        totalNodes ++;
    }
    public int getNode(String node) {
        for(int i = 0; i < nodeList.size(); i ++) {
            if(nodeList.elementAt(i).equals(node)) return i;
        }
        return -1;
    }

    // return the number of vertices
    public int length() {
        return nodeList.size();
    }

    // add edge from v1 to v2:
    public void setEdge(int v1, int v2, int weight) {
        LinkedList<Integer> tmp = adjList.elementAt(v1);
        if(adjList.elementAt(v1).contains(v2) == false) {
            tmp.add(v2);
            adjList.set(v1,  tmp);
            totalEdges ++;
            LinkedList<Integer> tmp2 = adjWeight.elementAt(v1);
            tmp2.add(weight);
            adjWeight.set(v1,  tmp2);
        }
    }

    public void setEdge(String v1, String v2, int weight) {
        if ((getNode(v1) != -1) && (getNode(v2) != -1)) {
            // add edge from v1 to v2:
            setEdge(getNode(v1), getNode(v2), weight);
            // for undirected graphs, add edge from v2 to v1 as well:
            if (digraph == false) {
                setEdge(getNode(v2), getNode(v1), weight);
            }
        }
    }

    // keep track whether a vertex has been visited or not,
    //    for graph traversal purposes:
    public void setVisited(int v) {
        visited.set(v, true);
        nodeEnum.add(v);
    }

    public boolean ifVisited(int v) {
        return visited.get(v);
    }
    
    // new for Lab 17:
    public LinkedList<Integer> getNeighbors(int v) {
        return adjList.get(v);
    }

    public int getWeight(int v, int u) {
        LinkedList<Integer> tmp = getNeighbors(v);
        LinkedList<Integer> weight = adjWeight.get(v);
        if (tmp.contains(u)) {
            return weight.get(tmp.indexOf(u));
        } else {
            return Integer.MAX_VALUE;
        }
    }
    
    public void clearWalk() {
        nodeEnum.clear();
        for (int i = 0; i < nodeList.size(); i ++)
            visited.set(i, false);
    }

    public void walk(String method) {
        clearWalk();
        // traverse the graph:
        for (int i = 0; i < nodeList.size(); i ++) {
            if (ifVisited(i) == false) {
                if (method.equals("BFS")) BFS(i);      // i is the start node
                else if (method.equals("DFS")) DFS(i); // i is the start node
                else {
                    System.out.println("unrecognized traversal order: " + method);
                    System.exit(0);
                }
            }
        }
        System.out.println(method + ":");
        displayEnum();
    }

    // ----------------
    // Lab 17 solution:
    // ----------------
    // the edge relaxation operation, as seen at Lecture 19, operating on the d[] array:
    private void relax(int u, int v, int w) {
        if ( d[v] > ( d[u] + w ) ) {
            d[v] = d[u] + w;
            // we don't need parent/predecessor nodes for Dijkstra's algorithm,
            //  but if necessary it would be set here:
            // predecessor[v] = u;
        }
    } // end of relax()

    // the minVertex() method, as from textbook page 391 :
    //   find the next closest vertex to the set of visited vertices
    //   (uncomment println() statements for lots of debugging printout)
    private int minVertex() {
        // System.out.println("---- starting minVertex() ----");
        int v = 0;
        // System.out.println("minVertex() choosing first v:");
        // initialize v to any unvisited vertex:
        for (int i = 0; i < totalNodes; i++) {
            // System.out.println("minVertex()     i = " + i);
            if (ifVisited(i) == false) {
                v = i;
                // System.out.println("minVertex()     v = " + v);
                break;
            }
        }
        // System.out.println("minVertex() looking for min d[i] :");
        // now look for the closest unvisited vertex:
        for (int i = 0; i < totalNodes; i++) {
            // System.out.println("minVertex()     i = " + i);
            // System.out.println("minVertex()     d[i] = " + d[i]);

            if ( (ifVisited(i) == false) && (d[i] < d[v]) ) {
                // System.out.println("minVertex()     d[v] = " + d[v]);
                v = i;
                // System.out.println("minVertex()     v changed to = " + i);
            }
        }
        // System.out.println("---- minVertex() chose vertex " + v + " ----");
        return v;
    }
    
    // Dijkstra's shortest path algorithm, implemented using a minHeap
    public void dijkstra2(String sNode) {
    	//the current vertex
    	int v;
    	int s = getNode(sNode);
    	//heap for edges
    	DijkElem[] E = new DijkElem[nodeList.size()];
    	E[0] = new DijkElem(s, 0);
    	MinHeap<DijkElem> H = new MinHeap<DijkElem>(E, 1, nodeList.size());
    	int[] D = new int[totalNodes];
    	for (int i = 0; i < nodeList.size(); i++) {
    		D[i] = Integer.MAX_VALUE;
    	}
    	D[s] = 0;
    	for (int i = 0; i < nodeList.size(); i++) {
    		do { v = (H.removermin()).vertex(); }
    			while (ifVisited(v) == true);
    		setVisited(v);
    		if (D[v] == Integer.MAX_VALUE) return;
    		for (int w : adjList.elementAt(v) ) {
    			if (D[w] > (D[v] + getWeight(v,w))) {
    				D[w] = D[v] + getWeight(v,w);
    				H.insert(new DijkElem(w, D[w]));
    			}
    		}
    	}
    }
    
    // Dijkstra's shortest path algorithm, implemented as from textbook page 391 :
    public void dijkstra1(String sNode) {
        System.out.println ("");
        System.out.println("Dijkstra's shortest path algorithm, starting from node " + sNode);
        d = new int[totalNodes];
        int s = getNode(sNode);
        // initialize the d array:
        for (int i = 0; i < totalNodes; i++) {
            d[i] = Integer.MAX_VALUE;
        }
        d[s] = 0;
        // print out the d array:
        System.out.println("the initial d[] array is : ");
        for (int i = 0; i < totalNodes; i++) { 
        	System.out.print(d[i] + " " );
        }
        System.out.println ("");
        // make sure that all vertices are unmarked:
        clearWalk();
        // starting Dijkstra's main loop:
        for (int i = 0; i < totalNodes; i++) {
            // find the closest vertex to all visited nodes:
            //   Note: the O() of minVertex impacts the O() of the entire Dijkstra's algorithm:
            int v = minVertex();
            setVisited(v);
            if (d[v] == Integer.MAX_VALUE) {
                System.out.println("unreachable vertex found in Dijkstra's algorithm!");
                return;
            }
            // now *relax* all neighbors of the node v:
            for (int neighbor : adjList.elementAt(v) ) {
                relax(v, neighbor, getWeight(v, neighbor));
            }
            System.out.println("after step " + i + " the d[] array is : ");
            for (int j = 0; j < totalNodes; j++) {  System.out.print(d[j] + " " ); } System.out.println ("");
        }
    }
    
    public void DFS(int v) {
        setVisited(v);
        LinkedList<Integer> neighbors = adjList.elementAt(v);
        for (int i = 0; i < neighbors.size(); i ++) {
            int v1 = neighbors.get(i);
            if (ifVisited(v1) == false) DFS(v1);
        }
    }
    
    public void BFS(int s) {
        ArrayList<Integer> toVisit = new ArrayList<Integer>();
        toVisit.add(s);
        while(toVisit.size() > 0) {
            int v = toVisit.remove(0); // first-in, first-visit
            setVisited(v);
            LinkedList<Integer> neighbors = adjList.elementAt(v);
            for (int i = 0; i < neighbors.size(); i ++) {
                int v1 = neighbors.get(i);
                if ((ifVisited(v1) == false) && (toVisit.contains(v1) == false)) {
                    toVisit.add(v1);
                }
            }
        }
    }
    
    public void display() {
        System.out.println("total nodes: " + totalNodes);
        System.out.println("total edges: " + totalEdges);
    }
    
    public void displayEnum() {
        for(int i = 0; i < nodeEnum.size(); i ++) {
            System.out.print(nodeList.elementAt(nodeEnum.elementAt(i)) + " ");
        }
        System.out.println();
    }
    
    class DijkElem implements Comparable<DijkElem> {
    	private int vertex;
    	private int weight;
    	public DijkElem(int inv, int inw) {
    		vertex = inv;
    		weight = inw;
    	}
    	public DijkElem() {vertex = 0; weight = 0; }
    	public int key() { return weight; }
    	public int vertex() { return vertex; }
    	public int compareTo(DijkElem that) {
    		if (weight < that.key()) return -1;
    		else if (weight == that.key()) return 0;
    		else return 1;
    	}
    }

    public static void main(String argv[]) {
    	//TESTING Dijkstra2
    	
    	//example 1
        AdjGraph test = new AdjGraph(true);
        String[] nodes1 = {"A", "B", "C", "D", "E"};
        test.setVertices(nodes1);
        test.setEdge("A", "B", 10);
        test.setEdge("A", "C", 3);
        test.setEdge("A", "D", 20);
        test.setEdge("B", "D", 5);
        test.setEdge("C", "B", 2);
        test.setEdge("C", "E", 15);
        test.setEdge("D", "E", 11);
        test.dijkstra2("A");

        //example 2
        AdjGraph test2 = new AdjGraph(false);
        String[] nodes2 = {"a", "b", "c", "d", "e", "f"};
        test2.setVertices(nodes2);
        test2.setEdge("a", "b", 7);
        test2.setEdge("a", "c", 9);
        test2.setEdge("a", "f", 14);
        test2.setEdge("b", "c", 10);
        test2.setEdge("b", "d", 15);
        test2.setEdge("c", "d", 11);
        test2.setEdge("c", "f", 2);
        test2.setEdge("d", "e", 6);
        test2.setEdge("e", "f", 9);
        test2.dijkstra2("a");

        //example 3
        AdjGraph test3 = new AdjGraph(true);
        String[] nodes3 = {"A", "B", "C", "D", "E"};
        test3.setVertices(nodes3);
        test3.setEdge("A", "B", 10);
        test3.setEdge("A", "C", 3);
        test3.setEdge("B", "C", 1);
        test3.setEdge("B", "D", 2);
        test3.setEdge("C", "B", 4);
        test3.setEdge("C", "D", 9);
        test3.setEdge("C", "E", 2);
        test3.setEdge("D", "E", 7);
        test3.setEdge("E", "D", 9);
        test3.dijkstra1("A");
    }
}